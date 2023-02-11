package e_appliance_warehouse.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import e_appliance_warehouse.controller.EmployeeController;
import e_appliance_warehouse.repository.SalesOrderRepository;
import e_appliance_warehouse.repository.EmployeeRepository;
import e_appliance_warehouse.table.PermissionGroups;
import e_appliance_warehouse.table.SalesOrder;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SalesOrderService {
/*	
	private SalesOrderRepository saleOrderRepo;
	private SalesOrderItemsService saleOrderItemsService;
	private UserRepository userRepo;
	private JavaMailSender mailSender;
	
	// GET USER PERMISSIONS
	public PermissionGroups getUserPermissions() {
		return userRepo.getUserByUsername(UserController.uEmail).getAccessGroup();
	}
	
	// ADD NEW SALE ORDER
	public SalesOrder addSaleOrder(SalesOrder saleOrder) {
		saleOrder.setGrossTotal(0.0);
		saleOrder.setOrderCost(0.0);
		saleOrder.setDiscountPercent(0.0);
		saleOrder.setDiscountAmount(0.0);
		saleOrder.setDiscountTotal(0.0);
		saleOrder.setOrderTotal(0.0);
		saleOrder.setSaleTax(0.0);
		saleOrder.setStateTax(0.0);
		saleOrder.setTotalAmount(0.0);
		saleOrder.setAmountPaid(0.0);
		saleOrder.setAmountDue(0.0);
		saleOrder.setItemsWeight(0.0);
		saleOrder.setItemsCount(0);
		saleOrder.setPickupCount(0);
		saleOrder.setBillingStatus(false);
		saleOrder.setUser(userRepo.getUserById(UserController.uId));
		saleOrder.setCreatedBy(UserController.uName + " - Date: " + LocalDate.now() + ", Time: " + LocalTime.now());
		saleOrderRepo.save(saleOrder);
		
		if(!getUserPermissions().getOrderCost()) {
			saleOrder.setOrderCost(null);
		}

		return saleOrder;
	}
	
	// GET ALL SALE ORDERS
	public List<SalesOrder> getAllSaleOrders() {
		List<SalesOrder> ordersList = saleOrderRepo.getAllOrders();
		
		// Save user full name to the object attribute [createdUser]
		/*String fullName = "";
		for(int i = 0; i < ordersList.size(); i++) {
			fullName = ordersList.get(i).getUser().getFirstName() + " " + ordersList.get(i).getUser().getLastName();
			ordersList.get(i).setCreatedUser(fullName);
			ordersList.get(i).setUser(null);
		}*/
/*		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		//if(getUserPermissions().getOrderCurrentUser() | !getUserPermissions().getOrderOtherUserFind()) {
		if(!getUserPermissions().getOrderOtherUserFind()) {
			List<SalesOrder> newOrdersList = new ArrayList<>();
			
			for(int i = 0; i < ordersList.size(); i++) {
				if(ordersList.get(i).getUser().getUserId().intValue() == UserController.uId) {
					newOrdersList.add(ordersList.get(i));
				}
			}
			
			ordersList = newOrdersList;
		}
		
		return ordersList;
	}
	
	// GET SALE ORDERS FOR TODAY DATE
	public List<SalesOrder> getTodayOrders() {
		List<SalesOrder> ordersList = saleOrderRepo.getTodayOrders();
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		if(!getUserPermissions().getOrderOtherUserFind()) {
			List<SalesOrder> newOrdersList = new ArrayList<>();
			
			for(int i = 0; i < ordersList.size(); i++) {
				if(ordersList.get(i).getUser().getUserId().intValue() == UserController.uId) {
					newOrdersList.add(ordersList.get(i));
				}
			}
			
			ordersList = newOrdersList;
		}
		
		return ordersList;
	}
	
	// GET SALE ORDERS FOR DATE RANGE
	public List<SalesOrder> getDateRangeOrders(LocalDate dateFrom, LocalDate dateTo) {
		List<SalesOrder> ordersList = saleOrderRepo.getDateRangeOrders(dateFrom, dateTo);
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		if(!getUserPermissions().getOrderOtherUserFind()) {
			List<SalesOrder> newOrdersList = new ArrayList<>();
			
			for(int i = 0; i < ordersList.size(); i++) {
				if(ordersList.get(i).getUser().getUserId().intValue() == UserController.uId) {
					newOrdersList.add(ordersList.get(i));
				}
			}
			
			ordersList = newOrdersList;
		}
		
		return ordersList;
	}
	
	// GET SALE ORDER BY ID
	public SalesOrder getOrderById(int orderId) {
		SalesOrder saleOrder = saleOrderRepo.getOrderById(orderId);
		
		try {
			if(!getUserPermissions().getOrderCost()) {
				saleOrder.setOrderCost(null);
			}
			
			if(!getUserPermissions().getOrderOtherUserFind() & saleOrder.getUser().getUserId().intValue() != UserController.uId) {
				saleOrder = new SalesOrder();
			}
		} catch(NullPointerException e) {
			saleOrder = new SalesOrder();
		}
		
		return saleOrder;
	}
	
	// GET SALE ORDER BY CUSTOMER NAME
	public List<SalesOrder> getOrdersByCustomerName(String customerName) {
		List<SalesOrder> ordersList = saleOrderRepo.getOrdersByCustomerName(customerName);
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		if(!getUserPermissions().getOrderOtherUserFind()) {
			List<SalesOrder> newOrdersList = new ArrayList<>();
			
			for(int i = 0; i < ordersList.size(); i++) {
				if(ordersList.get(i).getUser().getUserId().intValue() == UserController.uId) {
					newOrdersList.add(ordersList.get(i));
				}
			}
			
			ordersList = newOrdersList;
		}
		
		return ordersList;
	}
	
	// GET SALE ORDER BY CREATED USER
	public List<SalesOrder> getOrdersByCreatedUser(String createdUser) {
		List<SalesOrder> ordersList = saleOrderRepo.getOrdersByCreatedUser(createdUser);
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		return ordersList;
	}
	
	// GET SALE ORDER BY CUSTOMER NAME & CREATED USER   // *** NOT USED
	public List<SalesOrder> getOrdersByCustomerNameAndCreatedUser(String customerName, String createdUser) {
		List<SalesOrder> ordersList = saleOrderRepo.getOrdersByCustomerNameAndCreatedUser(customerName, createdUser);
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		return ordersList;
	}
	
	// GET ALL SALE ORDERS FOR STOCK ITEM BY itemID
	public List<SalesOrder> getOrdersForStockItem(int itemId) {
		List<SalesOrder> ordersList = saleOrderRepo.getOrdersForStockItem(itemId);
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		if(!getUserPermissions().getOrderOtherUserFind()) {
			List<SalesOrder> newOrdersList = new ArrayList<>();
			
			for(int i = 0; i < ordersList.size(); i++) {
				if(ordersList.get(i).getUser().getUserId().intValue() == UserController.uId) {
					newOrdersList.add(ordersList.get(i));
				}
			}
			
			ordersList = newOrdersList;
		}

		return ordersList;
	}
	
	// UPDATE SALE ORDER
	public SalesOrder updateOrder(SalesOrder saleOrder) {
		saleOrder.setUpdatedBy(UserController.uName + " - Order Summary Updated - Date: " + LocalDate.now() + ", Time: " + LocalTime.now());
		saleOrderRepo.save(saleOrder);
		
		if(!getUserPermissions().getOrderCost()) {
			saleOrder.setOrderCost(null);
		}
		
		return saleOrder;
	}
	
	// UPDATE SALE ORDER TOTAL
	public void updateOrderTotal(double orderTotal, int orderId) {
		saleOrderRepo.updateOrderTotal(orderTotal, orderId);
	}
	
	// CANCEL SALE ORDER
	public Boolean cancelOrder(int orderId) {
		boolean resp = false;
		
		double total = saleOrderRepo.getOrderById(orderId).getOrderTotal();
		if(total >= 0) {  // It will be changed to paid==0 & picked==0
			saleOrderItemsService.removeAllOrderItems(orderId);
			saleOrderRepo.cancelOrder(orderId);
			resp = true;
		}
		
		return resp;
	}
	
	// SEND EMAIL
	public void sendEmail() {
		String from = "m_allamee@yahoo.com";
		String to = "m_allamee@yahoo.com";
		 
		SimpleMailMessage message = new SimpleMailMessage();
		 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject("This is a plain text email");
		message.setText("Hello guys! This is a plain text email.");
		 
		mailSender.send(message);
	}
*/	
}
