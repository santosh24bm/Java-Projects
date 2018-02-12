package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "employeeimage" )
public class EmployeeImage  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 398012259277993550L;


	@Id
	@Column(name = "emp_id")
	private String employeeId;
	
	
	@Column(name = "empImage")
	private byte[]  employeeImage;
	
	@Column(name= "filename")
	private String filename ;


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


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}

	 
	 
}