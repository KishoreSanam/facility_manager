package com.fms.service;

import static com.fms.util.FmsConstants.ASSIGNEE;
import static com.fms.util.FmsConstants.DESCRIPTION;
import static com.fms.util.FmsConstants.END_DATE;
import static com.fms.util.FmsConstants.INVALID_TICKET_MSG;
import static com.fms.util.FmsConstants.IN_PROGRESS;
import static com.fms.util.FmsConstants.NEW;
import static com.fms.util.FmsConstants.NO_RECORDS_MSG;
import static com.fms.util.FmsConstants.RESOLVED;
import static com.fms.util.FmsConstants.START_DATE;
import static com.fms.util.FmsConstants.STATUS;
import static com.fms.util.FmsConstants.TICKET_NO;
import static com.fms.util.FmsConstants.TICKET_PRIORITY;
import static com.fms.util.FmsConstants.TICKET_TYPE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

import com.fms.entity.employee.attender.Attender;
import com.fms.entity.employee.facilitymanager.FacilityManager;
import com.fms.entity.ticket.Ticket;
import com.fms.entity.ticket.complaint.Complaint;
import com.fms.entity.ticket.incident.Incident;
import com.fms.entity.ticket.request.Request;
import com.fms.entity.ticket.reservation.Reservation;
import com.fms.factory.TicketFactory;
/**
 * This is a Service Class used to create/assign/close tickets
 * @author Kishore Sanam
 *
 */
public class ServiceImpl {

	HashMap<Integer, Ticket> map = new HashMap<>();
	TicketFactory ticketFactory = new TicketFactory();
	Scanner sc = new Scanner(System.in);
	Random random = new Random();
	List<String> validPriorities = Arrays.asList("HIGH", "MEDIUM", "LOW");
	Map<Integer, FacilityManager> attenderMap = new HashMap<>();
/**
 * This method creates the ticket
 * @param string
 * @throws ParseException
 */
	public void createTicket(String string) throws ParseException {
		if ("Incident".equalsIgnoreCase(string)) {

			Incident incident = (Incident) ticketFactory.getObject(string);
			setBasicTicketDetails(incident);
			map.put(incident.getTicketNo(), incident);
			System.out.println("Incident Registered Successfully and Your Incident No is: " + incident.getTicketNo());
		}
		if ("Request".equalsIgnoreCase(string)) {
			Request request = (Request) ticketFactory.getObject(string);
			setBasicTicketDetails(request);
			map.put(request.getTicketNo(), request);
			System.out.println("Request Done Successfully and Your Request No is: " + request.getTicketNo());

		}
		if ("Reservation".equalsIgnoreCase(string)) {
			Reservation reservation = (Reservation) ticketFactory.getObject("Reservation");
			setBasicTicketDetails(reservation);

			System.out.println("Enter the Start Date Time for meeting (dd-mm-yyyy):");
			String startDate = sc.nextLine();

			Date startTime = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
			LocalDate localDate = LocalDate.now();
			Date today = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			if(startTime.before(today))
			{
				System.out.println("Start Date cannot be earlier than Today. Re-directing back to Menu Services");
				return;
			}
			
			System.out.println("Enter the End Date Time for meeting (dd-mm-yyyy):");
			String endDate = sc.nextLine();
			Date endTime = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);
			if (!startTime.after(endTime)) {
				reservation.setStartTime(startTime);
				reservation.setEndTime(endTime);
				map.put(reservation.getTicketNo(), reservation);
				System.out.println(
						"Reservation Done Successfully and Your Reservation No is: " + reservation.getTicketNo());
			} else {
				System.out.println("Start Date cannot be after End Date. Re-directing back to Menu Services");
			}

		}
		if ("Complaint".equalsIgnoreCase(string)) {

			Complaint complaint = (Complaint) ticketFactory.getObject(string);
			setBasicTicketDetails(complaint);
			map.put(complaint.getTicketNo(), complaint);
			System.out
					.println("Complaint Registered Successfully and Your Complaint No is: " + complaint.getTicketNo());
		}
	}
/**
 * This method sets the basic ticket details
 */
	private void setBasicTicketDetails(Ticket ticket) {
		System.out.println("\nEnter the Priority of the Ticket (High / Medium /Low):");
		String ticketPriority = sc.nextLine();
		if (validPriorities.contains(ticketPriority.toUpperCase())) {
			ticket.setTicketPriority(ticketPriority.toUpperCase());
			Integer ticketNo = random.nextInt(1000);
			ticket.setTicketNo(ticketNo);
			System.out.println("Enter the Description for the Ticket :");
			String purpose = sc.nextLine();
			ticket.setDescription(purpose.toUpperCase());
			ticket.setStatus(NEW);
		}

		else {
			System.out.println("\n Wrong Input !! Please enter valid priority and try again");
			setBasicTicketDetails(ticket);
		}

	}
/**
 * This method shows the open tickets
 */
	public void showOpenTickets() {
		Stream<Entry<Integer, Ticket>> result = map.entrySet().stream()
				.filter(newTickets -> NEW.equalsIgnoreCase(newTickets.getValue().getStatus()));
		if (result.count() != 0) {
			System.out.println("Below are the Open tickets:");
			for (Map.Entry<Integer, Ticket> entry : map.entrySet()) {
				Ticket ticket = entry.getValue();
				if (NEW.equalsIgnoreCase(ticket.getStatus()) && ticket instanceof Reservation) {
					Reservation reservation = (Reservation) ticket;
					System.out.println("[" + TICKET_TYPE + " : " + reservation.getClass().getSimpleName() + ", "
							+ TICKET_NO + " : " + entry.getKey() + ", " + TICKET_PRIORITY + " : "
							+ reservation.getTicketPriority() + ", " + STATUS + " : " + reservation.getStatus() + ", "
							+ DESCRIPTION + " : " + reservation.getDescription() + ", " + ASSIGNEE + " : "
							+ reservation.getAssignee() + " , " + START_DATE + " : " + reservation.getStartTime() + ", "
							+ END_DATE + " : " + reservation.getEndTime() + "]");
				} else if (NEW.equalsIgnoreCase(ticket.getStatus()) && !(ticket instanceof Reservation)) {

					System.out.println("[" + TICKET_TYPE + " : " + ticket.getClass().getSimpleName() + ", " + TICKET_NO
							+ " : " + entry.getKey() + ", " + TICKET_PRIORITY + " : " + ticket.getTicketPriority()
							+ ", " + STATUS + " : " + ticket.getStatus() + ", " + DESCRIPTION + " : "
							+ ticket.getDescription() + ", " + ASSIGNEE + " : " + ticket.getAssignee() + "]");

				}
			}
		} else {
			System.out.println(NO_RECORDS_MSG);
		}
	}
/**
 * This method shows the in-progress tickets
 */
	public void showInProgressTickets() {
		Stream<Entry<Integer, Ticket>> result = map.entrySet().stream()
				.filter(inProgressTickets -> IN_PROGRESS.equalsIgnoreCase(inProgressTickets.getValue().getStatus()));
		if (result.count() != 0) {

			System.out.println("Below are the In-Progress tickets:");

			for (Map.Entry<Integer, Ticket> entry : map.entrySet()) {
				Ticket ticket = entry.getValue();

				if (IN_PROGRESS.equalsIgnoreCase(entry.getValue().getStatus())
						&& entry.getValue() instanceof Reservation) {
					Reservation reservation = (Reservation) ticket;
					System.out.println("[" + TICKET_TYPE + " : " + reservation.getClass().getSimpleName() + ", "
							+ TICKET_NO + " : " + entry.getKey() + ", " + TICKET_PRIORITY + " : "
							+ reservation.getTicketPriority() + ", " + STATUS + " : " + reservation.getStatus() + ", "
							+ DESCRIPTION + " : " + reservation.getDescription() + ", " + ASSIGNEE + " : "
							+ reservation.getAssignee() + " , " + START_DATE + " : " + reservation.getStartTime() + ", "
							+ END_DATE + " : " + reservation.getEndTime() + "]");
				} else if (IN_PROGRESS.equalsIgnoreCase(entry.getValue().getStatus())
						&& !(entry.getValue() instanceof Reservation)) {

					System.out.println("[" + TICKET_TYPE + " : " + ticket.getClass().getSimpleName() + ", " + TICKET_NO
							+ " : " + entry.getKey() + ", " + TICKET_PRIORITY + " : " + ticket.getTicketPriority()
							+ ", " + STATUS + " : " + ticket.getStatus() + ", " + DESCRIPTION + " : "
							+ ticket.getDescription() + ", " + ASSIGNEE + " : " + ticket.getAssignee() + "]");

				}
			}
		} else {
			System.out.println(NO_RECORDS_MSG);
		}
	}
/**
 * This method shows the closed tickets
 */
	public void showClosedTickets() {
		Stream<Entry<Integer, Ticket>> result = map.entrySet().stream()
				.filter(resolvedTickets -> RESOLVED.equalsIgnoreCase(resolvedTickets.getValue().getStatus()));
		if (result.count() != 0) {

			System.out.println("Below are the Resolved tickets:");

			for (Map.Entry<Integer, Ticket> entry : map.entrySet()) {
				Ticket ticket = entry.getValue();

				if (RESOLVED.equalsIgnoreCase(entry.getValue().getStatus())
						&& entry.getValue() instanceof Reservation) {
					Reservation reservation = (Reservation) ticket;

					System.out.println("[" + TICKET_TYPE + " : " + reservation.getClass().getSimpleName() + ", "
							+ TICKET_NO + " : " + entry.getKey() + ", " + TICKET_PRIORITY + " : "
							+ reservation.getTicketPriority() + ", " + STATUS + " : " + reservation.getStatus() + ", "
							+ DESCRIPTION + " : " + reservation.getDescription() + ", " + ASSIGNEE + " : "
							+ reservation.getAssignee() + " , " + START_DATE + " : " + reservation.getStartTime() + ", "
							+ END_DATE + " : " + reservation.getEndTime() + "]");
				} else if (RESOLVED.equalsIgnoreCase(entry.getValue().getStatus())
						&& !(entry.getValue() instanceof Reservation)) {

					System.out.println("[" + TICKET_TYPE + " : " + ticket.getClass().getSimpleName() + ", " + TICKET_NO
							+ " : " + entry.getKey() + ", " + TICKET_PRIORITY + " : " + ticket.getTicketPriority()
							+ ", " + STATUS + " : " + ticket.getStatus() + ", " + DESCRIPTION + " : "
							+ ticket.getDescription() + ", " + ASSIGNEE + " : " + ticket.getAssignee() + "]");

				}
			}
		} else {
			System.out.println(NO_RECORDS_MSG);

		}
	}
/**
 * This method closes the tickets which are open
 */
	public void closeOpenTickets() {
		Stream<Entry<Integer, Ticket>> newTickets = map.entrySet().stream()
				.filter(openNewTickets -> NEW.equalsIgnoreCase(openNewTickets.getValue().getStatus()));
		Stream<Entry<Integer, Ticket>> inProgressTickets = map.entrySet().stream().filter(
				openInProgressTickets -> IN_PROGRESS.equalsIgnoreCase(openInProgressTickets.getValue().getStatus()));
		if (newTickets.count() != 0 || inProgressTickets.count() != 0) {
			System.out.println("Enter the Ticket No to be closed :");
			Integer ticketNo = sc.nextInt();
			Ticket t = map.get(ticketNo);
			if (t != null) {

				t.setStatus(RESOLVED);
				map.put(ticketNo, t);
				System.out.println("Ticket Closed Successfully and changed to Resolved");
			} else {
				System.out.println(INVALID_TICKET_MSG);
				closeOpenTickets();
			}
		} else {
			System.out.println("No Open Tickets Found to be closed");
		}
	}
/**
 * This method assigns the open tickets
 * @param fmId
 * @param employeeData
 */
	public void assignOpenTickets(Integer fmId, Map<Integer, FacilityManager> employeeData) {
		Stream<Entry<Integer, Ticket>> result = map.entrySet().stream()
				.filter(newOpenTickets -> NEW.equalsIgnoreCase(newOpenTickets.getValue().getStatus()));
		List<String> validNames = new ArrayList<>();
		if (result.count() != 0) {
			System.out.println("Enter the Ticket No which you want to Assign :");
			Integer ticketNo = sc.nextInt();
			Ticket t = map.get(ticketNo);
			if (t != null) {
				sc.nextLine();
				System.out.println("Below are the available attenders :");
				List<Attender> availableList = employeeData.get(fmId).getAttendersList();
				for (Attender attender : availableList) {
					System.out.println(attender.getEmpName());
					validNames.add(attender.getEmpName().toUpperCase());
				}
				System.out.println("Enter the Name of the Assignee to whom you want to assign the ticket :");
				String assignee = sc.nextLine();
				if (validNames.contains(assignee.toUpperCase())) {
					t.setStatus(IN_PROGRESS);
					t.setAssignee(assignee);
					map.put(ticketNo, t);
					System.out.println("Ticket Assigned Successfully and changed to In Progress");
				} else {
					System.out.println("Not a valid assignee..Please Try Again");
					assignOpenTickets(fmId, employeeData);
				}
			} else {
				System.out.println(INVALID_TICKET_MSG);
				assignOpenTickets(fmId, employeeData);
			}
		} else {
			System.out.println("No Open Tickets Found to be assigned");
		}
	}
}
