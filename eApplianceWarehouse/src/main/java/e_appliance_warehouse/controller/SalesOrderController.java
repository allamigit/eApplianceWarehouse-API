package e_appliance_warehouse.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import e_appliance_warehouse.service.SalesOrderService;
import e_appliance_warehouse.table.SalesOrder;
import e_appliance_warehouse.util.SalesOrderExcelUtil;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "order")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@AllArgsConstructor
public class SalesOrderController {
/*	
	private SalesOrderService saleOrderService;
	private List<SalesOrder> ordersList;
	
	// ADD NEW SALE ORDER
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "addNewOrder.iwh")
	public SalesOrder addSaleOrder(HttpServletRequest req, @RequestBody SalesOrder saleOrder) {
		return saleOrderService.addSaleOrder(saleOrder);
	}
	
	// GET ALL SALE ORDERS
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listAllOrders.iwh")
	public List<SalesOrder> getAllSaleOrders(HttpServletRequest req) {
		ordersList = saleOrderService.getAllSaleOrders();
		return ordersList;
	}
	
	// GET SALE ORDERS FOR TODAY DATE
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listTodayOrders.iwh")
	public List<SalesOrder> getTodayOrders(HttpServletRequest req) {
		return saleOrderService.getTodayOrders();
	}
	
	// GET SALE ORDERS FOR DATE RANGE
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listDateRangeOrders.iwh:From={dateFrom}&To={dateTo}")
	public List<SalesOrder> getDateRangeOrders(HttpServletRequest req, @PathVariable String dateFrom, @PathVariable String dateTo) {
		try {
			return saleOrderService.getDateRangeOrders(LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
		} catch(DateTimeParseException e) {
			return null;
		}
	}
	
	// GET SALE ORDER BY ID
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getOrder.iwh:Id={orderId}")
	public SalesOrder getOrderById(HttpServletRequest req, @PathVariable int orderId) {
		return saleOrderService.getOrderById(orderId);
	}
	
	// GET SALE ORDER BY CUSTOMER NAME (OR PART OF THE NAME)
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getOrders.iwh:customerName={customerName}")
	public List<SalesOrder> getOrdersByCustomerName(HttpServletRequest req, @PathVariable String customerName) {
		return saleOrderService.getOrdersByCustomerName(customerName);
	}
	
	// GET SALE ORDER BY CREATED USER
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getOrders.iwh:createdUser={createdUser}")
	public List<SalesOrder> getOrdersByCreatedUser(HttpServletRequest req, @PathVariable String createdUser) {
		return saleOrderService.getOrdersByCreatedUser(createdUser);
	}
	
	// GET SALE ORDER BY CUSTOMER NAME & CREATED USER   // *** NOT USED
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getOrders.iwh:customerName={customerName}&createdUser={createdUser}")
	public List<SalesOrder> getOrdersByCustomerNameAndCreatedUser(HttpServletRequest req, @PathVariable String customerName, @PathVariable String createdUser) {
		return saleOrderService.getOrdersByCustomerNameAndCreatedUser(customerName, createdUser);
	}
	
	// GET ALL SALE ORDERS FOR STOCK ITEM BY itemID
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "getOrders.iwh:itemId={itemId}")
	public List<SalesOrder> getOrdersForStockItem(HttpServletRequest req, @PathVariable int itemId) {
		return saleOrderService.getOrdersForStockItem(itemId);
	}
	
	// UPDATE SALE ORDER
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "updateOrder.iwh:Id={orderId}")
	public SalesOrder updateOrder(HttpServletRequest req, @RequestBody SalesOrder saleOrder, @PathVariable int orderId) {
		return saleOrderService.updateOrder(saleOrder);
	}
	
	// CANCEL SALE ORDER
	@DeleteMapping(value = "cancelOrder.iwh:Id={orderId}")
	public SalesOrder cancelOrder(HttpServletRequest req, HttpServletResponse resp, @PathVariable int orderId) {
		SalesOrder saleOrder = saleOrderService.getOrderById(orderId);
		
		if(saleOrderService.cancelOrder(orderId)) {
			resp.setStatus(200);  // Order cancelled successfully
			
			if(!saleOrderService.getUserPermissions().getOrderCost()) {
				saleOrder.setOrderCost(null);
			}
		} else {
			resp.setStatus(406);  // Will be changed to cannot cancel due to payment or pick up
		}		
		
		return saleOrder;
	}
	
	// Generate Excel File
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "listAllOrders.iwh/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy_hh:mm_a");
        String currentDateTime = dateFormatter.format(new Date());
        
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Orders_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        //List<SaleOrder> ordersList = saleOrderService.getAllSaleOrders();
         
        SalesOrderListExcel excelList = new SalesOrderListExcel(ordersList);
         
        excelList.generateExcel(response); 
        
        //saleOrderService.sendEmail();
    }  
*/	
}
