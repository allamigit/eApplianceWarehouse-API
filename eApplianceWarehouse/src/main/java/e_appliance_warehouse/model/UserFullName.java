package e_appliance_warehouse.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFullName {
	
	private Long userId;
	
	private String fullName;

}
