package e_appliance_warehouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import e_appliance_warehouse.model.RequestStatus;
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
	public RequestStatus addGroup(HttpServletRequest req, @RequestBody PermissionGroup group) {
		permissionGroupService.addGroup(group);
		RequestStatus requestStatus = RequestStatus.builder()
				.statusCode(HttpServletResponse.SC_CREATED)
				.statusName(HttpStatus.CREATED)
				.statusDescription("New Permission Group was added: " + group.getGroupName())
				.build();
		return requestStatus;
	}
	
	// CLONE GROUP 
	@PostMapping(value = "cloneGroup.wh")
	public RequestStatus cloneGroup(HttpServletRequest req, HttpServletResponse resp, 
			@Param(value = "sourceGroupId") Long sourceGroupId, @Param(value = "targetGroupName") String targetGroupName) {
		if(permissionGroupService.cloneGroup(sourceGroupId, targetGroupName)) {
			resp.setStatus(201);
			RequestStatus requestStatus = RequestStatus.builder()
					.statusCode(HttpServletResponse.SC_CREATED)
					.statusName(HttpStatus.CREATED)
					.statusDescription("New Permission Group was cloned: " + targetGroupName)
					.build();
			return requestStatus;
		} else {
			resp.setStatus(406);
			RequestStatus requestStatus = RequestStatus.builder()
					.statusCode(HttpServletResponse.SC_NOT_ACCEPTABLE)
					.statusName(HttpStatus.NOT_ACCEPTABLE)
					.statusDescription("Missing target Permission Group name")
					.build();
			return requestStatus;
		}
	}
	
	// GET ALL GROUPS
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listAllGroups.wh")
	public List<PermissionGroup> getAllGroups(HttpServletRequest req) {
		return permissionGroupService.getAllGroups();
	}
	
	// GET GROUP BY groupID
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getGroup.wh:groupId={groupId}")
	public PermissionGroup getGroupById(HttpServletRequest req, @PathVariable Long groupId) {
		return permissionGroupService.getGroupById(groupId);
	}
	
	// GET GROUP BY groupName (OR CONTAINS PART OF THE NAME)
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getGroup.wh:groupName={groupName}")
	public List<PermissionGroup> getGroupByName(HttpServletRequest req, @PathVariable String groupName) {
		return permissionGroupService.getGroupByName(groupName);
	}
	
	// UPDATE GROUP
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "updateGroup.wh:groupId={groupId}")
	public void updateGroup(HttpServletRequest req, @RequestBody PermissionGroup group, @PathVariable Long groupId) {
		permissionGroupService.updateGroup(group);
	}
		
	// DELETE GROUP BY groupID
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping(value = "deleteGroup.wh:groupId={groupId}")
	public void deleteGroup(HttpServletRequest req, @PathVariable Long groupId) {
		permissionGroupService.deleteGroup(groupId);
	}

}
