package devops.medical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import devops.medical.model.BookedSlot;
import devops.medical.model.Doctor;
import devops.medical.model.DoctorLogin;
import devops.medical.model.DoctorSlots;

public class DoctorDaoImpl implements DoctorDao {
	
	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int register(Doctor doctor) {
		String sql = "insert into Doctor values (?,?,?,?)";
		
		return jdbcTemplate.update(sql, new Object[] {doctor.getId(), doctor.getUsername(), doctor.getType(), doctor.getPassword()});
	}
	
	public Doctor validateDoctor(Doctor doctor) {
		String sql = "select * from Doctor where id = '"+doctor.getId()+"'";
		List<Doctor> doctors = jdbcTemplate.query(sql, new DoctorMapper());
		
		return doctors.size()>0?doctors.get(0):null;
	}
	
	public Doctor check(DoctorLogin doctorlogin) {
		String sql = "select * from Doctor where id ='"+doctorlogin.getId()+"' and password='"+doctorlogin.getPassword()+"'";
		List<Doctor> doctors = jdbcTemplate.query(sql, new DoctorMapper());
		return doctors.size()>0?doctors.get(0):null;
	}
	
	public ArrayList<BookedSlot> getAllDoctorSlot(String id){
		String sql = "select * from DoctorSlots where id = '"+id+"'";
		System.out.println("reached here with id");
		System.out.println(id);
		List<DoctorSlots> patients = jdbcTemplate.query(sql, new DoctorSlotsMapper());
		ArrayList<BookedSlot> bookings = new ArrayList<BookedSlot>();
		for(DoctorSlots patient: patients) {
			BookedSlot temp = new BookedSlot();
			temp.setId(id);
			temp.setPatientid(patient.getPatient_id());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String time = patient.getTiming().format(formatter);
			temp.setBookedslot(time);
			bookings.add(temp);
		}
		return bookings;
	}
}