package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ats_aloocation_status_vw")
public class AtsAllocStatusBean implements Serializable{

	
	private static final long serialVersionUID = 2949124367142991918L;

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
		return "AllocStatusBean [alloc_stauts=" + alloc_stauts + ", count=" + count + "]";
	}
	
	
	
}
