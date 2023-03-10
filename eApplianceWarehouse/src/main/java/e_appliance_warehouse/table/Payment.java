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
import javax.persistence.Table;

import e_appliance_warehouse.model.CommonColumns;
import e_appliance_warehouse.table.data.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "payment")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends CommonColumns {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long paymentId;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "payment_method_id")
	private PaymentMethod paymentMethod;
	
	@Column(name = "payment_details")
	private String paymentDetails;
	
	@Column(name = "payment_amount", precision = 10, scale = 2)
	private BigDecimal paymentAmount;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "order_id")
	private SalesOrder salesOrder;
	
	@Column(name = "payment_comment")
	private String paymentComment;
	
}
