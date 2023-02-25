package e_appliance_warehouse.table;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "customer")
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends CommonColumns {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "customerseq", sequenceName = "customer_seq", initialValue = 3101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerseq")
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name= "customer_address")
	private String customerAddress;
	
	@Column(name= "customer_city")
	private String customerCity;
	
	@Column(name= "customer_state")
	private String customerState;
	
	@Column(name= "customer_zip")
	private String customerZip;
	
	@Column(name= "customer_phone")
	private String customerPhone;
	
	@Column(name= "customer_email")
	private String customerEmail;
	
	@Column(name= "customer_website")
	private String customerWebsite;

	@Column(name= "payment_term_days")
	private Integer paymentTermDays;
	
	@Column(name = "tax_exempt_status")
	private Boolean taxExemptStatus;
	
	@Column(name = "tax_exempt_certificate")
	private String taxExemptCertificate;
	
	@Column(name = "tax_exempt_end_date")
	private LocalDate taxExemptEndDate;

	@Column(name = "cc_number")
	private String ccNumber;
	
	@Column(name = "customer_comment")
	private String customerComment;
	
	@Column(name = "account_status")
	private Boolean accountStatus;

}
