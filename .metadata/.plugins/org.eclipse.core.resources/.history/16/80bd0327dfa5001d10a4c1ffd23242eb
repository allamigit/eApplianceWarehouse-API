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

@Entity
@Table(name = "warehouse_user")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class User extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "user_full_name")
	private String userFulltName;

	@Column(name = "passwd", nullable = false)
	private String password;
	
	@Column(name = "passwd_reset")
	private Boolean passwordReset;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "job_title_id")
	private JobTitle jobTitle;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "group_id")
	private PermissionGroups permissionGroup;

	@Column(name = "account_status")
	private Boolean accountStatus;

	@Column(name = "login_timestamp")
	private Timestamp loginTimestamp;
	
	@Column(name = "user_comment")
	private String userComment;
	
}
