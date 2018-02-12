package cigniti.rmg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="practices_aloocation_status_vw")
public class PracticesAllocationBean {

	@Id
	@Column(name = "alloc_status")
	private String alloc_stauts;
	
	@Column(name="count")
	private int count ;

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
		return "PracticesAllocationBean [alloc_stauts=" + alloc_stauts + ", count=" + count + "]";
	}
	
	
	
	
	
}
