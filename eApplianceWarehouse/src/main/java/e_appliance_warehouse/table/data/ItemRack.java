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
@Table(name = "item_rack")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ItemRack extends CommonColumns {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "rackseq", sequenceName = "item_rack_seq", initialValue = 561, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackseq")
	@Column(name = "rack_id")
	private Long rackId;

	@Column(name = "rack_name", nullable = false, unique = true)
	private String rackName;
	
}
