package e_appliance_warehouse.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import e_appliance_warehouse.table.PermissionGroups;

@Repository
public interface PermissionGroupsRepository extends JpaRepository<PermissionGroups, Long> {

	// Get List of All Groups
	@Query(value = "SELECT * FROM PermissionGroups ORDER BY groupName ASC")
	public List<PermissionGroups> getAllGroups();
	
	// Get Group by groupID
	@Query(value = "SELECT * FROM PermissionGroups WHERE groupId = ?1")
	public PermissionGroups getGroupById(Long groupId);

	// Get Group by groupName (or if contains part of the name)
	@Query(value = "SELECT * FROM PermissionGroups WHERE LOWER(groupName) LIKE %?1% ORDER BY groupName ASC")
	public List<PermissionGroups> getGroupByName(String groupName);

	// Delete Group by groupID
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM PermissionGroups WHERE groupId = ?1")
	public void deleteGroup(Long groupId);

}
