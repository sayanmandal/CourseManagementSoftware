package CourseRegulatorNew;

import java.io.Serializable;

public class Faculty implements Serializable{
	private int id;
	private String Department;
	private String Name;
	private String Address;
	private String Mobile;
	private String EmailAddress;
	public Faculty(String department, String name, String address, String mobile, String emailAddress) {
		super();
		Department = department;
		Name = name;
		Address = address;
		Mobile = mobile;
		EmailAddress = emailAddress;
	}
	public Faculty(int id,String department, String name, String address, String mobile, String emailAddress) {
		super();
		this.id = id;
		Department = department;
		Name = name;
		Address = address;
		Mobile = mobile;
		EmailAddress = emailAddress;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	@Override
	public String toString() {
		return "Faculty [Department=" + Department + ", Name=" + Name + ", Address=" + Address + ", Mobile=" + Mobile
				+ ", EmailAddress=" + EmailAddress + "]";
	}

}

