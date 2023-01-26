package e_appliance_warehouse.service;

import java.util.List;

import org.springframework.stereotype.Service;

import e_appliance_warehouse.repository.PermissionGroupsRepository;
import e_appliance_warehouse.table.PermissionGroups;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PermissionGroupsService {

	private PermissionGroupsRepository accessGroupRepo;
	
	// ADD NEW GROUP
	public void addGroup(PermissionGroups group) {
		accessGroupRepo.save(group);
	}
	
	// COPY GROUP
	public Boolean copyGroup(int sourceGroupId, String targetGroupName) {
		boolean resp = false;
		
		PermissionGroups sourceGroup = accessGroupRepo.getGroupById(sourceGroupId);
		PermissionGroups targetGroup = sourceGroup;
		targetGroup.setGroupId(0L);
		targetGroup.setGroupName(targetGroupName);
		
		if(targetGroupName != "") {
			resp = true;
			addGroup(targetGroup);
		}
		
		return resp;
	}
	
	// GET ALL GROUPS
	public List<PermissionGroups> getAllGroups() {
		return accessGroupRepo.getAllGroups();
	}
	
	// GET GROUP BY groupID
	public PermissionGroups getGroupById(int groupId) {
		return accessGroupRepo.getGroupById(groupId);
	}
	
	// GET GROUP BY groupNAME (OR CONTAINS PART OF THE NAME)
	public List<PermissionGroups> getGroupByName(String groupName) {
		return accessGroupRepo.getGroupByName(groupName);
	}
	
	// UPDATE GROUP
	public void updateGroup(PermissionGroups group) {
		accessGroupRepo.save(group);
	}
		
	// DELETE GROUP BY groupID
	public void deleteGroup(int groupId) {
		accessGroupRepo.deleteGroup(groupId);
	}
	
}
