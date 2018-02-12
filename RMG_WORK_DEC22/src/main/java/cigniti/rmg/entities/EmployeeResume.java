package cigniti.rmg.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_resume" )
public class EmployeeResume implements Serializable {

	private static final long serialVersionUID = 6522220543830896318L;


	@Id
	@Column(name = "emp_id")
	private String employeeId;
	
	
	@Column(name = "employee_resume")
	private byte[]  employeeResume;
	
	@Column(name= "resume_filename")
	private String resumefilename ;

	public String getEmployeeId() {
		return employeeId;
	}

	public byte[] getEmployeeResume() {
		return employeeResume;
	}

	public String getResumefilename() {
		return resumefilename;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeResume(byte[] employeeResume) {
		this.employeeResume = employeeResume;
	}

	public void setResumefilename(String resumefilename) {
		this.resumefilename = resumefilename;
	}

	@Override
	public String toString() {
		return "EmployeeResume [employeeId=" + employeeId + ", employeeResume=" + Arrays.toString(employeeResume)
				+ ", resumefilename=" + resumefilename + "]";
	}
	
	
	
}
