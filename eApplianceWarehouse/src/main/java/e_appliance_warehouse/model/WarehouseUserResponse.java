package e_appliance_warehouse.model;

import java.util.List;

import e_appliance_warehouse.table.WarehouseUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseUserResponse {

	private QueryStatus queryStatus;
	
	private List<WarehouseUser> queryResult;

}
