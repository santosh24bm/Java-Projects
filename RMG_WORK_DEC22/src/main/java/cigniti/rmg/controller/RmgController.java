package cigniti.rmg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cigniti.rmg.constants.RmgConstants;
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
import cigniti.rmg.entities.ResourceSupport;
import cigniti.rmg.entities.SkillAndDomainCertificationDtls;
import cigniti.rmg.entities.SkillCategoryMaster;
import cigniti.rmg.entities.SkillMaster;
import cigniti.rmg.entities.SubDomain;
import cigniti.rmg.entities.ThemeEmployee;
import cigniti.rmg.entities.ThemeMaster;
import cigniti.rmg.entities.User;
import cigniti.rmg.entities.VisaEmployeeDetails;
import cigniti.rmg.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Santosh BM
 *
 */
@Api(value = "RmgController", description = "Services avaliable for RMG sheet controller", basePath = "/mytr")
@RequestMapping(value = "/mytr", produces = "application/json")
@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RmgController {

	@Autowired
	UserService userService;

	final static Logger logger = Logger.getLogger(RmgController.class);

	/**
	 * This method will help to validate user based on his name and password.
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get LDAP Authenticate")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Authenticate Users by LDAP", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_LDAP_AUTHENTICATION, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<LoginDetailsBean> getAuthenticatedUserDetails(@RequestBody User user,
			HttpServletRequest request) {
		System.out.println("user " + user);
		
		
		HttpHeaders header = corsOrginHeaders();
		LoginDetailsBean ldBean = null;
		if (user == null) {
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		}

		ldBean = userService.getAuthenticationValidation(user, request, header, ldBean);
		return new ResponseEntity(ldBean, HttpStatus.OK);

	}

	/**
	 * This method will help to validate user based on his name and password.
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get employee details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get employee details", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_EMPLOYEE, method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<EmployeeDetails> getEmployeeDetails(@RequestParam("empId") String empId) {
		System.out.println("empId " + empId);
		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			EmployeeDetails employeeDetailsBean = userService.getEmployeeDetails(empId);

			resource.setActionStatus(true);
			resource.setDetails(employeeDetailsBean);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/**
	 * This method will help to validate user based on his name and password.
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Update employee details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Update employee details", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.UPDATE_EMPLOYEE, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity updateEmployeeDetails(@RequestBody EmployeeBean empBean) {
	//	System.out.println("empId " + empId);
		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean flag = userService.updateEmployeeDetails(empBean);

			resource.setActionStatus(true);
			resource.setDetails(flag);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This method will give Employee skill domain certification info based on
	 * his user id.
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Employee Skill Domain Certification details EmployeeId")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Emp details based on given Id", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No Employee has found with given EmpID", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_SKILL_DOMAIN_CERTFN_BY_EMPID, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getUserByID(@RequestParam("empId") String empId) {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		try {
			SkillAndDomainCertificationDtls skillDtlsBean = userService.getSkillDomainCertfn(empId);
			resource.setActionStatus(true);
			resource.setDetails(skillDtlsBean);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method will give Skill Category info .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Skill lOok up : Skill category ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "GetSkill category and Skills", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No Skill category and Skills", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_SKILL_CATEGORY, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getSkillCategory() {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<SkillCategoryMaster> skillList;
		try {
			skillList = userService.getSkillCategoryDetails();
			resource.setDetails(skillList);
			resource.setActionStatus(true);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	/**
	 * This method will give Skill details based on skill Category Id
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Skill lOok up : Skills By category ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "GetSkills by  category ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No Skills for the category ", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_SKILLS_BY_CATEGORY, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getSkillsByCateory(@RequestParam("skill_catg_id") int skill_category_id) {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<SkillMaster> skillList;
		try {
			skillList = userService.getSkillsByCateory(skill_category_id);
			resource.setDetails(skillList);
			resource.setActionStatus(true);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method will give SUpload image and store in DB based on EMployee ID
	 * .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Upload image and store in DB ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Update the image DB successfully", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "Not updated Image in Database ", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.UPLOAD_IMAGE, method = RequestMethod.POST)
	public ResponseEntity imageUpload(@RequestParam("empId") String employeeId,	@RequestParam("progressbar") int progressbar,
			                          @RequestParam("file") CommonsMultipartFile[] fileUpload , @RequestParam("lastUpdate") String lastUpdate)
					throws Exception {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		boolean status = false;
		try {
			if (fileUpload != null) {
				CommonsMultipartFile aFile = fileUpload[0];

			/*	System.out.println("Saving file: " + aFile.getOriginalFilename());

				Employee uploadFile = 
						// new Employee();
				uploadFile.setEmployeeId(employeeId);
				uploadFile.setImage_name(aFile.getOriginalFilename());
				uploadFile.setEmployeeImage(aFile.getBytes());*/
				status = userService.saveImage(aFile, progressbar ,employeeId ,lastUpdate);
				resource.setDetails(status);
				resource.setActionStatus(true);

			}
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	/**
	 * This method will give Upload resume and store in DB based on EMployee ID
	 * .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Upload image and store in DB ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Update the resmue DB successfully", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "Not updated resume in Database ", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.UPLOAD_RESUME, method = RequestMethod.POST)
	public ResponseEntity resumeUpload(@RequestParam("empId") String employeeId,@RequestParam("progressbar") int progressbar,
			@RequestParam("file") CommonsMultipartFile[] fileUpload , @RequestParam("lastUpdate") String lastUpdate)
					throws Exception {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		boolean status = false;

		try {
			if (fileUpload != null) {
				CommonsMultipartFile aFile = fileUpload[0];

				System.out.println("Saving file: " + aFile.getOriginalFilename());

	/*			EmployeeResume resumeObj = new EmployeeResume();
				resumeObj.setEmployeeId(employeeId);
				resumeObj.setResumefilename(aFile.getOriginalFilename());
				resumeObj.setEmployeeResume(aFile.getBytes());*/
				status = userService.saveResume(aFile, progressbar,employeeId,lastUpdate);
				resource.setDetails(status);
				resource.setActionStatus(true);

			}
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	
	/**
	 * This method will help todelete a domain based on employee id .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "DELETE_EMP_DOMAIN")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get employee details", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.DELETE_RESUME, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity deleteEmployeeResume(@RequestBody Employee empObj,
			@RequestParam("progressbar") int progressbar, @RequestParam("lastUpdate") String lastUpdate) {

		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean progressBarUpdated = userService.deleteEmployeeResume(empObj, progressbar, lastUpdate);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	
	
	/**
	 * This method will help to delete image on employee id .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "DELETE_EMP_IMAGE")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete  employee Image", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.DELETE_IMAGE, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity deleteEmployeeImage(@RequestBody Employee empObj,
			@RequestParam("progressbar") int progressbar, @RequestParam("lastUpdate") String lastUpdate) {

		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean progressBarUpdated = userService.deleteEmployeeImage(empObj, progressbar, lastUpdate);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/**
	 * This method will give user info based on his user id.
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Project Details by Employee ID ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get  Project Details ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No Projects for the employee ID", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_PROJECT_DETAILS_BY_EMPID, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getProjectsBYId(@RequestParam("empId") String empId) {
		System.out.println(" Inside getProjectsBYId ");

		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<ProjectEmployeeMap> projectList;
		try {
			projectList = userService.getProjectsByOId(empId);
			// resource.setDetails(projectList);
			return new ResponseEntity(projectList, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method will give Domains .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Domain lOok up ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Domain details  ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No  Domain details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_DOMAINS, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getDomains() {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<DomainMaster> skillList;
		try {
			skillList = userService.getDomains();
			// resource.setDetails(skillList.toString());
			return new ResponseEntity(skillList, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method will give user info based on his user id.
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Sub Domain lOok up  ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Sub Domain details  ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No SUB  Domain details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_SUB_DOMAINS, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getSubDomains(@RequestParam("domainId") int domainId) {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<SubDomain> subDomainList;
		try {
			subDomainList = userService.getSubDomains(domainId);
			// resource.setDetails(skillList.toString());
			return new ResponseEntity(subDomainList, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method will give user info based on his user id.
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Child Domain lOok up  ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Child Domain details  ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No Child  Domain details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_CHILD_DOMAINS, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getChildDomains(@RequestParam("domainId") int domainId,
			@RequestParam("subDomainId") int subDomainId) {
		corsOrginHeaders();
		System.out.println("Inside getChildDomains() method");
		ResourceSupport resource = new ResourceSupport();
		List<ChildDomain> childDomainList;
		try {
			childDomainList = userService.getChildDomains(domainId, subDomainId);
			// resource.setDetails(skillList.toString());
			return new ResponseEntity(childDomainList, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method will update the phone number email and total experience based
	 * on employee id .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Update employee details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Updated employee details", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.UPDATE_EMPLOYEE_PHN_MAIL_EXPRNCE, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity saveEmpPhoneMailExperience(@RequestBody EmployeeDto emp) {

		/*
		 * public ResponseEntity<LoginDetailsBean>
		 * saveEmpPhoneMailExperience(@RequestParam("empId") String empId
		 * ,@RequestParam("personalMailId") String personalMailId ,
		 * 
		 * @RequestParam("phoneNo") int phoneNo
		 * , @RequestParam("totalExperience") int totalExperience) {
		 */
		corsOrginHeaders();
		System.out.println("empId " + emp.getEmployeeId());

		ResourceSupport resource = new ResourceSupport();
		try {
			int progressBarUpdated = userService.updateContactAndExp(emp);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This method will help to Update the UI themes based on Employee ID
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get employee details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Update UI theme ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.UPDATE_THEME, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity updateTheme(@RequestBody ThemeEmployee themeObj) {

		/*
		 * public ResponseEntity<LoginDetailsBean>
		 * saveEmpPhoneMailExperience(@RequestParam("empId") String empId
		 * ,@RequestParam("personalMailId") String personalMailId ,
		 * 
		 * @RequestParam("phoneNo") int phoneNo
		 * , @RequestParam("totalExperience") int totalExperience) {
		 */
		corsOrginHeaders();
		System.out.println("empId " + themeObj.getEmployeeId() + " theme id " + themeObj.getThemeId());

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean progressBarUpdated = userService.updateTheme(themeObj);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Get All the themes ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get UI themes ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO Themes present ", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.THEMES_LOOKUP, method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity getThemes() {

		/*
		 * public ResponseEntity<LoginDetailsBean>
		 * saveEmpPhoneMailExperience(@RequestParam("empId") String empId
		 * ,@RequestParam("personalMailId") String personalMailId ,
		 * 
		 * @RequestParam("phoneNo") int phoneNo
		 * , @RequestParam("totalExperience") int totalExperience) {
		 */
		corsOrginHeaders();
		System.out.println("Inside getThemes");

		ResourceSupport resource = new ResourceSupport();
		try {
			List<ThemeMaster> themedtls = new ArrayList<ThemeMaster>();
			themedtls = userService.getThemesUi();

			resource.setActionStatus(true);
			resource.setDetails(themedtls);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This method will help to Add or update skill based on employee id .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "ADD_EMP_SKILL")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "update the skill of a employee", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.ADD_UPDATE_EMP_SKILL, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity addUpdateEmployeeSkills(@RequestBody EmployeeSkills empSkillObj,
			@RequestParam("progressbar") int progressbar, @RequestParam("lastUpdate") String lastUpdate) {

		corsOrginHeaders();
		System.out.println("empId " + empSkillObj.getEmployeeId() + "progress bar " + progressbar);

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean progressBarUpdated = userService.addEmplSkill(empSkillObj, progressbar,  lastUpdate);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This method will help to Delete skill based on employee id .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "DELETE_EMP_SKILL")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete Skill of a  employee ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.DELETE_EMP_SKILL, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity deleteEmployeeSkills(@RequestBody EmployeeSkills empSkillObj,
			@RequestParam("progressbar") int progressbar, @RequestParam("lastUpdate") String lastUpdate) {

		corsOrginHeaders();
		
		ResourceSupport resource = new ResourceSupport();
		try {
			boolean progressBarUpdated = userService.deleteEmplSkill(empSkillObj, progressbar,  lastUpdate);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This method will help to Add or update Domain based on employee id .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "ADD_EMP_DOMAIN")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Update Domain of a  employee", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.ADD_UPDTAE_EMP_DOMAIN, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity addUpdateEmployeeDomain(@RequestBody EmployeeDomainMainDto empDomainObj,
			@RequestParam("progressbar") int progressbar, @RequestParam("lastUpdate") String lastUpdate) {

		corsOrginHeaders();
		System.out.println("empId " + empDomainObj.getEmployeeId());

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean progressBarUpdated = userService.addEmplDomain(empDomainObj, progressbar, lastUpdate);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This method will help todelete a domain based on employee id .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "DELETE_EMP_DOMAIN")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get employee details", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.DELETE_EMP_DOMAIN, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity deleteEmployeeDomain(@RequestBody EmployeeDomainBean empDomainObj,
			@RequestParam("progressbar") int progressbar, @RequestParam("lastUpdate") String lastUpdate) {

		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean progressBarUpdated = userService.deleteEmplDomain(empDomainObj, progressbar, lastUpdate);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This method will give allocation status and count for ATS .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get GET_ALOOC_STATUS_COUNT : Skill category and Skills ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get ATS allocation status and count ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No allocations ", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_ATS_ALLOC_STATUS_COUNT, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getAtsAllocationStatus() {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<AtsAllocStatusBean> aloocList = new ArrayList<AtsAllocStatusBean>();
		try {
			aloocList = userService.getAllocStatus();
			resource.setActionStatus(true);
			resource.setDetails(aloocList);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method will give allocation status and count for Practices .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get GET_ALOOC_STATUS_COUNT  ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get ATS allocation status and count ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No allocations ", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_PRACTICES_ALLOC_STATUS_COUNT, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getPracticeAllocationStatus() {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<PracticesAllocationBean> aloocList = new ArrayList<PracticesAllocationBean>();
		try {
			aloocList = userService.getPracticeAllocStatus();
			resource.setActionStatus(true);
			resource.setDetails(aloocList);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method will give allocation status and count for Practices .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get GET_ALOOC_STATUS_COUNT ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get BU allocation status and count ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No allocations ", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_BU_ALLOC_STATUS_COUNT, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getbusinessAllocationStatus() {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<BusinessUnitAllocBean> aloocList = new ArrayList<BusinessUnitAllocBean>();
		try {
			aloocList = userService.getBuAllocStatus();
			resource.setActionStatus(true);
			resource.setDetails(aloocList);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	/**
	 * This method will give visa details of employee .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Visa details for a employee id  ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get visa  details by id  ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No  visa details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_VISA_DTLS, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getVisaDetails(@RequestParam("empId") String empId) {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<VisaEmployeeDetails> skillList;
		try {
			skillList = userService.getVisaEmpDtls(empId);
			resource.setActionStatus(true);
			 resource.setDetails(skillList);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * This method will help to Add or update visa details  based on employee id .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "ADD_VISA_DETAILS")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Update Visa of a  employee", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.ADD_UPDTAE_VISA_DETAILS, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity addUpdateEmployeeVisa(@RequestBody VisaEmployeeDetails empvisaObj) {

		corsOrginHeaders();
		System.out.println("empId " + empvisaObj.getEmployeeId());

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean flag = userService.addEmplVisa(empvisaObj);

			resource.setActionStatus(true);
			resource.setDetails(flag);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	
	/**
	 * This method will help to delete Visa details based in employee Id 
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "DELETE_EMP_VISA")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete employee Visa details ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.DELETE_EMP_VISA, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity deleteEmployeeVisa(@RequestBody VisaEmployeeDetails empVisaObj) {

		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean progressBarUpdated = userService.deleteEmpVisa(empVisaObj);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/**
	 * This method will give Certification technologies  .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Certification technologies  lOok up  ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Certification technologies ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No Certification technologies", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_CERTIFICATION_TECHNOLOGIES, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getCertificationTechnologies() {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<CertificationTechnologies> cert_tech_List;
		try {
			cert_tech_List = userService.getCertTechnologies();
			resource.setActionStatus(true);
			resource.setDetails(cert_tech_List);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * This method will give Certification technologies  .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Certification names  lOok up  ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Certification names ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No Certification name", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_CERTIFICATION_NAMES, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getCertificationNames(@RequestParam("cert_tech_id") int cert_tech_id) {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<CertificationNames> cert_tech_List;
		try {
			cert_tech_List = userService.getCertNames(cert_tech_id);
			resource.setActionStatus(true);
			resource.setDetails(cert_tech_List);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * This method will give Certification technologies  .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Certification Institute Names   ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Certification Institute Names ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No Certification Institute Names", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_CERTIFICATION_INSTITUTES, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getCertificationInstitutes(@RequestParam("cert_tech_id") int cert_tech_id) {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<CertificationInstitutes> cert_institute_List;
		try {
			cert_institute_List = userService.getCertInstitutes(cert_tech_id);
			resource.setActionStatus(true);
			resource.setDetails(cert_institute_List);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * This method will help to update certification details based in employee Id 
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "SAVE_UPDATE_EMP_CERTIFICATES")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "UPdate employee certification details ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.SAVE_UPDATE_EMP_CERTIFICATES, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity saveOrUpdateEmployeeCertification(@RequestBody EmployeeCertificationMap empCertbj) {

		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean progressBarUpdated = userService.saveUpdateEmpCert(empCertbj);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/**
	 * This method will help to update certification details based in employee Id 
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "DELETE_EMP_CERTIFICATES")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete employee certification details ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.DELETE_EMP_CERTIFICATES, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity deleteEmployeeCertification(@RequestBody EmployeeCertificationMap empCertbj) {

		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean progressBarUpdated = userService.deleteEmpCert(empCertbj);

			resource.setActionStatus(true);
			resource.setDetails(progressBarUpdated);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/**
	 * This method will help to update certification details based in employee Id 
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "SAVE_UPDATE_EMP_PROJECTS")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "UPdate employee Project details ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.SAVE_UPDATE_EMP_PROJECTS, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity saveOrUpdateEmployeeProjects(@RequestBody EmployeeProjectsBean empProjbj) {

		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean flag = userService.saveUpdateEmpProj(empProjbj);

			resource.setActionStatus(true);
			resource.setDetails(flag);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/**
	 * This method will help to update certification details based in employee Id 
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "DELETE_EMP_PROJECTS")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete employee certification details ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.DELETE_EMP_PROJECTS, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity deleteEmployeeProject(@RequestBody EmployeeProjectsBean empCertbj) {

		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean flag = userService.deleteEmpProject(empCertbj);

			resource.setActionStatus(true);
			resource.setDetails(flag);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	
	/**
	 * This method will help to validate user based on his name and password.
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Designation master details  ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Designation master details  ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO Designation  details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_DESIGNATION_MASTER_DATA, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getDesignationMasterDtls() {
	
		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			List<DesignationMaster> designationList = userService.getDesinationList();

			resource.setActionStatus(true);
			resource.setDetails(designationList);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	/**
	 * This method will help to get the Location master data .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Location master details  ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Location  master details  ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO Location  details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_LOCATION_MASTER_DATA, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getLocationMasterDtls() {
	
		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			List<LocationMaster> designationList = userService.getLocationList();

			resource.setActionStatus(true);
			resource.setDetails(designationList);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	 * This method will help to get theBusiness Unit master data .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Business Unit master details  ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Business Unit  master details  ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO Business Unit  details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_BU_MASTER_DATA, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getBusinessUnitMasterDtls() {
	
		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			List<BuMaster> designationList = userService.getBusinessUnitList();

			resource.setActionStatus(true);
			resource.setDetails(designationList);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	 * This method will help to get the Delivery Unit master data .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Delivery Unit master details  ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get Delivery Unit  master details  ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO Delivery Unit  details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_DU_MASTER_DATA, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getDeliveryUnitMasterDtls() {
	
		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			List<DuMaster> designationList = userService.getDeliveryUnitList();

			resource.setActionStatus(true);
			resource.setDetails(designationList);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/**
	 * This method will help to update BU details based in employee Id 
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "SAVE_UPDATE_BU DETAILS")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "UPdate employee BU  details ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.UPDATE_BU_DETAILS, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity UpdateBuDetailsbyEmpId(@RequestBody EmployeeDto empBean) {

		corsOrginHeaders();

		ResourceSupport resource = new ResourceSupport();
		try {
			boolean flag = userService.updateBudetails(empBean);

			resource.setActionStatus(true);
			resource.setDetails(flag);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/**
	 * This method will give Upload resume and store in DB based on EMployee ID
	 * .
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Update Passport details of Employee ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Update the Passport details of Employee in DB successfully", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "Not updated Passport details of Employee in Database ", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.UPDATE_PASSPORT, method = RequestMethod.POST)
	public ResponseEntity updatePassportDetails(@RequestParam("empId") String employeeId,@RequestParam(name="passposrt_Scan", required=false) CommonsMultipartFile[] fileUpload 
			, @RequestParam("passport_no") String passport_no, @RequestParam("passport_Valid_From") String passport_Valid_From,
			@RequestParam("passport_Valid_To") String passport_Valid_To , @RequestParam("passport_Issue_Date") String passport_Issue_Date)
					throws Exception {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		boolean status = false;
		CommonsMultipartFile aFile= null;
		try {
			if (fileUpload != null) {
				aFile = fileUpload[0];
				System.out.println("Saving file: " + aFile.getOriginalFilename());
				status = userService.updatePassportDtlsByEmpl(aFile, employeeId,passport_no,passport_Valid_From,passport_Valid_To,passport_Issue_Date);
				resource.setDetails(status);
				resource.setActionStatus(true);
			}else {
				status = userService.updatePassportDtlsByEmpl(aFile, employeeId,passport_no,passport_Valid_From,passport_Valid_To,passport_Issue_Date);
				resource.setDetails(status);
				resource.setActionStatus(true);
			}
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	
	
	
	
	/**
	 * This method will give user info based on his user id.
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get Domain Details by Employee ID ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get  Domain  Details ", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No Domain for the employee ID", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })

	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_DOMAIN_DETAILS_BY_EMPID, method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity getDomainDtlsById(@RequestParam("empId") String empId) {
		System.out.println(" Inside getProjectsBYId ");

		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		List<EmployeeDomainView> domainList;
		try {
			domainList = userService.getDomainDtlsBYId(empId);
			 resource.setDetails(domainList);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	/**
	 * This method will help to validate user based on his name and password.
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "Get employee details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Get employee details", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "NO EMP details", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.EMP_PROFILE_REPORT, method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity getEmployeeProfileReport(@RequestParam("empId") String empId) {
		System.out.println("empId " + empId);
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		
		try {
			List<EmployeeProfileReport> employeeProfileList = userService.getEmployeeProfileReport(empId);
			resource.setActionStatus(true);
			resource.setDetails(employeeProfileList);
			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}

	}
	

/*	*//**
	 * This method will give Upload resume and store in DB based on EMployee ID
	 * .
	 * 
	 * @param userId
	 * @return
	 *//*
	@ApiOperation(value = "Fetch Passport details of Employee ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Fetch the Passport details of Employee in DB successfully", response = ResourceSupport.class),
			@ApiResponse(code = 403, message = "No Passport details of Employee in Database ", response = ResourceSupport.class),
			@ApiResponse(code = 500, message = "Internal server problem", response = ResourceSupport.class), })
	@CrossOrigin
	@RequestMapping(value = RmgConstants.GET_PASSPORT_DTLS, method = RequestMethod.GET)
	public ResponseEntity getPassportDetails(@RequestParam("empId") String employeeId)
					throws Exception {
		corsOrginHeaders();
		ResourceSupport resource = new ResourceSupport();
		EmployeeBean passportData = null;

		try {
		

				System.out.println("Inside getPassportDetails");

				passportData = userService.getPassportDetails(employeeId);
				resource.setDetails(passportData);
				resource.setActionStatus(true);

			return new ResponseEntity(resource, HttpStatus.OK);
		} catch (Exception e) {
			resource.setActionStatus(false);
			resource.setErrorMessage(e.getMessage());
			return new ResponseEntity(resource, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	*/
	
	/**
	 * @return
	 */
	public static HttpHeaders corsOrginHeaders() {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Access-Control-Allow-Origin", "*");
		/*
		 * if(null != base64ClientCredentials && basicAuthenticationCount == 0){
		 * httpHeaders.add("Authorization", "Basic " + base64ClientCredentials);
		 * basicAuthenticationCount ++; }
		 */

		return httpHeaders;
	}

}