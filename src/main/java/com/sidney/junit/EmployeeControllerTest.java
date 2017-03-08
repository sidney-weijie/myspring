package com.sidney.junit;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

public class EmployeeControllerTest {


	
	@Test
	public void shouldReturnProjectedCountOfEmployeesFromTheService(){
		
		EmployeeService mock = PowerMockito.mock(EmployeeService.class);
		
		PowerMockito.when(mock.getEmployeeCount()).thenReturn(8);
		
		EmployeeController employeeController = new EmployeeController(mock);
		//Assert.assertEquals(8, employeeController.getProjectedEmpolyeeCount());
	}
	
}
