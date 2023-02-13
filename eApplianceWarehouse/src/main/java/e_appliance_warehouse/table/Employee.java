package e_appliance_warehouse.table;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column(name = "first_name", nullable = false)
	private String empFirstName;

	@Column(name = "last_name", nullable = false)
	private String empLastName;

	@Column(name= "employee_address")
	private String employeeAddress;
	
	@Column(name= "employee_city")
	private String employeeCity;
	
	@Column(name= "employee_state")
	private String employeeState;
	
	@Column(name= "employee_zip")
	private String employeeZip;

	@Column(name = "employee_phone", nullable = false, unique = true)
	private String employeePhone;

	@Column(name = "employee_email", nullable = false, unique = true)
	private String employeeEmail;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "job_title_id", nullable = false)
	private JobTitle jobTitle;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "employment_type_id", nullable = false)
	private EmploymentType employmentType;
	
	@Column(name = "report_to")
	private Long reportTo;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "annual_compensation", precision = 10, scale = 2, nullable = false)
	private BigDecimal annualCompensation;
	
	@Column(name = "hourly_rate", precision = 10, scale = 2)
	private BigDecimal hourlyRate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "payroll_type_id", nullable = false)
	private PayrollType payrollType;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "group_id")
	private PermissionGroup permissionGroup;

	@Column(name = "account_status", nullable = false)
	private Boolean accountStatus;

}
