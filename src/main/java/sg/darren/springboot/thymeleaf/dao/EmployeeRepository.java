package sg.darren.springboot.thymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.darren.springboot.thymeleaf.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public List<Employee> findAllByOrderByLastNameAsc();
	
}
