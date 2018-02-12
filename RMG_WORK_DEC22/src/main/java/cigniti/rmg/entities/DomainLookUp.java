package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domain_lookUp_view")
public class DomainLookUp  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4264205457877492000L;

	@Id
	@Column(name="id")
	private int id ;
	
	@Column(name="domain_name")
	private String domainName;
	
	@Column(name="sub_domain_name")
	private String subDomainName; 
	
	
	@Column(name="child_domain_name")
	private String childDomainName;


	public int getId() {
		return id;
	}


	public String getDomainName() {
		return domainName;
	}


	public String getSubDomainName() {
		return subDomainName;
	}


	public String getChildDomainName() {
		return childDomainName;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}


	public void setSubDomainName(String subDomainName) {
		this.subDomainName = subDomainName;
	}


	public void setChildDomainName(String childDomainName) {
		this.childDomainName = childDomainName;
	}


	@Override
	public String toString() {
		return "DomainLookUp [id=" + id + ", domainName=" + domainName + ", subDomainName=" + subDomainName
				+ ", childDomainName=" + childDomainName + "]";
	} 
	
	
	
	
}
