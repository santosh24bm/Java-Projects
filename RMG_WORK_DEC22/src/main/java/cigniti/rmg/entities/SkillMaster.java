package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "skill_master")
public class SkillMaster implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8227560066471745561L;

	@Id
	@Column(name = "skill_id")
	private int skill_id;

	@Column(name = "skill_category_id")
	private int skill_category_id;
	
	@Column(name = "skill_name")
	private String skill_name;

	public long getSkill_id() {
		return skill_id;
	}

	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}

	public long getSkill_category_id() {
		return skill_category_id;
	}

	public void setSkill_category_id(int skill_category_id) {
		this.skill_category_id = skill_category_id;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}

	@Override
	public String toString() {
		return "SkillMaster [skill_id=" + skill_id + ", skill_category_id=" + skill_category_id + ", skill_name="
				+ skill_name + "]";
	}
	
	
}
