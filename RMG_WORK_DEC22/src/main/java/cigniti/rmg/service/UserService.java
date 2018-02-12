package cigniti.rmg.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cigniti.rmg.dao.impl.UserImpl;
import cigniti.rmg.entities.AtsAllocStatusBean;
import cigniti.rmg.entities.BuMaster;
import cigniti.rmg.entities.BusinessUnitAllocBean;
import cigniti.rmg.entities.CertificationInstitutes;
import cigniti.rmg.entities.CertificationNames;
import cigniti.rmg.entities.CertificationTechnologies;
import cigniti.rmg.entities.ChildDomain;
import cigniti.rmg.entities.DesignationMaster;
import cigniti.rmg.entities.DomainMaster;
import cigniti.rmg.entities.DuMaster;
import cigniti.rmg.entities.Employee;
import cigniti.rmg.entities.EmployeeBean;
import cigniti.rmg.entities.EmployeeCertificationMap;
import cigniti.rmg.entities.EmployeeDetails;
import cigniti.rmg.entities.EmployeeDomainBean;
import cigniti.rmg.entities.EmployeeDomainMainDto;
import cigniti.rmg.entities.EmployeeDomainView;
import cigniti.rmg.entities.EmployeeDto;
import cigniti.rmg.entities.EmployeeProfileReport;
import cigniti.rmg.entities.EmployeeProjectsBean;
import cigniti.rmg.entities.EmployeeSkills;
import cigniti.rmg.entities.LocationMaster;
import cigniti.rmg.entities.LoginAudit;
import cigniti.rmg.entities.LoginDetailsBean;
import cigniti.rmg.entities.PracticesAllocationBean;
import cigniti.rmg.entities.ProjectEmployeeMap;
import cigniti.rmg.entities.ResourceSupport;
import cigniti.rmg.entities.SkillAndDomainCertificationDtls;
import cigniti.rmg.entities.SkillCategoryMaster;
import cigniti.rmg.entities.SkillEmbed;
import cigniti.rmg.entities.SkillMaster;
import cigniti.rmg.entities.SubDomain;
import cigniti.rmg.entities.ThemeEmployee;
import cigniti.rmg.entities.ThemeMaster;
import cigniti.rmg.entities.User;
import cigniti.rmg.entities.UserBean;
import cigniti.rmg.entities.VisaEmployeeDetails;

/**
 * @author Santosh BM
 *
 */
@Repository
public class UserService {

	final static Logger logger = Logger.getLogger(UserService.class);
	public static ResourceBundle timesheetResourceBundle = ResourceBundle.getBundle("timesheet", Locale.getDefault());

	@Autowired
	UserImpl userImpl;

	/**
	 * @param user
	 * @param request
	 * @param header
	 * @param ldBean
	 * @return
	 */
	public LoginDetailsBean getAuthenticationValidation(User user, HttpServletRequest request, HttpHeaders header,
			LoginDetailsBean ldBean) {
		String isValid = isAuthenticateUser(user.getUserId(), user.getPassword());
		String loginUserDetails = null;
		String localHostIP = "0:0:0:0:0:0:0:1";
		if (isValid.equalsIgnoreCase("true")) {

			ldBean = getLoginDetails(user.getUserId());

			InetAddress inetAddress = null;
			String machineName = null;

			String remoteAddress = request.getRemoteAddr();
			try {
				if (remoteAddress.equals(localHostIP)) {
					inetAddress = InetAddress.getLocalHost();
					remoteAddress = String.valueOf(inetAddress);
					machineName = inetAddress.getHostName();
				} else {
					inetAddress = InetAddress.getByName(remoteAddress);
					machineName = inetAddress.getHostName();
				}
			} catch (UnknownHostException e) {

				e.printStackTrace();
			}

			LoginAudit loginAudit = new LoginAudit();

			loginAudit.setUser_id(ldBean.getEmployeeId());
			loginAudit.setUser_name(ldBean.getEmployeeName());

			Date updatedLastLoginDate = userImpl.getUpdatedLastLoginDateById(ldBean.getEmployeeId());
			logger.info("lastLoginDate for user ->" + ldBean.getEmployeeId() + " is :->" + updatedLastLoginDate);

			Date currentDateAndTime = new Date();

			if (null != updatedLastLoginDate)
				loginAudit.setLast_login_date(updatedLastLoginDate);
			else
				loginAudit.setLast_login_date(currentDateAndTime);

			loginAudit.setUpdated_login_date(currentDateAndTime);
			loginAudit.setLast_login_ip_address(String.valueOf(remoteAddress));
			loginAudit.setHostname(machineName);

			saveLoginAuditDetails(loginAudit);
		} else {
			loginUserDetails = "invalid user";
			header.add(String.valueOf(user.getUserId()), loginUserDetails);
		}
		return ldBean;
	}

	/**
	 * @param chargeCodeBean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResourceSupport saveLoginAuditDetails(LoginAudit loginAudit) {

		ResourceSupport resource = userImpl.saveLoginAuditDetails(loginAudit);

		return resource;
	}

	/**
	 * This method will help for user or employee authentication through LDAP.
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public String isAuthenticateUser(String userId, String password) {

		boolean isAuthenticatedUser = userImpl.getAuthenticateUserbyLDAP(userId, password);
		String isValidUserInJson = null;
		try {
			isValidUserInJson = new ObjectMapper().writeValueAsString(isAuthenticatedUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isValidUserInJson;
	}

	/**
	 * @param userId
	 * @return
	 */
	public LoginDetailsBean getLoginDetails(String userId) {

		LoginDetailsBean ldBean = new LoginDetailsBean();
		List<LoginDetailsBean> loginDetailsBeanList = userImpl.getUserLoginDetailsbyID(userId);
		if (!loginDetailsBeanList.isEmpty())

			return loginDetailsBeanList.get(0);
		else {
			ldBean.setEmployeeId(userId);
			UserBean userDetBean = userImpl.getEmpDetById(userId);
			ldBean.setEmployeeName(userDetBean.getUserName());
			ldBean.setDeliveryUnit(userDetBean.getDu_id());
			ldBean.setEmployeeRoleId(
					Integer.parseInt(timesheetResourceBundle.getObject("DEFAULT_USER_ROLE_ID").toString()));
			ldBean.setEmployeeRoleName(timesheetResourceBundle.getObject("DEFAULT_USER_ROLE_NAME").toString());
			return ldBean;
		}

	}

	/**
	 * @param userId
	 * @return
	 */
	public EmployeeDetails getEmployeeDetails(String empId) {
		EmployeeDetails empDetails = userImpl.getEmployeeDetails(empId);
		return empDetails;
	}

	public SkillAndDomainCertificationDtls getSkillDomainCertfn(String empId) {
		SkillAndDomainCertificationDtls skillDtls = userImpl.getSkillDomainCertfn(empId);
		return skillDtls;

	}

	public List<SkillEmbed> getSkillCategoryAndSkills() {

		List<SkillEmbed> skp = userImpl.getSkillCategoryAndSkills();
		System.out.println("Size : " + skp.size());
		return skp;
	}

	public  List<SkillCategoryMaster> getSkillCategoryDetails() {
		return userImpl.getSkillCategoryDetails();

	}

	public List<ProjectEmployeeMap> getProjectsByOId(String empId) {
		return userImpl.getProjectsByOId(empId);

	}

	public List<DomainMaster> getDomains() {

		return userImpl.getDomains();

	}

	public List<SubDomain> getSubDomains(int domainId) {

		return userImpl.getSubDomains(domainId);

	}

	public List<ChildDomain> getChildDomains(int domainId, int subDomainId) {

		return userImpl.getChildDomains(domainId, subDomainId);
	}

	public int updateContactAndExp(EmployeeDto emp) {

		return userImpl.updateContactAndExp(emp);

	}

	public boolean updateTheme(ThemeEmployee obj) {
		return userImpl.updateTheme(obj);
	}

	public List<ThemeMaster> getThemesUi() {

		return userImpl.getThemesUi();
	}

	public boolean saveImage(CommonsMultipartFile uploadFile, int progressbar , String employeeId, String lastUpdate) {

		return userImpl.saveImage(uploadFile, progressbar , employeeId, lastUpdate);
	}

	public boolean addEmplSkill(EmployeeSkills empSkillObj, int progressbar, String lastUpdate) {

		return userImpl.addEmplSkill(empSkillObj, progressbar,  lastUpdate);
	}

	public boolean deleteEmplSkill(EmployeeSkills empSkillObj, int progressbar, String lastUpdate) {
		return userImpl.deleteEmplSkill(empSkillObj, progressbar,  lastUpdate);
	}

	public boolean addEmplDomain(EmployeeDomainMainDto empDomainObj, int progressbar, String lastUpdate) {
		return userImpl.addEmplDomain(empDomainObj, progressbar,  lastUpdate);
	}

	public boolean deleteEmplDomain(EmployeeDomainBean empDomainObj, int progressbar, String lastUpdate) {
		return userImpl.deleteEmplDomain(empDomainObj, progressbar,  lastUpdate);
	}

	public boolean saveResume(CommonsMultipartFile aFile, int progressbar, String employeeId, String lastUpdate) {
		return userImpl.saveResume(aFile, progressbar,employeeId,  lastUpdate);
	}

	public List<AtsAllocStatusBean> getAllocStatus() {

		return userImpl.getAllocStatus();
	}

	public List<PracticesAllocationBean> getPracticeAllocStatus() {

		return userImpl.getPracticeAllocStatus();
	}

	public List<BusinessUnitAllocBean> getBuAllocStatus() {
		return userImpl.getBuAllocStatus();
	}

	public List<VisaEmployeeDetails> getVisaEmpDtls(String empId) {
	
		return userImpl.getVisaEmpDtls(empId);
	}

	public List<CertificationTechnologies> getCertTechnologies() {
		
		return userImpl.getCertTechnologies();
	}

	public List<CertificationNames> getCertNames(int cert_tech_id) {
		return userImpl.getCertNames(cert_tech_id);
	}

	public List<CertificationInstitutes> getCertInstitutes(int cert_tech_id) {
		return userImpl.getCertInstitutes(cert_tech_id);
	}

	public boolean saveUpdateEmpCert(EmployeeCertificationMap empCertbj) {
		return userImpl.saveUpdateEmpCert(empCertbj);
	}

	public boolean deleteEmpCert(EmployeeCertificationMap empCertbj) {
		return userImpl.deleteEmpCert(empCertbj);
	}
	
	public boolean saveUpdateEmpProj(EmployeeProjectsBean empCertbj) {
		return userImpl.saveUpdateEmpProj(empCertbj);
	}

	public boolean deleteEmpProject(EmployeeProjectsBean empCertbj) {
		return userImpl.deleteEmpProject(empCertbj);
	}

	public List<DesignationMaster> getDesinationList() {
		
		return userImpl.getDesinationList();

	}

	public List<LocationMaster> getLocationList() {
		return userImpl.getLocationList();
	}

	public List<BuMaster> getBusinessUnitList() {
		return userImpl.getBusinessUnitList();
	}
	
	public List<DuMaster> getDeliveryUnitList() {
		return userImpl.getDeliveryUnitList();
	}

	public boolean updateEmployeeDetails(EmployeeBean empBean) {
		return userImpl.updateEmployeeDetails(empBean);
	}

	public boolean updateBudetails(EmployeeDto empBean) {
		return userImpl.updateBudetails(empBean);
	}

	public List<SkillMaster> getSkillsByCateory(int skill_category_id) {
		return userImpl.getSkillsByCateory(skill_category_id);
	}

	public boolean addEmplVisa(VisaEmployeeDetails empvisaObj) {
		
		return userImpl.addEmplVisa(empvisaObj);
	}

	public boolean deleteEmpVisa(VisaEmployeeDetails empVisaObj) {
		return userImpl.deleteEmpVisa(empVisaObj);
	}

	public boolean updatePassportDtlsByEmpl(CommonsMultipartFile aFile, String employeeId, String passport_no,
			String passport_Valid_From, String passport_Valid_To , String passport_Issue_Date) {
	
		return userImpl.updatePassportDtlsByEmpl(aFile, employeeId,passport_no,passport_Valid_From,passport_Valid_To,passport_Issue_Date);
	}

	public List<EmployeeDomainView> getDomainDtlsBYId(String empId) {
	
		return userImpl.getDomainDtlsBYId(empId);
	}

	public boolean deleteEmployeeResume(Employee empObj, int progressbar, String lastUpdate) {
		return userImpl.deleteEmployeeResume(empObj, progressbar,  lastUpdate);
	}
	
	public boolean deleteEmployeeImage(Employee empObj, int progressbar, String lastUpdate) {
		return userImpl.deleteEmployeeImage(empObj, progressbar,  lastUpdate);
	}
	
	public List<EmployeeProfileReport> getEmployeeProfileReport(String empId) {
		return userImpl.getEmployeeProfileReport(empId);
	}

	/*public EmployeeBean getPassportDetails(String employeeId) {
		return userImpl.getPassportDetails(employeeId);
	}*/

}
