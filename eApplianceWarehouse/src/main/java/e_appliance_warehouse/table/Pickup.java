package e_appliance_warehouse.table;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import e_appliance_warehouse.model.CommonColumns;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "pickup")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Pickup extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pickup_id")
	private String pickupId;
	
	@Column(name = "pickup_date", nullable = false)
	@ColumnDefault(value = "current_timestamp")
	private LocalDate pickupDate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "order_id")
	private SalesOrder salesOrder;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "item_code")	
	private Stock soldItem;
	
	@Column(name = "sold_qty")
	private Integer soldQTY;
	
	@Column(name = "item_weight")
	private Double itemWeight;

	@Column(name = "pickup_count")
	private Integer pickupCount;
	
	@Column(name = "items_left")
	private Integer itemsLeft;
	
	@Column(name = "pickup_comment")
	private String pickupComment;
	
	@Column(name = "pickup_internal_comment")
	private String pickupInternalComment;
	
}
