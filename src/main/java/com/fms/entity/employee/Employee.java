package com.fms.entity.employee;

/**
 * Pojo class for Employee details
 * 
 * @author Kishore Sanam
 *
 */
public abstract class Employee {

	protected Integer empId;
	protected String empName;

	/**
	 * Getter for Employee Id
	 * 
	 * @return - empId
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * Setter for Employee Id
	 * 
	 * @param empId
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * Getter for Employee Name
	 * 
	 * @return - empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * Setter for Employee Name
	 * 
	 * @param empName
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
