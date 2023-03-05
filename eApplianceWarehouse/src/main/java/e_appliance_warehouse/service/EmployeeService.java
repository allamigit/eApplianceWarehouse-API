package e_appliance_warehouse.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import e_appliance_warehouse.controller.WarehouseUserController;
import e_appliance_warehouse.repository.EmployeeRepository;
import e_appliance_warehouse.repository.WarehouseUserRepository;
import e_appliance_warehouse.table.Employee;
import e_appliance_warehouse.table.WarehouseUser;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;	
	private WarehouseUserRepository warehouseUserRepository;
	
	// ADD NEW EMPLOYEE
	public void addEmployee(Employee employee) {
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		employee.setAccountStatus(Boolean.TRUE);
		employee.setEmployeeEmail(employee.getEmpFirstName().toLowerCase() + "." + employee.getEmpLastName().toLowerCase() + "@eappwh.com");
		employee.setCreatedUserId(WarehouseUserController.uId);
		employee.setCreatedTimestamp(currentTimestamp);
		employee.setUpdatedUserId(WarehouseUserController.uId);
		employee.setUpdatedTimestamp(currentTimestamp);
		employeeRepository.save(employee);
		
		employee = getEmployeeByFirstAndLastName(employee.getEmpFirstName(), employee.getEmpLastName());
		String userId = employee.getEmpFirstName().substring(0, 1).toUpperCase() + "-" + employee.getEmployeeId();
		
		WarehouseUser newUser = WarehouseUser.builder()
				.userId(userId)
				.password("password1234")
				.resetPassword(Boolean.TRUE)
				.createdUserId(WarehouseUserController.uId)
				.createdTimestamp(currentTimestamp)
				.updatedUserId(WarehouseUserController.uId)
				.updatedTimestamp(currentTimestamp)
				.build();
		warehouseUserRepository.save(newUser);

		employee.setUserId(userId);
		employeeRepository.save(employee);
	}
	
	// GET ALL EMPLOYEES
	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployees();
	}
	
	// GET INACTIVE EMPLOYEES
	public List<Employee> getInactiveEmployees() {
		return employeeRepository.getInactiveEmployees();
	}
	
	// GET ACTIVE EMPLOYEES
	public List<Employee> getActiveEmployees() {
		return employeeRepository.getActiveEmployees();
	}	
	
	// GET EMPLOYEE BY employeeID
	public Employee getEmployeeById(Long employeeId) {
		return employeeRepository.getEmployeeById(employeeId);
	}
	
	// GET EMPLOYEE BY userID
	public Employee getEmployeeByUserId(String userId) {
		return employeeRepository.getEmployeeByUserId(userId);
	}
	
	// GET EMPLOYEE BY empFirstName (OR CONTAINS PART OF THE NAME)
	public List<Employee> getEmployeeByFirstName(String empFirstName) {
		return employeeRepository.getEmployeeByFirstName(empFirstName);
	}
	
	// GET EMPLOYEE BY empFirstName AND empLastName
	public Employee getEmployeeByFirstAndLastName(String empFirstName, String empLastName) {
		return employeeRepository.getEmployeeByFirstAndLastName(empFirstName, empLastName);
	}
	
	// UPDATE EMPLOYEE
	public Boolean updateEmployee(Employee employee) {
		boolean resp = false;
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		if(employeeRepository.getEmployeeById(employee.getEmployeeId()) != null) {
			resp = true;
			employee.setEmployeeEmail(employee.getEmployeeEmail().toLowerCase());
			employee.setUpdatedUserId(WarehouseUserController.uId);
			employee.setUpdatedTimestamp(currentTimestamp);
			employeeRepository.save(employee);
		}
		
		return resp;
	}
	
	// DELETE EMPLOYEE By employeeID
	public Boolean deleteEmployee(Long employeeId) {
		boolean resp = false;
		Employee employee = employeeRepository.getEmployeeById(employeeId);
		if(employee != null) {
			resp = true;
			employeeRepository.deleteEmployee(employeeId);
			warehouseUserRepository.deleteUser(employee.getUserId());
		}
		
		return resp;
	}
	
}
