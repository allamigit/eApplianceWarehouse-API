package e_appliance_warehouse.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "memo", indexes = @Index(columnList = "order_id"))
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Memo extends CommonColumns {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "memo_id")
	private String memoId;
	
	@Column(name = "memo_date")
	private LocalDate memoDate;
	
	@Column(name = "memo_amount", precision = 10, scale = 2)
	private BigDecimal memoAmount;
	
	@Column(name = "order_id")
	private Long orderId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "memo_comment")
	private String memoComment;

}
