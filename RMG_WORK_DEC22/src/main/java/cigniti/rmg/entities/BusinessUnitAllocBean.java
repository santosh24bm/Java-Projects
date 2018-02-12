package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="business_unit_aloocation_status_vw")
public class BusinessUnitAllocBean implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5967925669523764495L;

	@Id
	@Column(name = "bu_name")
	private String bu_name;
		
	@Id
	@Column(name = "alloc_status")
	private String alloc_stauts;
	
	@Column(name="count")
	private int count ;

	public String getBu_name() {
		return bu_name;
	}

	public void setBu_name(String bu_name) {
		this.bu_name = bu_name;
	}

	public String getAlloc_stauts() {
		return alloc_stauts;
	}

	public void setAlloc_stauts(String alloc_stauts) {
		this.alloc_stauts = alloc_stauts;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "BusinessUnitAllocBean [bu_name=" + bu_name + ", alloc_stauts=" + alloc_stauts + ", count=" + count
				+ "]";
	}
	
	
	
}
