package com.fms.client;

import static com.fms.util.FmsConstants.ENTER_INPUT;
import static com.fms.util.FmsConstants.INVALID_INPUT;
import static com.fms.util.FmsConstants.THANKS_MSG;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;

import com.fms.entity.employee.facilitymanager.FacilityManager;
import com.fms.service.ServiceImpl;
import com.fms.util.FmsEmployeeData;

/**
 * This Class is the main class for starting the execution of the program
 * 
 * @author Kishore Sanam
 *
 */
public class UserClient {

	public static void main(String[] args) throws IOException, SQLException, ParseException {
		ServiceImpl service = new ServiceImpl();
		menuServices(service);
	}

	/**
	 * This method contains menu services of the system
	 * 
	 * @param service
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	private static void menuServices(ServiceImpl service) throws IOException, SQLException, ParseException {

		Integer fmId;
		Map<Integer, FacilityManager> employeeMap = FmsEmployeeData.getEmployeeData();
		System.out.println(
				"\nWelcome to Facility Management System \n------------------------------------------\n \n Select the respective number from below options :\n\n 1. Facility Manager \n 2. Employee");
		try (Scanner sc = new Scanner(System.in)) {

			System.out.println(ENTER_INPUT);
			Integer input = sc.nextInt();
			switch (input) {
			case 1:
				System.out.println("Enter Facility Manager Id :");
				fmId = sc.nextInt();
				if (employeeMap.containsKey(fmId)) {
					adminServices(fmId, service, employeeMap);
				} else {
					System.out.println("No Such Facility Manager Id. Please Try Again");
					menuServices(service);
				}
				break;
			case 2:
				employeeServices(service);
				break;
			default:
				System.out.println(INVALID_INPUT);
				menuServices(service);
				break;
			}
		}
	}

	/**
	 * This method is used to show all the admin related services
	 * 
	 * @param fmId
	 * @param service
	 * @param employeeMap
	 * @throws IOException
	 * @throws SQLException
	 * @throws ParseException
	 */
	private static void adminServices(Integer fmId, ServiceImpl service, Map<Integer, FacilityManager> employeeMap)
			throws IOException, SQLException, ParseException {
		try (Scanner sc = new Scanner(System.in)) {

			System.out.print(
					"------------------------------------------\n Select the respective number from below options :\n\n 1. Show Open Tickets \n 2. Show In-Progress Tickets \n 3. Show Resolved Tickets  \n 4. Assign the Open Tickets  \n 5. Close the Open Tickets \n 6. Exit");
			System.out.println(ENTER_INPUT);
			Integer input = sc.nextInt();

			switch (input) {

			case 1:
				service.showOpenTickets();
				adminServices(fmId, service, employeeMap);
				break;
			case 2:
				service.showInProgressTickets();
				adminServices(fmId, service, employeeMap);
				break;
			case 3:
				service.showClosedTickets();
				adminServices(fmId, service, employeeMap);
				break;
			case 4:
				service.assignOpenTickets(fmId, employeeMap);
				adminServices(fmId, service, employeeMap);
				break;
			case 5:
				service.closeOpenTickets();
				adminServices(fmId, service, employeeMap);
				break;
			case 6:
				System.out.println(THANKS_MSG);
				break;
			default:
				System.out.println(INVALID_INPUT);
				adminServices(fmId, service, employeeMap);
				break;
			}
		}
	}

	/**
	 * This method is used to show all the employee availed services
	 * 
	 * @param service
	 * @throws ParseException
	 * @throws IOException
	 * @throws SQLException
	 */
	private static void employeeServices(ServiceImpl service) throws ParseException, IOException, SQLException {
		System.out.print(
				"------------------------------------------\n Select the respective number from below options :\n\n 1. Create New Incident \n 2. Create New Request \n 3. Create New Reservation \n 4. Create New Complaint \n 5. Exit");
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println(ENTER_INPUT);
			Integer input = sc.nextInt();

			switch (input) {
			case 1:
				service.createTicket("Incident");
				menuServices(service);
				break;
			case 2:
				service.createTicket("Request");
				menuServices(service);
				break;
			case 3:
				service.createTicket("Reservation");
				menuServices(service);
				break;
			case 4:
				service.createTicket("Complaint");
				menuServices(service);
				break;

			case 5:
				System.out.println(THANKS_MSG);
				break;
			default:
				System.out.println(INVALID_INPUT);
				menuServices(service);
				break;
			}
		}
	}
}
