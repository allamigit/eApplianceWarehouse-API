package e_appliance_warehouse.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import e_appliance_warehouse.model.PermissionGroups;

@Repository
@Transactional
public interface PermissionGroupsRepository extends JpaRepository<PermissionGroups,Integer> {

	// Get List of All Groups
	@Query(value = "SELECT * FROM access_group ORDER BY group_name ASC", nativeQuery = true)
	public List<PermissionGroups> getAllGroups();
	
	// Get Group by groupID
	@Query(value = "SELECT * FROM access_group WHERE group_id = ?", nativeQuery = true)
	public PermissionGroups getGroupById(int groupId);

	// Get Group by groupName (or if contains part of the name)
	@Query(value = "SELECT * FROM access_group WHERE LOWER(group_name) LIKE %?% ORDER BY group_name ASC", nativeQuery = true)
	public List<PermissionGroups> getGroupByName(String groupName);

	// Delete Group by groupID
	@Modifying
	@Query(value = "DELETE FROM access_group WHERE group_id = ?", nativeQuery = true)
	public void deleteGroup(int groupId);

}
