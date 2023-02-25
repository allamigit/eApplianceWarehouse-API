package e_appliance_warehouse.repository;

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
	@Query(value = "UPDATE WarehouseUser SET password = ?2, passwordReset = false WHERE LOWER(userId) = LOWER(?1)")
	public void changePassword(String userId, String newPassword);

	// Save Last Login Timestamp & User Comment
	@Transactional
	@Modifying
	@Query(value = "UPDATE WarehouseUser SET lastLoginTimestamp = ?1, userComment = ?2 WHERE LOWER(userId) = LOWER(?3)")
	public void saveUserLastLoginTimestampAndComment(String lastLoginTimestamp, String userComment, String userId);

}
