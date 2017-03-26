package application.dao;

import application.model.Employee;

import java.util.List;

/**
 * Created by ud on 19/3/17.
 */
public interface EmployeeDao {
    void saveEmployee(Employee employee);

    List<Employee> findAllEmployees();

    void deleteEmployeeBySsn(String ssn);

    Employee findBySsn(String ssn);

    void updateEmployee(Employee employee);
}
