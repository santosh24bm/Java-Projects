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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "domain_master")
public class DomainMaster implements Serializable{
	
	
	private static final long serialVersionUID = -1012696443024901945L;
	
	@Id
	@Column(name = "domain_id")
	private int id ; 
	
	@Column(name = "domain_name")
	private String domainName;
	

	public int getId() {
		return id;
	}

	public String getDomainName() {
		return domainName;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	@Override
	public String toString() {
		return "DomainMaster [id=" + id + ", domainName=" + domainName + "]";
	}


	
}
