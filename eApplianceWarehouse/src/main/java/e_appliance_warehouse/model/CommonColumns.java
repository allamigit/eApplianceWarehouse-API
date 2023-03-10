package e_appliance_warehouse.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class CommonColumns implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "created_user_id", nullable = false)
	private String createdUserId;
	
	@Column(name = "created_timestamp", nullable = false)
	@ColumnDefault(value = "current_timestamp")
	private Timestamp createdTimestamp;
	
	@Column(name = "updated_user_id", nullable = false)
	private String updatedUserId;
	
	@Column(name = "updated_timestamp", nullable = false)
	@ColumnDefault(value = "current_timestamp")
	private Timestamp updatedTimestamp;
	
	@Column(name = "update_summary")
	private String updateSummary;

}
