package com.iappliancewarehouse.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iappliancewarehouse.model.SaleOrderItems;

@Repository
@Transactional
public interface SaleOrderItemsRepo extends JpaRepository<SaleOrderItems,Integer> {
	
	// Get All Order Items by orderID
	@Query(value = "SELECT * FROM sale_order_items WHERE order_id = ? ORDER BY item_seq ASC", nativeQuery = true)
	public List<SaleOrderItems> getAllOrderItems(int orderId);
	
	// Calculate Order Items Total by orderID
	@Query(value = "SELECT SUM(subtotal) FROM sale_order_items WHERE order_id = ?", nativeQuery = true)
	public Double calculateOrderTotal(int orderId);
	
	// Calculate Order Items Cost by orderID
	@Query(value = "SELECT SUM(sold_qty * cost_price) FROM sale_order_items WHERE order_id = ?", nativeQuery = true)
	public Double calculateOrderCost(int orderId);
	
	// Calculate Order Items Weight by orderID
	@Query(value = "SELECT SUM(item_weight) FROM sale_order_items WHERE order_id = ?", nativeQuery = true)
	public Double calculateItemsWeight(int orderId);
	
	// Calculate Order Items Count by orderID
	@Query(value = "SELECT SUM(sold_qty) FROM sale_order_items WHERE order_id = ?", nativeQuery = true)
	public Integer calculateItemsCount(int orderId);
	
	// Calculate Order Pickup Count by orderID
	@Query(value = "SELECT SUM(pickup_count) FROM sale_order_items WHERE order_id = ?", nativeQuery = true)
	public Integer calculatePickupCount(int orderId);
	
	// Calculate Total of Non-Taxable Items by orderID
	@Query(value = "SELECT SUM(subtotal) FROM sale_order_items WHERE order_id = ? AND tax_status = false", nativeQuery = true)
	public Double calculateNonTaxableItems(int orderId);
	
	// Get One Item from Order Items
	@Query(value = "SELECT * FROM sale_order_items WHERE item_seq = ?", nativeQuery = true)
	public SaleOrderItems getOrderItem(int itemSEQ);
	
	// UPDATE soldQty, unitPrice, salePrice, subtotal, itemWeight & itemsLeft
	@Modifying
	@Query(value = "UPDATE sale_order_items SET sold_qty = ?, unit_price = ?, sale_price = ?, subtotal = ?, item_weight = ?, items_left = ?, tax_status = ? WHERE item_seq = ?", nativeQuery = true)
	public void updateOrderItem(int soldQty, double unitPrice, double salePrice, double subtotal, double itemWeight, int itemsLeft, boolean taxStatus, int itemSEQ);

	// Remove Order Item
	@Modifying
	@Query(value = "DELETE FROM sale_order_items WHERE item_seq = ?", nativeQuery = true)
	public void removeOrderItem(int itemSEQ);

	// Remove All Order Items
	@Modifying
	@Query(value = "DELETE FROM sale_order_items WHERE order_id = ?", nativeQuery = true)
	public void removeAllOrderItems(int orderId);

}
