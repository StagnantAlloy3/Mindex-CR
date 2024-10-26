package com.mindex.challenge.data;

import java.util.List;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    public Employee() {
    }

    /**
     * Gets EmployeeId
     * @return employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets EmployeeId
     * @param employeeId employeeId
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets FirstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets FirstName
     * @param firstName firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets LastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets LastName
     * @param lastName lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets Position
     * @return position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets Position
     * @param position position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets Department
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets Department
     * @param department department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Gets DirectReports
     * @return directReports
     */
    public List<Employee> getDirectReports() {
        return directReports;
    }

    /**
     * Sets DirectReports
     * @param directReports directReports
     */
    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }
}
