package devops.medical.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import devops.medical.model.BookedSlot;
import devops.medical.model.Doctor;
import devops.medical.dao.DoctorDao;
import devops.medical.model.DoctorLogin;

public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	public DoctorDao DoctorDao;
	
	public int register(Doctor doctor) {
		return DoctorDao.register(doctor);
	}
	
	public Doctor validateDoctor(Doctor doctor) {
		return DoctorDao.validateDoctor(doctor);
	}
	
	public Doctor check(DoctorLogin doctorlogin) {
		return DoctorDao.check(doctorlogin);
	}
	
	public ArrayList<BookedSlot> getAllDoctorSlot(String id) {
		return DoctorDao.getAllDoctorSlot(id);
	}
	
}
