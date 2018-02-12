package cigniti.rmg.entities;

import java.io.Serializable;
import java.security.Timestamp;
import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name= "employee")
public class EmployeeBean implements Serializable{

	private static final long serialVersionUID = 273039024943035587L;
	@Id
	@Column(name= "cmpny_emp_id" )
	@JsonProperty("empId")
	@JsonDeserialize
	private String employeeId;
	
	
	
	@JsonProperty("phoneNo")
	@JsonDeserialize
	@Column(name= "personal_phone_no" ,  nullable = true ) 
	private String phoneNo;
	
	@JsonProperty("alternate_phone_no")
	@JsonDeserialize
	@Column(name= "alternate_phone_no" ,  nullable = true ) 
	private String alternatePhoneNo;
	
	
	
	@Column(name= "personal_email_id" ,  nullable = true )
	@JsonProperty("personalMailId")
	@JsonDeserialize
	private String personalMailId;
	
	@Column(name= "total_exp" ,  nullable = true )
	@JsonProperty("totalExperience")
	@JsonDeserialize
	private float totalExperience;

	@Column(name="progressbar" ,  nullable = true )
	private int progressbar;
	
	
/*employee update fields */

	
	@Column(name="name" ,  nullable = true )
	private String employeeName;
	
	@Column(name="desgn_id" ,  nullable = true )
	private String designation;
	
	@Column(name="doj" ,  nullable = true )
	private String doj;
	
	@Column(name="permanent_loc_id" ,  nullable = true )
	private String joiningLocation;
	
	@Column(name="current_loc_id" ,  nullable = true )
	private String currentLocation;
	
	@Column(name="emp_nature" ,  nullable = true )
	private String emplType;
		
	
	@Column(name="manager" ,  nullable = true )
	private String reportingManager;
	
	@Column(name="rmg_spoc" ,  nullable = true )
	private String rmgSpoc;
	
	/* BU details save */
	@Column(name="bu_id" ,  nullable = true )
	private int bu_id;
	
	@Column(name="du_id" ,  nullable = true )
	private int du_id;
	

	
	@Column(name="prjct_mngr" ,  nullable = true )
	private String projectManger;
	
	// account  manager is already there : reportingManager - acoount Manager 
	
	@Column(name="bu_head" ,  nullable = true )
	private String buHead ;
	
	@Column(name="hr_spoc" ,  nullable = true )
	private String hrSpoc;
	
	@Column(name="lastUpdate" ,  nullable = true )
	private java.sql.Timestamp lastUpdate;
	
	// passport details 
	
	@Column(name="passport_no" ,  nullable = true )
	private String passport_no ;
	
	@Column(name="passport_Valid_From" ,  nullable = true )
	private String passport_Valid_From;
	
	@Column(name="passport_Valid_To" ,  nullable = true )
	private String passport_Valid_To;
	
	@Column(name="passport_Issue_Date" ,  nullable = true )
	private String passport_Issue_Date;
	
	
	@Column(name="passposrt_Scan" ,  nullable = true )
	private byte[] passposrt_Scan;
	
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	
	

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getPersonalMailId() {
		return personalMailId;
	}

	public float getTotalExperience() {
		return totalExperience;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	
	
	public String getAlternatePhoneNo() {
		return alternatePhoneNo;
	}



	public void setAlternatePhoneNo(String alternatePhoneNo) {
		this.alternatePhoneNo = alternatePhoneNo;
	}



	public void setPersonalMailId(String personalMailId) {
		this.personalMailId = personalMailId;
	}

	public void setTotalExperience(float totalExperience) {
		this.totalExperience = totalExperience;
	}



	public int getProgressbar() {
		return progressbar;
	}



	public void setProgressbar(int progressbar) {
		this.progressbar = progressbar;
	}



	public String getEmployeeName() {
		return employeeName;
	}



	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



	public String getDoj() {
		return doj;
	}



	public void setDoj(String doj) {
		this.doj = doj;
	}



	public String getJoiningLocation() {
		return joiningLocation;
	}



	public void setJoiningLocation(String joiningLocation) {
		this.joiningLocation = joiningLocation;
	}



	public String getCurrentLocation() {
		return currentLocation;
	}



	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}



	public String getEmplType() {
		return emplType;
	}



	public void setEmplType(String emplType) {
		this.emplType = emplType;
	}



	public String getReportingManager() {
		return reportingManager;
	}



	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}



	public String getRmgSpoc() {
		return rmgSpoc;
	}



	public void setRmgSpoc(String rmgSpoc) {
		this.rmgSpoc = rmgSpoc;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public int getBu_id() {
		return bu_id;
	}



	public void setBu_id(int bu_id) {
		this.bu_id = bu_id;
	}



	public int getDu_id() {
		return du_id;
	}



	public void setDu_id(int du_id) {
		this.du_id = du_id;
	}



	public String getProjectManger() {
		return projectManger;
	}



	public void setProjectManger(String projectManger) {
		this.projectManger = projectManger;
	}



	public String getBuHead() {
		return buHead;
	}



	public void setBuHead(String buHead) {
		this.buHead = buHead;
	}



	public String getHrSpoc() {
		return hrSpoc;
	}



	public void setHrSpoc(String hrSpoc) {
		this.hrSpoc = hrSpoc;
	}



	public java.sql.Timestamp getLastUpdate() {
		return lastUpdate;
	}



	public void setLastUpdate(java.sql.Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}



	public String getPassport_no() {
		return passport_no;
	}



	public void setPassport_no(String passport_no) {
		this.passport_no = passport_no;
	}



	public String getPassport_Valid_From() {
		return passport_Valid_From;
	}



	public void setPassport_Valid_From(String passport_Valid_From) {
		this.passport_Valid_From = passport_Valid_From;
	}



	public String getPassport_Valid_To() {
		return passport_Valid_To;
	}



	public void setPassport_Valid_To(String passport_Valid_To) {
		this.passport_Valid_To = passport_Valid_To;
	}



	public String getPassport_Issue_Date() {
		return passport_Issue_Date;
	}



	public void setPassport_Issue_Date(String passport_Issue_Date) {
		this.passport_Issue_Date = passport_Issue_Date;
	}



	public byte[] getPassposrt_Scan() {
		return passposrt_Scan;
	}



	public void setPassposrt_Scan(byte[] passposrt_Scan) {
		this.passposrt_Scan = passposrt_Scan;
	}



	@Override
	public String toString() {
		return "EmployeeBean [employeeId=" + employeeId + ", phoneNo=" + phoneNo + ", alternatePhoneNo="
				+ alternatePhoneNo + ", personalMailId=" + personalMailId + ", totalExperience=" + totalExperience
				+ ", progressbar=" + progressbar + ", employeeName=" + employeeName + ", designation=" + designation
				+ ", doj=" + doj + ", joiningLocation=" + joiningLocation + ", currentLocation=" + currentLocation
				+ ", emplType=" + emplType + ", reportingManager=" + reportingManager + ", rmgSpoc=" + rmgSpoc
				+ ", bu_id=" + bu_id + ", du_id=" + du_id + ", projectManger=" + projectManger + ", buHead=" + buHead
				+ ", hrSpoc=" + hrSpoc + ", lastUpdate=" + lastUpdate + ", passport_no=" + passport_no
				+ ", passport_Valid_From=" + passport_Valid_From + ", passport_Valid_To=" + passport_Valid_To
				+ ", passport_Issue_Date=" + passport_Issue_Date + ", passposrt_Scan=" + Arrays.toString(passposrt_Scan)
				+ "]";
	}






	
	
}
