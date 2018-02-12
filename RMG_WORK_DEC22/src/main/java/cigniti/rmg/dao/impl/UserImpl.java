package cigniti.rmg.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cigniti.rmg.dao.UserDAO;
import cigniti.rmg.entities.AtsAllocStatusBean;
import cigniti.rmg.entities.BuMaster;
import cigniti.rmg.entities.BusinessUnitAllocBean;
import cigniti.rmg.entities.CertificationInstitutes;
import cigniti.rmg.entities.CertificationNames;
import cigniti.rmg.entities.CertificationTechnologies;
import cigniti.rmg.entities.ChildDomain;
import cigniti.rmg.entities.DesignationMaster;
import cigniti.rmg.entities.DomailDetails;
import cigniti.rmg.entities.DomainMaster;
import cigniti.rmg.entities.DuMaster;
import cigniti.rmg.entities.Employee;
import cigniti.rmg.entities.EmployeeBean;
import cigniti.rmg.entities.EmployeeCertificationMap;
import cigniti.rmg.entities.EmployeeChildDomainMap;
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
import cigniti.rmg.entities.Skills;
import cigniti.rmg.entities.SubDomain;
import cigniti.rmg.entities.ThemeEmployee;
import cigniti.rmg.entities.ThemeMaster;
import cigniti.rmg.entities.UserBean;
import cigniti.rmg.entities.VisaEmployeeDetails;
import cigniti.rmg.utils.RmgUtilities;

/**
 * @author Santosh BM 
 *
 */
@Repository
public class UserImpl implements UserDAO {
	
	final static Logger logger = Logger.getLogger(UserImpl.class);
	public static ResourceBundle queriesResourceBundle = ResourceBundle.getBundle("queries", Locale.getDefault());
	public static ResourceBundle timesheetResourceBundle = ResourceBundle.getBundle("timesheet", Locale.getDefault());

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionfactory() {
		return sessionFactory;
	}

	public void setSessionfactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public UserBean getEmpDetById(String userId) {
		List<UserBean> empDetList = new ArrayList<UserBean>(0);
		UserBean userBean = new UserBean();
		
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(queriesResourceBundle.getObject("getUserDetailsByIdSQLQuery").toString());
		query.setParameter(0, userId);
		query.addEntity(UserBean.class);
		empDetList = query.list();
		session.close();
		
		logger.info("empDetList for user id:->" + userId + " is :->" + empDetList.toString());
		if (!empDetList.isEmpty()) {
			userBean = empDetList.get(0);
		}
		return userBean;
	}
	
	/**
	 * This method will help us to save Login Audit details.
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public <T> ResourceSupport saveLoginAuditDetails(LoginAudit loginAudit) {
		ResourceSupport resource = new ResourceSupport();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(loginAudit);
			resource.setActionStatus(true);
			session.flush();
			session.clear();
			tx.commit();
			logger.error("Login Audit table Updated Successfully for user:->" + loginAudit.getUser_id()
					+ " And hostName:->" + loginAudit.getHostname());
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			tx.rollback();
			logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
		} finally {
			session.close();
		}
		return resource;
	}
	
	/**
	 * @param userId
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Date getUpdatedLastLoginDateById(String userId) {
		List<Date> updatedLastLoginDateList = new ArrayList<Date>(0);
		Date lastLoginDate = null;
		Session session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery(queriesResourceBundle.getObject("getUpdatedLastLoginDateByIDSQLQuery").toString());
		query.setParameter(0, userId);
		updatedLastLoginDateList = query.list();
		session.close();
		logger.info("lastLoginDateList for user id:->" + userId + " is :->" + updatedLastLoginDateList.toString());
		if (!updatedLastLoginDateList.isEmpty()) {
			lastLoginDate = updatedLastLoginDateList.get(0);
		}
		return lastLoginDate;
	}
	
	/**
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LoginDetailsBean> getUserLoginDetailsbyID(String userId) {

		List<LoginDetailsBean> userLoginDetailsList = new ArrayList<LoginDetailsBean>(0);
		Session session = sessionFactory.openSession();
		SQLQuery query = session
				.createSQLQuery(queriesResourceBundle.getObject("getEmpLoginDetbyUserIDSQLQuery").toString());
		query.addEntity(LoginDetailsBean.class);
		query.setParameter(0, userId);
		userLoginDetailsList = query.list();
		session.close();
		return userLoginDetailsList;
	}

	
	/**
	 * @param userId
	 * @param password
	 * @return
	 */
	@Override
	public boolean getAuthenticateUserbyLDAP(String userId, String password) {

		String ldap_username = userId + timesheetResourceBundle.getObject("LDAP_USERNAME_SUFFIX").toString();
		
		Properties props = new Properties();

		props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, timesheetResourceBundle.getObject("LDAP_JNDI").toString());
		props.put(Context.PROVIDER_URL, timesheetResourceBundle.getObject("LDAP_URL").toString());
		props.put(Context.SECURITY_PRINCIPAL, ldap_username);
		props.put(Context.SECURITY_CREDENTIALS, password);

		boolean user = false;
		NamingEnumeration<SearchResult> results = null;
		try {
			InitialDirContext context = new InitialDirContext(props);
			SearchControls ctrls = new SearchControls();
			ctrls.setReturningAttributes(new String[] { "givenName", "sn", "memberOf" });
			ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			results = context.search("OU=HYD,OU=StandardUsers,OU=CignitiUsers,DC=cignitiglobal,DC=com",
					"(& (userPrincipalName=" + ldap_username + ")(objectClass=user))", ctrls);
			System.out.println(userId + " is a valid user");
		} catch (Exception e) {

			System.out.println(userId + " not a valid user");
			user = false;
		}

		if (results != null)
			if (results.nextElement() != null)
				user = true;

		return user;
	}
	
	/**
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<UserBean> getUserDetailsList() {

		List<UserBean> userDetailsList = new ArrayList<UserBean>(0);
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(queriesResourceBundle.getObject("getUserDetailsSQLQuery").toString());
		query.addEntity(UserBean.class);
		userDetailsList = query.list();
		session.close();

		return userDetailsList;
	}
	
	
	/**
	 * @param userId
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public EmployeeDetails getEmployeeDetails(String empId) {
		System.out.println("Inside  getEmployeeDetails implementation ");
		List<EmployeeDetails> personalDetBeanList = new ArrayList<EmployeeDetails>(0);
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(EmployeeDetails.class);
		criteria.add(Restrictions.eq("emp_id", empId));
		personalDetBeanList = criteria.list();
	
		EmployeeDetails employeeDetails = personalDetBeanList.get(0);
		session.close();
		return employeeDetails;
	}

	/**
	 * @param userId
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public SkillAndDomainCertificationDtls getSkillDomainCertfn(String empId) {
	//List<SkillAndDomainCertificationDtls> skllDtsList = new ArrayList<SkillAndDomainCertificationDtls>();
	List<Skills> skill_List = new ArrayList<Skills>();  
	List<DomailDetails> domain_List = new ArrayList<DomailDetails>();  
	List<EmployeeSkills> certification_List = new ArrayList<EmployeeSkills>();  
	
	SkillAndDomainCertificationDtls skillDtls = new SkillAndDomainCertificationDtls() ;
		Session session = sessionFactory.openSession();
		
		Criteria criteria = session.createCriteria(Skills.class);
		criteria.add(Restrictions.eq("employeeId", empId));
		skill_List = criteria.list();
		System.out.println("skill_List --------------"+skill_List.size());
		
		/*Criteria criteria_domain = session.createCriteria(DomailDetails.class);
		criteria_domain.add(Restrictions.eq("employeeId", empId));
		criteria_domain.addOrder(Order.desc("rowid"));
		domain_List = criteria_domain.list();
		System.out.println("domain_List --------------"+domain_List.size());*/
		
		Criteria criteria_certficate = session.createCriteria(EmployeeCertificationMap.class);
		criteria_certficate.add(Restrictions.eq("empId", empId));
		criteria_certficate.addOrder(Order.desc("rowid"));
		certification_List = criteria_certficate.list();
		System.out.println("certification_List --------------"+certification_List.size());
		
	
		skillDtls.setSkill_List(skill_List);
	//	skillDtls.setDomain_List(domain_List);	
		skillDtls.setCertification_List(certification_List);
		
	/*	if(skllDtsList.isEmpty())
			skillDtls = skllDtsList.get(0);
		*/
		session.close();
		return skillDtls;
	}
	
	
	/**
	 * @param userId
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SkillEmbed> getSkillCategoryAndSkills() {
		List<SkillEmbed> SkillLookupbean = new ArrayList<SkillEmbed>();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(SkillEmbed.class);
	//	criteria.add(Restrictions.eq("emp_id", empId));
		SkillLookupbean  = criteria.list();
	
	//	SkillLookUp skillDtls = SkillLookupbean.get(0);
		session.close();
		return SkillLookupbean;
	}



	/** GET SKILL CATEGORY AND SKILLS  
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public 	 List<SkillCategoryMaster>  getSkillCategoryDetails() {
		System.out.println(" inside  getSkillDetails ");
		
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();
		List<SkillCategoryMaster> skillCategoryBeanList = new ArrayList<SkillCategoryMaster>();
		Session session = sessionFactory.openSession();
			try{

		
				Criteria criteria = session.createCriteria(SkillCategoryMaster.class);
				// criteria.add(Restrictions.eq("emp_id", empId));
				skillCategoryBeanList = criteria.list();
				
				System.out.println("skillCategoryBeanList size "+skillCategoryBeanList.size());
			//	DomainLookUp domainDtls  = domainBeanList.get(0);
			
			
			
			}catch(Exception e){
				resource.setActionStatus(false);
				resource.setErrorMessage(e.getMessage());
				e.printStackTrace();
				logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
			}finally{
				session.close();
			}
			return skillCategoryBeanList;
		}
	
	
	
	/** GET Domains by Employee ID  
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public 	 List<EmployeeDomainMainDto>  getEmployeeDomains() {
		System.out.println(" inside  getEmployeeDomains ");
		
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();
		List<EmployeeDomainMainDto> skillCategoryBeanList = new ArrayList<EmployeeDomainMainDto>();
		Session session = sessionFactory.openSession();
			try{

		
				Criteria criteria = session.createCriteria(SkillCategoryMaster.class);
				// criteria.add(Restrictions.eq("emp_id", empId));
				skillCategoryBeanList = criteria.list();
				
				System.out.println("skillCategoryBeanList size "+skillCategoryBeanList.size());
			//	DomainLookUp domainDtls  = domainBeanList.get(0);
			
			
			
			}catch(Exception e){
				resource.setActionStatus(false);
				resource.setErrorMessage(e.getMessage());
				e.printStackTrace();
				logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
			}finally{
				session.close();
			}
			return skillCategoryBeanList;
		}
	
	
	/**  GET THE DOMAIN AND SUBDOMAINS 
	 * @param userId
	 */
/*	@SuppressWarnings("unchecked")
	public void getDomainAndSubDomains() {
		System.out.println(" inside  getDomainAndSubDomains ");
		Session session = sessionFactory.openSession();
		Criteria criteria = 	session.createCriteria(DomainMaster.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)  ;
		
	//	Query query = session.createQuery("from SkillCategoryMaster as scm LEFT JOIN FETCH  scm.skillList sc  where scm.id = sc.skillCategoryMaster.id"); 
		List<DomainMaster> domainDtlsList = criteria.list();
		JSONObject obj = new JSONObject();
		
	   System.out.println("size domainList "+domainDtlsList.size());
		
		
		session.close();
		
		return obj.toString();
	}*/

/*	@SuppressWarnings("unchecked")
	public List<EmployeeProjectDetails> getProjectsByOId(String empId) {
		
		List<EmployeeProjectDetails> projectsBeanList = new ArrayList<EmployeeProjectDetails>();
		//Session session = sessionFactory.openSession();
		//Criteria criteria = session.createCriteria(EmployeeProjectDetails.class);
	    //criteria.add(Restrictions.eq("emp_id", empId));
		//projectsBeanList = criteria.list();
	
		
		
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery(queriesResourceBundle.getObject("getProjectsByIdSQLQuery").toString());
		query.setParameter(0, empId);
		query.addEntity(EmployeeProjectDetails.class);
		projectsBeanList = query.list();
		System.out.println(" sizw "+ projectsBeanList.size());
		
	//	EmployeeDetails personalDetBeanList = personalDetBeanList.get(0);
		session.close();
		return projectsBeanList;
	}*/

	@SuppressWarnings("unchecked")
	public List<ProjectEmployeeMap> getProjectsByOId(String empId) {
		
		List<ProjectEmployeeMap> projectsBeanList = new ArrayList<ProjectEmployeeMap>();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ProjectEmployeeMap.class);
	    criteria.add(Restrictions.eq("emp_id", empId));
		projectsBeanList = criteria.list();
	
		System.out.println(" size "+ projectsBeanList.size());
		
	//	EmployeeDetails personalDetBeanList = personalDetBeanList.get(0);
		session.close();
		return projectsBeanList;
	}
	
	/**
	 *  GET DOMAINS 
	 * @return
	 */
	@Override
	public List<DomainMaster> getDomains() {
		List<DomainMaster> domainBeanList = new ArrayList<DomainMaster>();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(DomainMaster.class);
		// criteria.add(Restrictions.eq("emp_id", empId));
		domainBeanList = criteria.list();
		
		System.out.println("domainBeanList size "+domainBeanList.size());
	//	DomainLookUp domainDtls  = domainBeanList.get(0);
		session.close();
		return domainBeanList;
	}

	
	/**
	 *  GET SUB DOMAINS  
	 * @return
	 */
	@Override
	public List<SubDomain>  getSubDomains(int id) {
		List<SubDomain> subDomainBeanList = new ArrayList<SubDomain>();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(SubDomain.class);
		 criteria.add(Restrictions.eq("domainId", id));
		 subDomainBeanList = criteria.list();
		
		System.out.println("subDomainBeanList size "+subDomainBeanList.size());
	//	DomainLookUp domainDtls  = domainBeanList.get(0);
		session.close();
		return subDomainBeanList;
	}

	
	
	/**
	 *  GET CHILD  DOMAINS  
	 * @return
	 */
	@Override
	public List<ChildDomain> getChildDomains(int domain_Id , int subDomain_Id) {
		List<ChildDomain> childBeanList = new ArrayList<ChildDomain>();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ChildDomain.class);
		 criteria.add(Restrictions.eq("domainId", domain_Id));
		 criteria.add(Restrictions.eq("subDomainId", subDomain_Id));
		childBeanList = criteria.list();
		
		System.out.println("childBeanList size "+childBeanList.size());
	//	DomainLookUp domainDtls  = domainBeanList.get(0);
		session.close();
		return childBeanList;
	}

	@Override
	public int updateContactAndExp(EmployeeDto emp) {
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();
	//	EmployeeBean empBean = new EmployeeBean(personalMailId, totalExperience, personalMailId, totalExperience, totalExperience);
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//EmployeeBean empBean = new EmployeeBean();
		EmployeeBean empBean = (EmployeeBean) session.get(EmployeeBean.class, emp.getEmployeeId());
	
		try {
		 empBean.setEmployeeId(emp.getEmployeeId());
		 empBean.setEmployeeName(emp.getEmployeeName());
		 empBean.setPersonalMailId(emp.getPersonalMailId());
		 empBean.setPhoneNo(emp.getPhoneNo());
		 empBean.setAlternatePhoneNo(emp.getAlternatePhoneNo());
		 empBean.setTotalExperience(emp.getTotalExperience());
		 empBean.setLastUpdate(RmgUtilities.StringToTimestamp(emp.getLastUpdate()));
		 // get the existing employee details to get the progress bar value 
		 EmployeeDetails empAlldtls = getEmployeeDetails(emp.getEmployeeId());
		 System.out.println("empAlldtls.getProgressbar() "+empAlldtls.getProgressbar());
		 System.out.println("emp.getProgressbar() "+emp.getProgressbar());
		 empBean.setProgressbar(empAlldtls.getProgressbar() + emp.getProgressbar());
		 session.update(empBean);
				 
			session.flush();
			session.clear();
			tx.commit();
			System.out.println("Upated successfully ");
			
	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
		int progressBarAfterUpdate = getEmployeeDetails(emp.getEmployeeId()).getProgressbar();
		// returning the updated progress bar value by again hitting the DB using getEmployeeDetails(empId) method 
	return progressBarAfterUpdate;
	}
	
	/**
	 * 
	 * @param empId
	 * @param themeColor
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public boolean updateTheme(ThemeEmployee obj) {
		
		//update theme_employe set theme_id = (select theme_id from themes_ui where theme_col = ? ) and emp_id = ?
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		ThemeEmployee empTheme = new ThemeEmployee();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try{	
		//Update the theme_employee table after getting the theme id 
				
		empTheme.setEmployeeId(obj.getEmployeeId());
		empTheme.setThemeId(obj.getThemeId());
		
	   session.saveOrUpdate(empTheme);
		 
			session.flush();
			session.clear();
			tx.commit();
			flag = true;
			System.out.println("Upated successfully ");

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}



	public List<ThemeMaster> getThemesUi() {
		List<ThemeMaster> themeslist = new ArrayList<ThemeMaster>();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ThemeMaster.class);
		
		themeslist = criteria.list();
		
		System.out.println("childBeanList size "+themeslist.size());
	//	DomainLookUp domainDtls  = domainBeanList.get(0);
		session.close();
		return themeslist;
	}

	
	/**
	 * 
	 * @return
	 */
	public boolean saveImage(CommonsMultipartFile aFile , int progressbar , String employeeId , String lastUpdate) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
	
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//String employeeId = aFile.getEmployeeId();
		
		try{	
		Employee empImage = (Employee) session.get(Employee.class, employeeId);// new Employee();
		 
			
				
		empImage.setEmployeeId(employeeId);
		empImage.setImage_name(aFile.getOriginalFilename());
		empImage.setEmployeeImage(aFile.getBytes()); 
	//	empImage.setLastUpdate(RmgUtilities.StringToTimestamp(lastUpdate));
		
		// updating the image and other details in employee image table 	
	
	    session.update(empImage);
	    
	    // Update progress bar 
	   
	     EmployeeDetails empAlldtls = getEmployeeDetails(employeeId);
		 System.out.println("empAlldtls.getProgressbar() "+empAlldtls.getProgressbar());
		 System.out.println(" value recieved  in progressbar "+progressbar);
		 
		 EmployeeBean empBean = (EmployeeBean) session.get(EmployeeBean.class, employeeId);  // new EmployeeBean(); 
		 empBean.setProgressbar(empAlldtls.getProgressbar() + progressbar );
		 empBean.setEmployeeId(employeeId);
		 empBean.setEmployeeName(empAlldtls.getEmployeeName());
		 empBean.setLastUpdate(RmgUtilities.StringToTimestamp(lastUpdate));
		
		 session.update(empBean);
				 
		session.flush();
		session.clear();
		tx.commit();
		flag= true;
		System.out.println("Upated successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	public boolean addEmplSkill(EmployeeSkills empSkillObj , int progressbar , String lastUpdate) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		EmployeeSkills empSkill = new EmployeeSkills();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	

		try{
			
	    String employeeId = empSkillObj.getEmployeeId();
	    
	        empSkill.setRowid(empSkillObj.getRowid());
		    empSkill.setEmployeeId(employeeId);
		    empSkill.setSkillCategoryId(empSkillObj.getSkillCategoryId());
		    empSkill.setSkillId(empSkillObj.getSkillId());
		    empSkill.setSkillRating(empSkillObj.getSkillRating());
		    session.saveOrUpdate(empSkill);
	  
			
	     EmployeeDetails empAlldtls = getEmployeeDetails(empSkillObj.getEmployeeId());
		 System.out.println("empAlldtls.getProgressbar() "+empAlldtls.getProgressbar());
		 System.out.println(" value recieved  in progressbar "+progressbar);
		 
		 EmployeeBean empBean = (EmployeeBean) session.get(EmployeeBean.class, employeeId); 
		 empBean.setProgressbar(empAlldtls.getProgressbar() + progressbar );
		 empBean.setEmployeeId(employeeId);
		 empBean.setEmployeeName(empAlldtls.getEmployeeName());
		 empBean.setLastUpdate(RmgUtilities.StringToTimestamp(lastUpdate));
		 
		 session.update(empBean);
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Upated or inserted successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	public boolean deleteEmplSkill(EmployeeSkills empSkillObj , int progressbar ,String lastUpdate) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		EmployeeSkills empSkill = new EmployeeSkills();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	

		try{
			
	    String employeeId = empSkillObj.getEmployeeId();

	    empSkill.setRowid(empSkillObj.getRowid());
	    empSkill.setEmployeeId(employeeId);
		session.delete(empSkill);
	  
			
	     EmployeeDetails empAlldtls = getEmployeeDetails(empSkillObj.getEmployeeId());
		 System.out.println("empAlldtls.getProgressbar() "+empAlldtls.getProgressbar());
		 System.out.println(" value recieved  in progressbar "+progressbar);
		 
		 EmployeeBean empBean = (EmployeeBean) session.get(EmployeeBean.class, employeeId); 
		 empBean.setProgressbar(empAlldtls.getProgressbar() - progressbar );
		 empBean.setEmployeeName(empAlldtls.getEmployeeName());
		 empBean.setLastUpdate(RmgUtilities.StringToTimestamp(lastUpdate));
		 empBean.setEmployeeId(employeeId);
		 session.update(empBean);
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Deleted successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	@Override
	public boolean addEmplDomain(EmployeeDomainMainDto empDomainObj , int progressbar, String lastUpdate) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		EmployeeDomainBean empDomain = new EmployeeDomainBean();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	

		try{
			
			/*Insert employee main domain bean */
	    String employeeId = empDomainObj.getEmployeeId();
	    empDomain.setRowid(empDomainObj.getRowid());
	    empDomain.setEmployeeId(employeeId);
	    empDomain.setDomainId(empDomainObj.getDomainId());
	    empDomain.setSubDomainId(empDomainObj.getSubDomainId());
	//  empDomain.setChildDomainId(empDomainObj.getChildDomainId());
	    empDomain.setDomain_experience(empDomainObj.getDomainExperience());
	    empDomain.setComments(empDomainObj.getComments());
		session.saveOrUpdate(empDomain);
	  
		System.out.println(empDomain.toString());
		
		/** delete the existing child record w.r.t employee_main_domain_id  record**/ 
		
		String hql = "delete from EmployeeChildDomainMap where empDomainMapId= :empDomainMapId";
		Query q =  (Query) session.createQuery(hql).setInteger("empDomainMapId", empDomainObj.getRowid());
		int recordstatus = q.executeUpdate();
		
	/*	EmployeeChildDomainMap persistentInstance = (EmployeeChildDomainMap) session.get(EmployeeChildDomainMap.class, empDomain.getRowid());
		if(persistentInstance != null){
			session.delete(persistentInstance);
		}	
		*/
		
		/*Insert employee child domain map */
		if(empDomainObj.getChildDomain().length > 0){
			for(int childIds :empDomainObj.getChildDomain() ){
				EmployeeChildDomainMap empChildMapBean = new EmployeeChildDomainMap();
					/** insert the new set of child domains **/
					// future : need to check how to save the recird with same rowid - i.e combine identy generator and manual to
					// achieve empChildMapBean.setRowid(empDomain.getRowid()); --generated new everytime
					empChildMapBean.setEmpDomainMapId(empDomain.getRowid());
					empChildMapBean.setEmployeeId(employeeId);
					empChildMapBean.setChildDomainId(childIds);
					session.save(empChildMapBean);
								
			}
		}
		
		
	     EmployeeDetails empAlldtls = getEmployeeDetails(employeeId);
		 System.out.println("empAlldtls.getProgressbar() "+empAlldtls.getProgressbar());
		 System.out.println(" value recieved  in progressbar "+progressbar);
		 
		 EmployeeBean empBean = (EmployeeBean) session.get(EmployeeBean.class, employeeId); 
		 empBean.setProgressbar(empAlldtls.getProgressbar() + progressbar );
		 empBean.setEmployeeId(employeeId);
		 empBean.setEmployeeName(empAlldtls.getEmployeeName());
		 empBean.setLastUpdate(RmgUtilities.StringToTimestamp(lastUpdate));
		 session.update(empBean);
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Upated or inserted successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}
	
	
/**
 *  Delete employee domain details 
 */
	@Override
	public boolean deleteEmplDomain(EmployeeDomainBean empDomainObj , int progressbar , String lastUpdate) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		EmployeeDomainBean empDomain = new EmployeeDomainBean();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	

		try{
			
	    String employeeId = empDomainObj.getEmployeeId();

	    empDomain.setRowid(empDomainObj.getRowid());
		session.delete(empDomain);
		
		
	    //delete child emp domain dtails based on main domain table 
		
		String hql = "delete from EmployeeChildDomainMap where empDomainMapId= :empDomainMapId";
		Query q =  (Query) session.createQuery(hql).setInteger("empDomainMapId", empDomainObj.getRowid());
		int recordstatus = q.executeUpdate();
		
		// Progressbar update 		
	     EmployeeDetails empAlldtls = getEmployeeDetails(employeeId);
		 System.out.println("empAlldtls.getProgressbar() "+empAlldtls.getProgressbar());
		 System.out.println(" value recieved  in progressbar "+progressbar);
		 
		 EmployeeBean empBean = (EmployeeBean) session.get(EmployeeBean.class, employeeId); 
		 empBean.setProgressbar(empAlldtls.getProgressbar() - progressbar );
		 empBean.setEmployeeId(employeeId);
		 empBean.setEmployeeName(empAlldtls.getEmployeeName());
		 empBean.setLastUpdate(RmgUtilities.StringToTimestamp(lastUpdate));
		 session.update(empBean);
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Deleted successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to delete the domain details :->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	@Override
	public boolean saveResume(CommonsMultipartFile aFile, int progressbar,String employeeId, String lastUpdate) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
	//	EmployeeResume empResume = new EmployeeResume();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
				
		try{
			
			Employee empResume = (Employee) session.get(Employee.class, employeeId);// new Employee();
		
			empResume.setEmployeeId(employeeId);
			empResume.setResume_filename(aFile.getOriginalFilename());
			empResume.setResume(aFile.getBytes()); 
			
			// updating the Resume and other details in employee image table 	
		
		    session.merge(empResume);
	    
	    // Update progress bar 
	   
	     EmployeeDetails empAlldtls = getEmployeeDetails(employeeId);
		 System.out.println("empAlldtls.getProgressbar() "+empAlldtls.getProgressbar());
		 System.out.println(" value recieved  in progressbar "+progressbar);
		 
		 EmployeeBean empBean = (EmployeeBean) session.get(EmployeeBean.class, employeeId); 
		 empBean.setProgressbar(empAlldtls.getProgressbar() + progressbar );
		 empBean.setEmployeeId(employeeId);
		 empBean.setEmployeeName(empAlldtls.getEmployeeName());
		 empBean.setLastUpdate(RmgUtilities.StringToTimestamp(lastUpdate));
		 session.update(empBean);
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true;
		System.out.println("Upated successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	@SuppressWarnings("unchecked")
	public List<AtsAllocStatusBean> getAllocStatus() {
		List<AtsAllocStatusBean> allocBeanList = new ArrayList<AtsAllocStatusBean>();
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		Session session = sessionFactory.openSession();
		try{
		
		
			Criteria criteria = session.createCriteria(AtsAllocStatusBean.class);
		//	criteria.add(Restrictions.eq("emp_id", empId));
			allocBeanList = criteria.list();
			
			
			
		
		/*	EmployeeDetails employeeDetails = personalDetBeanList.get(0);
			session.close();
			return employeeDetails;*/
		
	//	SQLQuery query = session.createSQLQuery(queriesResourceBundle.getObject("List").toString());
		//query.addEntity(AllocStatusBean.class);
	
	//	allocBean  = query.list();
	
		System.out.println("Fetched  successfully "+allocBeanList.get(0));
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		e.printStackTrace();
	
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
		return allocBeanList;
	
	}
	
	

	@SuppressWarnings("unchecked")
	public List<PracticesAllocationBean> getPracticeAllocStatus() {
		List<PracticesAllocationBean> allocBeanList = new ArrayList<PracticesAllocationBean>();
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		Session session = sessionFactory.openSession();
		try{
		
		
			Criteria criteria = session.createCriteria(PracticesAllocationBean.class);
		
			allocBeanList = criteria.list();
		
	
		System.out.println("Fetched  successfully "+allocBeanList.get(0));
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		e.printStackTrace();
	
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
		return allocBeanList;
	
	}

	public List<BusinessUnitAllocBean> getBuAllocStatus() {
		List<BusinessUnitAllocBean> allocBeanList = new ArrayList<BusinessUnitAllocBean>();
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		Session session = sessionFactory.openSession();
		try{
		
		
			Criteria criteria = session.createCriteria(BusinessUnitAllocBean.class);
		
			allocBeanList = criteria.list();

		System.out.println("Fetched  successfully "+allocBeanList.get(0));
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		e.printStackTrace();
	
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
		return allocBeanList;
	
	}

	@SuppressWarnings("unchecked")
	public List<VisaEmployeeDetails> getVisaEmpDtls(String empId) {
		List<VisaEmployeeDetails> visaList = new ArrayList<VisaEmployeeDetails>();
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		Session session = sessionFactory.openSession();
		try{
		
		
			Criteria criteria = session.createCriteria(VisaEmployeeDetails.class);
			criteria.add(Restrictions.eq("employeeId", empId));
			criteria.addOrder(Order.desc("rowid"));
			visaList = criteria.list();

		System.out.println("Fetched  successfully "+visaList.size());
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		e.printStackTrace();
	
		logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
	} finally {
		session.close();
	}
		return visaList;
	
	}

	@SuppressWarnings("unchecked")
	public List<CertificationTechnologies> getCertTechnologies() {
		List<CertificationTechnologies> certTechList = new ArrayList<CertificationTechnologies>();
		ResourceSupport resource = new ResourceSupport();	
		Session session = sessionFactory.openSession();
		try{
		
		Criteria criteria = session.createCriteria(CertificationTechnologies.class);
		certTechList  = criteria.list();
	
	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		e.printStackTrace();
	
		logger.error("Failed to fetch the certification technology details :->" + e.getMessage());
	} finally {
		session.close();
	}
  return certTechList;
	}

	public List<CertificationNames> getCertNames(int cert_tech_id) {
		List<CertificationNames> certNames_List = new ArrayList<CertificationNames>();
		ResourceSupport resource = new ResourceSupport();	
		Session session = sessionFactory.openSession();
		try{
		
		Criteria criteria = session.createCriteria(CertificationNames.class);
		criteria.add(Restrictions.eq("cert_tech_id", cert_tech_id));
		
		certNames_List  = criteria.list();
	
	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		e.printStackTrace();
	
		logger.error("Failed to fetch the certification Names details :->" + e.getMessage());
	} finally {
		session.close();
	}
  return certNames_List;
	}

	@Override
	public List<CertificationInstitutes> getCertInstitutes(int cert_tech_id) {
		List<CertificationInstitutes> certInstitute_List = new ArrayList<CertificationInstitutes>();
		ResourceSupport resource = new ResourceSupport();	
		Session session = sessionFactory.openSession();
		try{
		
		Criteria criteria = session.createCriteria(CertificationInstitutes.class);
		criteria.add(Restrictions.eq("cert_tech_id", cert_tech_id));
		certInstitute_List  = criteria.list();
	
	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		e.printStackTrace();
	
		logger.error("Failed to fetch the CertificationInstitutes  details :->" + e.getMessage());
	} finally {
		session.close();
	}
  return certInstitute_List;
	}

	public boolean saveUpdateEmpCert(EmployeeCertificationMap empCertbj) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		EmployeeCertificationMap empCert = new EmployeeCertificationMap();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	

		try{
			
	    String employeeId = empCertbj.getEmpId();

	    empCert.setRowid(empCertbj.getRowid());
	    empCert.setEmpId(employeeId);
	    empCert.setTechnology(empCertbj.getTechnology());
	    empCert.setCertification(empCertbj.getCertification());
	  //  empCert.setLevels(empCertbj.getLevels());
	    empCert.setBoardInstitute(empCertbj.getBoardInstitute());
	    empCert.setValidFrom(empCertbj.getValidFrom());
	    empCert.setValidTo(empCertbj.getValidTo());
	    empCert.setComments(empCertbj.getComments());
		session.saveOrUpdate(empCert);
	  
		/*	
	     EmployeeDetails empAlldtls = getEmployeeDetails(employeeId);
		 System.out.println("empAlldtls.getProgressbar() "+empAlldtls.getProgressbar());
		 System.out.println(" value recieved  in progressbar "+progressbar);
		 
		 EmployeeBean empBean = new EmployeeBean(); 
		 empBean.setProgressbar(empAlldtls.getProgressbar() + progressbar );
		 empBean.setEmployeeId(employeeId);
		 session.update(empBean);*/
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Upated or inserted Cetification details  successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to insert Cetification details:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	public boolean deleteEmpCert(EmployeeCertificationMap empCertbj) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		EmployeeCertificationMap empCert = new EmployeeCertificationMap();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	

		try{
			
	 

	    empCert.setRowid(empCertbj.getRowid());
	
		session.delete(empCert);
	  
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Deleted Cetification details  successfully " );
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to delete Cetification details:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	@Override
	public boolean saveUpdateEmpProj(EmployeeProjectsBean empProjObj) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		EmployeeProjectsBean empBean = new EmployeeProjectsBean();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	

		try{
			
	    String employeeId = empProjObj.getEmp_id();

	    empBean.setRowid(empProjObj.getRowid());
	    empBean.setEmp_id(employeeId);
	    empBean.setProject_name(empProjObj.getProject_name());
	    empBean.setAccount_name(empProjObj.getAccount_name());
	    empBean.setAllocation_start_date(empProjObj.getAllocation_start_date());
	    empBean.setAllocation_end_date(empProjObj.getAllocation_end_date());
	    empBean.setLast_working_day(empProjObj.getLast_working_day());
	    empBean.setAllocation_status(empProjObj.getAllocation_status());
	 
		session.saveOrUpdate(empBean);
	  				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Upated or inserted Project details  successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to insert Project details:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	public boolean deleteEmpProject(EmployeeProjectsBean empProjObject) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		EmployeeProjectsBean empProjBean = new EmployeeProjectsBean();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	

		try{
		empProjBean.setRowid(empProjObject.getRowid());
		session.delete(empProjBean);
	  
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Deleted Project details  successfully " );
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to delete Project details:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	@SuppressWarnings("unchecked")
	public List<DesignationMaster> getDesinationList() {
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		
		List<DesignationMaster> DesgnationList = new ArrayList<DesignationMaster>();
		Session session = sessionFactory.openSession();
		try{
			Criteria criteria = session.createCriteria(DesignationMaster.class);
			DesgnationList  = criteria.list();
					
		}catch(Exception e ){
			
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
		
			e.printStackTrace();
			
			logger.error("Failed to fetch desination details:->" + e.getMessage());
		}finally {
			session.close();
		}
		return DesgnationList;
		
	
	}

	@Override
	public List<LocationMaster> getLocationList() {
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		
		List<LocationMaster> LocationList = new ArrayList<LocationMaster>();
		Session session = sessionFactory.openSession();
		try{
			Criteria criteria = session.createCriteria(LocationMaster.class);
			LocationList  = criteria.list();
					
		}catch(Exception e ){
			
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
		
			e.printStackTrace();
			
			logger.error("Failed to fetch Location details:->" + e.getMessage());
		}finally {
			session.close();
		}
		return LocationList;
		
	
	}

	@Override
	public List<BuMaster> getBusinessUnitList() {
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		
		List<BuMaster> BUList = new ArrayList<BuMaster>();
		Session session = sessionFactory.openSession();
		try{
			Criteria criteria = session.createCriteria(BuMaster.class);
			BUList  = criteria.list();
					
		}catch(Exception e ){
			
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
		
			e.printStackTrace();
			
			logger.error("Failed to fetch Business Unit details:->" + e.getMessage());
		}finally {
			session.close();
		}
		return BUList;
		
	
	}

	@Override
	public List<DuMaster> getDeliveryUnitList() {
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		
		List<DuMaster> DUList = new ArrayList<DuMaster>();
		Session session = sessionFactory.openSession();
		try{
			Criteria criteria = session.createCriteria(DuMaster.class);
			DUList  = criteria.list();
					
		}catch(Exception e ){
			
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
		
			e.printStackTrace();
			
			logger.error("Failed to fetch Delivery Unit details:->" + e.getMessage());
		}finally {
			session.close();
		}
		return DUList;
		
	
	}

	@Override
	public boolean updateEmployeeDetails(EmployeeBean empBean) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
	//	EmployeeResume empResume = new EmployeeResume();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
				
		try{
			
			EmployeeBean empObj = (EmployeeBean) session.get(EmployeeBean.class, empBean.getEmployeeId());// new Employee();
		
			empObj.setEmployeeId(empBean.getEmployeeId());
			empObj.setEmployeeName(empBean.getEmployeeName());
			empObj.setDesignation(empBean.getDesignation());
			empObj.setDoj(empBean.getDoj());
			empObj.setJoiningLocation(empBean.getJoiningLocation());
			empObj.setCurrentLocation(empBean.getCurrentLocation());
			empObj.setEmplType(empBean.getEmplType());
			empObj.setReportingManager(empBean.getReportingManager());
			empObj.setRmgSpoc(empBean.getRmgSpoc());
			
			// updating the Resume and other details in employee image table 	
		
		session.update(empObj);
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true;
		System.out.println("Upated successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to update employee data for employee :->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	public boolean updateBudetails(EmployeeDto empBean) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
	//	EmployeeResume empResume = new EmployeeResume();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
				
		try{
			
			EmployeeBean empObj = (EmployeeBean) session.get(EmployeeBean.class, empBean.getEmployeeId());// new Employee();
		
			empObj.setEmployeeId(empBean.getEmployeeId());
			empObj.setEmployeeName(empBean.getEmployeeName());
			empObj.setBu_id(empBean.getBu_id());
			empObj.setDu_id(empBean.getDu_id());
			empObj.setProjectManger(empBean.getProjectManger());
			empObj.setReportingManager(empBean.getReportingManager()); // reporting manager is set as Account manager 
			empObj.setBuHead(empBean.getBuHead());
			empObj.setHrSpoc(empBean.getHrSpoc());
	//		empObj.setLastUpdate(RmgUtilities.StringToTimestamp(empBean.getLastUpdate()));
		
			
			
		
		session.update(empObj);
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true;
		System.out.println("Upated successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to update employee BU details for employee Id:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	public List<SkillMaster> getSkillsByCateory(int skill_category_id) {
		List<SkillMaster> skillListBycategory = new ArrayList<SkillMaster>();
		Session session = sessionFactory.openSession();
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		try{
		Criteria criteria = session.createCriteria(SkillMaster.class);
		 criteria.add(Restrictions.eq("skill_category_id", skill_category_id));
		 skillListBycategory = criteria.list();
		
		System.out.println("skillListBycategory size "+skillListBycategory.size());
	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		
		e.printStackTrace();
	
		logger.error("Failed to get skillListBycategory category  Id:->" + e.getMessage());
	} finally {
		session.close();
	}
	return skillListBycategory;
	}

	public boolean addEmplVisa(VisaEmployeeDetails empvisaObj) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		VisaEmployeeDetails empBean = new VisaEmployeeDetails();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	

		try{
			
	    String employeeId = empvisaObj.getEmployeeId();

	    empBean.setRowid(empvisaObj.getRowid());
	    empBean.setEmployeeId(employeeId);
	    empBean.setCountry(empvisaObj.getCountry());
	    empBean.setVisa(empvisaObj.getVisa());
	    empBean.setVisa_type(empvisaObj.getVisa_type());
	    empBean.setStatus(empvisaObj.getStatus());
	    empBean.setValidFrom(empvisaObj.getValidFrom());
	    empBean.setValidTo(empvisaObj.getValidTo());
	  
	 
		session.saveOrUpdate(empBean);
	  				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Upated or inserted Visa details  successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to insert visa details:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	public boolean deleteEmpVisa(VisaEmployeeDetails empVisaObj) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		VisaEmployeeDetails empVisa = new VisaEmployeeDetails();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
			try{
	
		empVisa.setRowid(empVisaObj.getRowid());
	
		session.delete(empVisa);
	  
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Deleted visa details  successfully " );
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to delete Cetification details:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	@Override
	public boolean updatePassportDtlsByEmpl(CommonsMultipartFile aFile, String employeeId, String passport_no,
			String passport_Valid_From, String passport_Valid_To , String passport_Issue_Date) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
				
		try{
			
			EmployeeBean emp = (EmployeeBean) session.get(EmployeeBean.class, employeeId);// new Employee();
		
			emp.setPassport_no(passport_no);
			emp.setPassport_Valid_From(passport_Valid_From);
			emp.setPassport_Valid_To(passport_Valid_To); 
			if(aFile != null){
				emp.setPassposrt_Scan(aFile.getBytes()); 
				emp.setPassposrt_Scan(aFile.getBytes());
			}		
			emp.setPassport_Issue_Date(passport_Issue_Date);
					
		    session.update(emp);
	 
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true;
		System.out.println("Upated successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to insert data for passport details:->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeDomainView> getDomainDtlsBYId(String empId) {
		List<EmployeeDomainView> domainList = new ArrayList<EmployeeDomainView>();
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		Session session = sessionFactory.openSession();
		try{
		
		
			Criteria criteria = session.createCriteria(EmployeeDomainView.class);
			criteria.add(Restrictions.eq("employeeId", empId));
			criteria.addOrder(Order.desc("rowid"));
			domainList = criteria.list();

		System.out.println("Fetched  successfully : domainList size =  "+domainList.size());
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		e.printStackTrace();
	
		logger.error("Failed to fetch domainList by emp ID :->" + e.getMessage());
	} finally {
		session.close();
	}
		return domainList;
	
	}

	public boolean deleteEmployeeResume(Employee empObj, int progressbar, String lastUpdate) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	
		String employeeId = empObj.getEmployeeId();
		Employee empBean = (Employee) session.get(Employee.class, employeeId); 
		try{
	    empBean.setEmployeeId(employeeId);
	    empBean.setResume_filename(null);
	    empBean.setResume(null);
	//  empDomain.setRowid(empObj.getRowid()); 
		session.update(empBean);
		
		
		// Progressbar update 		
	     EmployeeDetails empAlldtls = getEmployeeDetails(employeeId);
		 System.out.println("empAlldtls.getProgressbar() "+empAlldtls.getProgressbar());
		 System.out.println(" value recieved  in progressbar "+progressbar);
		 
		 EmployeeBean empBean_second = (EmployeeBean) session.get(EmployeeBean.class, employeeId); 
		 empBean_second.setProgressbar(empAlldtls.getProgressbar() - progressbar );
		 empBean_second.setEmployeeId(employeeId);
		 empBean_second.setEmployeeName(empAlldtls.getEmployeeName());
		 empBean_second.setLastUpdate(RmgUtilities.StringToTimestamp(lastUpdate));
		 session.update(empBean_second);
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Deleted successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to delete the resume details :->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}

	
	public boolean deleteEmployeeImage(Employee empObj, int progressbar, String lastUpdate) {		
		
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		boolean flag = false;	
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	
		String employeeId = empObj.getEmployeeId();
		Employee empBean = (Employee) session.get(Employee.class, employeeId); 
		try{
	    empBean.setEmployeeId(employeeId);
	    empBean.setImage_name(null);
	    empBean.setEmployeeImage(null);
	//  empDomain.setRowid(empObj.getRowid()); 
		session.update(empBean);
		
		
		// Progressbar update 		
	     EmployeeDetails empAlldtls = getEmployeeDetails(employeeId);
		 System.out.println("empAlldtls.getProgressbar() "+empAlldtls.getProgressbar());
		 System.out.println(" value recieved  in progressbar "+progressbar);
		 
		 EmployeeBean empBean_second = (EmployeeBean) session.get(EmployeeBean.class, employeeId); 
		 empBean_second.setProgressbar(empAlldtls.getProgressbar() - progressbar );
		 empBean_second.setEmployeeId(employeeId);
		 empBean_second.setEmployeeName(empAlldtls.getEmployeeName());
		 empBean_second.setLastUpdate(RmgUtilities.StringToTimestamp(lastUpdate));
		 session.update(empBean_second);
				 
		session.flush();
		session.clear();
		tx.commit();
		flag = true ; 
		System.out.println("Deleted successfully ");
	

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
		flag = false ; 
		logger.error("Failed to delete the Image details :->" + e.getMessage());
	} finally {
		session.close();
	}
	return flag;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<EmployeeProfileReport> getEmployeeProfileReport(String empId) {		
	
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();	
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		List<EmployeeProfileReport> empProfileList = new ArrayList<>();
		
		List<EmployeeDetails>  emplList = null;
		try{
		
			
			Criteria criteria = session.createCriteria(EmployeeDetails.class);
			emplList = criteria.list();
			System.out.println("emplList "+emplList.size());
		
			emplList.forEach(obj -> {
				
				EmployeeDetails empBean = (EmployeeDetails) session.get(EmployeeDetails.class, obj.getEmp_id()); 
				
				EmployeeProfileReport empProfile = new EmployeeProfileReport();
				empProfile.setEmpBean(empBean);
			//	projects = getProjectsByOId(empId);
				empProfile.setProjects(getProjectsByOId(obj.getEmp_id()));
				empProfile.setSkillCert(getSkillDomainCertfn(obj.getEmp_id()));
				empProfile.setDomainList(getDomainDtlsBYId(obj.getEmp_id()));
				empProfile.setVisaList(getVisaEmpDtls(obj.getEmp_id())); 
				empProfileList.add(empProfile); 
				
			});
			
		 
		session.flush();
		session.clear();
		tx.commit();
	
		System.out.println("Fetched successfully ");

	} catch (Exception e) {
		resource.setActionStatus(false);
		resource.setErrorMessage(e.getMessage());
		tx.rollback();
		e.printStackTrace();
	
		logger.error("Failed to get  the employee profile  details :->" + e.getMessage());
	} finally {
		session.close();
	}
	return empProfileList;
	}

	
	/*
	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public EmployeeBean getPassportDetails(String employeeId) {
		System.out.println("Inside  getPassportDetails ");
		
		@SuppressWarnings("rawtypes")
		ResourceSupport resource = new ResourceSupport();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		EmployeeBean empPassportBean=  (EmployeeBean) session.get(EmployeeBean.class, employeeId);
			try{				
				String hql = "select passport_no, passport_Valid_From, passport_Valid_To, passport_Issue_Date, passposrt_Scan "
						+ " from EmployeeBean where employeeId= :employeeId";
				Query q =   (Query) session.createQuery(hql).setString("employeeId", employeeId)
						    .setResultTransformer(new AliasToBeanResultTransformer(EmployeeBean.class));
	
				SQLQuery query = session.createSQLQuery(queriesResourceBundle.getObject("getPassportDetailsByIdSQLQuery").toString());
				query.addEntity(EmployeeBean.class);
				query.setParameter(0, employeeId);
				empPassportBean = (EmployeeBean) query.uniqueResult();
						
				
				Criteria crit = session.createCriteria(EmployeeBean.class);
				crit.add(Restrictions.eq("employeeId",employeeId));
				ProjectionList projList = Projections.projectionList();
				projList.add(Projections.property("passport_no"));
				projList.add(Projections.property("passport_Valid_From"));
				projList.add(Projections.property("passport_Valid_To"));
				projList.add(Projections.property("passport_Issue_Date"));
				projList.add(Projections.property("passposrt_Scan"));
				crit.setProjection(projList);
				
				List l=crit.list();
				 
				 Iterator it=l.iterator();
				 
				 while(it.hasNext())
				 {
				 Object ob[] = (Object[])it.next();
				 System.out.println(ob[0]+"--------"+ob[1]);
				 empPassportBean.setPassport_no((ob[0].toString()));
				 empPassportBean.setPassport_no((ob[1].toString()));
				 empPassportBean.setPassport_no((ob[2].toString()));
				 empPassportBean.setPassport_no((ob[3].toString()));
				 empPassportBean.setPassport_no((ob[4].toString()));
				 				 
				 }
				 
				 System.out.println(empPassportBean.toString());
				
				 Object result = crit.uniqueResult();
				 System.out.println(result.toString());
		            if (result != null) {
		            	empPassportBean= (EmployeeBean) result;
		            }
		            
		         System.out.println(empPassportBean.toString());   
			
				tx.commit();
								
			}catch(Exception e){
				resource.setActionStatus(false);
				resource.setErrorMessage(e.getMessage());
				tx.rollback();
				e.printStackTrace();
				logger.error("Failed to insert data for Login Audit table:->" + e.getMessage());
			}finally{
				session.close();
			}
			return empPassportBean;
		}
*/



	
}
	
	

