package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

@Service
public class ReportingStructureImpl implements ReportingStructureService {

    //Define the logger as in the Employee Structure
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureImpl.class);

    //Define the EmployeeRepository for use
    @Autowired
    private EmployeeRepository employeeRepository;


    //Define the read method as tasked in Task 1 to retrieve the number of direct reports for a given employee
    @Override
    public ReportingStructure read(String id) {

        //Get the employee by their ID
        Employee employee = employeeRepository.findByEmployeeId(id);

        //Init empty HashSet to track reports recursively
        HashSet<String> employeeIds = new HashSet<>();

        //Get the number of reports for the given employee
        HashSet<String> directreports = getNumberOfReports(id, employeeIds);

        //Create a new ReportingStructure object and set the number of reports
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setNumberOfReports(directreports.size());
        reportingStructure.setEmployee(employee);

        //Return the ReportingStructure object
        return reportingStructure;
    }

    //Recursively get the number of reports for the given employee
    private HashSet<String> getNumberOfReports(String id, HashSet<String> dirreports) {
        //Get the employee by their ID
        Employee employee = employeeRepository.findByEmployeeId(id);

        //If they have no direct reports, return the dirreports
        if (employee.getDirectReports() == null) {
            return dirreports;
        }
        //for each direct report listed, add the report to the dirreports and recursively call the method
        employee.getDirectReports().forEach(directReport -> {
            if (dirreports.add(directReport.getEmployeeId())) {
                getNumberOfReports(directReport.getEmployeeId(), dirreports);
            }
        });

        //Return the dirreports
        return dirreports;
    }
}
