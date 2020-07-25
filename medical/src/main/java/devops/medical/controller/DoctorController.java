package devops.medical.controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import devops.medical.controller.PatientController.Servlets;
import devops.medical.model.BookedSlot;
import devops.medical.model.Doctor;
import devops.medical.model.DoctorLogin;
import devops.medical.service.DoctorService;

@Controller
public class DoctorController {
	
	@Autowired
	public DoctorService DoctorService;
	
	@RequestMapping(value="/doctorlogin", method=RequestMethod.GET)
	public ModelAndView doctorlogin(HttpServletRequest request, HttpServletResponse response, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		if(typeCookie.equals("doctor")) {
			return new ModelAndView("redirect:/doctor/"+userCookie);
		}
		if(typeCookie.equals("patient")) {
			return new ModelAndView("redirect:/patient/"+userCookie);
		}
		ModelAndView mav = new ModelAndView("DoctorLogin");
		mav.addObject(new Doctor());
		return mav;
	}

	@RequestMapping(value="/doctor/{id}", method=RequestMethod.GET)
	public ModelAndView doctorcatalog(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") String id, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		if(typeCookie.equals("unknown")) {
			return new ModelAndView("redirect:/");
		}
		else if(typeCookie.equals("patient")) {
			return new ModelAndView("redirect:/patient/"+userCookie);
		}
		else if(typeCookie.equals("doctor")) {
			if(userCookie == "unknown" || (!userCookie.equals(id))) {
				System.out.println("****"+id);
				return new ModelAndView("redirect:/");
			}
			ModelAndView mav = new ModelAndView("DoctorCatalog");
			ArrayList<BookedSlot> bookedslots = DoctorService.getAllDoctorSlot(id);
			mav.addObject("bookedslots", bookedslots);
			return mav;
		}else {
			return new ModelAndView("redirect:/");
		}
	}

	// for erasing cookies
		public final static class Servlets {

		    private Servlets() {}

		    public static Cookie getCookie(HttpServletRequest request, String name) {
		        if (request.getCookies() != null) {
		            for (Cookie cookie : request.getCookies()) {
		                if (cookie.getName().equals(name)) {
		                    return cookie;
		                }
		            }
		        }
		        return null;
		    }
		}
		@RequestMapping(value="/doctor/logout", method=RequestMethod.GET)
		public ModelAndView ModelAndView(HttpServletRequest request, HttpServletResponse response) {
			Cookie cookie = Servlets.getCookie(request, "username");
			if (cookie != null) {
				cookie.setPath("/medical");
				cookie.setMaxAge(0);
			    response.addCookie(cookie);
			}
			cookie = Servlets.getCookie(request, "type");
			if (cookie != null) {
				cookie.setPath("/medical");
				cookie.setMaxAge(0);
			    response.addCookie(cookie);
			}
			return new ModelAndView("redirect:/");
		}
	
	@RequestMapping(value="/doctorloginprocess", method=RequestMethod.POST)
	public ModelAndView doctorloginprocess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("doctor") DoctorLogin doctorlogin, RedirectAttributes redirectAttrs, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		if(typeCookie.equals("patient"))return new ModelAndView("redirect:/patient/"+userCookie);
		else if(typeCookie.equals("doctor"))return new ModelAndView("redirect:/doctor/"+userCookie);
		Doctor doctor = DoctorService.check(doctorlogin);
		if(doctor==null) {
			redirectAttrs.addFlashAttribute("message", "ID or password is incorrect");
			return new ModelAndView("redirect:/doctorlogin");
		}else {
			String id = doctorlogin.getId();
			Cookie cookie = new Cookie("username", id);
			cookie.setPath("/medical");
			response.addCookie(cookie);
			cookie = new Cookie("type","doctor");
			cookie.setPath("medical");
			response.addCookie(cookie);
			return new ModelAndView("redirect:/doctor/"+id);
		}
	}
	
}
