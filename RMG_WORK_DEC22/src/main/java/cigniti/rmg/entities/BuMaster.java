package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bu_master")
public class BuMaster implements Serializable{


	private static final long serialVersionUID = 1789723696849948376L;

	@Id
	@Column(name="bu_id")
	private int bu_id ; 
	
	
	@Column(name="bu_name")
	private String bu_name;


	public int getBu_id() {
		return bu_id;
	}


	public void setBu_id(int bu_id) {
		this.bu_id = bu_id;
	}


	public String getBu_name() {
		return bu_name;
	}


	public void setBu_name(String bu_name) {
		this.bu_name = bu_name;
	}


	@Override
	public String toString() {
		return "BuMaster [bu_id=" + bu_id + ", bu_name=" + bu_name + "]";
	}
	
	
	
}
