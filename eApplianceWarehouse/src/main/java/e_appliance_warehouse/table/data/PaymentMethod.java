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
@Table(name = "payment_method")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod extends CommonColumns {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "pmtmethodseq", sequenceName = "payment_method_seq", initialValue = 601, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pmtmethodseq")
	@Column(name = "payment_method_id")
	private Long paymentMethodId;
	
	@Column(name = "payment_method_name", nullable = false, unique = true)
	private String paymentMethodName;
	
}
