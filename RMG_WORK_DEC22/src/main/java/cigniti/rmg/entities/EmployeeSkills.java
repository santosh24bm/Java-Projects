package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emp_skill_map")
public class EmployeeSkills implements Serializable {

	private static final long serialVersionUID = 7832554042547993809L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rowid")
	private int rowid;
	
	
	@Column(name = "emp_id")
	private String employeeId;

	@Column(name = "skill_category_id")
	private String skillCategoryId;
	
	@Column(name = "skill_id")
	private String skillId;

	@Column(name = "skill_rating")
	private int skillRating;

	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getSkillCategoryId() {
		return skillCategoryId;
	}

	public void setSkillCategoryId(String skillCategoryId) {
		this.skillCategoryId = skillCategoryId;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public int getSkillRating() {
		return skillRating;
	}

	public void setSkillRating(int skillRating) {
		this.skillRating = skillRating;
	}

	@Override
	public String toString() {
		return "EmployeeSkills [rowid=" + rowid + ", employeeId=" + employeeId + ", skillCategoryId=" + skillCategoryId
				+ ", skillId=" + skillId + ", skillRating=" + skillRating + "]";
	}


	
}
