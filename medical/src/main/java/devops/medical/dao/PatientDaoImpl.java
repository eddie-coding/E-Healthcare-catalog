package devops.medical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import devops.medical.model.BookedLabSlot;
import devops.medical.model.BookedSlot;
import devops.medical.model.Doctor;
import devops.medical.model.DoctorSlots;
import devops.medical.model.Lab;
import devops.medical.model.LabSlots;
import devops.medical.model.Patient;
import devops.medical.model.PatientLogin;


public class PatientDaoImpl implements PatientDao {
	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int register(Patient patient) {
		String sql = "insert into Patient values (?,?,?)";
		
		return jdbcTemplate.update(sql, new Object[] {patient.getId(), patient.getUsername(), patient.getPassword()});
	}
	
	public Patient validatePatient(Patient patient) {
		String sql = "select * from Patient where id='"+patient.getId()+"'";
		List<Patient> patients = jdbcTemplate.query(sql, new PatientMapper());
		
		return patients.size() > 0 ? patients.get(0) : null;
	}
	
	public Patient check(PatientLogin patientlogin) {
		String sql = "select * from Patient where id='"+patientlogin.getId()+"' and password='"+patientlogin.getPassword()+"'";
		List<Patient> patients = jdbcTemplate.query(sql, new PatientMapper());
		return patients.size()>0?patients.get(0):null;
	}
	
	public List<DoctorSlots> getAllPatient(String patient_id){
		String sql = "select * from DoctorSlots where patient_id='"+patient_id+"'";
		List<DoctorSlots> meetings = jdbcTemplate.query(sql, new DoctorSlotsMapper());
		
		return meetings;
	}
	
	public List<Doctor> getAllDoctors(){
		String sql = "select * from Doctor";
		List<Doctor> doctors = jdbcTemplate.query(sql, new DoctorMapper());
		return doctors;
	}
	
	public List<Lab> getAllLabs(){
		String sql = "select * from Lab";
		List<Lab> labs = jdbcTemplate.query(sql, new LabMapper());
		System.out.println(labs.size());
		return labs;
	}
	
	public HashSet<LocalDateTime> getAllSlots(String id){
		String sql = "select * from DoctorSlots where id='"+id+"'";
		List<DoctorSlots> allMeetings = jdbcTemplate.query(sql, new DoctorSlotsMapper());
		HashSet<LocalDateTime> allSlots = new HashSet<LocalDateTime>();
		for(DoctorSlots d : allMeetings) {
			allSlots.add(d.getTiming());
		}
		return allSlots;
	}
	
	public int updateSlot(BookedSlot bookedslot) {
		String sql = "insert into DoctorSlots values (?,?,?)";
		java.sql.Timestamp timing = java.sql.Timestamp.valueOf(bookedslot.getBookedslot());
		return jdbcTemplate.update(sql, new Object[] {bookedslot.getId(), timing, bookedslot.getPatientid()});
	}
	
	public int updateLabSlot(BookedLabSlot bookedlabslot) {
		String sql = "insert into LabSlots values (?,?,?)";
		java.sql.Timestamp timing = java.sql.Timestamp.valueOf(bookedlabslot.getBookedlabslot());
		return jdbcTemplate.update(sql, new Object[] {bookedlabslot.getId(), timing, bookedlabslot.getPatientid()});
	}
	
	public ArrayList<BookedSlot> getAllBookedSlot(String patientid){
		String sql = "select * from DoctorSlots where patient_id = '"+patientid+"'";
		List<DoctorSlots> patients = jdbcTemplate.query(sql, new DoctorSlotsMapper());
		ArrayList<BookedSlot> bookings = new ArrayList<BookedSlot>();
		for(DoctorSlots patient: patients) {
			BookedSlot temp = new BookedSlot();
			temp.setId(patient.getId());
			temp.setPatientid(patient.getPatient_id());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String time = patient.getTiming().format(formatter);
			temp.setBookedslot(time);
			bookings.add(temp);
		}
		return bookings;
	}
	
	public ArrayList<BookedLabSlot> getAllBookedLabSlot(String patientid){
		String sql = "select * from LabSlots where patient_id = '"+patientid+"'";
		List<LabSlots> patients = jdbcTemplate.query(sql, new LabSlotsMapper());
		ArrayList<BookedLabSlot> labbookings = new ArrayList<BookedLabSlot>();
		for(LabSlots patient: patients) {
			BookedLabSlot temp = new BookedLabSlot();
			temp.setId(patient.getId());
			temp.setPatientid(patient.getPatient_id());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String time = patient.getTiming().format(formatter);
			temp.setBookedlabslot(time);
			labbookings.add(temp);
		}
		return labbookings;
	}
	
}

class PatientMapper implements RowMapper<Patient>{
	public Patient mapRow(ResultSet rs, int arg1) throws SQLException {
		Patient patient = new Patient();
		patient.setId(rs.getString("id"));
		patient.setUsername(rs.getString("name"));
		patient.setPassword(rs.getString("password"));
		
		return patient;
	}
}


class DoctorSlotsMapper implements RowMapper<DoctorSlots>{
	public DoctorSlots mapRow(ResultSet rs, int arg1) throws SQLException {
		DoctorSlots meeting = new DoctorSlots();
		meeting.setId(rs.getString("id"));
		meeting.setPatient_id(rs.getString("patient_id"));
		Date timing1 = new Date(rs.getTimestamp("timing").getTime());
		LocalDateTime timing = LocalDateTime.ofInstant(timing1.toInstant(),ZoneId.systemDefault());
		meeting.setTiming(timing);
		
		return meeting;
	}
}

class LabSlotsMapper implements RowMapper<LabSlots>{
	public LabSlots mapRow(ResultSet rs, int arg1) throws SQLException {
		LabSlots meeting = new LabSlots();
		meeting.setId(rs.getString("id"));
		meeting.setPatient_id(rs.getString("patient_id"));
		Date timing1 = new Date(rs.getTimestamp("timing").getTime());
		LocalDateTime timing = LocalDateTime.ofInstant(timing1.toInstant(),ZoneId.systemDefault());
		meeting.setTiming(timing);
		
		return meeting;
	}
}