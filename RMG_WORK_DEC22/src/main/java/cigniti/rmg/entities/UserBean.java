package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Kumar Gaurav
 *
 */

@Entity
@Table(name = "user_master")
public class UserBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6187313720835411998L;

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "project")
	private String project;
	
	@Column(name = "bu_id")
	private int bu_id;
	
	@Column(name = "du_id")
	private int du_id;
	
	@Transient
	private String password;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public int getBu_id() {
		return bu_id;
	}

	public void setBu_id(int bu_id) {
		this.bu_id = bu_id;
	}

	public int getDu_id() {
		return du_id;
	}

	public void setDu_id(int du_id) {
		this.du_id = du_id;
	}
}
