package e_appliance_warehouse.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import e_appliance_warehouse.table.PermissionGroup;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {

	// Get List of All Groups
	@Query(value = "SELECT p FROM PermissionGroup p ORDER BY groupName ASC")
	public List<PermissionGroup> getAllGroups();
	
	// Get Group by groupID
	@Query(value = "SELECT p FROM PermissionGroup p WHERE groupId = ?1")
	public PermissionGroup getGroupById(Long groupId);

	// Get Group by groupName (or if contains part of the name)
	@Query(value = "SELECT p FROM PermissionGroup p WHERE LOWER(groupName) LIKE %?1% ORDER BY groupName ASC")
	public List<PermissionGroup> getGroupByName(String groupName);

	// Delete Group by groupID
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM PermissionGroup p WHERE groupId = ?1")
	public void deleteGroup(Long groupId);

}
