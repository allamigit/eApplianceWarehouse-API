package e_appliance_warehouse.table;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import e_appliance_warehouse.model.CommonColumns;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sales_order_items")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderItems extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_seq")
	private Long itemSEQ;
	
	@Column(name = "order_id")
	private Long orderId;
	
	@Column(name = "item_code")	
	private Long itemCode;
	
	@Column(name = "sold_qty")
	private Integer soldQTY;
	
	@Column(name = "cost_price")
	private BigDecimal costPrice;
	
	@Column(name = "selling_price")
	private BigDecimal sellingPrice;

	@Column(name = "sale_price")
	private BigDecimal salePrice;
	
	@Column(name = "subtotal")
	private BigDecimal subtotal;
	
	@Column(name = "tax_status")
	private Boolean taxStatus;

	@Column(name = "item_defect_status")
	private Boolean itemDefectStatus;
	
	@Column(name = "item_weight")
	private Double itemWeight;

	@Column(name = "pickup_count")
	private Integer pickupCount;
	
	@Column(name = "items_left")
	private Integer itemsLeft;
	
}
