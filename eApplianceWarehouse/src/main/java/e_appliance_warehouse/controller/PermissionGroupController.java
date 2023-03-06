package e_appliance_warehouse.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import e_appliance_warehouse.model.PermissionGroupResponse;
import e_appliance_warehouse.model.QueryStatus;
import e_appliance_warehouse.service.PermissionGroupService;
import e_appliance_warehouse.table.PermissionGroup;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "permission-group")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@AllArgsConstructor
public class PermissionGroupController {

	private PermissionGroupService permissionGroupService;
	
	// ADD NEW GROUP
	@PostMapping(value = "addNewGroup.wh")
	public QueryStatus addGroup(HttpServletResponse resp, @RequestBody PermissionGroup group) {
		permissionGroupService.addGroup(group);
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription("New Permission Group was added: " + group.getGroupName())
				.build();
	}
	
	// CLONE GROUP 
	@PostMapping(value = "cloneGroup.wh")
	public QueryStatus cloneGroup(HttpServletResponse resp, 
			@RequestParam(name = "sourceGroupId") Long sourceGroupId, 
			@RequestParam(name = "targetGroupName") String targetGroupName) {
		
		String statusDescription = null;
		if(permissionGroupService.cloneGroup(sourceGroupId, targetGroupName)) {
			statusDescription = "New Permission Group Cloned: " + targetGroupName;
		} else {
			resp.setStatus(409);
			statusDescription = "Group not found or missing target Permission Group name";
		}
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(statusDescription)
				.build();
	}
	
	// GET ALL GROUPS
	@GetMapping(value = "getAllGroups.wh")
	public PermissionGroupResponse getAllGroups(HttpServletResponse resp) {
		List<PermissionGroup> groupList = permissionGroupService.getAllGroups();
		
		return PermissionGroupResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(groupList.isEmpty()?"No Result Found":"All Permission Groups Retrieved Successfully")
							.build())
				.queryResult(groupList)
				.build();
	}
	
	// GET GROUP BY groupID
	@GetMapping(value = "getGroup.wh")
	public PermissionGroupResponse getGroupById(HttpServletResponse resp, @RequestParam(name = "groupId") Long groupId) {
		List<PermissionGroup> groupList = new ArrayList<>();
		groupList.add(permissionGroupService.getGroupById(groupId));
		
		return PermissionGroupResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(groupList.get(0)==null?"No Result Found":"Permission Group Retrieved Successfully")
							.build())
				.queryResult(groupList)
				.build();
	}
	
	// GET GROUP BY groupName (OR CONTAINS PART OF THE NAME)
	@GetMapping(value = "getGroupByName.wh")
	public PermissionGroupResponse getGroupByName(HttpServletResponse resp, @RequestParam(name = "groupName") String groupName) {
		List<PermissionGroup> groupList =  permissionGroupService.getGroupByName(groupName.toLowerCase());
		
		return PermissionGroupResponse.builder()
				.queryStatus(QueryStatus.builder()
							.statusCode(resp.getStatus())
							.statusDescription(groupList.isEmpty()?"No Result Found":"Permission Groups by Group Name Retrieved Successfully")
							.build())
				.queryResult(groupList)
				.build();
	}
	
	// UPDATE GROUP
	@PutMapping(value = "updateGroup.wh")
	public QueryStatus updateGroup(HttpServletResponse resp, @RequestBody PermissionGroup group) {
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(!permissionGroupService.updateGroup(group)?"No Result Found":"Permission Group Updated: " + group.getGroupName())
				.build();
	}
		
	// DELETE GROUP BY groupID
	@DeleteMapping(value = "deleteGroup.wh")
	public QueryStatus deleteGroup(HttpServletResponse resp, @RequestParam(name = "groupId") Long groupId) {
		PermissionGroup group = permissionGroupService.getGroupById(groupId);
		String groupName = null;
		if(group != null)  groupName = group.getGroupName();
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(!permissionGroupService.deleteGroup(groupId)?"No Result Found":"Permission Group Deleted: " + groupName)
				.build();
	}

}
