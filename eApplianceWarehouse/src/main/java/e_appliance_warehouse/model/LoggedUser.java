package e_appliance_warehouse.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUser {

	private String userId;
	
	private String userFullName;

	private String jobTitle;

	private Timestamp loginTimestamp;

	private String userComment;

}
