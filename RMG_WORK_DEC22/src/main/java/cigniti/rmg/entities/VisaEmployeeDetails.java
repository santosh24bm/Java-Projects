package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="visa_employee_details")
public class VisaEmployeeDetails implements Serializable{

	
	private static final long serialVersionUID = 5826217787406897214L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rowid;
	
	//@Id
	@Column(name="emp_id")
	private String employeeId;
	
	@Column(name="visa")
	private String visa;
	
	//@Id
	@Column(name="visa_type")
	private String visa_type;
	
//	@Id
	@Column(name="country")
	private String country;
	
//	@Id
	@Column(name="status")
	private String status;
	
	@Column(name="ValidFrom")
	private String ValidFrom;
	
	@Column(name="validTo")
	private String validTo;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getVisa_type() {
		return visa_type;
	}

	public void setVisa_type(String visa_type) {
		this.visa_type = visa_type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidFrom() {
		return ValidFrom;
	}

	public void setValidFrom(String validFrom) {
		ValidFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	
	

	public String getVisa() {
		return visa;
	}

	public void setVisa(String visa) {
		this.visa = visa;
	}

	@Override
	public String toString() {
		return "VisaEmployeeDetails [rowid=" + rowid + ", employeeId=" + employeeId + ", visa=" + visa + ", visa_type="
				+ visa_type + ", country=" + country + ", status=" + status + ", ValidFrom=" + ValidFrom + ", validTo="
				+ validTo + "]";
	}


	
	
	
}
