package devops.medical.dao;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import devops.medical.model.BookedLabSlot;
import devops.medical.model.BookedSlot;
import devops.medical.model.Doctor;
import devops.medical.model.DoctorSlots;
import devops.medical.model.Lab;
import devops.medical.model.Patient;
import devops.medical.model.PatientLogin;

public interface PatientDao {
	int register(Patient patient);
	
	Patient validatePatient(Patient patient);
	
	Patient check(PatientLogin patientlogin);
	
	//list of slots of meeting with doctors for a patient with patient_id
	List<DoctorSlots> getAllPatient(String patient_id);
	
	List<Doctor> getAllDoctors();

	List<Lab> getAllLabs();
	//all available slots for a specific doctor id
	HashSet<LocalDateTime> getAllSlots(String id);
	
	int updateSlot(BookedSlot bookedslot);
	
	int updateLabSlot(BookedLabSlot bookedlabslot);
	
	//all booked slots by a patient
	ArrayList<BookedSlot> getAllBookedSlot(String patientid);
	
	ArrayList<BookedLabSlot> getAllBookedLabSlot(String patientid);
}
