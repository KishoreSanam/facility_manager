package com.fms.entity.employee.attender;

import com.fms.entity.employee.Employee;

/**
 * Pojo class for Attender details
 * 
 * @author Kishore Sanam
 *
 */
public class Attender extends Employee {
	/**
	 * This is a constructor method for Attender
	 * 
	 * @param empId
	 * @param empName
	 */
	public Attender(Integer empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}

}
