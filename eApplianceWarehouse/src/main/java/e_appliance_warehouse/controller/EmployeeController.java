package e_appliance_warehouse.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import e_appliance_warehouse.model.EmployeeResponse;
import e_appliance_warehouse.model.QueryStatus;
import e_appliance_warehouse.service.EmployeeService;
import e_appliance_warehouse.table.Employee;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "employee")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService employeeService;
	
	// ADD NEW EMPLOYEE
	@PostMapping(value = "addNewEmployee.wh")
	public QueryStatus addEmployee(HttpServletResponse resp, @RequestBody Employee employee) {		
		employeeService.addEmployee(employee);
				
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription("New Employee Added")
				.build();
	}
	
	// GET ALL EMPLOYEES
	@GetMapping(value = "getAllEmployees.wh")
	public EmployeeResponse getAllEmployees(HttpServletResponse resp) {
		List<Employee> employeeList = employeeService.getAllEmployees();
		
		return EmployeeResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(employeeList.isEmpty()?"No Result Found":"All Employees Retrieved Successfully")
							.build())
				.queryResult(employeeList)
				.build();
	}
	
	// GET INACTIVE EMPLOYEES
	@GetMapping(value = "getInactiveEmployees.wh")
	public EmployeeResponse getInactiveEmployees(HttpServletResponse resp) {
		List<Employee> employeeList = employeeService.getInactiveEmployees();
		
		return EmployeeResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(employeeList.isEmpty()?"No Result Found":"All Inactive Employees Retrieved Successfully")
							.build())
				.queryResult(employeeList)
				.build();
	}
	
	// GET ACTIVE EMPLOYEES
	@GetMapping(value = "getActiveEmployees.wh")
	public EmployeeResponse getActiveEmployees(HttpServletResponse resp) {
		List<Employee> employeeList = employeeService.getActiveEmployees();
		
		return EmployeeResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(employeeList.isEmpty()?"No Result Found":"All Active Employees Retrieved Successfully")
							.build())
				.queryResult(employeeList)
				.build();
	}	
	
	// GET EMPLOYEE BY employeeID
	@GetMapping(value = "getEmployee.wh")
	public EmployeeResponse getEmployeeById(HttpServletResponse resp, @RequestParam(name = "employeeId") Long employeeId) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employeeService.getEmployeeById(employeeId));
		
		return EmployeeResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(employeeList.get(0)==null?"No Result Found":"Employee Retrieved Successfully")
							.build())
				.queryResult(employeeList)
				.build();
	}
	
	// GET Employee BY empFirstName (OR CONTAINS PART OF THE NAME)
	@GetMapping(value = "getEmployeeByName.wh")
	public EmployeeResponse getEmployeeByFirstName(HttpServletResponse resp, @RequestParam(name = "empFirstName") String empFirstName) {
		List<Employee> employeeList = employeeService.getEmployeeByFirstName(empFirstName.toLowerCase());

		return EmployeeResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(employeeList.isEmpty()?"No Result Found":"Employees by First Name Retrieved Successfully")
							.build())
				.queryResult(employeeList)
				.build();
	}
	
	// GET Employee BY empFirstName AND empLastName
	@GetMapping(value = "getEmployeeByFullName.wh")
	public EmployeeResponse getEmployeeByFirstAndLastName(HttpServletResponse resp, 
			@RequestParam(name = "empFirstName") String empFirstName, 
			@RequestParam(name = "empLastName") String empLastName) {

		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employeeService.getEmployeeByFirstAndLastName(empFirstName, empLastName));
		
		return EmployeeResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(employeeList.get(0)==null?"No Result Found":"Employee by First and Last Name Retrieved Successfully")
							.build())
				.queryResult(employeeList)
				.build();
	}
	
	// UPDATE EMPLOYEE
	@PutMapping(value = "updateEmployee.wh")
	public QueryStatus updateUser(HttpServletResponse resp, @RequestBody Employee employee) {
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(!employeeService.updateEmployee(employee)?"No Result Found":"Employee Updated")
				.build();
	}
	
	// DELETE EMPLOYEE By employeeID
	@DeleteMapping(value = "deleteEmployee.wh")
	public QueryStatus deleteEmployee(HttpServletResponse resp, @RequestParam(name = "employeeId") Long employeeId) throws Exception {
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(!employeeService.deleteEmployee(employeeId)?"No Result Found":"Employee Deleted")
				.build();
	}
	
}
