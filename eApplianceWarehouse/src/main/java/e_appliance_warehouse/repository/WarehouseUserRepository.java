package e_appliance_warehouse.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import e_appliance_warehouse.table.WarehouseUser;

@Repository
public interface WarehouseUserRepository extends JpaRepository <WarehouseUser, String> {

	// Get All Users
	@Query(value = "SELECT u FROM WarehouseUser u ORDER BY userId")
	public List<WarehouseUser> getAllUsers();

	// Get User by userID
	@Query(value = "SELECT u FROM WarehouseUser u WHERE LOWER(userId) = LOWER(?1)")
	public WarehouseUser getUserById(String userId);

	// Change Password
	@Transactional
	@Modifying
	@Query(value = "UPDATE WarehouseUser SET password = ?2, resetPassword = false WHERE LOWER(userId) = LOWER(?1)")
	public void changePassword(String userId, String newPassword);

	// Reset Password
	@Transactional
	@Modifying
	@Query(value = "UPDATE WarehouseUser SET resetPassword = true WHERE LOWER(userId) = LOWER(?1)")
	public void resetPassword(String userId);

	// Save Last Login Timestamp & User Comment
	@Transactional
	@Modifying
	@Query(value = "UPDATE WarehouseUser SET lastLoginTimestamp = ?2, userComment = ?3 WHERE LOWER(userId) = LOWER(?1)")
	public void saveUserLastLoginTimestampAndComment(String userId, Timestamp lastLoginTimestamp, String userComment);

	// Delete User by userID
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM WarehouseUser WHERE LOWER(userId) = LOWER(?1)")
	public void deleteUser(String userId);

}
