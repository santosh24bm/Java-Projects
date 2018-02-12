package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity 
@Table(name="theme_employee")
public class ThemeEmployee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5690007207357251302L;

	@Id
	@Column(name="emp_id")
	private String employeeId ;
	
  
	@Column(name="theme_id")
	private int themeId ;
	



	public String getEmployeeId() {
		return employeeId;
	}


	public int getThemeId() {
		return themeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}



	@Override
	public String toString() {
		return "ThemeEmployee [employeeId=" + employeeId + ", themeId=" + themeId + "]";
	} 
	
	
	
	
}
