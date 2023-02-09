package e_appliance_warehouse.table;

import java.math.BigDecimal;

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
	private Long itemSEQ;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "order_id")
	private SalesOrder salesOrder;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "item_code")	
	private Stock soldItem;
	
	@Column(name = "sold_qty", nullable = false)
	private Integer soldQTY;
	
	@Column(name = "cost_price", precision = 10, scale = 2, nullable = false)
	private BigDecimal costPrice;
	
	@Column(name = "selling_price", precision = 10, scale = 2, nullable = false)
	private BigDecimal sellingPrice;

	@Column(name = "sale_price", precision = 10, scale = 2)
	private BigDecimal salePrice;
	
	@Column(name = "subtotal", precision = 10, scale = 2)
	private BigDecimal subtotal;
	
	@Column(name = "tax_status")
	private Boolean taxStatus;

	@Column(name = "item_weight")
	private Double itemWeight;

	@Column(name = "pickup_count")
	private Integer pickupCount;
	
	@Column(name = "items_left")
	private Integer itemsLeft;
	
}
