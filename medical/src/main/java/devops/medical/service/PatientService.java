package devops.medical.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import devops.medical.model.BookedLabSlot;
import devops.medical.model.BookedSlot;
import devops.medical.model.Doctor;
import devops.medical.model.DoctorSlots;
import devops.medical.model.Lab;
import devops.medical.model.Patient;
import devops.medical.model.PatientLogin;

public interface PatientService {
	
	int register(Patient patient);
	
	Patient validatePatient(Patient Patient);
	
	Patient check(PatientLogin patientlogin);
	
	List<DoctorSlots> getAllPatient(String patient_id);
	
	List<Doctor> getAllDoctors();
	
	List<Lab> getAllLabs();
	
	HashSet<LocalDateTime> getAllSlots(String id);
	
	ArrayList<String> validDates(String id);
	
	int updateSlot(BookedSlot bookedslot);
	
	int updateLabSlot(BookedLabSlot bookedlabslot);
	
	ArrayList<BookedSlot> getAllBookedSlot(String patientid);
	
	ArrayList<BookedLabSlot> getAllBookedLabSlot(String patientid);
}
