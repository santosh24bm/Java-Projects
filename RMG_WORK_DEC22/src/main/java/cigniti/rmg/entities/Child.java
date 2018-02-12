package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class Child implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6916424904891649218L;


	private String alloc_stauts;
	

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
	
	
	
	
}
