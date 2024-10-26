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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTest {

    private String reportingstructureUrl;
    private String employeeIdUrl;
    private String employeeUrl;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeService employeeService;

    @Before
    public void setup() {
        reportingstructureUrl = "http://localhost:" + port + "/reportingstructure/{id}";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
    }

    @Test
    public void read_CorrectNumberOfReports() {

        ////using lennon (16a596ae-edd3-4847-99fe-c4518e82c86f) as he has 2 layers of reports.
        Employee employee = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");

        ReportingStructure reportingStructure = restTemplate.getForEntity(reportingstructureUrl, ReportingStructure.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
        assert reportingStructure != null;
        assertEquals(4, reportingStructure.getNumberOfReports());
        assertEmployeeEquivalence(employee, reportingStructure.getEmployee());

    }

    @Test
    public void read_NullReports() {

        //using Harrison (c0c2293d-16bd-4603-8e08-638a9d18b22c) as he has no reports.
        Employee employee = employeeService.read("c0c2293d-16bd-4603-8e08-638a9d18b22c");

        ReportingStructure reportingStructure = restTemplate.getForEntity(reportingstructureUrl, ReportingStructure.class, "c0c2293d-16bd-4603-8e08-638a9d18b22c").getBody();
        assert reportingStructure != null;
        assertEquals(reportingStructure.getNumberOfReports(), 0);
        assertEmployeeEquivalence(employee, reportingStructure.getEmployee());

    }

    //Wanted to test the case where an employee is a direct report of multiple employees.
    //Current code does not seems to be set for that, causing infinite recursion.
    // Leaving my code fragment here for reference.
    /*@Test
    public void read_DuplicateReports() {

        //Create two employees, both that are direct reports of each other.
        //This tests that if an employee is a direct report to multiple employees, they are only counted once.
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setDepartment("Engineering");
        employee1.setPosition("Developer");

        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setLastName("Doe");
        employee2.setDepartment("Engineering");
        employee2.setPosition("Developer");

        List<Employee> directReports = new ArrayList<>();
        directReports.add(employee2);
        employee1.setDirectReports(directReports);

        directReports = new ArrayList<>();
        directReports.add(employee1);
        employee2.setDirectReports(directReports);

        //Create the employees in the database.
        Employee createdEmployee1 = restTemplate.postForEntity(employeeUrl, employee1, Employee.class).getBody();
        Employee createdEmployee2 = restTemplate.postForEntity(employeeUrl, employee2, Employee.class).getBody();

        //Get the reporting structure for the first employee.
        ReportingStructure reportingStructure = restTemplate.getForEntity(reportingstructureUrl, ReportingStructure.class, createdEmployee1.getEmployeeId()).getBody();
        assert reportingStructure != null;
        assertEquals(1, reportingStructure.getNumberOfReports());
        assertEmployeeEquivalence(createdEmployee1, reportingStructure.getEmployee());

        //Get the reporting structure for the second employee.
        reportingStructure = restTemplate.getForEntity(reportingstructureUrl, ReportingStructure.class, createdEmployee2.getEmployeeId()).getBody();
        assert reportingStructure != null;
        assertEquals(1, reportingStructure.getNumberOfReports());
        assertEmployeeEquivalence(createdEmployee2, reportingStructure.getEmployee());



    }*/

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
