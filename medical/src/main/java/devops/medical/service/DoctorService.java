package devops.medical.service;

import java.util.ArrayList;

import devops.medical.model.BookedSlot;
import devops.medical.model.Doctor;
import devops.medical.model.DoctorLogin;

public interface DoctorService {
	
	int register(Doctor doctor);
	Doctor validateDoctor(Doctor doctor);
	Doctor check(DoctorLogin doctorlogin);
	ArrayList<BookedSlot> getAllDoctorSlot(String id);
}
