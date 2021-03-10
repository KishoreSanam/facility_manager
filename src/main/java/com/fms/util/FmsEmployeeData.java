package com.fms.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.entity.employee.attender.Attender;
import com.fms.entity.employee.facilitymanager.FacilityManager;

/**
 * This class helps in initializing the employee data
 * 
 * @author Kishore Sanam
 *
 */
public class FmsEmployeeData {

	private FmsEmployeeData() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * This is a static method for initializing the static employee data
	 * 
	 * @return attenderMap
	 */
	public static Map<Integer, FacilityManager> getEmployeeData() {
		Map<Integer, FacilityManager> attenderMap = new HashMap<>();

		Attender a1 = new Attender(11, "John");
		Attender a2 = new Attender(12, "Samuel");
		Attender a3 = new Attender(13, "Robert");
		Attender a4 = new Attender(14, "Watson");
		Attender a5 = new Attender(15, "Holmes");
		Attender a6 = new Attender(16, "Alexander");
		List<Attender> attendersList1 = Arrays.asList(a1, a3, a6);
		List<Attender> attendersList2 = Arrays.asList(a2, a4, a5);

		FacilityManager ramu = new FacilityManager(1, "Ramu", attendersList1);
		FacilityManager raju = new FacilityManager(2, "Raju", attendersList2);
		attenderMap.put(1, ramu);
		attenderMap.put(2, raju);
		return attenderMap;
	}
}
