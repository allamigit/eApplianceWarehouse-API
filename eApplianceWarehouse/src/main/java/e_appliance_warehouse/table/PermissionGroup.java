package e_appliance_warehouse.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import e_appliance_warehouse.model.CommonColumns;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "permission_group")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PermissionGroup extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Long groupId;
	
	@Column(name = "group_name")
	private String groupName;

	// SETTINGS
	@Column(name = "settings")
	private Boolean settings;
	
	@Column(name = "users")
	private Boolean users;
	
	@Column(name = "users_readonly")
	private Boolean usersReadOnly;
	
	@Column(name = "permission_groups")
	private Boolean permissionGroups;
	
	@Column(name = "permission_groups_readonly")
	private Boolean permissionGroupsReadOnly;
	
	@Column(name = "general_config")
	private Boolean generalConfig;
	
	@Column(name = "general_config_readonly")
	private Boolean generalConfigReadOnly;
	
	@Column(name = "employees")
	private Boolean employees;
	
	@Column(name = "employees_readonly")
	private Boolean employeesReadOnly;
	
	@Column(name = "customers")
	private Boolean customers;
	
	@Column(name = "customers_readonly")
	private Boolean customersReadOnly;
	
	@Column(name = "payments")
	private Boolean payments;
	
	@Column(name = "payments_readonly")
	private Boolean paymentsReadOnly;
	
	@Column(name = "reports")
	private Boolean reports;
	
	@Column(name = "reports_public")
	private Boolean reportsPublic;
	
	// STOCK
	@Column(name = "stock")
	private Boolean stock;
	
	@Column(name = "new_stock")
	private Boolean newStock;
	
	@Column(name = "cost_price")
	private Boolean costPrice;
	
	@Column(name = "cost_price_readonly")
	private Boolean costPriceReadOnly;
	
	@Column(name = "stock_delete")
	private Boolean stockDelete;
	
	@Column(name = "stock_update")
	private Boolean stockUpdate;
	
	@Column(name = "stock_info")
	private Boolean stockInfo;
	
	// SALES ORDER
	@Column(name = "sales_order")
	private Boolean salesOrder;
	
	@Column(name = "all_orders")
	private Boolean allOrders;
	
	@Column(name = "order_cost")
	private Boolean orderCost;
	
	@Column(name = "order_change_price")
	private Boolean orderChangePrice;
	
	@Column(name = "order_delete")
	private Boolean orderDelete;
	
	@Column(name = "order_update")
	private Boolean orderUpdate;
	
	@Column(name = "new_order")
	private Boolean newOrder;

	@Column(name = "order_current_user")
	private Boolean orderCurrentUser;

	@Column(name = "order_other_user_readonly")
	private Boolean orderOtherUserReadOnly;

	@Column(name = "order_other_user_open")
	private Boolean orderOtherUserOpen;

	@Column(name = "order_other_user_find")
	private Boolean orderOtherUserFind;

	@Column(name = "order_info")
	private Boolean orderInfo;
	
	@Column(name = "order_invoice")
	private Boolean orderInvoice;
	
	@Column(name = "order_payment")
	private Boolean orderPayment;
	
	@Column(name = "order_pickup")
	private Boolean orderPickup;
	
	@Column(name = "order_pickup_readonly")
	private Boolean orderPickupReadOnly;
	
	@Column(name = "order_approval")
	private Boolean orderApproval;

	@Column(name = "order_refund")
	private Boolean orderRefund;

	@Column(name = "order_restocking_fee")
	private Boolean orderRestockingFee;

}
