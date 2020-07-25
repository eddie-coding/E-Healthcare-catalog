package devops.medical.model;

import java.time.LocalDateTime;
import java.util.Date;

public class LabSlots {
	private String id;
	private LocalDateTime timing;
	private String patient_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getTiming() {
		return timing;
	}
	public void setTiming(LocalDateTime timing) {
		this.timing = timing;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
}
