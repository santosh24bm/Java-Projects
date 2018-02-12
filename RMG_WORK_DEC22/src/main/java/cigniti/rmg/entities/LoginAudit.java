package cigniti.rmg.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Kumar Gaurav
 *
 */
/**
 * The persistent class for the login_audit database table.
 * 
 */
@Entity
@Table(name="login_audit")
public class LoginAudit implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1390554536357394199L;
	
	@Id
	@Column(name="user_id")
	private String user_id;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="last_login_ip_address")
	private String last_login_ip_address;
	
	@Column(name="hostname")
	private String hostname;
	
	@Column(name="last_login_date")
	private Date last_login_date;
	
	@Column(name="updated_login_date")
	private Date updated_login_date;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(Date last_login_date) {
		this.last_login_date = last_login_date;
	}

	public String getLast_login_ip_address() {
		return last_login_ip_address;
	}

	public void setLast_login_ip_address(String last_login_ip_address) {
		this.last_login_ip_address = last_login_ip_address;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Date getUpdated_login_date() {
		return updated_login_date;
	}

	public void setUpdated_login_date(Date updated_login_date) {
		this.updated_login_date = updated_login_date;
	}
}