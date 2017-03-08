package com.sidney.junit;

public class EmployeeController {
	private EmployeeService employeeService;
	public EmployeeController(EmployeeService employeeService){
		this.employeeService = employeeService;
	}
	
	public int getProjectedEmpolyeeCount(){
		final int actualEmployeeCount = employeeService.getEmployeeCount();
		
		return (int) Math.ceil(actualEmployeeCount * 1.2);
	}
}
