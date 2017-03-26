package org.my.test.repository;

import org.springframework.data.repository.CrudRepository;

import org.my.test.domain.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.Query;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("select e from Employee e where not exists(select u.id from Users u where u.employee = e.id)")
    List<Employee> findByUserNotExists();

    @Query("select distinct e.city as city, s.name as desc from Employee e, Skills s where s.employee = e.id \n" +
           "order by e.city")
    List< Object[] >  findCitiesAndSkills();
}
