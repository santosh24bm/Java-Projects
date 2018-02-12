package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="deliveryunit_master")
public class DuMaster implements Serializable {

	
	private static final long serialVersionUID = 2669626776476025451L;

	@Id
	@Column(name="du_id")
	private int du_id ; 
	
	
	@Column(name="du_name")
	private String du_name;


	public int getDu_id() {
		return du_id;
	}


	public void setDu_id(int du_id) {
		this.du_id = du_id;
	}


	public String getDu_name() {
		return du_name;
	}


	public void setDu_name(String du_name) {
		this.du_name = du_name;
	}


	@Override
	public String toString() {
		return "DuMaster [du_id=" + du_id + ", du_name=" + du_name + "]";
	}
	
	
	
	
}
