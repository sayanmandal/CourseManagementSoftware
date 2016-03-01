package CourseRegulatorNew;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable{
	private String CreateDate;
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	private String ExpireDate;
	public String getExpireDate() {
		return ExpireDate;
	}
	public void setExpireDate(String expireDate) {
		ExpireDate = expireDate;
	}
	private int id;
	private String name;
	private BigDecimal fee;
	private String startdate;
	private String duration;
	private Faculty faculty;
	private List<Faculty> instructorlist = new ArrayList<Faculty>(5);
	private List<Participant> participantlist = new ArrayList<Participant>(5);
	public Course(String name, BigDecimal fee, String startdate, String duration, Faculty faculty,
			List<Faculty> instructorlist, List<Participant> participantlist) {
		super();
		this.name = name;
		this.fee = fee;
		this.startdate = startdate;
		this.duration = duration;
		this.faculty = faculty;
		this.instructorlist = instructorlist;
		this.participantlist = participantlist;
	}
	public Course(int id,String name, BigDecimal fee, String startdate, String duration, Faculty faculty,
			List<Faculty> instructorlist, List<Participant> participantlist) {
		super();
		this.id = id;
		this.name = name;
		this.fee = fee;
		this.startdate = startdate;
		this.duration = duration;
		this.faculty = faculty;
		this.instructorlist = instructorlist;
		this.participantlist = participantlist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getStartdate() {
		return startdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	public List<Faculty> getInstructorlist() {
		return instructorlist;
	}
	public void setInstructorlist(List<Faculty> instructorlist) {
		this.instructorlist = instructorlist;
	}
	public List<Participant> getParticipantlist() {
		return participantlist;
	}
	public void setParticipantlist(List<Participant> participantlist) {
		this.participantlist = participantlist;
	}
	@Override
	public String toString() {
		return "Course [name=" + name + ", fee=" + fee + ", startdate=" + startdate + ", duration=" + duration + "]";
	}
	
	

}
