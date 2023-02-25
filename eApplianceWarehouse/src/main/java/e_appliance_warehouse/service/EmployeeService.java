package e_appliance_warehouse.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import e_appliance_warehouse.controller.EmployeeController;
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
		employee.setCreatedUserId(EmployeeController.uId);
		employee.setCreatedTimestamp(currentTimestamp);
		employee.setUpdatedUserId(EmployeeController.uId);
		employee.setUpdatedTimestamp(currentTimestamp);
		employeeRepository.save(employee);
		
		employee = getEmployeeByFirstAndLastName(employee.getEmpFirstName(), employee.getEmpLastName());
		String userId = employee.getEmpFirstName().substring(0, 1).toUpperCase() + "-" + employee.getEmployeeId();
		
		WarehouseUser newUser = WarehouseUser.builder()
				.userId(userId)
				.password("password1234")
				.passwordReset(Boolean.TRUE)
				.createdUserId(EmployeeController.uId)
				.createdTimestamp(currentTimestamp)
				.updatedUserId(EmployeeController.uId)
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
	public void updateEmployee(Employee employee) {
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		employee.setEmployeeEmail(employee.getEmployeeEmail().toLowerCase());
		employee.setUpdatedUserId(EmployeeController.uId);
		employee.setUpdatedTimestamp(currentTimestamp);
		employeeRepository.save(employee);
	}
	
	// DELETE EMPLOYEE By employeeID
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteEmployee(employeeId);
	}

	// GET ALL USERS
	public List<WarehouseUser> getAllUsers() {
		return warehouseUserRepository.getAllUsers();
	}
	
	// GET USER BY userID
	public WarehouseUser getUserById(String userId) {
		return warehouseUserRepository.getUserById(userId);
	}
	
	// USER LOGIN
	public Boolean userLogin(String userId, String password) {
		boolean resp = false;
		WarehouseUser user = getUserById(userId);
		
		if(user != null) 
			if(user.getUserId().equalsIgnoreCase(userId) & user.getPassword().equals(password)) 
				resp = true;
					
		return resp;
	}
	
	// CHANGE PASSWORD
	public String changePassword(String userId, String oldPassword, String newPassword) {
		userId = userId.toLowerCase();
		WarehouseUser user = warehouseUserRepository.getUserById(userId);
		String statusMessage = null;
		if(user != null && oldPassword != "" && newPassword != ""
				&& user.getUserId().equals(userId.toUpperCase()) 
				&& user.getPassword().equals(oldPassword)
				&& !newPassword.equals(oldPassword)) {		
			warehouseUserRepository.changePassword(userId, newPassword);
			statusMessage = "Password Changed Successfully";
		} else {
			if(userId != null && user == null) statusMessage = "Invalid Username";
			if(user != null && !user.getPassword().equals(oldPassword)) statusMessage = "Invalid Old Password";
			if(oldPassword != "" && newPassword != "" && newPassword.equals(oldPassword)) statusMessage = "Invalid New Password/Same Old Password";
			if(userId == null || (user != null && oldPassword == "") || (user != null && newPassword == "")) statusMessage = "Missing Parameters";
		}
		
		return statusMessage;
	}
	
	// SAVE LAST LOGIN TIMESTAMP & USER COMMENT
	public void saveUserLastLoginTimestampAndComment(String lastLoginTimestamp, String userComment, String userId) {
		warehouseUserRepository.saveUserLastLoginTimestampAndComment(lastLoginTimestamp, userComment, userId);
	}
	
}
