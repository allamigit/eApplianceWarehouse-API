package e_appliance_warehouse.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import e_appliance_warehouse.controller.UserController;
import e_appliance_warehouse.model.AccessGroup;
import e_appliance_warehouse.model.SaleOrder;
import e_appliance_warehouse.repository.SaleOrderRepo;
import e_appliance_warehouse.repository.UserRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaleOrderService {
	
	private SaleOrderRepo saleOrderRepo;
	private SaleOrderItemsService saleOrderItemsService;
	private UserRepo userRepo;
	private JavaMailSender mailSender;
	
	// GET USER PERMISSIONS
	public AccessGroup getUserPermissions() {
		return userRepo.getUserByUsername(UserController.uEmail).getAccessGroup();
	}
	
	// ADD NEW SALE ORDER
	public SaleOrder addSaleOrder(SaleOrder saleOrder) {
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
	public List<SaleOrder> getAllSaleOrders() {
		List<SaleOrder> ordersList = saleOrderRepo.getAllOrders();
		
		// Save user full name to the object attribute [createdUser]
		/*String fullName = "";
		for(int i = 0; i < ordersList.size(); i++) {
			fullName = ordersList.get(i).getUser().getFirstName() + " " + ordersList.get(i).getUser().getLastName();
			ordersList.get(i).setCreatedUser(fullName);
			ordersList.get(i).setUser(null);
		}*/
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		//if(getUserPermissions().getOrderCurrentUser() | !getUserPermissions().getOrderOtherUserFind()) {
		if(!getUserPermissions().getOrderOtherUserFind()) {
			List<SaleOrder> newOrdersList = new ArrayList<>();
			
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
	public List<SaleOrder> getTodayOrders() {
		List<SaleOrder> ordersList = saleOrderRepo.getTodayOrders();
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		if(!getUserPermissions().getOrderOtherUserFind()) {
			List<SaleOrder> newOrdersList = new ArrayList<>();
			
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
	public List<SaleOrder> getDateRangeOrders(LocalDate dateFrom, LocalDate dateTo) {
		List<SaleOrder> ordersList = saleOrderRepo.getDateRangeOrders(dateFrom, dateTo);
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		if(!getUserPermissions().getOrderOtherUserFind()) {
			List<SaleOrder> newOrdersList = new ArrayList<>();
			
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
	public SaleOrder getOrderById(int orderId) {
		SaleOrder saleOrder = saleOrderRepo.getOrderById(orderId);
		
		try {
			if(!getUserPermissions().getOrderCost()) {
				saleOrder.setOrderCost(null);
			}
			
			if(!getUserPermissions().getOrderOtherUserFind() & saleOrder.getUser().getUserId().intValue() != UserController.uId) {
				saleOrder = new SaleOrder();
			}
		} catch(NullPointerException e) {
			saleOrder = new SaleOrder();
		}
		
		return saleOrder;
	}
	
	// GET SALE ORDER BY CUSTOMER NAME
	public List<SaleOrder> getOrdersByCustomerName(String customerName) {
		List<SaleOrder> ordersList = saleOrderRepo.getOrdersByCustomerName(customerName);
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		if(!getUserPermissions().getOrderOtherUserFind()) {
			List<SaleOrder> newOrdersList = new ArrayList<>();
			
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
	public List<SaleOrder> getOrdersByCreatedUser(String createdUser) {
		List<SaleOrder> ordersList = saleOrderRepo.getOrdersByCreatedUser(createdUser);
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		return ordersList;
	}
	
	// GET SALE ORDER BY CUSTOMER NAME & CREATED USER   // *** NOT USED
	public List<SaleOrder> getOrdersByCustomerNameAndCreatedUser(String customerName, String createdUser) {
		List<SaleOrder> ordersList = saleOrderRepo.getOrdersByCustomerNameAndCreatedUser(customerName, createdUser);
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		return ordersList;
	}
	
	// GET ALL SALE ORDERS FOR STOCK ITEM BY itemID
	public List<SaleOrder> getOrdersForStockItem(int itemId) {
		List<SaleOrder> ordersList = saleOrderRepo.getOrdersForStockItem(itemId);
		
		if(!getUserPermissions().getOrderCost()) {
			for(int i = 0; i < ordersList.size(); i++) {
				ordersList.get(i).setOrderCost(null);
			}
		}
		
		if(!getUserPermissions().getOrderOtherUserFind()) {
			List<SaleOrder> newOrdersList = new ArrayList<>();
			
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
	public SaleOrder updateOrder(SaleOrder saleOrder) {
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
	
}
