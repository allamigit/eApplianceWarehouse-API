package e_appliance_warehouse.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import e_appliance_warehouse.controller.EmployeeController;
import e_appliance_warehouse.repository.SalesOrderItemsRepository;
import e_appliance_warehouse.repository.SalesOrderRepository;
import e_appliance_warehouse.repository.StockRepository;
import e_appliance_warehouse.repository.EmployeeRepository;
import e_appliance_warehouse.table.PermissionGroup;
import e_appliance_warehouse.table.SalesOrder;
import e_appliance_warehouse.table.SalesOrderItems;
import e_appliance_warehouse.table.WarehouseUser;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SalesOrderItemsService {
/*	
	private StockRepository stockRepo;
	private SalesOrderRepository saleOrderRepo;
	private SalesOrderItemsRepository saleOrderItemsRepo;
	private UserRepository userRepo;
	
	// GET USER PERMISSIONS
	public PermissionGroups getUserPermissions() {
		return userRepo.getUserByUsername(UserController.uEmail).getAccessGroup();
	}
	
	// ADD NEW ITEM TO SALE ORDER
	public Boolean addOrderItem(SalesOrderItems saleOrderItems) {
		boolean resp = false;		
		int orderId = saleOrderItems.getSaleOrder().getOrderId();
		int soldItemId = saleOrderItems.getStock().getItemId();
		String soldItemName = saleOrderItems.getStock().getItemName();
		double unitPrice = saleOrderItems.getUnitPrice();
		double salePrice = saleOrderItems.getSalePrice();
		double costPrice = stockRepo.getItemById(soldItemId).getCostPrice();
		double itemWeight = stockRepo.getItemById(soldItemId).getItemWeight();
		boolean taxStatus = stockRepo.getItemById(soldItemId).getTaxStatus();
		
		SalesOrder saleOrder = saleOrderRepo.getOrderById(orderId);
		User createdUser = saleOrder.getUser();
		
		// Save cost price from Stock to cost price in SaleOrderItems 
		saleOrderItems.setCostPrice(costPrice);
		
		// Save tax status from Stock to tax status in SaleOrderItems 
		saleOrderItems.setTaxStatus(taxStatus);
		
		// Save sale price in SaleOrderItems 
		saleOrderItems.setSalePrice(salePrice);
		
		int stockQty = stockRepo.getItemById(soldItemId).getItemQTY();
		int soldQty = saleOrderItems.getSoldQTY();
		
		double stockSalePrice = stockRepo.getItemById(soldItemId).getSalePrice();	
		if(stockSalePrice == 0) salePrice = unitPrice;
				
		if(soldQty > 0 & soldQty <= stockQty & unitPrice >= costPrice) {			
			// Calculate Subtotal
			if(salePrice > 0) {
				saleOrderItems.setSubtotal(soldQty * salePrice);
			} else {
				saleOrderItems.setSubtotal(soldQty * unitPrice);
			}

			// Calculate Item Weight
			saleOrderItems.setItemWeight(itemWeight * soldQty);
			
			// Avoid Null Value in Stock Table
			saleOrderItems.getStock().setCostPrice(costPrice);
			saleOrderItems.getStock().setItemWeight(itemWeight);
			saleOrderItems.getStock().setTaxStatus(taxStatus);

			// SAVE NEW ITEM
			if(stockSalePrice == 0) saleOrderItems.setSalePrice(0.0);
			saleOrderItems.setPickupCount(0);
			saleOrderItems.setItemsLeft(soldQty);
			saleOrderItemsRepo.save(saleOrderItems);
			
			// Update stock QTY
			int newQty = stockQty - soldQty;
			stockRepo.updateStockQty(newQty, soldItemId);
			
			// Avoid Null Value in Sale Order Table
			saleOrder.setUser(createdUser);
			saleOrderRepo.save(saleOrder);

			// Update sale order record
			String updatedByMessage = UserController.uName + " - Item [" + soldItemName + "] Added - Date: " + LocalDate.now() + ", Time: " + LocalTime.now();
			updateSaleOrderRecord(orderId, updatedByMessage);

			resp = true;
		}
		
		return resp;
	}
	
	// GET ALL SALE ORDER ITEMS BY ORDER ID
	public List<SalesOrderItems> getAllOrderItems(int orderId) {
		List<SalesOrderItems> itemsList = saleOrderItemsRepo.getAllOrderItems(orderId);
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < itemsList.size(); i++) {
				itemsList.get(i).setCostPrice(null);
				itemsList.get(i).getSaleOrder().setOrderCost(null);
				itemsList.get(i).getStock().setCostPrice(null);
			}
		}
		
		return itemsList;
	}
	
	// GET ONE ORDER ITEM
	public SalesOrderItems getOrderItem(int itemSEQ) {
		SalesOrderItems saleOrderItems = saleOrderItemsRepo.getOrderItem(itemSEQ);
		
		if(!getUserPermissions().getOrderCost()) {
			saleOrderItems.setCostPrice(null);
		}

		return saleOrderItems;
	}
	
	// CALCULATE TOTAL OF ORDER ITEMS BY ORDER ID
	public Double calculateOrderTotal(int orderId) {
		return saleOrderItemsRepo.calculateOrderTotal(orderId);
	}
	
	// RETRIEVE CURRENT UNIT PRICE FOR ALL ORDER ITEMS BY ORDER ID  *** NOT USED
	public void retrieveUnitPrice(int orderId) {
		List<SalesOrderItems> itemsList = new ArrayList<>();
		itemsList = saleOrderItemsRepo.getAllOrderItems(orderId);
		
		int itemId = 0;
		double unitPrice = 0;
		for(SalesOrderItems item : itemsList) {
			itemId = item.getStock().getItemId();
			unitPrice = stockRepo.getItemById(itemId).getSellingPrice();
			updateOrderItem(item.getSoldQTY(), unitPrice, item.getSalePrice(), item.getTaxStatus(), item.getItemSEQ());
		}
	}

	// RETRIEVE CURRENT SALE PRICE FOR ALL ORDER ITEMS BY ORDER ID  *** NOT USED
	public void retrieveSalePrice(int orderId) {
		List<SalesOrderItems> itemsList = new ArrayList<>();
		itemsList = saleOrderItemsRepo.getAllOrderItems(orderId);
		
		int itemId = 0;
		double salePrice = 0;
		for(SalesOrderItems item : itemsList) {
			itemId = item.getStock().getItemId();
			salePrice = stockRepo.getItemById(itemId).getSalePrice();
			updateOrderItem(item.getSoldQTY(), item.getUnitPrice(), salePrice, item.getTaxStatus(), item.getItemSEQ());
		}
	}

	// UPDATE ORDER ITEM (SOLD QTY, UNIT PRICE & SALE PRICE)
	public Boolean updateOrderItem(int soldQty, double unitPrice, double salePrice, boolean taxStatus, int itemSEQ) {
		boolean resp = false;
		double subtotal = 0;
		int orderId = saleOrderItemsRepo.getOrderItem(itemSEQ).getSaleOrder().getOrderId();
		int itemId = saleOrderItemsRepo.getOrderItem(itemSEQ).getStock().getItemId();
		String itemName = saleOrderItemsRepo.getOrderItem(itemSEQ).getStock().getItemName();
		int stockQty = stockRepo.getItemById(itemId).getItemQTY();
		int orderQty = saleOrderItemsRepo.getOrderItem(itemSEQ).getSoldQTY();
		int differenceQty = soldQty - orderQty;	
		double costPrice = stockRepo.getItemById(itemId).getCostPrice();
		double itemWeight = stockRepo.getItemById(itemId).getItemWeight();
		
		double stockSalePrice = stockRepo.getItemById(itemId).getSalePrice();	
		if(stockSalePrice == 0) salePrice = unitPrice;
		
		if(soldQty > 0 & differenceQty <= stockQty & unitPrice >= costPrice) {
			// Update stock QTY
			stockRepo.updateStockQty(stockQty - differenceQty, itemId);
			
			// Calculate Subtotal
			if(salePrice > 0) {
				subtotal = soldQty * salePrice;
			} else {
				subtotal = soldQty * unitPrice;
			}

			// Calculate Item Weight
			itemWeight = itemWeight * soldQty;
			
			// UPDATE SOLD QTY, UNIT PRICE, SALE PRICE, SUBTOTAL, ITEM WEIGHT & ITEMS LEFT
			int itemsLeft = soldQty - saleOrderItemsRepo.getOrderItem(itemSEQ).getPickupCount();
			if(stockSalePrice == 0) salePrice = 0;
			saleOrderItemsRepo.updateOrderItem(soldQty, unitPrice, salePrice, subtotal, itemWeight, itemsLeft, taxStatus, itemSEQ);

			// Update sale order record
			if(saleOrderItemsRepo.calculateOrderTotal(orderId) != null) {				
				String updatedByMessage = UserController.uName + " - Item [" + itemName + "] Updated - Date: " + LocalDate.now() + ", Time: " + LocalTime.now();
				updateSaleOrderRecord(orderId, updatedByMessage);
			} 
			
			resp = true;
		}

		return resp;
	}
	
	// REMOVE ORDER ITEM
	public void removeOrderItem(int itemSEQ) {
		int orderId = saleOrderItemsRepo.getOrderItem(itemSEQ).getSaleOrder().getOrderId();
		int itemId = saleOrderItemsRepo.getOrderItem(itemSEQ).getStock().getItemId();
		String itemName = saleOrderItemsRepo.getOrderItem(itemSEQ).getStock().getItemName();

		// Update stock QTY
		int newQty = stockRepo.getItemById(itemId).getItemQTY() + saleOrderItemsRepo.getOrderItem(itemSEQ).getSoldQTY();
		stockRepo.updateStockQty(newQty, itemId);
		
		// REMOVE ORDER ITEM
		saleOrderItemsRepo.removeOrderItem(itemSEQ);
		
		// Update sale order record
		if(saleOrderItemsRepo.calculateOrderTotal(orderId) != null) {
			String updatedByMessage = UserController.uName + " - Item [" + itemName + "] Removed - Date: " + LocalDate.now() + ", Time: " + LocalTime.now();
			updateSaleOrderRecord(orderId, updatedByMessage);
		} 
		
	}

	// REMOVE ALL ORDER ITEMS BY ORDER ID - VOID SALE ORDER
	public void removeAllOrderItems(int orderId) {
		List<SalesOrderItems> itemsList = new ArrayList<>();
		itemsList = saleOrderItemsRepo.getAllOrderItems(orderId);
		
		// Update stock QTY
		int itemId = 0;
		int newQty = 0;
		for(SalesOrderItems item : itemsList) {
			itemId = item.getStock().getItemId();
			newQty = stockRepo.getItemById(itemId).getItemQTY() + item.getSoldQTY();
			stockRepo.updateStockQty(newQty, itemId);			
		}
		
		// REMOVE ALL ORDER ITEMS
		saleOrderItemsRepo.removeAllOrderItems(orderId);		
	}
	
	// UPDATE SALE ORDER RECORD
	public void updateSaleOrderRecord(int orderId, String updatedByMessage) {		
		SalesOrder saleOrder = saleOrderRepo.getOrderById(orderId);
		
		saleOrder.setGrossTotal(saleOrderItemsRepo.calculateOrderTotal(orderId));			
		saleOrder.setOrderCost(saleOrderItemsRepo.calculateOrderCost(orderId));
		if(saleOrder.getDiscountPercent() == null) saleOrder.setDiscountPercent(0.0);
		if(saleOrder.getDiscountAmount() == null) saleOrder.setDiscountAmount(0.0);
		
		double discountTotal = 0;
		if(saleOrder.getDiscountPercent() > 0) {
			discountTotal = saleOrder.getDiscountPercent() / 100 * saleOrderItemsRepo.calculateOrderTotal(orderId);
		}
		if(saleOrder.getDiscountAmount() > 0) {
			discountTotal = saleOrder.getDiscountAmount();
		}
		saleOrder.setDiscountTotal(discountTotal);
		
		saleOrder.setOrderTotal(saleOrder.getGrossTotal() - saleOrder.getDiscountTotal());
		
		double taxableItems = 0;
		try {
			taxableItems = saleOrder.getOrderTotal() - saleOrderItemsRepo.calculateNonTaxableItems(orderId);
		} catch(NullPointerException e) {
			taxableItems = saleOrder.getOrderTotal();
		}
		saleOrder.setSaleTax(taxableItems * 0.0825);
		
		saleOrder.setStateTax(taxableItems * 0.0);
		saleOrder.setTotalAmount(saleOrder.getOrderTotal() + saleOrder.getSaleTax() + saleOrder.getStateTax());
		saleOrder.setAmountPaid(0.0);
		saleOrder.setAmountDue(saleOrder.getTotalAmount() - saleOrder.getAmountPaid());
		saleOrder.setItemsWeight(saleOrderItemsRepo.calculateItemsWeight(orderId));
		saleOrder.setItemsCount(saleOrderItemsRepo.calculateItemsCount(orderId));
		saleOrder.setPickupCount(saleOrderItemsRepo.calculatePickupCount(orderId));
		saleOrder.setUpdatedBy(updatedByMessage);
		
		saleOrderRepo.save(saleOrder);
	}
*/
}
