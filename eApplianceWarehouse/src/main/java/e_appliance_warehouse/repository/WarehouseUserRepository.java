package e_appliance_warehouse.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import e_appliance_warehouse.table.WarehouseUser;

@Repository
public interface WarehouseUserRepository extends JpaRepository <WarehouseUser, String> {

	// Get User by userID
	@Query(value = "SELECT u FROM WarehouseUser u WHERE LOWER(userId) = ?1")
	public WarehouseUser getUserById(String userId);

	// Change Password
	@Transactional
	@Modifying
	@Query(value = "UPDATE WarehouseUser SET password = ?1, passwordReset = false WHERE userId = ?2")
	public void changePassword(String password, String userId);

	// Save Login Timestamp & User Comment
	@Transactional
	@Modifying
	@Query(value = "UPDATE WarehouseUser SET loginTimestamp = ?1, userComment = ?2 WHERE userId = ?3")
	public void saveUserLoginTimestampAndComment(String loginTimestamp, String userComment, String userId);

}
