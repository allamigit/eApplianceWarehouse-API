package e_appliance_warehouse.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "item_zone")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ItemZone extends CommonColumns {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "zoneseq", sequenceName = "item_zone_seq", initialValue = 501, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zoneseq")
	@Column(name = "zone_id")
	private Long zoneId;

	@Column(name = "zone_name", nullable = false)
	private String zoneName;
	
	@Column(name = "zone_aisle")
	private String zoneAisle;
	
	@Column(name = "zone_rack")
	private String zoneRack;
	
}
