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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "employment_type")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentType extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "emptypeseq", sequenceName = "employment_type_seq", initialValue = 241, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emptypeseq")
	@Column(name = "employment_type_id")
	private Long employmentTypeId;
	
	@Column(name = "employment_type_name", nullable = false, unique = true)
	private String employmentTypeName;

}
