package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;

public interface EmployeeService {

    /**
     * Create a new Employee object
     * @param employee Employee object
     * @return Employee object
     */
    Employee create(Employee employee);

    /**
     * Read an Employee object
     * @param id Employee ID
     * @return Employee object
     */
    Employee read(String id);

    /**
     * Update an Employee object
     * @param employee Employee object
     * @return Employee object
     */
    Employee update(Employee employee);
}
