package devops.medical.model;

public class BookedLabSlot {
	private String bookedlabslot;
	private String id;
	private String patientid;
	
	public String getBookedlabslot() {
		return bookedlabslot;
	}
	public void setBookedlabslot(String bookedlabslot) {
		this.bookedlabslot = bookedlabslot;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
}
