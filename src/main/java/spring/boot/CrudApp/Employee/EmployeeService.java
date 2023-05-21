package spring.boot.CrudApp.Employee;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    final
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeEntity> getAllEmployee(){
        List<EmployeeEntity> allEmployeelist = employeeRepository.findAll();
        return allEmployeelist;
    }
    public EmployeeEntity getEmployeebyId( Integer employeeId)

    {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        return employeeEntity;
    }

    public EmployeeEntity createEmployee(EmployeeEntity employee) {

        EmployeeEntity savedEmployee = employeeRepository.save(employee);

        return savedEmployee;
    }

    public ResponseEntity<EmployeeEntity> updateEmployee(Integer employeeId,
                                                         EmployeeEntity employeeDetails) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).get();

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setName(employeeDetails.getName());
        employee.setLocation(employeeDetails.getLocation());
        final EmployeeEntity updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    public Map<String, Boolean> deleteEmployee(Integer employeeId)
    {
        EmployeeEntity employee = employeeRepository.findById(employeeId).get();

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
