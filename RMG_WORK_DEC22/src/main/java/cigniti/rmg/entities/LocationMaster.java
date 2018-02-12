package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location_master")
public class LocationMaster implements Serializable{

	private static final long serialVersionUID = -2190163518595296063L;


	@Id
	@Column(name="loc_id")
	private int loc_id ; 
	
	
	@Column(name="location_name")
	private String location_name;


	public int getLoc_id() {
		return loc_id;
	}


	public void setLoc_id(int loc_id) {
		this.loc_id = loc_id;
	}


	public String getLocation_name() {
		return location_name;
	}


	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}


	@Override
	public String toString() {
		return "LocationMaster [loc_id=" + loc_id + ", location_name=" + location_name + "]";
	}
	
	
	
	
	
	
	
}
