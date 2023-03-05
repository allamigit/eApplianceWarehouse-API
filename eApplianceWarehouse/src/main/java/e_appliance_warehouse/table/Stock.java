package e_appliance_warehouse.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import e_appliance_warehouse.model.CommonColumns;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "stock")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Stock extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "stockseq", sequenceName = "stock_seq", initialValue = 2101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stockseq")
	@Column(name = "item_code")
	private Long itemCode;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "color_id")
	private Long colorId;
	
	@Column(name = "brand_id")
	private Long brandId;
	
	@Column(name = "item_photo")
	private Byte[] itemPhoto;
	
	@Column(name = "item_size")
	private Double itemSize;
	
	@Column(name = "size_unit")
	private String sizeUnit;
	
	@Column(name = "item_qty")
	private Integer itemQTY;
	
	@Column(name = "item_weight")
	private Double itemWeight;
	
	@Column(name = "zone_id")
	private Long zoneId;
	
	@Column(name = "aisle_id")
	private Long aisleId;

	@Column(name = "rack_id")
	private Long rackId;

	@Column(name = "cost_price")
	private BigDecimal costPrice;
	
	@Column(name = "selling_price")
	private BigDecimal sellingPrice;
	
	@Column(name = "new_selling_price")
	private BigDecimal newSellingPrice;
	
	@Column(name = "new_price_eff_date")
	private LocalDate newPriceEffectiveDate;
	
	@Column(name = "sale_price")
	private BigDecimal salePrice;
	
	@Column(name = "item_list_status")
	private Boolean itemListStatus;
	
	@Column(name = "tax_status")
	private Boolean taxStatus;
	
	@Column(name = "item_comment")
	private String itemComment;
	
}
