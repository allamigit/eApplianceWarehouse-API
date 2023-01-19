package e_appliance_warehouse.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales_order")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrder extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "orderseq", sequenceName = "order_seq", initialValue = 2101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderseq")
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "order_date", nullable = false)
	private LocalDate orderDate;
	
	@Column(name = "order_po")
	private String orderPO;
	
	@Column(name = "approval_needed")
	private Boolean approvalNeeded;
	
	@Column(name = "customer_name", nullable = false)
	private String customerName;
	
	@Column(name = "order_comment")
	private String orderComment;
	
	@Column(name = "invoice_comment")
	private String invoiceComment;
	
	@Column(name = "gross_total_amount")
	private Double grossTotalAmount;
	
	@Column(name = "order_cost_amount")
	private Double orderCostAmount;

	@Column(name = "discount_percent")
	private Double discountPercent;

	@Column(name = "discount_amount")
	private Double discountAmount;

	@Column(name = "discount_total_amount")
	private Double discountTotalAmount;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "tax_exempt_status")
	private Boolean taxExemptStatus;

	@Column(name = "sales_tax_amount")
	private Double salesTaxAmount;

	@Column(name = "order_total_amount")
	private Double orderTotalAmount;

	@Column(name = "amount_paid")
	private Double amountPaid;

	@Column(name = "amount_due")
	private Double amountDue;

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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "order_id")
	private SalesOrderItems orderItems;
	
}
