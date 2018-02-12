package cigniti.rmg.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="employee_Details_view")
public class EmployeeDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5561826377827590272L;
	
	@Id
	@Column(name="emp_id")
	private String emp_id;
	
	@Column(name="emp_name")
	private String employeeName;
	
	@Column(name="designation_name")
	private String designation;
	
	@Column(name="doj")
	private String doj;
	
	@Column(name="joinginLocation")
	private String joinginLocation;  
	
	@Column(name="currentLocation")
	private String currentLocation;
	
	@Column(name="employeementType")
	private String employeementType;
	
	@Column(name="reporting_manager")
	private String reportingManager;
	
	@Column(name="accountManager")
	private String accountManager;
	
	
	
	@Column(name="visa")
	private String visa;
	
	@Column(name="progressbar")
	private int progressbar ;
	

	@Column(name="officialEmailId")
	 private String officialEmailId ;
	
	@Column(name="personalEmailId")
	 private String  personalEmailId;
	
	@Column(name="mobile")
	 private String	mobile;
	
	
	@Column(name="alternatePhoneNo")
	 private String	alternatePhoneNo;
	
	
	@Column(name="project_manager")
	 private String	project_manager;
		
	@Column(name="bu_name")
	private String	bu;
	
	@Column(name="du_name")
	private String	du;
	
	@Column(name="rmg_spoc")
	private String	rmg_spoc;
	
	@Column(name="project_name")
	private String	project_name;
	
	@Column(name="proj_startdate")
	private String	proj_startdate;
	
	@Column(name="proj_enddate")
	private String	proj_enddate;
	
/*	
	@Transient
	 private String	cignitiExperience;*/
	
	@Column(name="totalExperience")
	 private String	totalExperience;
		
	
	@Column(name="image_name")
	private String image_name;
	
	
	@Column(name="employee_image")
	private byte[] employeeImage;
	
	@Column(name= "ResumeFile")
	 private byte[]	ResumeFile;
	
	
	@Column(name="resume_filename")
	 private String	employeeResume;
	
	@Column(name="bu_head")
	private String	bu_head;
	
	@Column(name="hr_spoc")
	private String	hr_spoc;
	
	
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
	
	

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getJoinginLocation() {
		return joinginLocation;
	}

	public void setJoinginLocation(String joinginLocation) {
		this.joinginLocation = joinginLocation;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getEmployeementType() {
		return employeementType;
	}

	public void setEmployeementType(String employeementType) {
		this.employeementType = employeementType;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public String getVisa() {
		return visa;
	}

	public void setVisa(String visa) {
		this.visa = visa;
	}

	public int getProgressbar() {
		return progressbar;
	}

	public void setProgressbar(int progressbar) {
		this.progressbar = progressbar;
	}

	public String getOfficialEmailId() {
		return officialEmailId;
	}

	public void setOfficialEmailId(String officialEmailId) {
		this.officialEmailId = officialEmailId;
	}

	public String getPersonalEmailId() {
		return personalEmailId;
	}

	public void setPersonalEmailId(String personalEmailId) {
		this.personalEmailId = personalEmailId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProject_manager() {
		return project_manager;
	}

	public void setProject_manager(String project_manager) {
		this.project_manager = project_manager;
	}

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public String getDu() {
		return du;
	}

	public void setDu(String du) {
		this.du = du;
	}

	public String getRmg_spoc() {
		return rmg_spoc;
	}

	public void setRmg_spoc(String rmg_spoc) {
		this.rmg_spoc = rmg_spoc;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProj_startdate() {
		return proj_startdate;
	}

	public void setProj_startdate(String proj_startdate) {
		this.proj_startdate = proj_startdate;
	}

	public String getProj_enddate() {
		return proj_enddate;
	}

	public void setProj_enddate(String proj_enddate) {
		this.proj_enddate = proj_enddate;
	}

	public String getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public byte[] getEmployeeImage() {
		return employeeImage;
	}

	public void setEmployeeImage(byte[] employeeImage) {
		this.employeeImage = employeeImage;
	}

	public String getEmployeeResume() {
		return employeeResume;
	}

	public void setEmployeeResume(String employeeResume) {
		this.employeeResume = employeeResume;
	}

	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	public String getBu_head() {
		return bu_head;
	}

	public void setBu_head(String bu_head) {
		this.bu_head = bu_head;
	}

	public String getHr_spoc() {
		return hr_spoc;
	}

	public void setHr_spoc(String hr_spoc) {
		this.hr_spoc = hr_spoc;
	}

	public String getAlternatePhoneNo() {
		return alternatePhoneNo;
	}

	public void setAlternatePhoneNo(String alternatePhoneNo) {
		this.alternatePhoneNo = alternatePhoneNo;
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

	public byte[] getResumeFile() {
		return ResumeFile;
	}

	public void setResumeFile(byte[] resumeFile) {
		ResumeFile = resumeFile;
	}

	@Override
	public String toString() {
		return "EmployeeDetails [emp_id=" + emp_id + ", employeeName=" + employeeName + ", designation=" + designation
				+ ", doj=" + doj + ", joinginLocation=" + joinginLocation + ", currentLocation=" + currentLocation
				+ ", employeementType=" + employeementType + ", reportingManager=" + reportingManager
				+ ", accountManager=" + accountManager + ", visa=" + visa + ", progressbar=" + progressbar
				+ ", officialEmailId=" + officialEmailId + ", personalEmailId=" + personalEmailId + ", mobile=" + mobile
				+ ", alternatePhoneNo=" + alternatePhoneNo + ", project_manager=" + project_manager + ", bu=" + bu
				+ ", du=" + du + ", rmg_spoc=" + rmg_spoc + ", project_name=" + project_name + ", proj_startdate="
				+ proj_startdate + ", proj_enddate=" + proj_enddate + ", totalExperience=" + totalExperience
				+ ", image_name=" + image_name + ", employeeImage=" + Arrays.toString(employeeImage) + ", ResumeFile="
				+ Arrays.toString(ResumeFile) + ", employeeResume=" + employeeResume + ", bu_head=" + bu_head
				+ ", hr_spoc=" + hr_spoc + ", passport_no=" + passport_no + ", passport_Valid_From="
				+ passport_Valid_From + ", passport_Valid_To=" + passport_Valid_To + ", passport_Issue_Date="
				+ passport_Issue_Date + ", passposrt_Scan=" + Arrays.toString(passposrt_Scan) + "]";
	}


	


	
	
}
