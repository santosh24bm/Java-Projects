package cigniti.rmg.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "employee")
public class Employee implements Serializable{
	

	private static final long serialVersionUID = 8845321843474224035L;


	@Id
	@Column(name = "cmpny_emp_id")
	private String employeeId;
	
	
	@Column(name = "Image", nullable = true )
	private byte[]  employeeImage;
	
	@Column(name= "image_name", nullable = true )
	private String image_name ;
	
	@Column(name = "resume" , nullable = true )
	private byte[]  resume;
	
	@Column(name= "resume_filename" , nullable = true)
	private String resume_filename ;
	
	@Column(name="lastUpdate" ,  nullable = true )
	private java.sql.Timestamp lastUpdate;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public byte[] getEmployeeImage() {
		return employeeImage;
	}

	public void setEmployeeImage(byte[] employeeImage) {
		this.employeeImage = employeeImage;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public byte[] getResume() {
		return resume;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

	public String getResume_filename() {
		return resume_filename;
	}

	public void setResume_filename(String resume_filename) {
		this.resume_filename = resume_filename;
	}

	public java.sql.Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(java.sql.Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeImage=" + Arrays.toString(employeeImage)
				+ ", image_name=" + image_name + ", resume=" + Arrays.toString(resume) + ", resume_filename="
				+ resume_filename + ", lastUpdate=" + lastUpdate + "]";
	}

	
	
	
}
