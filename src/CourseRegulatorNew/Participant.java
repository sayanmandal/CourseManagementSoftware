package CourseRegulatorNew;

import java.io.Serializable;

public class Participant implements Serializable{
	
	private int id;
	private String name;
	private String address;
	private String mobilenumber;
	private String organization;
	private String emailaddress;
	public Participant(String name, String address, String mobilenumber, String organization, String emailaddress) {
		super();
		this.name = name;
		this.address = address;
		this.mobilenumber = mobilenumber;
		this.organization = organization;
		this.emailaddress = emailaddress;
	}
	public Participant(int id,String name, String address, String mobilenumber, String organization, String emailaddress) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobilenumber = mobilenumber;
		this.organization = organization;
		this.emailaddress = emailaddress;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	@Override
	public String toString() {
		return "Participant [name=" + name + ", address=" + address + ", mobilenumber=" + mobilenumber
				+ ", organization=" + organization + ", emailaddress=" + emailaddress + "]";
	}
	

}
