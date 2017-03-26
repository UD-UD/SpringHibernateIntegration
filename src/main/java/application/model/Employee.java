package application.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by ud on 19/3/17.
 */

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;


    //Type is used provide mapping between database date type and Joda-Time
    @Column(name = "JOINING_DATE", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate joiningDate;

    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;

    @Column(name = "SSN", unique=true, nullable = false)
    private String ssn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", joiningDate=" + joiningDate +
                ", salary=" + salary +
                ", ssn='" + ssn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getId() != employee.getId()) return false;
        if (getName() != null ? !getName().equals(employee.getName()) : employee.getName() != null) return false;
        if (getJoiningDate() != null ? !getJoiningDate().equals(employee.getJoiningDate()) : employee.getJoiningDate() != null)
            return false;
        if (getSalary() != null ? !getSalary().equals(employee.getSalary()) : employee.getSalary() != null)
            return false;
        return getSsn() != null ? getSsn().equals(employee.getSsn()) : employee.getSsn() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getJoiningDate() != null ? getJoiningDate().hashCode() : 0);
        result = 31 * result + (getSalary() != null ? getSalary().hashCode() : 0);
        result = 31 * result + (getSsn() != null ? getSsn().hashCode() : 0);
        return result;
    }
}
