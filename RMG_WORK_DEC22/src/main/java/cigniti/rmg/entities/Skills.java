package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="skill_view")
public class Skills implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2052418942652185924L;

	@Id
	@Column(name="skill_master_id")
	private String skill_master_id;
	
	
	@Column(name="employeeId")
	private String employeeId;
	
	@Column(name = "skill_name")
	private String  skill;
	
	@Column( name = "skill_rating")
	private String  skillRating;
	
	@Column(name = "skill_category")
	private String 	skillCategory;
	
	
	public String getSkill_master_id() {
		return skill_master_id;
	}
	public void setSkill_master_id(String skill_master_id) {
		this.skill_master_id = skill_master_id;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getSkillRating() {
		return skillRating;
	}
	public void setSkillRating(String skillRating) {
		this.skillRating = skillRating;
	}
	public String getSkillCategory() {
		return skillCategory;
	}
	public void setSkillCategory(String skillCategory) {
		this.skillCategory = skillCategory;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
