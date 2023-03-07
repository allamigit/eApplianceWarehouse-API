package e_appliance_warehouse.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import e_appliance_warehouse.controller.WarehouseUserController;
import e_appliance_warehouse.repository.PermissionGroupRepository;
import e_appliance_warehouse.table.PermissionGroup;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PermissionGroupService {

	private PermissionGroupRepository permissionGroupRepository;
	
	// ADD NEW GROUP
	public Boolean addGroup(PermissionGroup group) {
		boolean resp = false;
		if(WarehouseUserController.loggedUser.getPermissionList().getPermissionGroups()) {
			resp = true;
			Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
			group.setCreatedUserId(WarehouseUserController.uId);
			group.setCreatedTimestamp(currentTimestamp);
			group.setUpdatedUserId(WarehouseUserController.uId);
			group.setUpdatedTimestamp(currentTimestamp);
			permissionGroupRepository.save(group);
		}
		
		return resp;
	}
	
	// CLONE GROUP
	public Boolean cloneGroup(Long sourceGroupId, String targetGroupName) {
		boolean resp = false;
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
				
		PermissionGroup sourceGroup = permissionGroupRepository.getGroupById(sourceGroupId);
		if(sourceGroup != null && targetGroupName != null && !targetGroupName.equals("") 
				&& WarehouseUserController.loggedUser.getPermissionList().getPermissionGroups()) {
			resp = true;
			PermissionGroup targetGroup = PermissionGroup.builder()
					.groupName(targetGroupName)
					.settings(sourceGroup.getSettings())
					.users(sourceGroup.getUsers())
					.usersReadOnly(sourceGroup.getUsersReadOnly())
					.permissionGroups(sourceGroup.getPermissionGroups())
					.permissionGroupsReadOnly(sourceGroup.getPermissionGroupsReadOnly())
					.generalConfig(sourceGroup.getGeneralConfig())
					.generalConfigReadOnly(sourceGroup.getGeneralConfigReadOnly())
					.stock(sourceGroup.getStock())
					.newStock(sourceGroup.getNewStock())
					.costPrice(sourceGroup.getCostPrice())
					.costPriceReadOnly(sourceGroup.getCostPriceReadOnly())
					.stockDelete(sourceGroup.getStockDelete())
					.stockUpdate(sourceGroup.getStockUpdate())
					.stockInfo(sourceGroup.getStockInfo())
					.salesOrder(sourceGroup.getSalesOrder())
					.allOrders(sourceGroup.getAllOrders())
					.orderCost(sourceGroup.getOrderCost())
					.orderChangePrice(sourceGroup.getOrderChangePrice())
					.orderDelete(sourceGroup.getOrderDelete())
					.orderUpdate(sourceGroup.getOrderUpdate())
					.newOrder(sourceGroup.getNewOrder())
					.orderCurrentUser(sourceGroup.getOrderCurrentUser())
					.orderOtherUserReadOnly(sourceGroup.getOrderOtherUserReadOnly())
					.orderOtherUserOpen(sourceGroup.getOrderOtherUserOpen())
					.orderOtherUserFind(sourceGroup.getOrderOtherUserFind())
					.orderInfo(sourceGroup.getOrderInfo())
					.orderInvoice(sourceGroup.getOrderInvoice())
					.orderPayment(sourceGroup.getOrderPayment())
					.orderPickup(sourceGroup.getOrderPickup())
					.orderPickupReadOnly(sourceGroup.getOrderPickupReadOnly())
					.orderApproval(sourceGroup.getOrderApproval())
					.createdUserId(sourceGroup.getCreatedUserId())
					.createdTimestamp(sourceGroup.getCreatedTimestamp())
					.updatedUserId(WarehouseUserController.uId)
					.updatedTimestamp(currentTimestamp)
					.build();
			permissionGroupRepository.save(targetGroup);
		}
		
		return resp;
	}
	
	// GET ALL GROUPS
	public List<PermissionGroup> getAllGroups() {
		return WarehouseUserController.loggedUser.getPermissionList().getPermissionGroups()?permissionGroupRepository.getAllGroups():null;
	}
	
	// GET GROUP BY groupID
	public PermissionGroup getGroupById(Long groupId) {
		return WarehouseUserController.loggedUser.getPermissionList().getPermissionGroups()?permissionGroupRepository.getGroupById(groupId):null;
	}
	
	// GET GROUP BY groupName (OR CONTAINS PART OF THE NAME)
	public List<PermissionGroup> getGroupByName(String groupName) {
		return WarehouseUserController.loggedUser.getPermissionList().getPermissionGroups()?permissionGroupRepository.getGroupByName(groupName.toLowerCase()):null;
	}
	
	// UPDATE GROUP
	public Boolean updateGroup(PermissionGroup group) {
		boolean resp = false;
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());	
		if(permissionGroupRepository.getGroupById(group.getGroupId()) != null 
				&& WarehouseUserController.loggedUser.getPermissionList().getPermissionGroups()) {
			resp = true;
			group.setUpdatedUserId(WarehouseUserController.uId);
			group.setUpdatedTimestamp(currentTimestamp);
			permissionGroupRepository.save(group);
		}
		
		return resp;
	}
		
	// DELETE GROUP BY groupID
	public Boolean deleteGroup(Long groupId) {
		boolean resp = false;
		if(permissionGroupRepository.getGroupById(groupId) != null 
				&& WarehouseUserController.loggedUser.getPermissionList().getPermissionGroups()) {
			resp = true;
			permissionGroupRepository.deleteGroup(groupId);
		}
		
		return resp;
	}

}
