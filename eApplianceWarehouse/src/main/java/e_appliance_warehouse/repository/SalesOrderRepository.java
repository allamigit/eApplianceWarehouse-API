package e_appliance_warehouse.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import e_appliance_warehouse.table.SalesOrder;

@Repository
@Transactional
public interface SalesOrderRepository extends JpaRepository<SalesOrder,Integer> {
	
	// Get All Sale Orders
	@Query(value = "SELECT * FROM sale_order ORDER BY order_date DESC, order_id DESC", nativeQuery = true)
	public List<SalesOrder> getAllOrders();

	// Get Sale Orders for Today Date
	@Query(value = "SELECT * FROM sale_order WHERE order_date = CURRENT_DATE ORDER BY order_date DESC, order_id DESC", nativeQuery = true)
	public List<SalesOrder> getTodayOrders();

	// Get Sale Orders for Date Range
	@Query(value = "SELECT * FROM sale_order WHERE order_date >= ? AND order_date <= ? ORDER BY order_date DESC, order_id DESC", nativeQuery = true)
	public List<SalesOrder> getDateRangeOrders(LocalDate dateFrom, LocalDate dateTo);

	// Get Sale Order by orderID
	@Query(value = "SELECT * FROM sale_order WHERE order_id = ?", nativeQuery = true)
	public SalesOrder getOrderById(int orderId);

	// Get Sale Order by customerName (or if contains part of the name)
	@Query(value = "SELECT * FROM sale_order WHERE LOWER(customer_name) LIKE %?% ORDER BY order_date DESC, order_id DESC", nativeQuery = true)
	public List<SalesOrder> getOrdersByCustomerName(String customerName);

	// Get Sale Order by createdUser
	@Query(value = "SELECT * FROM sale_order WHERE created_user = ? ORDER BY order_date DESC, order_id DESC", nativeQuery = true)
	public List<SalesOrder> getOrdersByCreatedUser(String createdUser);

	// Get Sale Order by customerName and createdUser   // *** NOT USED
	@Query(value = "SELECT * FROM sale_order WHERE customer_name = ? AND created_user = ? ORDER BY order_date DESC, order_id DESC", nativeQuery = true)
	public List<SalesOrder> getOrdersByCustomerNameAndCreatedUser(String customerName, String createdUser);

	// Get All Sale Orders for Stock Item by itemID
	@Query(value = "SELECT * FROM sale_order WHERE order_id IN (SELECT order_id FROM sale_order_items WHERE item_id = ?) ORDER BY order_date DESC, order_id DESC", nativeQuery = true)
	public List<SalesOrder> getOrdersForStockItem(int itemId);

	// Update Sale Order Total by orderID
	@Modifying
	@Query(value = "UPDATE sale_order SET order_total = ? WHERE order_id = ?", nativeQuery = true)
	public void updateOrderTotal(double orderTotal, int orderId);

	// Update Sale Order Cost by orderID
	@Modifying
	@Query(value = "UPDATE sale_order SET order_cost = ? WHERE order_id = ?", nativeQuery = true)
	public void updateOrderCost(double orderCost, int orderId);

	// Update Sale Order Weight by orderID
	@Modifying
	@Query(value = "UPDATE sale_order SET items_weight = ? WHERE order_id = ?", nativeQuery = true)
	public void updateItemsWeight(double itemsWeight, int orderId);

	// Update Sale Order Items Count by orderID
	@Modifying
	@Query(value = "UPDATE sale_order SET items_count = ? WHERE order_id = ?", nativeQuery = true)
	public void updateItemsCount(int itemsCount, int orderId);

	// Update Sale Order Pickup Count by orderID
	@Modifying
	@Query(value = "UPDATE sale_order SET pickup_count = ? WHERE order_id = ?", nativeQuery = true)
	public void updatePickupCount(int pickupCount, int orderId);

	// Update Sale Order createdUser & createdBy by orderID
	@Modifying
	@Query(value = "UPDATE sale_order SET created_user = ?, created_by = ? WHERE order_id = ?", nativeQuery = true)
	public void changeCreated(String createdUser, String createdBy, int orderId);

	// Update Sale Order updatedBy by orderID
	@Modifying
	@Query(value = "UPDATE sale_order SET updated_by = ? WHERE order_id = ?", nativeQuery = true)
	public void changeUpdated(String updatedBy, int orderId);

	// Delete Sale Order by orderID
	@Modifying
	@Query(value = "DELETE FROM sale_order WHERE order_id = ?", nativeQuery = true)
	public void cancelOrder(int orderId);
	
}
