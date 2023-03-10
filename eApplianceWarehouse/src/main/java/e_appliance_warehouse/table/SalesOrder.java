package e_appliance_warehouse.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import e_appliance_warehouse.model.CommonColumns;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sales_order")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrder extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_id")
	private Long orderId;
	
	@Column(name = "order_date")
	@ColumnDefault(value = "current_timestamp")
	private LocalDate orderDate;
	
	@Column(name = "order_po")
	private String orderPO;
	
	@Column(name = "order_approval_status")
	private Boolean orderApprovalStatus;

	@Column(name = "approver_id")
	private Long approverId;
	
	@Column(name = "project_id")
	private Long projectId;
	
	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "gross_total_amount")
	private BigDecimal grossTotalAmount;
	
	@Column(name = "order_cost_amount")
	private BigDecimal orderCostAmount;

	@Column(name = "discount_percent")
	private Double discountPercent;

	@Column(name = "discount_amount")
	private BigDecimal discountAmount;

	@Column(name = "discount_total_amount")
	private BigDecimal discountTotalAmount;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@Column(name = "tax_status")
	private Boolean taxStatus;

	@Column(name = "sales_tax_percent")
	private Double salesTaxPercent;

	@Column(name = "sales_tax_amount")
	private BigDecimal salesTaxAmount;

	@Column(name = "admin_fee_percent")
	private Double adminFeePercent;

	@Column(name = "admin_fee_amount")
	private BigDecimal adminFeeAmount;

	@Column(name = "order_total_amount")
	private BigDecimal orderTotalAmount;

	@Column(name = "amount_paid")
	private BigDecimal amountPaid;

	@Column(name = "amount_due")
	private BigDecimal amountDue;

	@Column(name = "items_weight")
	private Double itemsWeight;
	
	@Column(name = "items_count")
	private Integer itemsCount;
	
	@Column(name = "pickup_count")
	private Integer pickupCount;
	
	@Column(name = "items_left")
	private Integer itemsLeft;
	
	@Column(name = "billing_status")
	private Boolean billingStatus;
	
	@Column(name = "billing_date")
	private LocalDate billingDate;
	
	@Column(name = "due_date")
	private LocalDate dueDate;
	
	@Column(name = "order_comment")
	private String orderComment;
	
	@Column(name = "order_internal_comment")
	private String orderInternalComment;
	
	@Column(name = "invoice_comment")
	private String invoiceComment;
	
}
