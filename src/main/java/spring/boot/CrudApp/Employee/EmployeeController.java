package spring.boot.CrudApp.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<EmployeeEntity> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/get-employee/{id}")
    public EmployeeEntity getEmployeebyId(@PathVariable(value = "id") Integer employeeId) {
        return employeeService.getEmployeebyId(employeeId);
    }

    @PostMapping("/create-employees")
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity employee) {

        return employeeService.createEmployee(employee);
    }

    @PutMapping("/update-employees/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable(value = "id") Integer employeeId,
                                                         @RequestBody EmployeeEntity employeeDetails) {

        return employeeService.updateEmployee(employeeId, employeeDetails);
    }

    @DeleteMapping("/delete-employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/get-employee/selected-emp/{name}/{employeeId}")
    public List<EmployeeEntity> getEmployees(@PathVariable(value = "name") List<String> names,
                                             @PathVariable(value = "employeeId") List<Integer> employeeIds) {
        return employeeService.getEmployees(names, employeeIds);
    }

    @GetMapping("/get-employee/selected-column")
    public List<Object[]> getColumns() {
        return employeeService.getColumns();
    }

    @GetMapping("/get-employee/selected-emp-column")
    public List<Object[]> getEmployeeColumns(@RequestParam(value = "id", required = false) List<Integer> employeeId) {
        return employeeService.getEmployeeColumns(employeeId);
    }

    @GetMapping("/get-employee/selected-emp-column2") //using hashmap
    public List<Map<String, Object>> getEmployeeColumns2(@RequestParam(value = "id", required = false) List<Integer> employeeId) {
        return employeeService.getEmployeeColumns2(employeeId);
    }

    @GetMapping("/get-employee/selected-emp-column3")  //understanding DTO class
    public List<EmployeeDTO> getEmployeeColumns3(@RequestParam(value = "id", required = false) List<Integer> employeeId) {

        List<EmployeeEntity> results = employeeService.getEmployeeColumns3(employeeId);

        List<EmployeeDTO> finalResult = new ArrayList<>();

        for (EmployeeEntity x : results) {

            EmployeeDTO obj = new EmployeeDTO();

            obj.setName(x.getName());
            obj.setLocation(x.getLocation());
            finalResult.add(obj);
        }

        return finalResult;

    }

    @GetMapping("/get-employee/selected-emp-column4") //sum of all employeeIDs
    public ResponseEntity<?> getEmployeeColumns4(@RequestParam(value = "id", required = false) List<Integer> employeeId) {

        int summation = employeeService.getSummOfId();

        return new ResponseEntity<>(summation, HttpStatus.OK);
    }

}
