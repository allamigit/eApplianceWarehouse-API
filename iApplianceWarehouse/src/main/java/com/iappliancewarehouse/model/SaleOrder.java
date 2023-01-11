package com.iappliancewarehouse.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sale_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrder {
	
	@Id
	@SequenceGenerator(name = "orderseq", sequenceName = "order_seq", initialValue = 6101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderseq")
	@Column(name = "order_id")
	private Integer orderId;
	
	@Column(name = "order_date", nullable = false)
	private LocalDate orderDate;
	
	@Column(name = "order_po")  //*
	private String orderPO;
	
	@Column(name = "customer_name", nullable = false)
	private String customerName;
	
	@Column(name = "order_comment")
	private String orderComment;
	
	@Column(name = "invoice_comment")
	private String invoiceComment;
	
	@Column(name = "gross_total")
	private Double grossTotal;
	
	@Column(name = "order_cost")
	private Double orderCost;

	@Column(name = "discount_percent")  //***
	private Double discountPercent;

	@Column(name = "discount_amount")
	private Double discountAmount;

	@Column(name = "discount_total")
	private Double discountTotal;

	@Column(name = "order_total")
	private Double orderTotal;

	@Column(name = "sale_tax")
	private Double saleTax;

	@Column(name = "state_tax")
	private Double stateTax;

	@Column(name = "total_amount")
	private Double totalAmount;

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
	
	@Column(name = "billing_status")
	private Boolean billingStatus;
	
	@Column(name = "billing_date")  //***
	private LocalDate billingDate;
	
	/*@Column(name = "created_user")
	private String createdUser;*/
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
}
