package com.fms.entity.ticket;

/**
 * Pojo class for Ticket related details
 * 
 * @author Kishore Sanam
 *
 */
public abstract class Ticket {

	protected Integer ticketNo;
	protected String ticketPriority;
	protected String status;
	protected String description;
	protected String assignee = "Not Yet Assigned";

	/**
	 * Getter for Ticket Number
	 * 
	 * @return - ticketNo
	 */
	public Integer getTicketNo() {
		return ticketNo;
	}

	/**
	 * Setter for Ticket Number
	 * 
	 * @param ticketNo
	 */
	public void setTicketNo(Integer ticketNo) {
		this.ticketNo = ticketNo;
	}

	/**
	 * Getter for Ticket Priority
	 * 
	 * @return - ticketPriority
	 */
	public String getTicketPriority() {
		return ticketPriority;
	}

	/**
	 * Setter for Ticket Priority
	 * 
	 * @param ticketPriority
	 */
	public void setTicketPriority(String ticketPriority) {
		this.ticketPriority = ticketPriority;
	}

	/**
	 * Getter for Ticket Satus
	 * 
	 * @return - status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Setter for Ticket Satus
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Getter for Ticket Description
	 * 
	 * @return - description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter for Ticket Description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter for Assignee
	 * 
	 * @return - assignee
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * Setter for Assignee
	 * 
	 * @param assignee
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

}
