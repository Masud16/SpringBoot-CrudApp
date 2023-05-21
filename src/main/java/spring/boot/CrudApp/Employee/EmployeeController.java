package spring.boot.CrudApp.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

//    final
//    EmployeeRepository employeeRepository;
//
//    public EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
    final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get-all-employees")
    public List<EmployeeEntity> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/get-employee/{id}")
    public EmployeeEntity getEmployeebyId(@PathVariable(value = "id") Integer employeeId)

    {
       return employeeService.getEmployeebyId(employeeId);
    }

    @PostMapping("/create-employees")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {

        return employeeService.createEmployee(employee);
    }

    @PutMapping("/update-employees/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable(value = "id") Integer employeeId,
                                                         @RequestBody EmployeeEntity employeeDetails) {

        return employeeService.updateEmployee(employeeId,employeeDetails);
    }

    @DeleteMapping("/delete-employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer employeeId)
    {
      return employeeService.deleteEmployee(employeeId);
    }
    @GetMapping("/get-employee/selected-emp")
    public List<EmployeeEntity> getEmployees(@RequestParam(value = "name", required = false) List<String> names,
                               @RequestParam(value = "employeeId", required = false) List<Integer> employeeIds) {
        return employeeService.getEmployees(names,employeeIds);
    }

}
