package e_appliance_warehouse.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales_order_items")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderItems extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_seq")
	private Integer itemSEQ;
	
	@Column(name = "order_id")
	private Integer orderId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "item_code")	
	private Stock soldItem;
	
	@Column(name = "sold_qty", nullable = false)
	private Integer soldQTY;
	
	@Column(name = "cost_price", nullable = false)
	private Double costPrice;
	
	@Column(name = "selling_price", nullable = false)
	private Double sellingPrice;

	@Column(name = "sale_price")
	private Double salePrice;
	
	@Column(name = "subtotal")
	private Double subtotal;
	
	@Column(name = "tax_status")
	private Boolean taxStatus;

	@Column(name = "item_weight")
	private Double itemWeight;

	@Column(name = "pickup_count")
	private Integer pickupCount;
	
	@Column(name = "items_left")
	private Integer itemsLeft;
	
}
