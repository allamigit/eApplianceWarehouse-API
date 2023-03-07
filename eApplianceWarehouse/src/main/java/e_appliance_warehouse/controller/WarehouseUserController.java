package e_appliance_warehouse.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import e_appliance_warehouse.model.LoggedUser;
import e_appliance_warehouse.model.QueryStatus;
import e_appliance_warehouse.model.WarehouseUserResponse;
import e_appliance_warehouse.repository.EmployeeRepository;
import e_appliance_warehouse.repository.JobTitleRepository;
import e_appliance_warehouse.repository.PermissionGroupRepository;
import e_appliance_warehouse.repository.WarehouseUserRepository;
import e_appliance_warehouse.service.WarehouseUserService;
import e_appliance_warehouse.table.Employee;
import e_appliance_warehouse.table.WarehouseUser;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "user")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@AllArgsConstructor
public class WarehouseUserController {

	private WarehouseUserService warehouseUserService;
	private WarehouseUserRepository warehouseUserRepository;
	private PermissionGroupRepository permissionGroupRepository;
	private EmployeeRepository employeeRepository;
	private JobTitleRepository jobTitleRepository;
	
	public static String uId;
	public static Timestamp lastLoginTimestamp;
	public static LoggedUser loggedUser;

	// GET ALL USERS
	@GetMapping(value = "listAllUsers.wh")
	public WarehouseUserResponse getAllUsers(HttpServletResponse resp) {
		List<WarehouseUser> userList = warehouseUserService.getAllUsers();
		
		return WarehouseUserResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(userList==null || userList.isEmpty()?"No Result Found/No Permission":"All Users Retrieved Successfully")
							.build())
				.queryResult(userList)
				.build();
	}
	
	// GET USER BY userID
	@GetMapping(value = "getUser.wh")
	public WarehouseUserResponse getUserById(HttpServletResponse resp, @RequestParam(name = "userId") String userId) {
		List<WarehouseUser> userList = new ArrayList<>();
		userList.add(warehouseUserService.getUserById(userId));
		
		return WarehouseUserResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(userList.get(0)==null?"No Result Found/No Permission":"User Retrieved Successfully")
							.build())
				.queryResult(userList)
				.build();
	}
	
	// UPDATE USER
	@PutMapping(value = "updateUser.wh")
	public QueryStatus updateUser(HttpServletResponse resp, @RequestBody WarehouseUser user) {

		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(warehouseUserService.updateUser(user)?"User Updated":"User Not Found/No Permission")
				.build();
	}
	
	// CHANGE PASSWORD
	@PatchMapping(value = "changePassword.wh")
	public QueryStatus changePassword(HttpServletResponse resp, 
			@RequestParam(name = "userId") String userId, 
			@RequestParam(name = "oldPassword") String oldPassword, 
			@RequestParam(name = "newPassword") String newPassword) {
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(warehouseUserService.changePassword(userId, oldPassword, newPassword))
				.build();
	}
	
	// RESET PASSWORD
	@PatchMapping(value = "resetPassword.wh")
	public QueryStatus changePassword(HttpServletResponse resp, @RequestParam(name = "userId") String userId) {
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(warehouseUserService.resetPassword(userId)?"Reset Password Success":"Reset Password Failed: User Not Found/No Permission")
				.build();
	}
	
	// SAVE LAST LOGIN TIMESTAMP & USER COMMENT
	@PatchMapping(value = "saveUserLastLoginTimestampAndComment.wh")
	public QueryStatus saveUserLastLoginTimestampAndComment(HttpServletResponse resp,
			@RequestParam(name = "userId") String userId, 
			@RequestParam(name = "userComment") String userComment) {
		
		warehouseUserService.saveUserLastLoginTimestampAndComment(userId, loggedUser.getLoginTimestamp(), userComment);

		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription("User Last Login Timestamp and Comment Saved Successfully")
				.build();
	}
	
	// USER LOGIN
	@PostMapping(value = "userLogin.wh")
	public LoggedUser userLogin(HttpServletRequest req, HttpServletResponse resp, 
			@RequestParam(name = "userId") String userId, @RequestParam(name = "password") String password) {
		boolean accountStatus = false;
		userId = userId.toLowerCase();
		WarehouseUser user = warehouseUserRepository.getUserById(userId);
		Employee employee = employeeRepository.getEmployeeByUserId(userId);
		if(user != null) accountStatus = employee.getAccountStatus();
		uId = user.getUserId();
		loggedUser = null;
		String statusDescription = null;
		if(warehouseUserService.userLogin(userId, password) && accountStatus && !user.getResetPassword()) {
			loggedUser = LoggedUser.builder()
					.userId(userId.toUpperCase())
					.userFullName(employee.getEmpFirstName() + " " + employee.getEmpLastName())
					.jobTitle(jobTitleRepository.getJobTitleName(employee.getJobTitleId()))
					.loginTimestamp(new Timestamp(System.currentTimeMillis()))
					.lastLoginTimestamp(user.getLastLoginTimestamp())
					.userComment(user.getUserComment())
					.permissionList(permissionGroupRepository.getGroupById(employee.getGroupId()))
					.build();
			lastLoginTimestamp = new Timestamp(System.currentTimeMillis());
			statusDescription = "Login Successful";
		} else if(user != null && user.getResetPassword()) {			
				resp.setStatus(409);
				statusDescription = "Reset Password";
				} else {
					resp.setStatus(401);
					if(user == null && !accountStatus) statusDescription = "Invalid Username";
					if(user != null && accountStatus) statusDescription =  "Invalid Password";
					if(user != null && !accountStatus) statusDescription = "Inactive Account";
					if(userId == "" && password == "") statusDescription = "Missing Credentials";
				}
		
		QueryStatus querytStatus = QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(statusDescription)
				.build();
		
		if(loggedUser == null) {
			loggedUser = LoggedUser.builder()
					.userId(userId.toUpperCase())
					.build();
		}
		loggedUser.setLoginStatus(querytStatus);
		if(loggedUser != null) req.getSession().setAttribute("currentUser", uId);

		return loggedUser;
	}
	
	// USER LOGOUT
	@PostMapping(value = "userLogout.wh")
	public QueryStatus userLogout(HttpServletRequest req, HttpServletResponse resp) {
		if(uId == null) {
			resp.setStatus(401);
		} else {
			WarehouseUser user = warehouseUserService.getUserById(uId);
			saveUserLastLoginTimestampAndComment(resp, uId, user.getUserComment());
			req.getSession().invalidate();		
			uId = null;
		}
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(resp.getStatus()==200?"Logout Successful":"Logout Failed/No Login User Found")
				.build();
	}

}
