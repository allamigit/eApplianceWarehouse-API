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

import e_appliance_warehouse.model.UserInfo;
import e_appliance_warehouse.service.UserService;
import e_appliance_warehouse.table.PermissionGroups;
import e_appliance_warehouse.table.User;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "user")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@AllArgsConstructor
public class UserController {
/*
	private UserService userService;
	public static Integer uId;
	public static String uName;
	public static String uEmail;

	// ADD NEW USER
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "addNewUser.iwh")
	public void addUser(HttpServletRequest req, @RequestBody User user) {
		userService.addUser(user);
	}
	
	// GET ALL USERS
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listAllUsers.iwh")
	public List<User> getAllUsers(HttpServletRequest req) {
		return userService.getAllUsers();
	}
	
	// GET INACTIVE USERS
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listInactiveUsers.iwh")
	public List<User> getInactiveUsers(HttpServletRequest req) {
		return userService.getInactiveUsers();
	}
	
	// GET ACTIVE USERS
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listActiveUsers.iwh")
	public List<User> getActiveUsers(HttpServletRequest req) {
		return userService.getActiveUsers();
	}	
	
	// GET USER BY userID
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getUser.iwh:Id={userId}")
	public User getUserById(HttpServletRequest req, @PathVariable int userId) {
		return userService.getUserById(userId);
	}
	
	// GET USER BY firstNAME (OR CONTAINS PART OF THE NAME)
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getUser.iwh:firstName={firstName}")
	public List<User> getUserByName(HttpServletRequest req, @PathVariable String firstName) {
		return userService.getUserByName(firstName);
	}
	
	// USER LOGIN
	@GetMapping(value = "userLogin.iwh:Username={username}&Password={password}")
	public UserInfo userLogin(HttpServletRequest req, HttpServletResponse resp, @PathVariable String username, @PathVariable String password) {
		boolean accountStatus = false;
		User user = userService.getUserByUsername(username);
		UserInfo userInfo = new UserInfo();

		if(user != null) accountStatus = user.getEmpStatus();
		
		if(userService.userLogin(username, password) & accountStatus) {
			uId = user.getUserId();
			uName = user.getFirstName() + " " + user.getLastName();
			uEmail = user.getEmail();
			req.getSession().setAttribute("userFullName", uName);
			resp.setStatus(200);
			//System.out.println(req.getSession().getAttribute("userFullName") != null);  //TRUE
			userInfo.setUserId(uId);
			userInfo.setFullName(uName);
			userInfo.setEmail(uEmail);
			userInfo.setJobTitle(user.getJobTitle());
			userInfo.setLoginTimestamp(user.getLoginTimestamp());
			userInfo.setUserComment(user.getUserComment());
		} else {
			if(user != null & !accountStatus) {
				resp.setStatus(423);  // Inactive Account
			}
			if(user != null & accountStatus) {
				resp.setStatus(401);  // Invalid Password
			} 
			if(user == null & !accountStatus) {
				resp.setStatus(406);  // Invalid Username
			}
		}
		
		return userInfo;
	}
	
	// USER LOGOUT
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "userLogout.iwh")
	public void userLogout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		
		uId = 0;
		uName = null;
		uEmail = null;
		req.getSession().setAttribute("userFullName", uName);
		//System.out.println(req.getSession().getAttribute("userFullName") != null);  //FALSE
	}
	
	// GET USER PERMISSIONS
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getUserPermissions.iwh:Username={username}")
	public PermissionGroups getUserPermissions(HttpServletRequest req, @PathVariable String username) {
		PermissionGroups group = userService.getUserByUsername(username).getAccessGroup();
		return group;
	}
	
	// GET OLD PASSWORD
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getOldPassword.iwh:Username={username}")
	public String getOldPassword(HttpServletRequest req, @PathVariable String username) {
		User user = userService.getUserByUsername(username);
		return user.getPassword();
	}
	
	// CHANGE PASSWORD
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "changePassword.iwh:Username={username}&Password={password}")
	public String changePassword(HttpServletRequest req, @PathVariable String password, @PathVariable String username) {
		userService.changePassword(password, username);
		return "Password changed successfully";
	}
	
	// SAVE LOGIN TIMESTAMP & USER COMMENT
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "saveUserLoginAndComment.iwh:Username={username}&Login={loginTimestamp}&Comment={userComment}")
	public void saveUserLoginAndComment(HttpServletRequest req, @PathVariable String loginTimestamp, @PathVariable String userComment, @PathVariable String username) {
		userService.saveUserLoginAndComment(loginTimestamp, userComment, username);
	}
	
	// UPDATE USER
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "updateUser.iwh:Id={userId}")
	public void updateUser(HttpServletRequest req, @RequestBody User user, @PathVariable int userId) {
		userService.updateUser(user);
	}
	
	// DELETE USER By userID
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping(value = "deleteUser.iwh:Id={userId}")
	public void deleteUser(HttpServletRequest req, @PathVariable int userId) {
		userService.deleteUser(userId);
	}
*/
}
