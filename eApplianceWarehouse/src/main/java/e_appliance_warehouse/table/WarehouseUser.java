package e_appliance_warehouse.table;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import e_appliance_warehouse.model.CommonColumns;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "warehouse_user")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseUser extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "passwd")
	private String password;
	
	@Column(name = "reset_passwd")
	private Boolean resetPassword;
	
	@Column(name = "zone_id")
	private Long zoneId;

	@Column(name = "aisle_id")
	private Long aisleId;

	@Column(name = "rack_id")
	private Long rackId;

	@Column(name = "last_login_timestamp")
	private Timestamp lastLoginTimestamp;
	
	@Column(name = "user_comment")
	private String userComment;
	
}
