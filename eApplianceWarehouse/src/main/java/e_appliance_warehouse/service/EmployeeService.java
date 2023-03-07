package e_appliance_warehouse.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import e_appliance_warehouse.controller.WarehouseUserController;
import e_appliance_warehouse.repository.EmployeeRepository;
import e_appliance_warehouse.repository.WarehouseUserRepository;
import e_appliance_warehouse.table.Employee;
import e_appliance_warehouse.table.WarehouseUser;
import e_appliance_warehouse.util.PasswordUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;	
	private WarehouseUserRepository warehouseUserRepository;
	
	// ADD NEW EMPLOYEE
	public Boolean addEmployee(Employee employee) {
		boolean resp = false;
		if(WarehouseUserController.loggedUser.getPermissionList().getEmployees()) {
			resp = true;
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			employee.setEmployeeEmail(employee.getEmpFirstName().toLowerCase() + "." + employee.getEmpLastName().toLowerCase() + "@eappwh.com");
			employee.setAccountStatus(Boolean.TRUE);
			employee.setCreatedUserId(WarehouseUserController.uId);
			employee.setCreatedTimestamp(currentTimestamp);
			employee.setUpdatedUserId(WarehouseUserController.uId);
			employee.setUpdatedTimestamp(currentTimestamp);
			employeeRepository.save(employee);
			
			employee = getEmployeeByFirstAndLastName(employee.getEmpFirstName(), employee.getEmpLastName());
			String userId = employee.getEmpFirstName().substring(0, 1).toUpperCase() + "-" + employee.getEmployeeId();
			
			WarehouseUser newUser = WarehouseUser.builder()
					.userId(userId)
					.password(PasswordUtil.hashPassword("password1234"))
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
		
		return resp;		
	}
	
	// GET ALL EMPLOYEES
	public List<Employee> getAllEmployees() {
		return prepareResponseList(employeeRepository.getAllEmployees());
	}
	
	// GET INACTIVE EMPLOYEES
	public List<Employee> getInactiveEmployees() {
		return prepareResponseList(employeeRepository.getInactiveEmployees());
	}
	
	// GET ACTIVE EMPLOYEES
	public List<Employee> getActiveEmployees() {
		return prepareResponseList(employeeRepository.getActiveEmployees());
	}	
	
	// GET EMPLOYEE BY employeeID
	public Employee getEmployeeById(Long employeeId) {
		return prepareResponse(employeeRepository.getEmployeeById(employeeId));
	}
	
	// GET EMPLOYEE BY userID
	public Employee getEmployeeByUserId(String userId) {
		return prepareResponse(employeeRepository.getEmployeeByUserId(userId));
	}
	
	// GET EMPLOYEE BY empFirstName (OR CONTAINS PART OF THE NAME)
	public List<Employee> getEmployeeByFirstName(String empFirstName) {
		return prepareResponseList(employeeRepository.getEmployeeByFirstName(empFirstName));
	}
	
	// GET EMPLOYEE BY empFirstName AND empLastName
	public Employee getEmployeeByFirstAndLastName(String empFirstName, String empLastName) {
		return prepareResponse(employeeRepository.getEmployeeByFirstAndLastName(empFirstName, empLastName));
	}
	
	// UPDATE EMPLOYEE
	public Boolean updateEmployee(Employee employee) {
		boolean resp = false;
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		if(employeeRepository.getEmployeeById(employee.getEmployeeId()) != null 
				&& WarehouseUserController.loggedUser.getPermissionList().getEmployees()) {
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
		if(employee != null && WarehouseUserController.loggedUser.getPermissionList().getEmployees()) {
			resp = true;
			employeeRepository.deleteEmployee(employeeId);
			warehouseUserRepository.deleteUser(employee.getUserId());
		}
		
		return resp;
	}
	
	//** PREPARE/UPDATE EMPLOYEE LIST
	private List<Employee> prepareResponseList(List<Employee> employeeList) {
		if(WarehouseUserController.loggedUser.getPermissionList().getEmployees() 
				|| WarehouseUserController.loggedUser.getPermissionList().getEmployeesReadOnly()) {
			for(int i=0; i<employeeList.size(); i++) {
				if(WarehouseUserController.loggedUser.getPermissionList().getEmployeesReadOnly()) {
					employeeList.get(i).setAnnualCompensation(null);
					employeeList.get(i).setHourlyRate(null);
					employeeList.get(i).setPayrollTypeId(null);
				}
			}
		} else employeeList = null;
		
		return employeeList;
	}
	
	//** PREPARE/UPDATE EMPLOYEE OBJECT
	private Employee prepareResponse(Employee employee) {
		if(WarehouseUserController.loggedUser.getPermissionList().getEmployees() 
				|| WarehouseUserController.loggedUser.getPermissionList().getEmployeesReadOnly()) {
			if(WarehouseUserController.loggedUser.getPermissionList().getEmployeesReadOnly()) {
				employee.setAnnualCompensation(null);
				employee.setHourlyRate(null);
				employee.setPayrollTypeId(null);
			}
		} else employee = null;
		
		return employee;
	}

}
