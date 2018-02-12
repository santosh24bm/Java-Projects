package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_employee_vw")
public class ProjectEmployeeMap implements Serializable {

	private static final long serialVersionUID = -9205615066799100820L;

	@Id
	@Column(name = "rowid")
	private int rowid;

	@Column(name = "emp_id")
	private String emp_id;

	@Column(name = "project_name")
	private String project_name;

	@Column(name = "account_name")
	private String account_name;

	@Column(name = "allocation_start_date")
	private String allocation_start_date;

	@Column(name = "allocation_end_date")
	private String allocation_end_date;

	@Column(name = "last_working_day")
	private String last_working_day;

	@Column(name = "allocation_status")
	private String allocation_status;

	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAllocation_start_date() {
		return allocation_start_date;
	}

	public void setAllocation_start_date(String allocation_start_date) {
		this.allocation_start_date = allocation_start_date;
	}

	public String getAllocation_end_date() {
		return allocation_end_date;
	}

	public void setAllocation_end_date(String allocation_end_date) {
		this.allocation_end_date = allocation_end_date;
	}

	public String getLast_working_day() {
		return last_working_day;
	}

	public void setLast_working_day(String last_working_day) {
		this.last_working_day = last_working_day;
	}

	public String getAllocation_status() {
		return allocation_status;
	}

	public void setAllocation_status(String allocation_status) {
		this.allocation_status = allocation_status;
	}

	@Override
	public String toString() {
		return "ProjectEmployeeMap [rowid=" + rowid + ", emp_id=" + emp_id + ", project_name=" + project_name
				+ ", account_name=" + account_name + ", allocation_start_date=" + allocation_start_date
				+ ", allocation_end_date=" + allocation_end_date + ", last_working_day=" + last_working_day
				+ ", allocation_status=" + allocation_status + "]";
	}

	
	
}
