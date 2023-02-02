package e_appliance_warehouse.table;

import java.math.BigDecimal;
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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "stock")
@Data
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
	
	@Column(name = "item_name", nullable = false, unique = true)
	private String itemName;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "color_id")
	private Color itemColor;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "brand_id")
	private Brand itemBrand;
	
	@Column(name = "item_photo")
	private Byte[] itemPhoto;
	
	@Column(name = "item_size")
	private Double itemSize;
	
	@Column(name = "size_unit")
	private String sizeUnit;
	
	@Column(name = "item_qty", nullable = false)
	private Integer itemQTY;
	
	@Column(name = "item_weight")
	private Double itemWeight;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "zone_id")
	private Zone itemZone;
	
	@Column(name = "cost_price", precision = 10, scale = 2, nullable = false)
	private BigDecimal costPrice;
	
	@Column(name = "selling_price", precision = 10, scale = 2, nullable = false)
	private BigDecimal sellingPrice;
	
	@Column(name = "new_selling_price", precision = 10, scale = 2)
	private BigDecimal newSellingPrice;
	
	@Column(name = "new_price_eff_date")
	private LocalDate newPriceEffectiveDate;
	
	@Column(name = "sale_price", precision = 10, scale = 2)
	private BigDecimal salePrice;
	
	@Column(name = "item_defect_status")
	private Boolean itemDefectStatus;
	
	@Column(name = "item_list_status", nullable = false)
	private Boolean itemListStatus;
	
	@Column(name = "tax_status")
	private Boolean taxStatus;
	
	@Column(name = "item_comment")
	private String itemComment;
	
}
