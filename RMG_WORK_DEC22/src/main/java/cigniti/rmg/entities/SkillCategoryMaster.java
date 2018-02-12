package cigniti.rmg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "skill_category_master")
public class SkillCategoryMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4600513699468063489L;

	@Id
	@Column(name = "skill_category_id")
	private long skill_category_id;

	@Column(name = "skill_category_name",nullable=false)
	private String name;



	public long getSkill_category_id() {
		return skill_category_id;
	}

	public void setSkill_category_id(long skill_category_id) {
		this.skill_category_id = skill_category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SkillCategoryMaster [skill_category_id=" + skill_category_id + ", name=" + name + "]";
	}


	
	
}
