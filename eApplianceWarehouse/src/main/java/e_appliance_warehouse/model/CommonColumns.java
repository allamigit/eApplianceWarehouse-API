package e_appliance_warehouse.model;

import java.io.Serializable;
import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class CommonColumns implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "created_user_id", nullable = false)
	private Integer createdUserId;
	
	@Column(name = "created_timestamp", nullable = false)
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	private Timestamp createdTimestamp;
	
	@Column(name = "updated_user_id", nullable = false)
	private Integer updatedUserId;
	
	@Column(name = "updated_timestamp", nullable = false)
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	private Timestamp updatedTimestamp;
	
	@Column(name = "update_comment")
	private String updateComment;

}
