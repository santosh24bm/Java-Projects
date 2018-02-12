package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="certification_institutes")
public class CertificationInstitutes implements Serializable {

	private static final long serialVersionUID = -3931983995926561545L;

	@Id	
	@Column(name="id")
	private int rowid ; 
			
	@Column(name="cert_tech_id")
	private int cert_tech_id ; 
	
	@Column(name="certification_institute_name")
	private String certification_institute_name;

	public int getId() {
		return rowid;
	}

	public void setId(int rowid) {
		this.rowid = rowid;
	}

	public String getCertification_institute_name() {
		return certification_institute_name;
	}

	public void setCertification_institute_name(String certification_institute_name) {
		this.certification_institute_name = certification_institute_name;
	}

	public int getCert_tech_id() {
		return cert_tech_id;
	}

	public void setCert_tech_id(int cert_tech_id) {
		this.cert_tech_id = cert_tech_id;
	}

	@Override
	public String toString() {
		return "CertificationInstitutes [rowid=" + rowid + ", cert_tech_id=" + cert_tech_id
				+ ", certification_institute_name=" + certification_institute_name + "]";
	}


	
	
	
}
