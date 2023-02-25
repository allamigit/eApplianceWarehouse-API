package e_appliance_warehouse.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	@ResponseStatus(value = HttpStatus.CREATED)
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
			@Param(value = "sourceGroupId") Long sourceGroupId, @Param(value = "targetGroupName") String targetGroupName) 
					throws SQLException {
		
		String statusDescription = null;
		if(permissionGroupService.cloneGroup(sourceGroupId, targetGroupName)) {
			resp.setStatus(201);
			statusDescription = "New Permission Group was cloned: " + targetGroupName;
		} else {
			resp.setStatus(406);
			statusDescription = "Missing target Permission Group name";
		}
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription(statusDescription)
				.build();
	}
	
	// GET ALL GROUPS
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listAllGroups.wh")
	public List<PermissionGroup> getAllGroups() {
		return permissionGroupService.getAllGroups();
	}
	
	// GET GROUP BY groupID
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getGroup.wh")
	public PermissionGroup getGroupById(@Param(value = "groupId") Long groupId) {
		return permissionGroupService.getGroupById(groupId);
	}
	
	// GET GROUP BY groupName (OR CONTAINS PART OF THE NAME)
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "findGroupName.wh")
	public List<PermissionGroup> getGroupByName(@Param(value = "groupName") String groupName) {
		return permissionGroupService.getGroupByName(groupName.toLowerCase());
	}
	
	// UPDATE GROUP
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "updateGroup.wh")
	public QueryStatus updateGroup(HttpServletResponse resp, @RequestBody PermissionGroup group) {
		permissionGroupService.updateGroup(group);
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription("Permission Group was updated: " + group.getGroupName())
				.build();
	}
		
	// DELETE GROUP BY groupID
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping(value = "deleteGroup.wh")
	public QueryStatus deleteGroup(HttpServletResponse resp, @Param(value = "groupId") Long groupId) {
		String groupName = getGroupById(groupId).getGroupName();
		permissionGroupService.deleteGroup(groupId);
		
		return QueryStatus.builder()
				.statusCode(resp.getStatus())
				.statusDescription("Permission Group was deleted: " + groupName)
				.build();
	}

}
