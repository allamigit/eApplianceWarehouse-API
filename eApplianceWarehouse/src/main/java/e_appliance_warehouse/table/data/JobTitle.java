package e_appliance_warehouse.table.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import e_appliance_warehouse.model.CommonColumns;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "job_title")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class JobTitle extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "jobtitleseq", sequenceName = "job_title_seq", initialValue = 221, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobtitleseq")
	@Column(name = "job_title_id")
	private Long jobTitleId;
	
	@Column(name = "job_title_name", nullable = false, unique = true)
	private String jobTitleName;
	
}
