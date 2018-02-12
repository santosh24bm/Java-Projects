package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

/**
 * @author Santosh BM
 *
 */

@Entity
@Immutable
@Table(name="login_details_master_vw")
public class LoginDetailsBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2390013380245643574L;

	@Id
	@Column(name="employee_id",insertable=false, updatable=false)
	private String employeeId;
	
	@Column(name="employee_name", insertable=false, updatable=false)
	private String employeeName;
	
	@Column(name="emp_role_id")
	private long employeeRoleId;
	
	@Column(name="emp_role_name")
	private String employeeRoleName;
	
	@Column(name="delivery_unit")
	private int deliveryUnit;
	
	@Column(name="theme_col")
	private String themeCol;
	
	@Column(name="last_login_date")
	private String last_login_date;
	
	

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public long getEmployeeRoleId() {
		return employeeRoleId;
	}

	public void setEmployeeRoleId(long employeeRoleId) {
		this.employeeRoleId = employeeRoleId;
	}

	public String getEmployeeRoleName() {
		return employeeRoleName;
	}

	public void setEmployeeRoleName(String employeeRoleName) {
		this.employeeRoleName = employeeRoleName;
	}

	public int getDeliveryUnit() {
		return deliveryUnit;
	}

	public void setDeliveryUnit(int deliveryUnit) {
		this.deliveryUnit = deliveryUnit;
	}

	public String getThemeCol() {
		return themeCol;
	}

	public void setThemeCol(String themeCol) {
		this.themeCol = themeCol;
	}

	public String getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}
	
	
	
}
