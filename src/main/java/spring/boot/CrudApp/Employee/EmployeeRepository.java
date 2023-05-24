package spring.boot.CrudApp.Employee;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    List<EmployeeEntity> findByNameIn(List<String> names);

    List<EmployeeEntity> findByEmployeeIdIn(List<Integer> employeeIds);

    @Query("select emp from EmployeeEntity emp where emp.employeeId=?1")
    EmployeeEntity findCustom(Integer id);

    @Query("select emp from EmployeeEntity emp where emp.employeeId=:id")
    List<EmployeeEntity> findCustomWithParameter(@Param("id") Integer id);

    @Query("SELECT emp.name,emp.location FROM EmployeeEntity emp where emp.employeeId=1")
    List<Object[]> getColumns();

    @Query("SELECT emp.name,emp.location FROM EmployeeEntity emp where emp.employeeId in :empId")
    List<Object[]> getEmployeeColumns(@Param("empId") List<Integer> employeeId);


    @Query("SELECT emp.name as name ,emp.location as location FROM EmployeeEntity emp where emp.employeeId in :empId")
    List<Map<String, Object>> getEmployeeColumns2(@Param("empId") List<Integer> employeeId);

    @Query("SELECT emp FROM EmployeeEntity emp where emp.employeeId in :employeeId")
    List<EmployeeEntity> getEmployeeColumns3(@Param("employeeId") List<Integer> employeeId);

    @Query("SELECT sum(emp.employeeId) FROM EmployeeEntity emp")
    int getSummOfId();

    List<EmployeeEntity> findByEmployeeIdInOrNameIn(List<Integer> employeeIds, List<String> names);

    @Query("select emp from EmployeeEntity emp where emp.employeeId In :empId or emp.name In :nam")
    List<EmployeeEntity> customFindUsingEmpIdAndName(@Param("empId") List<Integer> employeeIds, @Param("nam") List<String> names);

}
