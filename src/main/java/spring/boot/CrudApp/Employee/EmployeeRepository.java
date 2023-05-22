package spring.boot.CrudApp.Employee;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Integer> {

    List<EmployeeEntity> findByNameIn(@Param("names") List<String> names);

    List<EmployeeEntity> findByEmployeeIdIn(@Param("employeeIds") List<Integer> employeeIds);

    List<EmployeeEntity> findAllByEmployeeIdInAndNameIn(@Param("names") List<String> names, @Param("employeeIds") List<Integer> employeeIds);

    @Query("select emp from EmployeeEntity emp where emp.employeeId=?1")
    EmployeeEntity findCustom(Integer id);

    @Query("select emp from EmployeeEntity emp where emp.employeeId=:id")
    List<EmployeeEntity> findCustomWithParameter(@Param("id") Integer id);

    @Query("SELECT emp.name,emp.location FROM EmployeeEntity emp where emp.employeeId=1")
    List<Object[]> getColumns();

    @Query("SELECT emp.name,emp.location FROM EmployeeEntity emp where emp.employeeId in :employeeId")
    List<Object[]> getEmployeeColumns(@Param("employeeId") List<Integer> employeeId);


    @Query("SELECT emp.name as name ,emp.location as location FROM EmployeeEntity emp where emp.employeeId in :employeeId")
    List<Map<String,Object>> getEmployeeColumns2(@Param("employeeId") List<Integer> employeeId);

    @Query("SELECT emp FROM EmployeeEntity emp where emp.employeeId in :employeeId")
    List<EmployeeEntity> getEmployeeColumns3(@Param("employeeId") List<Integer> employeeId);

    @Query("SELECT sum(emp.employeeId) FROM EmployeeEntity emp")
    int getSummOfId();
}
