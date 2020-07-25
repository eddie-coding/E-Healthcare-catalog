package devops.medical.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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

import devops.medical.model.Patient;
import devops.medical.service.PatientService;
import devops.medical.model.PatientLogin;
import devops.medical.model.BookedLabSlot;
import devops.medical.model.BookedSlot;
import devops.medical.model.Doctor;
import devops.medical.model.DoctorSlots;
import devops.medical.model.Lab;

@Controller
public class PatientController {
	
	@Autowired
	public PatientService patientservice;
	
	@RequestMapping(value="/patientloginandsignup", method=RequestMethod.GET)
	public ModelAndView patientLoginandSignup(HttpServletRequest request, HttpServletResponse response, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		if(typeCookie.equals("doctor")) {
			return new ModelAndView("redirect:/doctor/"+userCookie);
		}
		if(typeCookie.equals("patient")) {
			return new ModelAndView("redirect:/patient/"+userCookie);
		}
		ModelAndView mav = new ModelAndView("PatientLoginAndSignup");
		return mav;
	}
	
	@RequestMapping(value="/patientregister", method=RequestMethod.GET)
	public ModelAndView patientregister(HttpServletRequest request, HttpServletResponse response, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		if(typeCookie.equals("doctor")) {
			return new ModelAndView("redirect:/doctor/"+userCookie);
		}
		if(typeCookie.equals("patient")) {
			return new ModelAndView("redirect:/patient/"+userCookie);
		}
		ModelAndView mav = new ModelAndView("PatientRegister");
		mav.addObject("patient", new Patient());
		return mav;
	}
	
	@RequestMapping(value="/patientlogin", method=RequestMethod.GET)
	public ModelAndView patientlogin(HttpServletRequest request, HttpServletResponse response, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		System.out.println(typeCookie+"  ** "+userCookie);
		if(typeCookie.equals("doctor")) {
			return new ModelAndView("redirect:/doctor/"+userCookie);
		}
		if(typeCookie.equals("patient")) {
			return new ModelAndView("redirect:/patient/"+userCookie);
		}
		ModelAndView mav = new ModelAndView("PatientLogin");
		mav.addObject("patient", new PatientLogin());
		return mav;
	}

	@RequestMapping(value="/patient/{id}", method=RequestMethod.GET)
	public ModelAndView patient(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") String id, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		System.out.println(userCookie);
		if(typeCookie.equals("unknown")) {
			return new ModelAndView("redirect:/");
		}
		else if(typeCookie.equals("doctor")) {
			return new ModelAndView("redirect:/doctor/"+userCookie);
		}
		else if(typeCookie.equals("patient")) {
			if(userCookie.equals("unknown") || (!userCookie.equals(id))) {
				System.out.println("****"+id);
				return new ModelAndView("redirect:/");
			} 	 	
			ModelAndView mav = new ModelAndView("Patient");
			mav.addObject("id", id);
			return mav;
		}else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value="/patient/{id}/bookdoctor", method=RequestMethod.GET)
	public ModelAndView bookdoctor(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") String id, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		if(typeCookie.equals("unknown")) {
			return new ModelAndView("redirect:/");
		}
		else if(typeCookie.equals("doctor")) {
			return new ModelAndView("redirect:/doctor/"+userCookie);
		}
		else if(typeCookie.equals("patient")){
			if(userCookie.equals("unknown") || (!userCookie.equals(id))) {
				return new ModelAndView("redirect:/");
			}
			ModelAndView mav = new ModelAndView("PatientCatalog");
			List<Doctor> doctors = patientservice.getAllDoctors();
			ArrayList<ArrayList<String> > available = new ArrayList<ArrayList<String>>();
			for(Doctor doctor: doctors) {
				ArrayList<String> slots = patientservice.validDates(doctor.getId());
				available.add(slots);
			}
			mav.addObject("id", id);
			mav.addObject("doctors", doctors);
			mav.addObject("slots", available);
			mav.addObject("bookedslots", new BookedSlot());
			return mav;
		}else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value="/patient/{id}/booklab", method=RequestMethod.GET)
	public ModelAndView booklab(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") String id, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		if(typeCookie.equals("unknown")) {
			return new ModelAndView("redirect:/");
		}
		else if(typeCookie.equals("doctor")) {
			return new ModelAndView("redirect:/doctor/"+userCookie);
		}
		else if(typeCookie.equals("patient")){
			if(userCookie.equals("unknown") || (!userCookie.equals(id))) {
				return new ModelAndView("redirect:/");
			}
			ModelAndView mav = new ModelAndView("PatientLabCatalog");
			List<Lab> labs = patientservice.getAllLabs();
			ArrayList<ArrayList<String> > available = new ArrayList<ArrayList<String>>();
			for(Lab lab: labs) {
				ArrayList<String> slots = patientservice.validDates(lab.getId());
				available.add(slots);
			}
			mav.addObject("id", id);
			mav.addObject("labs", labs);
			mav.addObject("slots", available);
			mav.addObject("bookedlabslots", new BookedLabSlot());
			return mav;
		}else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value="/patient/{id}/bookings", method=RequestMethod.GET)
	public ModelAndView bookings(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") String id, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		if(typeCookie.equals("unknown")) {
			return new ModelAndView("redirect:/");
		}
		if(typeCookie.equals("doctor")) {
			return new ModelAndView("redirect:/doctor/"+userCookie);
		}
		else if(typeCookie.equals("patient")){
			if(userCookie == "unknown" || (!userCookie.equals(id))) {
				return new ModelAndView("redirect:/");
			}
			ModelAndView mav = new ModelAndView("Bookings");
			ArrayList<BookedSlot> bookedslots = patientservice.getAllBookedSlot(id);
			mav.addObject("bookedslots", bookedslots);
			ArrayList<BookedLabSlot> bookedlabslots = patientservice.getAllBookedLabSlot(id);
			mav.addObject("bookedlabslots", bookedlabslots);
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
	@RequestMapping(value="/patient/logout", method=RequestMethod.GET)
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
	
	@RequestMapping(value="/patientloginprocess", method=RequestMethod.POST)
	public ModelAndView patientloginprocess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("patient") PatientLogin patientlogin,RedirectAttributes redirectAttrs, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		if(typeCookie.equals("patient"))return new ModelAndView("redirect:/patient/"+userCookie);
		else if(typeCookie.equals("doctor"))return new ModelAndView("redirect:/doctor/"+userCookie);
		Patient user = patientservice.check(patientlogin);
		System.out.println("yes");
		if(user!=null) {
			String id = user.getId();
			Cookie cookie = new Cookie("username", id);
			response.addCookie(cookie);
			cookie = new Cookie("type","patient");
			response.addCookie(cookie);
			return new ModelAndView("redirect:/patient/"+id);
		}else {
			redirectAttrs.addFlashAttribute("message", "Id or Password is incorrect");
			return new ModelAndView("redirect:/patientlogin");
		}
	}
	
	@RequestMapping(value="/patient/{patientid}/doctorbookingprocess", method=RequestMethod.POST)
	public ModelAndView doctorbookingprocess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("bookedslots") BookedSlot bookedslot, RedirectAttributes redirectAttrs, @PathVariable(value="patientid") String patientid, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) throws ParseException {
		if(typeCookie.equals("unknown")) {
			return new ModelAndView("redirect:/");
		}
		else if(typeCookie.equals("doctor")) {
			return new ModelAndView("redirect:/doctor/"+userCookie);
		}
		else if(typeCookie.equals("patient")){
			if(userCookie.equals("unknown") || (!userCookie.equals(patientid))) {
				System.out.println("****"+patientid);
				return new ModelAndView("redirect:/patientloginandsignup");
			}
			System.out.println(bookedslot.getBookedslot()+" ****** ");//bookedslot.getId());
			String id = bookedslot.getPatientid();
			patientservice.updateSlot(bookedslot);
			return new ModelAndView("redirect:/patient/"+id);
		}else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value="/patient/{patientid}/labbookingprocess", method=RequestMethod.POST)
	public ModelAndView labbookingprocess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("bookedlabslots") BookedLabSlot bookedlabslot, RedirectAttributes redirectAttrs, @PathVariable(value="patientid") String patientid, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) throws ParseException {
		if(typeCookie.equals("unknown")) {
			return new ModelAndView("redirect:/");
		}
		else if(typeCookie.equals("doctor")) {
			return new ModelAndView("redirect:/doctor/"+userCookie);
		}
		else if(typeCookie.equals("patient")){
			if(userCookie.equals("unknown") || (!userCookie.equals(patientid))) {
				System.out.println("****"+patientid);
				return new ModelAndView("redirect:/patientloginandsignup");
			}
			System.out.println(bookedlabslot.getBookedlabslot()+" ****** ");//bookedslot.getId());
			String id = bookedlabslot.getPatientid();
			patientservice.updateLabSlot(bookedlabslot);
			return new ModelAndView("redirect:/patient/"+id);
		}else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value="/patientregisterprocess", method=RequestMethod.POST)
	public ModelAndView patientregisterprocess(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("patient") Patient patient, RedirectAttributes redirectAttrs, @CookieValue(value="username", defaultValue="unknown") String userCookie, @CookieValue(value="type", defaultValue="unknown") String typeCookie) {
		if(typeCookie.equals("patient"))return new ModelAndView("redirect:/patient/"+userCookie);
		else if(typeCookie.equals("doctor"))return new ModelAndView("redirect:/doctor/"+userCookie);
		Patient user = patientservice.validatePatient(patient);
		if(user != null) {
			redirectAttrs.addFlashAttribute("message", "id already exists");
			return new ModelAndView("redirect:/patientregister");
		}else {
			patientservice.register(patient);
			String id = patient.getId();
			Cookie cookie = new Cookie("username", id);
			cookie.setPath("/medical");
			response.addCookie(cookie);
			cookie = new Cookie("type","patient");
			cookie.setPath("/medical");
			response.addCookie(cookie);
			return new ModelAndView("redirect:/patient/"+id);
		}
	}
	
}
