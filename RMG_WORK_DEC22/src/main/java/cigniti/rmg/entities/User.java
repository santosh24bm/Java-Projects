package cigniti.rmg.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -4208589337957211946L;
	@JsonProperty("userId")
	@JsonDeserialize
	private String userId;
	@JsonProperty("password")
	@JsonDeserialize
	private String password;

	 @JsonCreator
	 public User(@JsonProperty("userId") String userId, @JsonProperty("password") String password) {
	  this.userId = userId;
	  this.password = password;
	 }

	/* public User() {

	 }
*/
	 public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	 public String toString() {
	  StringBuilder str = new StringBuilder();
	  str.append("User Id:- " + getUserId());
	  str.append(" Password :- " + getPassword());
	  return str.toString();
	 }
}
