package com.fms.entity.employee.facilitymanager;

import java.util.List;

import com.fms.entity.employee.Employee;
import com.fms.entity.employee.attender.Attender;

/**
 * Pojo class for Attender details
 * 
 * @author Kishore Sanam
 *
 */
public class FacilityManager extends Employee {

	private List<Attender> attendersList;

	public List<Attender> getAttendersList() {
		return attendersList;
	}

	/**
	 * This is a constructor method for FacilityManager
	 * 
	 * @param empId
	 * @param empName
	 * @param attendersList
	 */
	public FacilityManager(Integer empId, String empName, List<Attender> attendersList) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.attendersList = attendersList;
	}

}
