package cigniti.rmg.entities;

import java.io.Serializable;
import java.util.List;


public class SkillAndDomainCertificationDtls implements Serializable {

	
	private static final long serialVersionUID = 5975124584361705850L;
	
	
	private List<Skills> skill_List;
	//private List<DomailDetails> domain_List;
	private List<EmployeeSkills> certification_List;
	
	
	public List<Skills> getSkill_List() {
		return skill_List;
	}
	public void setSkill_List(List<Skills> skill_List) {
		this.skill_List = skill_List;
	}

	public List<EmployeeSkills> getCertification_List() {
		return certification_List;
	}
	public void setCertification_List(List<EmployeeSkills> certification_List) {
		this.certification_List = certification_List;
	}
	
}
