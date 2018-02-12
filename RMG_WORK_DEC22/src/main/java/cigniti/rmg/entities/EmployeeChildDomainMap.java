package cigniti.rmg.entities;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emp_child_map")
public class EmployeeChildDomainMap implements Serializable{

	private static final long serialVersionUID = -6229409159932064865L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int rowid;
	
	@Column(name = "emp_id")
	private String employeeId;

	@Column(name = "emp_domn_subDomn_id")
	private int  empDomainMapId;
	
	@Column(name = "child_domain_id")
	private int childDomainId;

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

	public int getEmpDomainMapId() {
		return empDomainMapId;
	}

	public void setEmpDomainMapId(int empDomainMapId) {
		this.empDomainMapId = empDomainMapId;
	}

	public int getChildDomainId() {
		return childDomainId;
	}

	public void setChildDomainId(int childDomainId) {
		this.childDomainId = childDomainId;
	}

	@Override
	public String toString() {
		return "EmployeeChildDomainMap [rowid=" + rowid + ", employeeId=" + employeeId + ", empDomainMapId="
				+ empDomainMapId + ", childDomainId=" + childDomainId + "]";
	}
	
	


	
	
}
