package e_appliance_warehouse.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import e_appliance_warehouse.table.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// Get List of All Employees
	@Query(value = "SELECT e FROM Employee e ORDER BY empFirstName ASC")
	public List<Employee> getAllEmployees();
	
	// Get List of Inactive Employees
	@Query(value = "SELECT e FROM Employee e WHERE accountStatus = false ORDER BY empFirstName ASC")
	public List<Employee> getInactiveEmployees();
	
	// Get List of Active Employees
	@Query(value = "SELECT e FROM Employee e WHERE accountStatus = true ORDER BY empFirstName ASC")
	public List<Employee> getActiveEmployees();

	// Get Employee by employeeID
	@Query(value = "SELECT e FROM Employee e WHERE employeeId = ?1")
	public Employee getEmployeeById(Long employeeId);

	// Get Employee by empFirstName (or if contains part of the name)
	@Query(value = "SELECT e FROM Employee e WHERE LOWER(empFirstName) LIKE %?1% ORDER BY empFirstName ASC")
	public List<Employee> getEmployeeByFirstName(String empFirstName);

	// Get Employee by empFirstName and empLastName
	@Query(value = "SELECT e FROM Employee e WHERE empFirstName = ?1 AND empLastName = ?2")
	public Employee getEmployeeByFirstAndLastName(String empFirstName, String empLastName);

	// Delete Employee by employeeID
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM Employee e WHERE employeeId = ?1")
	public void deleteEmployee(Long employeeId);

}
