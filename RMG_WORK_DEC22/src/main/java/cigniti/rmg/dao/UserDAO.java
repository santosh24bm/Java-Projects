package cigniti.rmg.dao;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
import cigniti.rmg.entities.LoginDetailsBean;
import cigniti.rmg.entities.PracticesAllocationBean;
import cigniti.rmg.entities.ProjectEmployeeMap;
import cigniti.rmg.entities.SkillAndDomainCertificationDtls;
import cigniti.rmg.entities.SkillCategoryMaster;
import cigniti.rmg.entities.SkillEmbed;
import cigniti.rmg.entities.SkillMaster;
import cigniti.rmg.entities.SubDomain;
import cigniti.rmg.entities.ThemeEmployee;
import cigniti.rmg.entities.ThemeMaster;
import cigniti.rmg.entities.UserBean;
import cigniti.rmg.entities.VisaEmployeeDetails;

/**
 * @author Santosh BM
 *
 */

public interface UserDAO {

	public List<UserBean> getUserDetailsList();

	public List<LoginDetailsBean> getUserLoginDetailsbyID(String userId);

	public Date getUpdatedLastLoginDateById(String userId);

	public boolean getAuthenticateUserbyLDAP(String userId, String password);

	public EmployeeDetails getEmployeeDetails(String empId);
	
	public boolean updateEmployeeDetails(EmployeeBean empBean);

	public SkillAndDomainCertificationDtls getSkillDomainCertfn(String empId);

	public List<SkillEmbed> getSkillCategoryAndSkills(); // need to remove - may be 
	
	public 	 List<SkillCategoryMaster>  getSkillCategoryDetails() ;
	
	public List<SkillMaster> getSkillsByCateory(int skill_category_id) ;

	public List<DomainMaster> getDomains();

	public List<SubDomain> getSubDomains(int id);

	public List<ChildDomain> getChildDomains(int domainId, int subDomainId);

	public int updateContactAndExp(EmployeeDto emp);

	public boolean updateTheme(ThemeEmployee obj);

	public List<ThemeMaster> getThemesUi();

	public boolean saveImage(CommonsMultipartFile uploadFile, int progressbar, String employeeId, String lastUpdate);

	public boolean addEmplSkill(EmployeeSkills empSkillObj, int progressbar , String lastUpdate );

	public boolean deleteEmplSkill(EmployeeSkills empSkillObj, int progressbar , String lastUpdate);

	public boolean addEmplDomain(EmployeeDomainMainDto empSkillObj, int progressbar, String lastUpdate);

	public boolean deleteEmplDomain(EmployeeDomainBean empDomainObj, int progressbar, String lastUpdate);

	public boolean saveResume(CommonsMultipartFile aFile, int progressbar, String employeeId, String lastUpdate);
	
	public boolean deleteEmployeeResume(Employee empObj, int progressbar, String lastUpdate);
	
	public List<VisaEmployeeDetails> getVisaEmpDtls(String empId);
	
	public List<CertificationTechnologies> getCertTechnologies();
	
	public List<CertificationNames> getCertNames(int cert_tech_id);
	
	public List<CertificationInstitutes> getCertInstitutes(int cert_tech_id);
	
	public boolean saveUpdateEmpCert(EmployeeCertificationMap empCertbj);
	
	public boolean deleteEmpCert(EmployeeCertificationMap empCertbj);
	
	public List<ProjectEmployeeMap> getProjectsByOId(String empId);
	
	public boolean saveUpdateEmpProj(EmployeeProjectsBean empCertbj);
	
	public boolean deleteEmpProject(EmployeeProjectsBean empCertbj);
	
	public boolean updateBudetails(EmployeeDto empBean);
	
	public boolean addEmplVisa(VisaEmployeeDetails empvisaObj);

	public boolean deleteEmpVisa(VisaEmployeeDetails empVisaObj);
	
	public boolean updatePassportDtlsByEmpl(CommonsMultipartFile aFile, String employeeId, String passport_no,
			String passport_Valid_From, String passport_Valid_To , String passport_Issue_Date);
	
	public List<EmployeeDomainView> getDomainDtlsBYId(String empId) ;
	
	public boolean deleteEmployeeImage(Employee empObj, int progressbar, String lastUpdate); 
	
	public List<EmployeeProfileReport> getEmployeeProfileReport(String empId);
	//public EmployeeBean getPassportDetails(String employeeId); 
	
	/** RMG CODE **/

	public List<AtsAllocStatusBean> getAllocStatus();

	public List<PracticesAllocationBean> getPracticeAllocStatus();

	public List<BusinessUnitAllocBean> getBuAllocStatus();

	
	/** Master services  **/
	
	public List<DesignationMaster> getDesinationList(); 
	
	public List<LocationMaster> getLocationList(); 

	public List<BuMaster> getBusinessUnitList();
	
	public List<DuMaster> getDeliveryUnitList();
	
	


}
