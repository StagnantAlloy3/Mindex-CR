package com.mindex.challenge.data;

/**
 * The ReportingStructure class is a subclass of the Employee class. It is used to store the number of reports for a given employee.
 */

public class ReportingStructure {

    private Employee employee;
    private int numberOfReports;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

}
