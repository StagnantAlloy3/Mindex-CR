package com.mindex.challenge.data;

import java.util.Date;

/**
 * Compensation data object
 */
public class Compensation {
    private String employeeId;

    private double salary;
    private Date effectiveDate;

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
     * Gets Salary
     * @return salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Sets Salary
     * @param salary salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Gets EffectiveDate
     * @return effectiveDate
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets EffectiveDate
     * @param effectiveDate effectiveDate
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}
