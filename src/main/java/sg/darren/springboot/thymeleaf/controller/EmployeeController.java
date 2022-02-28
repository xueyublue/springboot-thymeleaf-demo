package sg.darren.springboot.thymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.darren.springboot.thymeleaf.entity.Employee;
import sg.darren.springboot.thymeleaf.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// in memory employee list - TESTING ONLY
//	private List<Employee> employees;
//	
//	@PostConstruct
//	private void loadData() {
//		employees = new ArrayList<Employee>();
//		employees.add(new Employee(1, "Danny", "Huang", "danny.huang@avl.com.sg"));
//		employees.add(new Employee(2, "Eddie", "Lee", "eddie.lee@avl.com.sg"));
//	}
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")
	public String listEmployees(Model model) {
		
		List<Employee> employees = employeeService.findAll();
		
		model.addAttribute("employees", employees);
			
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		model.addAttribute("employee", new Employee());
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {
		
		Employee employee = employeeService.findById(id);
		model.addAttribute("employee", employee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
		
		employeeService.deleteById(id);
		
		return "redirect:/employees/list";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee employee) {
	
		employeeService.save(employee);
		
		return "redirect:/employees/list";
	}
}