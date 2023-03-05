package e_appliance_warehouse.table.data;

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
@Table(name = "item_brand")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ItemBrand extends CommonColumns {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "brandseq", sequenceName = "item_brand_seq", initialValue = 401, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brandseq")
	@Column(name = "brand_id")
	private Long brandId;

	@Column(name = "brand_name", nullable = false, unique = true)
	private String brandName;
	
}
