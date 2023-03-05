package e_appliance_warehouse.model;

import java.util.List;

import e_appliance_warehouse.table.PermissionGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionGroupResponse {
	
	private QueryStatus queryStatus;
	
	private List<PermissionGroup> queryResult;

}
