package e_appliance_warehouse.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

	private Long userId;
	
	private String fullName;

	private String email;

	private String jobTitle;

	private Timestamp loginTimestamp;

	private String userComment;

}
