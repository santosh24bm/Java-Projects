package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="domain_view_multichild")
public class EmployeeDomainView implements Serializable{


private static final long serialVersionUID = -8255710190608424139L;


@Id
@Column(name="rowid")
private String rowid;

@Id
@Column(name="emp_id")
private String employeeId;

@Id
@Column (name = "domain_name")
private String domain_name;

@Id
@Column(name= "sub_domain_name")
private String sub_domain_name ;

@Id
@Column(name= "child_domain_name")
private String child_domain_name ;


  @Column(name="domain_experience")
private float domainExperience;

  @Column(name="comments")
private String comments;

public String getRowid() {
	return rowid;
}

public void setRowid(String rowid) {
	this.rowid = rowid;
}

public String getEmployeeId() {
	return employeeId;
}

public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}

public String getDomain_name() {
	return domain_name;
}

public void setDomain_name(String domain_name) {
	this.domain_name = domain_name;
}

public String getSub_domain_name() {
	return sub_domain_name;
}

public void setSub_domain_name(String sub_domain_name) {
	this.sub_domain_name = sub_domain_name;
}

public String getChild_domain_name() {
	return child_domain_name;
}

public void setChild_domain_name(String child_domain_name) {
	this.child_domain_name = child_domain_name;
}

public float getDomainExperience() {
	return domainExperience;
}

public void setDomainExperience(float domainExperience) {
	this.domainExperience = domainExperience;
}

public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
}

@Override
public String toString() {
	return "EmployeeDomainView [rowid=" + rowid + ", employeeId=" + employeeId + ", domain_name=" + domain_name
			+ ", sub_domain_name=" + sub_domain_name + ", child_domain_name=" + child_domain_name
			+ ", domainExperience=" + domainExperience + ", comments=" + comments + "]";
}
  
  
  
  
}
