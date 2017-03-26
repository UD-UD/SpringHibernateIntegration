package application.dao;

import application.model.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ud on 19/3/17.
 * Acts as a gateway for database transaction
 */

@Repository("employeeDao")
public class EmployeeDaoImpl  extends AbstractDao implements EmployeeDao{

    public void saveEmployee(Employee employee) {
        persist(employee);
    }

    @SuppressWarnings("unchecked")
    public List<Employee> findAllEmployees() {
        Criteria criteria = getSession().createCriteria(Employee.class);
        return (List<Employee>) criteria.list();
    }

    public void deleteEmployeeBySsn(String ssn) {
        Query query = getSession().createQuery("delete from Employee where ssn = :ssn");
        query.setString("ssn", ssn);
        query.executeUpdate();
    }

    public Employee findBySsn(String ssn){
        Criteria criteria = getSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("ssn",ssn));
        return (Employee) criteria.uniqueResult();
    }

    public void updateEmployee(Employee employee){
        getSession().update(employee);
    }

}
