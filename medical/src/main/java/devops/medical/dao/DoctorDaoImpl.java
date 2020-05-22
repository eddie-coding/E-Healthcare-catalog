package devops.medical.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import devops.medical.model.Doctor;
import devops.medical.model.DoctorLogin;

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
	
}

/*class DoctorMapper implements RowMapper<Doctor>{
	public Doctor mapRow(ResultSet rs, int arg1) throws SQLException{
		Doctor doctor = new Doctor();
		doctor.setId(rs.getString("id"));
		doctor.setUsername(rs.getString("name"));
		doctor.setType(rs.getString("type"));
		doctor.setPassword(rs.getString("password"));
		
		return doctor;
	}
}*/