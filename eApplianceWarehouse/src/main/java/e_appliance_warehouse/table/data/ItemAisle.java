package e_appliance_warehouse.table.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import e_appliance_warehouse.model.CommonColumns;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "item_aisle")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ItemAisle extends CommonColumns {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "aisleseq", sequenceName = "item_aisle_seq", initialValue = 531, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aisleseq")
	@Column(name = "aisle_id")
	private Long aisleId;

	@Column(name = "aisle_name", nullable = false, unique = true)
	private String aisleName;
	
}
