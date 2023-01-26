package e_appliance_warehouse.table;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
	@SequenceGenerator(name = "userseq", sequenceName = "user_seq", initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userseq")
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "phone", nullable = false, unique = true)
	private String phone;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "passwd", nullable = false)
	private String password;
	
	@Column(name = "pass_reset")
	private Boolean passwordReset;
	
	@Column(name = "job_title", nullable = false)
	private String jobTitle;
	
	@Column(name = "emp_type")
	private String employmentType;
	
	@Column(name = "report_to")
	private Long reportTo;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "group_id")
	private PermissionGroups permissionGroup;

	@Column(name = "account_status", nullable = false)
	private Boolean accountStatus;

	@Column(name = "login_timestamp")
	private Timestamp loginTimestamp;
	
	@Column(name = "user_comment")
	private String userComment;
	
}
