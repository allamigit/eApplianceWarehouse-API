package e_appliance_warehouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import e_appliance_warehouse.model.LoggedUser;
import e_appliance_warehouse.service.EmployeeService;
import e_appliance_warehouse.table.Employee;
import e_appliance_warehouse.table.PermissionGroups;
import e_appliance_warehouse.table.WarehouseUser;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "employee")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService employeeService;
	public static String uId;

	// ADD NEW EMPLOYEE
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "addNewEmployee.wh")
	public void addEmployee(HttpServletRequest req, @RequestBody Employee employee, String password) {
		employeeService.addEmployee(employee, password);
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
	@GetMapping(value = "getEmployee.iwh:Id={employeeId}")
	public Employee getEmployeeById(Long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}
	
	// GET Employee BY empFirstName (OR CONTAINS PART OF THE NAME)
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getEmployee.wh:firstName={empFirstName}")
	public List<Employee> getEmployeeByFirstName(HttpServletRequest req, @PathVariable String empFirstName) {
		return employeeService.getEmployeeByFirstName(empFirstName);
	}
	
	// GET Employee BY empFirstName AND empLastName
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getEmployee.wh:firstName={empFirstName}&lastName={empLastName}")
	public Employee getEmployeeByFirstAndLastName(HttpServletRequest req, @PathVariable String empFirstName, @PathVariable String empLastName) {
		return employeeService.getEmployeeByFirstAndLastName(empFirstName, empLastName);
	}
	
	// UPDATE EMPLOYEE
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "updateEmployee.iwh:Id={userId}")
	public void updateUser(HttpServletRequest req, @RequestBody Employee employee, @PathVariable Long employeeId) {
		employeeService.updateEmployee(employee);
	}
	
	// DELETE EMPLOYEE By employeeID
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping(value = "deleteEmployee.wh:Id={userId}")
	public void deleteEmployee(HttpServletRequest req, @PathVariable Long employeeId) {
		employeeService.deleteEmployee(employeeId);
	}

	// GET USER BY userID
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getUser.iwh:Id={userId}")
	public WarehouseUser getUserById(HttpServletRequest req, @PathVariable String userId) {
		return employeeService.getUserById(userId);
	}
	
	// USER LOGIN
	@GetMapping(value = "userLogin.wh:userId={userId}&password={password}")
	public LoggedUser userLogin(HttpServletRequest req, HttpServletResponse resp, @PathVariable String userId, @PathVariable String password) {
		boolean accountStatus = false;
		WarehouseUser user = employeeService.getUserById(userId);
		LoggedUser loggedUser = LoggedUser.builder()
				.userId(userId)
				.userFullName(user.getUserFullName())
				.jobTitle(user.getJobTitle().getJobTitleName())
				.loginTimestamp(user.getLoginTimestamp())
				.userComment(user.getUserComment())
				.build();

		if(user != null) accountStatus = user.getAccountStatus();
		
		if(employeeService.userLogin(userId, password) & accountStatus) {
			uId = user.getUserId();
			req.getSession().setAttribute("userId", uId);
			resp.setStatus(200);
			//System.out.println(req.getSession().getAttribute("userId") != null);  //TRUE
		} else {
			if(user != null & !accountStatus) {
				resp.setStatus(423);  // Inactive Account
			}
			if(user != null & accountStatus) {
				resp.setStatus(401);  // Invalid Password
			} 
			if(user == null & !accountStatus) {
				resp.setStatus(406);  // Invalid UserId
			}
		}
		
		return loggedUser;
	}
	
	// USER LOGOUT
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "userLogout.wh")
	public void userLogout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		
		uId = null;
		req.getSession().setAttribute("userId", uId);
		//System.out.println(req.getSession().getAttribute("userId") != null);  //FALSE
	}
	
	// GET USER PERMISSIONS
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getUserPermissions.wh:Username={userId}")
	public PermissionGroups getUserPermissions(HttpServletRequest req, @PathVariable String userId) {
		PermissionGroups group = employeeService.getUserById(userId).getPermissionGroup();
		return group;
	}
	
	// GET OLD PASSWORD
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getOldPassword.wh:UserId={userId}")
	public String getOldPassword(HttpServletRequest req, @PathVariable String userId) {
		WarehouseUser user = employeeService.getUserById(userId);
		return user.getPassword();
	}
	
	// CHANGE PASSWORD
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "changePassword.wh:UserId={userId}&password={password}")
	public String changePassword(HttpServletRequest req, @PathVariable String password, @PathVariable String userId) {
		employeeService.changePassword(password, userId);
		return "Password changed successfully";
	}
	
	// SAVE LOGIN TIMESTAMP & USER COMMENT
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "saveUserLoginTimestampAndComment.wh:userId={userId}&loginTimestamp={loginTimestamp}&userComment={userComment}")
	public void saveUserLoginTimestampAndComment(HttpServletRequest req, @PathVariable String loginTimestamp, @PathVariable String userComment, @PathVariable String userId) {
		employeeService.saveUserLoginTimestampAndComment(loginTimestamp, userComment, userId);
	}
	
}
