package e_appliance_warehouse.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RequestStatus {

	private Integer statusCode;
	
	private HttpStatus statusName;
	
	private String statusDescription;

}
