package cigniti.rmg.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 *
 * @param <T>
 */
@JsonPropertyOrder({ "details", "actionStatus" ,"errorMessage" })
public class ResourceSupport<T>   {
	@JsonProperty("details")
	private T details;
	@JsonProperty("actionStatus")
	private boolean actionStatus;
	@JsonProperty("errorMessage")
	private String errorMessage;

	public T getDetails() {
		return  details;
	}

	public void setDetails(T details) {
		this.details = details;
	}

	public boolean isActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(boolean actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
