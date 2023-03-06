package e_appliance_warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import e_appliance_warehouse.table.data.JobTitle;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

	@Query(value = "SELECT j.jobTitleName FROM JobTitle j WHERE j.jobTitleId = ?1")
	public String getJobTitleName(Long jobTitleId);
	
}
