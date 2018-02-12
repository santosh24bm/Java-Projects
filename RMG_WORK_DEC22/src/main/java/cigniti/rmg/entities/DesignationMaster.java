package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="designation_master")
public class DesignationMaster implements Serializable{

	
	private static final long serialVersionUID = 3622932437994962264L;
	
	@Id
	@Column(name="desg_id")
	private int desg_id ; 
	
	
	@Column(name="desg_name")
	private String desg_name;


	public int getDesg_id() {
		return desg_id;
	}


	public void setDesg_id(int desg_id) {
		this.desg_id = desg_id;
	}


	public String getDesg_name() {
		return desg_name;
	}


	public void setDesg_name(String desg_name) {
		this.desg_name = desg_name;
	}


	@Override
	public String toString() {
		return "DesignationMaster [desg_id=" + desg_id + ", desg_name=" + desg_name + "]";
	}
	
	
	
	
	
	
	
	

}
