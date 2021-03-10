package com.fms.entity.ticket.reservation;

import java.util.Date;

import com.fms.entity.ticket.Ticket;

/**
 * Pojo class for Reservation
 * 
 * @author Kishore Sanam
 *
 */
public class Reservation extends Ticket {

	private Date startTime;
	private Date endTime;

	/**
	 * Getter for Start Time
	 * 
	 * @return - startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Setter for Start Time
	 * 
	 * @param startTime
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Getter for End Time
	 * 
	 * @return - endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Setter for End Time
	 * 
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
