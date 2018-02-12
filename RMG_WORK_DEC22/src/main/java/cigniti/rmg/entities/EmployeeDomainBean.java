package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emp_domain_head_map")
public class EmployeeDomainBean implements Serializable{

	
	private static final long serialVersionUID = -6878805429684207699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int rowid;
	
	//@Id
	@Column(name = "emp_id")
	private String employeeId;

	//@Id
	@Column(name = "domain_id")
	private String domainId;

//	@Id
	@Column(name = "sub_domain_id")
	private String subDomainId;
	
	//@Id
/*	@Column(name = "child_domain_id")
	private int childDomainId;*/
	
	@Column(name = "domain_experience")
	private float domain_experience;
	
	@Column(name = "comments")
	private String comments;
	
	/*@Column(name = "child_map_id")
	private int childMapId;
*/
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getSubDomainId() {
		return subDomainId;
	}

	public void setSubDomainId(String subDomainId) {
		this.subDomainId = subDomainId;
	}

/*	public int getChildDomainId() {
		return childDomainId;
	}

	public void setChildDomainId(int childDomainId) {
		this.childDomainId = childDomainId;
	}*/

	public float getDomain_experience() {
		return domain_experience;
	}

	public void setDomain_experience(float domain_experience) {
		this.domain_experience = domain_experience;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	@Override
	public String toString() {
		return "EmployeeDomainBean [rowid=" + rowid + ", employeeId=" + employeeId + ", domainId=" + domainId
				+ ", subDomainId=" + subDomainId + ", domain_experience=" + domain_experience + ", comments=" + comments
				+ "]";
	}

/*	public int getChildMapId() {
		return childMapId;
	}

	public void setChildMapId(int childMapId) {
		this.childMapId = childMapId;
	}*/



	
	
	
}
