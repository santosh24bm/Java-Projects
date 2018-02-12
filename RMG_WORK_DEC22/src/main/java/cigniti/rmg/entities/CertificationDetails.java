package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="certification_view")
public class CertificationDetails implements Serializable{


	private static final long serialVersionUID = 5407556994770915155L;
	 
	
	@Id
	@Column(name="employeeId")
	private String employeeId;
	
	@Transient
	private String	certificationName;
	
	@Transient
	private String	certificationForm;
	
	@Transient
	private String	validFrom;
	
	@Transient
	private String	validTo;
	
	@Transient
	
	private String	comments;
	
	@Transient
	private String	Technology;
	
	@Transient
	private String	Level;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCertificationName() {
		return certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	public String getCertificationForm() {
		return certificationForm;
	}

	public void setCertificationForm(String certificationForm) {
		this.certificationForm = certificationForm;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTechnology() {
		return Technology;
	}

	public void setTechnology(String technology) {
		Technology = technology;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}
	
	


}
