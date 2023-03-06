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
@Table(name = "payroll_type")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PayrollType extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "paytypeseq", sequenceName = "payroll_type_seq", initialValue = 261, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paytypeseq")
	@Column(name = "payroll_type_id")
	private Long payrollTypeId;
	
	@Column(name = "payroll_type_name", nullable = false, unique = true)
	private String payrollTypeName;

}
