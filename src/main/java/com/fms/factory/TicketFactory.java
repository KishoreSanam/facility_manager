package com.fms.factory;

import com.fms.entity.ticket.Ticket;
import com.fms.entity.ticket.complaint.Complaint;
import com.fms.entity.ticket.incident.Incident;
import com.fms.entity.ticket.request.Request;
import com.fms.entity.ticket.reservation.Reservation;

/**
 * This class acts as a factory class which returns the respective object based
 * on the received request
 * 
 * @author Kishore Sanam
 *
 */
public class TicketFactory {
	/**
	 * This method returns the ticket object
	 * 
	 * @param ticketType
	 * @return
	 */
	public Ticket getObject(String ticketType) {
		if (ticketType == null) {
			return null;
		}
		if (ticketType.equalsIgnoreCase("COMPLAINT")) {
			return new Complaint();

		} else if (ticketType.equalsIgnoreCase("INCIDENT")) {
			return new Incident();

		} else if (ticketType.equalsIgnoreCase("REQUEST")) {
			return new Request();
		}

		else if (ticketType.equalsIgnoreCase("RESERVATION")) {
			return new Reservation();
		}
		return null;
	}
}