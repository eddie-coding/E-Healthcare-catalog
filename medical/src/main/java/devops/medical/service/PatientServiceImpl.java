package devops.medical.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import devops.medical.model.BookedLabSlot;
import devops.medical.model.BookedSlot;
import devops.medical.model.Doctor;
import devops.medical.model.DoctorSlots;
import devops.medical.model.Lab;
import devops.medical.model.Patient;
import devops.medical.dao.PatientDao;
import devops.medical.model.PatientLogin;

public class PatientServiceImpl implements PatientService {
	
	@Autowired
	public PatientDao PatientDao;
	
	public int register(Patient patient) {
		return PatientDao.register(patient);
	}
	
	public Patient validatePatient(Patient patient) {
		return PatientDao.validatePatient(patient);
	}
	
	public Patient check(PatientLogin patientlogin) {
		return PatientDao.check(patientlogin);
	}
	
	public List<DoctorSlots> getAllPatient(String patient_id){
		return PatientDao.getAllPatient(patient_id);
	}
	
	public List<Doctor> getAllDoctors(){
		return PatientDao.getAllDoctors();
	}
	
	public List<Lab> getAllLabs(){
		return PatientDao.getAllLabs();
	}
	
	//Slots of a specific doctor
	public HashSet<LocalDateTime> getAllSlots(String id){
		return PatientDao.getAllSlots(id);
	}
	
	public ArrayList<String> validDates(String id){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curr = sdf.format(cal.getTime());
		LocalDateTime ldt = LocalDateTime.parse(curr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		HashSet<LocalDateTime> allDate = getAllSlots(id);
		ArrayList<String> li = new ArrayList<String>();
		for(int i=0;i<7;i++) {
				LocalDateTime temp = ldt.plusDays(i);
				temp = temp.withHour(12).withMinute(0).withSecond(0);
				if(ldt.isBefore(temp)) {
					if(!(allDate.contains(temp))) {li.add(temp.format(formatter));}
				}
				temp = ldt.plusDays(i);
				temp = temp.withHour(14).withMinute(0).withSecond(0);
				if(ldt.isBefore(temp)) {
					if(!(allDate.contains(temp))) {li.add(temp.format(formatter));}
				}
				temp = ldt.plusDays(i);
				temp = temp.withHour(16).withMinute(0).withSecond(0);
				if(ldt.isBefore(temp)) {
					if(!(allDate.contains(temp))) {li.add(temp.format(formatter));}
				}
				temp = ldt.plusDays(i);
				temp = temp.withHour(18).withMinute(0).withSecond(0);
				if(ldt.isBefore(temp)) {
					if(!(allDate.contains(temp))) {li.add(temp.format(formatter));}
				}
		}
		return li;
	}
	
	public int updateSlot(BookedSlot bookedslot) {
		return PatientDao.updateSlot(bookedslot);
	}
	
	public int updateLabSlot(BookedLabSlot bookedlabslot) {
		return PatientDao.updateLabSlot(bookedlabslot);
	}
	
	public ArrayList<BookedSlot> getAllBookedSlot(String patientid){
		return PatientDao.getAllBookedSlot(patientid);
	}
	
	public ArrayList<BookedLabSlot> getAllBookedLabSlot(String patientid){
		return PatientDao.getAllBookedLabSlot(patientid);
	}
}
