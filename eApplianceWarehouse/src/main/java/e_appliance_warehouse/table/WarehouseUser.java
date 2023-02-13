package e_appliance_warehouse.table;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Column(name = "user_full_name")
	private String userFullName;

	@Column(name = "passwd", nullable = false)
	private String password;
	
	@Column(name = "passwd_reset")
	private Boolean passwordReset;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "job_title_id")
	private JobTitle jobTitle;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "group_id")
	private PermissionGroup permissionGroup;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "zone_id")
	private ItemZone itemZone;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "aisle_id")
	private ItemAisle itemAisle;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "rack_id")
	private ItemRack itemRack;

	@Column(name = "account_status")
	private Boolean accountStatus;

	@Column(name = "login_timestamp")
	private Timestamp loginTimestamp;
	
	@Column(name = "user_comment")
	private String userComment;
	
}
