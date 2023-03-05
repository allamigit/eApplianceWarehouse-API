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
@Table(name = "item_color")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ItemColor extends CommonColumns {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "colorseq", sequenceName = "item_color_seq", initialValue = 301, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "colorseq")
	@Column(name = "color_id")
	private Long colorId;

	@Column(name = "color_name", nullable = false, unique = true)
	private String colorName;
	
}
