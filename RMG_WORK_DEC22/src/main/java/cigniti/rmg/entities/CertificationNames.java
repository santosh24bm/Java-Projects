package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="certification_names")
public class CertificationNames implements Serializable{


	private static final long serialVersionUID = -350134558337309503L;

	@Id	
	@Column(name="cert_id")
	private int cert_id ; 
	
	
	@Column(name="cert_tech_id")
	private int cert_tech_id ; 
	
	@Column(name="cert_name")
	private String cert_name;
	
	
	

	public int getCert_id() {
		return cert_id;
	}

	public void setCert_id(int cert_id) {
		this.cert_id = cert_id;
	}

	public int getCert_tech_id() {
		return cert_tech_id;
	}

	public void setCert_tech_id(int cert_tech_id) {
		this.cert_tech_id = cert_tech_id;
	}

	public String getCert_name() {
		return cert_name;
	}

	public void setCert_name(String cert_name) {
		this.cert_name = cert_name;
	}

	@Override
	public String toString() {
		return "CertificationNames [cert_id=" + cert_id + ", cert_tech_id=" + cert_tech_id + ", cert_name=" + cert_name
				+ "]";
	}
	
	
	
	
	

	
}
