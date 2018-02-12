package cigniti.rmg.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDomainMainDto implements Serializable {


	private static final long serialVersionUID = 5193136740793392361L;

	@JsonProperty("rowid")
	private int rowid;
	@JsonProperty("employeeId")
	private String employeeId;
	@JsonProperty("domainId")
	private String domainId;
	@JsonProperty("subDomainId")
	private String subDomainId;
	@JsonProperty("domain_experience")
	private float domainExperience;
	@JsonProperty("comments")
	private String comments;
	@JsonProperty("child_domain")
	private int[] childDomain;
	
	
	
	
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
	public int[] getChildDomain() {
		return childDomain;
	}
	public void setChildDomain(int[] childDomain) {
		this.childDomain = childDomain;
	}
	@Override
	public String toString() {
		return "EmployeeDomainMainDto [rowid=" + rowid + ", employeeId=" + employeeId + ", domainId=" + domainId
				+ ", subDomainId=" + subDomainId + ", domainExperience=" + domainExperience + ", comments=" + comments
				+ ", childDomain=" + Arrays.toString(childDomain) + "]";
	}
	
	
	
	
}
