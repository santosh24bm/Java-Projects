package cigniti.rmg.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skill_look_up")
public class SkillEmbed implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5561826377827590272L;


	@Column(name = "skill_category")
	private String skillCategoryName;
	@Column(name = "skill_name")
	private String skill_name;
	@Id
	@Column(name="id")
	private String id;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSkillCategoryName() {
		return skillCategoryName;
	}

	public String getSkill_name() {
		return skill_name;
	}


	public void setSkillCategoryName(String skillCategoryName) {
		this.skillCategoryName = skillCategoryName;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}

}
