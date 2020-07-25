package devops.medical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import devops.medical.model.Admin;
import devops.medical.model.AdminLogin;
import devops.medical.model.Doctor;
import devops.medical.model.Lab;
import devops.medical.model.Patient;

public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	DataSource datasource;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Admin check(AdminLogin adminlogin) {
		String sql = "select * from Admin where id ='"+adminlogin.getId()+"' and password='"+adminlogin.getPassword()+"'";
		List<Admin> admins = jdbcTemplate.query(sql, new AdminMapper());
		return admins.size()>0?admins.get(0):null;
	}
	
	public List<Doctor> getAllDoctors(){
		String sql = "select * from Doctor";
		List<Doctor> doctors = jdbcTemplate.query(sql, new DoctorMapper());
		return doctors;
	}
	
	public Doctor validateDoctor(Doctor doctor) {
		String sql = "select * from Doctor where id = '"+doctor.getId()+"'";
		List<Doctor> doctors = jdbcTemplate.query(sql, new DoctorMapper());
		
		return doctors.size()>0?doctors.get(0):null;
	}
	
	public int registerDoctor(Doctor doctor) {
		String sql = "insert into Doctor values (?,?,?,?)";
		
		return jdbcTemplate.update(sql, new Object[] {doctor.getId(), doctor.getUsername(), doctor.getType(), doctor.getPassword()});
	}
	
	public Doctor getDoctor(String id) {
		String sql ="select * from Doctor where id= '"+id+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Doctor>() {
			
			public Doctor extractData(ResultSet rs) throws SQLException, DataAccessException {
				Doctor doctor = new Doctor();
				if(rs.next()) {
					
					doctor.setId(rs.getString("id"));
					doctor.setUsername(rs.getString("name"));
					doctor.setType(rs.getString("type"));
					doctor.setPassword(rs.getString("password"));
				}
				
					
					return doctor;
					
				}
			});
	}
	
	
	public int updateDoctor(Doctor doctor) {
		String sql = "update Doctor set name=?, type=?, password=? where id = '"+doctor.getId()+"'";
		return jdbcTemplate.update(sql, new Object[] {doctor.getUsername(), doctor.getType(), doctor.getPassword()});
		
	}
	
	public int deleteDoctor(String id) {
		String sql = "delete from Doctor where id = '"+id+"'";
		return jdbcTemplate.update(sql);
	}
	
	
	
	public List<Lab> getAllLabs(){
		String sql = "select * from Lab";
		List<Lab> labs = jdbcTemplate.query(sql, new LabMapper());
		return labs;
	}
	
	public Lab validateLab(Lab lab) {
		String sql = "select * from Lab where id = '"+lab.getId()+"'";
		List<Lab> labs = jdbcTemplate.query(sql, new LabMapper());
		
		return labs.size()>0?labs.get(0):null;
	}
	
	public int registerLab(Lab lab) {
		String sql = "insert into Lab values (?,?,?)";
		
		return jdbcTemplate.update(sql, new Object[] {lab.getId(), lab.getName(), lab.getType()});
	}
	
	
	
	public Lab getLab(String id) {
		String sql ="select * from Lab where id= '"+id+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Lab>() {
			
			public Lab extractData(ResultSet rs) throws SQLException, DataAccessException {
				Lab lab = new Lab();
				if(rs.next()) {
					
					lab.setId(rs.getString("id"));
					lab.setName(rs.getString("name"));
					lab.setType(rs.getString("type"));
				
				}
				
					
					return lab;
					
				}
			});
	}
	
	
	public int updateLab(Lab lab) {
		String sql = "update Lab set name=?, type=? where id = '"+lab.getId()+"'";
		return jdbcTemplate.update(sql, new Object[] {lab.getName(), lab.getType()});
		
	}
	
	
	public int deleteLab(String id) {
		String sql = "delete from Lab where id = '"+id+"'";
		return jdbcTemplate.update(sql);
	}
		
		
	
	
	
	
	
}
class AdminMapper implements RowMapper<Admin>{
	public Admin mapRow(ResultSet rs, int arg1) throws SQLException{
		Admin admin = new Admin();
		admin.setId(rs.getString("id"));
		admin.setUsername(rs.getString("name"));
		admin.setPassword(rs.getString("password"));
		
		return admin;
	}
}

class DoctorMapper implements RowMapper<Doctor>{
	public Doctor mapRow(ResultSet rs, int arg1) throws SQLException {
		Doctor doctor = new Doctor();
		doctor.setId(rs.getString("id"));
		doctor.setUsername(rs.getString("name"));
		doctor.setType(rs.getString("type"));
		doctor.setPassword(rs.getString("password"));
		
		return doctor;
	}
}


class DoctorExtractor implements ResultSetExtractor<Doctor>{
	public Doctor extractData(ResultSet rs) throws SQLException {
		Doctor doctor = new Doctor();
		doctor.setId(rs.getString("id"));
		doctor.setUsername(rs.getString("name"));
		doctor.setType(rs.getString("type"));
		doctor.setPassword(rs.getString("password"));
		
		return doctor;
	}
}

class LabMapper implements RowMapper<Lab>{
	public Lab mapRow(ResultSet rs, int arg1) throws SQLException {
		Lab lab = new Lab();
		lab.setId(rs.getString("id"));
		lab.setName(rs.getString("name"));
		lab.setType(rs.getString("type"));
	
		
		return lab;
	}
}

	
	


