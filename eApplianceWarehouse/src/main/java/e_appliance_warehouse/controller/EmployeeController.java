package e_appliance_warehouse.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import e_appliance_warehouse.model.LoggedUser;
import e_appliance_warehouse.model.RequestStatus;
import e_appliance_warehouse.repository.JobTitleRepository;
import e_appliance_warehouse.service.EmployeeService;
import e_appliance_warehouse.service.PermissionGroupService;
import e_appliance_warehouse.table.Employee;
import e_appliance_warehouse.table.PermissionGroup;
import e_appliance_warehouse.table.WarehouseUser;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "employee")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService employeeService;
	private PermissionGroupService permissionGroupService;
	private JobTitleRepository jobTitleRepository;
	public static String uId;
	public static Timestamp loginTimestamp;
	public static LoggedUser loggedUser;
	
	// ADD NEW EMPLOYEE
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "addNewEmployee.wh")
	public RequestStatus addEmployee(HttpServletResponse resp, @RequestBody Employee employee) {		
		employeeService.addEmployee(employee);
				
		return RequestStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription("New employee was added: " + employee.getEmpFirstName() + " " + employee.getEmpLastName())
				.build();
	}
	
	// GET ALL EMPLOYEES
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listAllEmployees.wh")
	public List<Employee> getAllEmployees(HttpServletRequest req) {
		return employeeService.getAllEmployees();
	}
	
	// GET INACTIVE EMPLOYEES
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listInactiveEmployees.wh")
	public List<Employee> getInactiveEmployees(HttpServletRequest req) {
		return employeeService.getInactiveEmployees();
	}
	
	// GET ACTIVE EMPLOYEES
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listActiveEmployees.wh")
	public List<Employee> getActiveEmployees(HttpServletRequest req) {
		return employeeService.getActiveEmployees();
	}	
	
	// GET EMPLOYEE BY employeeID
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getEmployee.wh")
	public Employee getEmployeeById(@Param("employeeId") Long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}
	
	// GET Employee BY empFirstName (OR CONTAINS PART OF THE NAME)
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getEmployeeByName.wh")
	public List<Employee> getEmployeeByFirstName(@Param("empFirstName") String empFirstName) {
		return employeeService.getEmployeeByFirstName(empFirstName.toLowerCase());
	}
	
	// GET Employee BY empFirstName AND empLastName
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getEmployeeByFullName.wh")
	public Employee getEmployeeByFirstAndLastName(@Param("empFirstName") String empFirstName, @Param("empLastName") String empLastName) {
		return employeeService.getEmployeeByFirstAndLastName(empFirstName, empLastName);
	}
	
	// UPDATE EMPLOYEE
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "updateEmployee.wh")
	public RequestStatus updateUser(HttpServletResponse resp, @RequestBody Employee employee) {
		employeeService.updateEmployee(employee);

		return RequestStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription("Employee was updated: " + employee.getEmpFirstName() + " " + employee.getEmpLastName())
				.build();
	}
	
	// DELETE EMPLOYEE By employeeID
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping(value = "deleteEmployee.wh")
	public RequestStatus deleteEmployee(HttpServletResponse resp, @Param("employeeId") Long employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		String fullName = employee.getEmpFirstName() + " " + employee.getEmpLastName();
		employeeService.deleteEmployee(employeeId);
		
		return RequestStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription("Employee was deleted: " + fullName)
				.build();
	}

	// GET ALL USERS
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listAllUsers.wh")
	public List<WarehouseUser> getAllUsers() {
		return employeeService.getAllUsers();
	}
	
	// GET USER BY userID
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getUser.wh")
	public WarehouseUser getUserById(@Param("userId") String userId) {
		return employeeService.getUserById(userId);
	}
	
	// USER LOGIN
	@GetMapping(value = "userLogin.wh")
	public LoggedUser userLogin(HttpServletRequest req, HttpServletResponse resp, 
			@Param(value ="userId") String userId, @Param("password") String password) {
		boolean accountStatus = false;
		userId = userId.toLowerCase();
		WarehouseUser user = employeeService.getUserById(userId);
		Employee employee = employeeService.getEmployeeByUserId(userId);
		if(user != null) accountStatus = employee.getAccountStatus();
		
		loggedUser = null;
		String statusMessage = null;
		if(employeeService.userLogin(userId, password) & accountStatus) {
			loggedUser = LoggedUser.builder()
					.userId(userId.toUpperCase())
					.userFullName(employee.getEmpFirstName() + " " + employee.getEmpLastName())
					.jobTitle(jobTitleRepository.getJobTitleName(employee.getJobTitleId()))
					.loginTimestamp(user.getLoginTimestamp())
					.userComment(user.getUserComment())
					.permissionList(getUserPermissions(userId))
					.build();
			uId = user.getUserId();
			loginTimestamp = new Timestamp(System.currentTimeMillis());
			statusMessage = "Login Successful";
		} else {
			resp.setStatus(401);
			if(user != null && !accountStatus) 
				statusMessage = "Inactive Account";
			if(user != null && accountStatus) 
				statusMessage =  "Invalid Password";
			if(user == null && !accountStatus) 
				statusMessage = "Invalid Username";
			if(userId == "" && password == "") 
				statusMessage = "Missing Credentials";
		}
		
		RequestStatus requestStatus = RequestStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(statusMessage)
				.build();
		
		if(loggedUser == null) {
			loggedUser = LoggedUser.builder()
					.userId(userId.toUpperCase())
					.build();
		}
		loggedUser.setLoginStatus(requestStatus);
		if(loggedUser != null) req.getSession().setAttribute("currentUser", uId);

		return loggedUser;
	}
	
	// USER LOGOUT
	@GetMapping(value = "userLogout.wh")
	public RequestStatus userLogout(HttpServletRequest req, HttpServletResponse resp) {
		if(uId == null) resp.setStatus(404);
		req.getSession().invalidate();		
		uId = null;
		
		return RequestStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(resp.getStatus()==200?"Logout Successful":"Logout Failed/No Session Found")
				.build();
	}
	
	// GET USER PERMISSIONS
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getUserPermissions.wh")
	public PermissionGroup getUserPermissions(@Param("userId") String userId) {
		Long groupId = employeeService.getEmployeeByUserId(userId).getGroupId();
		PermissionGroup group = permissionGroupService.getGroupById(groupId);
		return group;
	}
	
	// CHANGE PASSWORD
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "changePassword.wh")
	public String changePassword(@Param("password") String password, @Param("userId") String userId) {
		employeeService.changePassword(password, userId);
		return "Password changed successfully";
	}
	
	// SAVE LOGIN TIMESTAMP & USER COMMENT
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "saveUserLoginTimestampAndComment.wh")
	public void saveUserLoginTimestampAndComment(
			@Param("loginTimestamp") String loginTimestamp, 
			@Param("userComment") String userComment, 
			@Param("userId") String userId) {
		employeeService.saveUserLoginTimestampAndComment(loginTimestamp, userComment, userId);
	}
	
}
