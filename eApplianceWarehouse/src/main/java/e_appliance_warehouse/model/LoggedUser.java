package e_appliance_warehouse.model;

import java.sql.Timestamp;

import e_appliance_warehouse.table.PermissionGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUser {

	private QueryStatus loginStatus;

	private String userId;
	
	private String userFullName;

	private String jobTitle;

	private Timestamp loginTimestamp;

	private Timestamp lastLoginTimestamp;

	private String userComment;
	
	private PermissionGroup permissionList;
	
}
