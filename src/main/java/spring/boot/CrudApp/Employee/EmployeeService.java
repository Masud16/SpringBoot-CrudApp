package spring.boot.CrudApp.Employee;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
        EmployeeEntity employeeEntity = employeeRepository.findCustom(employeeId);
//        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

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
//        EmployeeEntity employee = employeeRepository.findById(employeeId).get();
        EmployeeEntity employee = employeeRepository.findCustom(employeeId);

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public List<EmployeeEntity> getEmployees(List<String> names, List<Integer> employeeIds) {
        if (names != null && employeeIds != null) {
            return employeeRepository.findAllByEmployeeIdInAndNameIn(names, employeeIds);
        } else if (names != null) {
            return employeeRepository.findByNameIn(names);
        } else if (employeeIds != null) {
            return employeeRepository.findByEmployeeIdIn(employeeIds);
        } else {
            return employeeRepository.findAll();
        }
    }
    public List<Object[]> getColumns() {
        return employeeRepository.getColumns();
    }

    public List<Object[]> getEmployeeColumns(List<Integer> employeeId) {
        return employeeRepository.getEmployeeColumns(employeeId);
    }


    public List<Map<String,Object>> getEmployeeColumns2(List<Integer> employeeId) {
        return employeeRepository.getEmployeeColumns2(employeeId);
    }


    public List<EmployeeEntity> getEmployeeColumns3(List<Integer> employeeId) {
        return employeeRepository.getEmployeeColumns3(employeeId);
    }


    public int getSummOfId() {
        return employeeRepository.getSummOfId();
    }
}
