package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="emp_certification_map")
public class EmployeeCertificationMap implements Serializable{

	private static final long serialVersionUID = 1967019106926759853L;

	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int rowid ; 
	
	//@Id	
	@Column(name="empId")
	private String empId ; 
	
	//@Id	
	@Column(name="technology")
	private String technology;
		
	//@Id	
	@Column(name="certification")
	private String certification ; 
	
	//@Id	
//	@Column(name="levels")
//	private String levels;
	

	@Column(name="board_or_institute_of_certification")
	private String boardInstitute ; 
	
	@Column(name="valid_From")
	private String validFrom;
	

	@Column(name="valid_To")
	private String validTo;
	
	@Column(name="comments")
	private String comments;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	/*public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}*/

	public String getBoardInstitute() {
		return boardInstitute;
	}

	public void setBoardInstitute(String boardInstitute) {
		this.boardInstitute = boardInstitute;
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

	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	@Override
	public String toString() {
		return "EmployeeCertificationMap [rowid=" + rowid + ", empId=" + empId + ", technology=" + technology
				+ ", certification=" + certification + ", boardInstitute=" + boardInstitute + ", validFrom=" + validFrom
				+ ", validTo=" + validTo + ", comments=" + comments + "]";
	}


	
	
	
	
	
}
