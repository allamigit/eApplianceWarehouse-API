package e_appliance_warehouse.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "employee")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends CommonColumns {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "employeeseq", sequenceName = "employee_seq", initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeseq")
	@Column(name = "employee_id")
	private Long employeeId;
	
	@Column(name = "first_name")
	private String empFirstName;

	@Column(name = "last_name")
	private String empLastName;

	@Column(name= "employee_address")
	private String employeeAddress;
	
	@Column(name= "employee_city")
	private String employeeCity;
	
	@Column(name= "employee_state")
	private String employeeState;
	
	@Column(name= "employee_zip")
	private String employeeZip;

	@Column(name = "employee_phone")
	private String employeePhone;

	@Column(name = "employee_email")
	private String employeeEmail;

	@Column(name = "job_title_id")
	private Long jobTitleId;
	
	@Column(name = "employment_type_id")
	private Long employmentTypeId;
	
	@Column(name = "report_to")
	private Long reportTo;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "annual_compensation")
	private BigDecimal annualCompensation;
	
	@Column(name = "hourly_rate")
	private BigDecimal hourlyRate;
	
	@Column(name = "payroll_type_id")
	private Long payrollTypeId;
	
	@Column(name = "group_id")
	private Long groupId;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "account_status")
	private Boolean accountStatus;

}
