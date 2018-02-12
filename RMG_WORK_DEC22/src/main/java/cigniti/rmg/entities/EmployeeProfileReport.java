package cigniti.rmg.entities;

import java.util.List;


public class EmployeeProfileReport {

	EmployeeDetails empBean;
	
	// list of projects 
		List <ProjectEmployeeMap>  projects ;
	// projects end 

		public EmployeeDetails getEmpBean() {
			return empBean;
		}
		
	// skill and certification 
		
		SkillAndDomainCertificationDtls skillCert ; 	
		
		// Domain 
		List<EmployeeDomainView>  domainList ;  
		// VISA
		List<VisaEmployeeDetails>  visaList ;  

		public void setEmpBean(EmployeeDetails empBean) {
			this.empBean = empBean;
		}

		public List<ProjectEmployeeMap> getProjects() {
			return projects;
		}

		public void setProjects(List<ProjectEmployeeMap> projects) {
			this.projects = projects;
		}

		public SkillAndDomainCertificationDtls getSkillCert() {
			return skillCert;
		}

		public void setSkillCert(SkillAndDomainCertificationDtls skillCert) {
			this.skillCert = skillCert;
		}

		
		public List<EmployeeDomainView> getDomainList() {
			return domainList;
		}

		public void setDomainList(List<EmployeeDomainView> domainList) {
			this.domainList = domainList;
		}

		public List<VisaEmployeeDetails> getVisaList() {
			return visaList;
		}

		public void setVisaList(List<VisaEmployeeDetails> visaList) {
			this.visaList = visaList;
		}

		@Override
		public String toString() {
			return "EmployeeProfileReport [empBean=" + empBean + ", projects=" + projects + ", skillCert=" + skillCert
					+ ", domainList=" + domainList + ", visaList=" + visaList + "]";
		}

	



		
	
}
