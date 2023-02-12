package e_appliance_warehouse.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import e_appliance_warehouse.repository.PermissionGroupRepository;
import e_appliance_warehouse.table.PermissionGroup;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PermissionGroupService {

	private PermissionGroupRepository permissionGroupRepository;
	
	// ADD NEW GROUP
	public void addGroup(PermissionGroup group) {
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		group.setCreatedUserId("SYSTEM");
		group.setCreatedTimestamp(currentTimestamp);
		group.setUpdatedUserId("SYSTEM");
		group.setUpdatedTimestamp(currentTimestamp);
		permissionGroupRepository.save(group);
	}
	
	// CLONE GROUP
	public Boolean cloneGroup(Long sourceGroupId, String targetGroupName) {
		boolean resp = false;
				
		if(targetGroupName != null) {
			resp = true;
			PermissionGroup sourceGroup = permissionGroupRepository.getGroupById(sourceGroupId);
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
					.build();
			addGroup(targetGroup);
		}
		
		return resp;
	}
	
	// GET ALL GROUPS
	public List<PermissionGroup> getAllGroups() {
		return permissionGroupRepository.getAllGroups();
	}
	
	// GET GROUP BY groupID
	public PermissionGroup getGroupById(Long groupId) {
		return permissionGroupRepository.getGroupById(groupId);
	}
	
	// GET GROUP BY groupName (OR CONTAINS PART OF THE NAME)
	public List<PermissionGroup> getGroupByName(String groupName) {
		return permissionGroupRepository.getGroupByName(groupName.toLowerCase());
	}
	
	// UPDATE GROUP
	public void updateGroup(PermissionGroup group) {
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());	
		group.setUpdatedUserId("SYSTEM");
		group.setUpdatedTimestamp(currentTimestamp);
		permissionGroupRepository.save(group);
	}
		
	// DELETE GROUP BY groupID
	public void deleteGroup(Long groupId) {
		permissionGroupRepository.deleteGroup(groupId);
	}

}
