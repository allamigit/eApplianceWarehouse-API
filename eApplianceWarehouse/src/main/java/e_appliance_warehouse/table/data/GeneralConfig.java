package e_appliance_warehouse.table.data;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import e_appliance_warehouse.model.CommonColumns;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "general_config")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GeneralConfig extends CommonColumns {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name= "company_code")
	private String companyCode;
	
	@Column(name= "company_name")
	private String companyName;
	
	@Column(name= "company_address")
	private String companyAddress;
	
	@Column(name= "company_city")
	private String companyCity;
	
	@Column(name= "company_state")
	private String companyState;
	
	@Column(name= "company_zip")
	private String companyZip;
	
	@Column(name= "company_phone")
	private String companyPhone;
	
	@Column(name= "company_email")
	private String companyEmail;
	
	@Column(name= "company_website")
	private String companyWebsite;
	
	@Column(name = "sales_tax_percent")
	private Double salesTaxPercent;

	@Column(name = "min_stock_item_qty")
	private Integer minStockItemQTY;

	@Column(name = "admin_fee_percent")
	private Double adminFeePercent;

	@Column(name = "admin_fee_amount")
	private BigDecimal adminFeeAmount;

	@Column(name = "restocking_fee_percent")
	private Double restockingFeePercent;

}
