package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="child_domain")
public class ChildDomain implements Serializable{

	private static final long serialVersionUID = -3918128058893486527L;

     @Id
	@Column(name="Id")
	private int id;
	
	@Column(name="domain_id")
	private int domainId;
	
	@Column(name="sub_domain_id")
	private int subDomainId;
	
	@Column(name="child_domain_name")
	private String chilDomainName;
	
	
//	@Column(name="child_domain_desc")
//	private String childDomainDesc;
		

/*	@ManyToOne
	@JoinColumn(name="sub_domain_id" , insertable=false,  updatable=false )
	private SubDomain SubDomainSet;

*/
	public int getId() {
		return id;
	}


	public int getDomainId() {
		return domainId;
	}


	public int getSubDomainId() {
		return subDomainId;
	}


	public String getChilDomainName() {
		return chilDomainName;
	}






	public void setId(int id) {
		this.id = id;
	}


	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}


	public void setSubDomainId(int subDomainId) {
		this.subDomainId = subDomainId;
	}


	public void setChilDomainName(String chilDomainName) {
		this.chilDomainName = chilDomainName;
	}


	@Override
	public String toString() {
		return "ChildDomain [id=" + id + ", domainId=" + domainId + ", subDomainId=" + subDomainId + ", chilDomainName="
				+ chilDomainName + "]";
	}




	
	
	
	
	
	
}
