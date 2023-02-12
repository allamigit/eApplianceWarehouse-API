package e_appliance_warehouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
	public void addEmployee(Employee employee, String password) {
		employee.setEmployeeEmail(employee.getEmployeeEmail().toLowerCase());
		employeeRepository.save(employee);
		
		Employee emp = getEmployeeByFirstAndLastName(employee.getEmpFirstName(), employee.getEmpLastName());
		WarehouseUser newUser = WarehouseUser.builder()
				.userId(emp.getEmpFirstName().substring(0, 1).toLowerCase() + "-" + emp.getEmployeeId())
				.userFullName(emp.getEmpFirstName() + " " + emp.getEmpLastName())
				.password(password)
				.passwordReset(false)
				.jobTitle(emp.getJobTitle())
				.permissionGroup(emp.getPermissionGroup()).accountStatus(true)
				.build();
		System.out.println(newUser);
		warehouseUserRepository.save(newUser);
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
		employee.setEmployeeEmail(employee.getEmployeeEmail().toLowerCase());
		employeeRepository.save(employee);
	}
	
	// DELETE EMPLOYEE By employeeID
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteEmployee(employeeId);
	}

	// GET USER BY userID
	public WarehouseUser getUserById(String userId) {
		return warehouseUserRepository.getUserById(userId);
	}
	
	// USER LOGIN
	public Boolean userLogin(String userId, String password) {
		boolean resp = false;
		WarehouseUser user = getUserById(userId);
		
		if(user != null) {
			if(user.getUserId().equalsIgnoreCase(userId) & user.getPassword().equals(password)) {
				resp = true;
			}
		}
		
		return resp;
	}
	
	// CHANGE PASSWORD
	public void changePassword(String password, String userId) {
		warehouseUserRepository.changePassword(password, userId);
	}
	
	// SAVE LOGIN TIMESTAMP & USER COMMENT
	public void saveUserLoginTimestampAndComment(String loginTimestamp, String userComment, String userId) {
		warehouseUserRepository.saveUserLoginTimestampAndComment(loginTimestamp, userComment, userId);
	}
	
}
