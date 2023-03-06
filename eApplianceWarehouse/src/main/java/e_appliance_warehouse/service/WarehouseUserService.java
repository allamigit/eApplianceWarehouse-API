package e_appliance_warehouse.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import e_appliance_warehouse.controller.WarehouseUserController;
import e_appliance_warehouse.repository.WarehouseUserRepository;
import e_appliance_warehouse.table.WarehouseUser;
import e_appliance_warehouse.util.PasswordUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WarehouseUserService {

	private WarehouseUserRepository warehouseUserRepository;

	// GET ALL USERS
	public List<WarehouseUser> getAllUsers() {
		return warehouseUserRepository.getAllUsers();
	}
	
	// GET USER BY userID
	public WarehouseUser getUserById(String userId) {
		return warehouseUserRepository.getUserById(userId);
	}
	
	// UPDATE USER
	public Boolean updateUser(WarehouseUser user) {
		boolean resp = false;
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		if(warehouseUserRepository.getUserById(user.getUserId()) != null) {
			resp = true;
			user.setUpdatedUserId(WarehouseUserController.uId);
			user.setUpdatedTimestamp(currentTimestamp);
			warehouseUserRepository.save(user);
		}
		
		return resp;
	}
	
	// CHANGE PASSWORD
	public String changePassword(String userId, String oldPassword, String newPassword) {
		userId = userId.toLowerCase();
		WarehouseUser user = warehouseUserRepository.getUserById(userId);
		String statusMessage = null;
		if(user != null && oldPassword != "" && newPassword != ""
				&& user.getUserId().equals(userId.toUpperCase()) 
				&& PasswordUtil.validatePassword(oldPassword, user.getPassword())
				&& !PasswordUtil.validatePassword(oldPassword, PasswordUtil.hashPassword(newPassword))) {		
			warehouseUserRepository.changePassword(userId, PasswordUtil.hashPassword(newPassword));
			statusMessage = "Password Changed Successfully";
		} else {
			if(userId != null && user == null) statusMessage = "Invalid Username";
			if(user != null && !PasswordUtil.validatePassword(oldPassword, user.getPassword())) statusMessage = "Invalid Old Password";
			if(oldPassword != "" && newPassword != "" && 
					PasswordUtil.validatePassword(oldPassword, PasswordUtil.hashPassword(newPassword))) statusMessage = "Invalid New Password/Same Old Password";
			if(userId == null || (user != null && oldPassword == "") || (user != null && newPassword == "")) statusMessage = "Missing Parameters";
		}
		
		return statusMessage;
	}
	
	// RESET PASSWORD
	public Boolean resetPassword(String userId) {
		boolean resp = false;
		if(warehouseUserRepository.getUserById(userId) != null) {
			resp = true;
			warehouseUserRepository.resetPassword(userId);
		}
		
		return resp;
	}
	
	// SAVE LAST LOGIN TIMESTAMP & USER COMMENT
	public void saveUserLastLoginTimestampAndComment(String userId, Timestamp lastLoginTimestamp, String userComment) {
		warehouseUserRepository.saveUserLastLoginTimestampAndComment(userId, lastLoginTimestamp, userComment);
	}
	
	// USER LOGIN
	public Boolean userLogin(String userId, String password) {
		boolean resp = false;
		WarehouseUser user = getUserById(userId);
		
		if(user != null && user.getUserId().equalsIgnoreCase(userId) && PasswordUtil.validatePassword(password, user.getPassword())) 
			resp = true;
					
		return resp;
	}

}
