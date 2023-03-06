package e_appliance_warehouse;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class eApplianceWarehouse implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(eApplianceWarehouse.class, args);
		
        System.out.println("\n ... eApplianceWarehouse API started ...\n");
	}

	@Override
	public void run(String... args) {
		
	}

}
