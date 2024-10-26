package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.Assert.assertEquals;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTest {

    private String reportingstructureUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @Before
    public void setup() {
        reportingstructureUrl = "http://localhost:" + port + "/reportingstructure/{id}";
    }

    @Test
    public void read_CorrectNumberOfReports() {

        ////using lennon (16a596ae-edd3-4847-99fe-c4518e82c86f) as he has 2 layers of reports.
        Employee employee = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        ReportingStructure reportingStructure = reportingStructureService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        int expectedReports = reportingStructure.getNumberOfReports();

        ReportingStructure actual = restTemplate.getForEntity(reportingstructureUrl, ReportingStructure.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
        assert actual != null;
        assertEquals(expectedReports, reportingStructure.getNumberOfReports());
        assertEmployeeEquivalence(employee, reportingStructure.getEmployee());

    }

    @Test
    public void read_NullReports() {

        //using Harrison (c0c2293d-16bd-4603-8e08-638a9d18b22c) as he has no reports.
        Employee employee = employeeService.read("c0c2293d-16bd-4603-8e08-638a9d18b22c");
        ReportingStructure reportingStructure = reportingStructureService.read("c0c2293d-16bd-4603-8e08-638a9d18b22c");
        int expectedReports = reportingStructure.getNumberOfReports();

        ReportingStructure actual = restTemplate.getForEntity(reportingstructureUrl, ReportingStructure.class, "c0c2293d-16bd-4603-8e08-638a9d18b22c").getBody();
        assert actual != null;
        assertEquals(expectedReports, actual.getNumberOfReports());
        assertEmployeeEquivalence(employee, reportingStructure.getEmployee());

    }

    /*
     *Wanted to test the case where an employee is a direct report of multiple employees.
     *Current code does not seem to be set for that, causing infinite recursion.
     *I would have to change the code to allow for that, but I am not sure if that is the intended behavior.
     */


    //Copy/paste of the defined method in EmployeeServiceImplTest.java.
    // I copied it here to avoid dependency between classes.
    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getPosition(), actual.getPosition());
        assertEquals(expected.getDepartment(), actual.getDepartment());

        //If we have no direct reports and we expect no direct reports, we can return.
        if (expected.getDirectReports() == null && actual.getDirectReports() == null) {
            return;
        }

        //Create two lists of employees to compare.
        List<Employee> actualReports = actual.getDirectReports();
        List<Employee> expectedReports = expected.getDirectReports();
        //First we make usre the sizes of the arrays are the same, as they should be.
        assertEquals(expectedReports.size(), actualReports.size());
        //Then we make sure that each value in the array is the same.
        for (int i = 0; i < expectedReports.size(); i++) {
            assertEquals(expectedReports.get(i).getEmployeeId(), actualReports.get(i).getEmployeeId());
        }
    }
}
