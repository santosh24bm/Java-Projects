package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="certification_technologies")
public class CertificationTechnologies implements Serializable{

	
	private static final long serialVersionUID = -6916666111597966060L;
	
	@Id	
	@Column(name="cert_tech_id")
	private int cert_tech_id ; 
	
	@Column(name="cert_technology_name")
	private String vert_technology_name;

	public int getCert_tech_id() {
		return cert_tech_id;
	}

	public void setCert_tech_id(int cert_tech_id) {
		this.cert_tech_id = cert_tech_id;
	}

	public String getVert_technology_name() {
		return vert_technology_name;
	}

	public void setVert_technology_name(String vert_technology_name) {
		this.vert_technology_name = vert_technology_name;
	}

	@Override
	public String toString() {
		return "CertificationTechnologies [cert_tech_id=" + cert_tech_id + ", vert_technology_name="
				+ vert_technology_name + "]";
	}
	
	
	

}
