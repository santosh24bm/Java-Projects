package cigniti.rmg.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sub_domain")
public class SubDomain implements Serializable{
	

	private static final long serialVersionUID = -8564397017798272480L;

	@Id
	@Column(name = "sub_domain_id")
	private int id;
	
	@Column(name = "sub_domain_name")
	private String subDomaineName;
	
/*	@Column(name = "sub_domain_description")
	private String  subDomainDescription;*/
	
	@Column(name = "domain_id")
	private int domainId;
	
	// List<ChildDomain> childDomainList ; 
	
	
/*	@ManyToOne
	@JoinColumn(name="domain_id", nullable=false)
	private DomainMaster DomainSet;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "SubDomainSet")
	private Collection<ChildDomain> childDomainDtlsList = new ArrayList<ChildDomain>();
*/

	
	
	/*public Collection<ChildDomain> getChildDomainDtlsList() {
		return childDomainDtlsList;
	}


	public void setChildDomainDtlsList(Collection<ChildDomain> childDomainDtlsList) {
		this.childDomainDtlsList = childDomainDtlsList;
	}*/


	public int getId() {
		return id;
	}


	public String getSubDomaineName() {
		return subDomaineName;
	}




	public int getDomainId() {
		return domainId;
	}




	public void setId(int id) {
		this.id = id;
	}


	public void setSubDomaineName(String subDomaineName) {
		this.subDomaineName = subDomaineName;
	}




	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}


	@Override
	public String toString() {
		return "SubDomain [id=" + id + ", subDomaineName=" + subDomaineName + ", domainId=" + domainId + "]";
	}


	


	
	
	
	
	
	
	
	

}
