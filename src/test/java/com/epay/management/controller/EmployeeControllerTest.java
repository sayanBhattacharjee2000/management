package com.epay.management.controller;

import com.epay.management.entity.EmployeeEntity;
import com.epay.management.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;


    @DisplayName("Verify that when user visit employee site, he should get navigated to welcome " +
            "page")
    @Test
    void testWelcomePage_WhenUserGoToEmployeeSite_ShouldBeNavigatedToWelcomePage() throws Exception {
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee"));
    }

    @DisplayName("Verify that when user visit employee site and click on view employee then he " +
            "should" +
            " get navigated to employee list")
    @Test
    void testGetAllEmployees_WhenUserClickOnViewList_ShouldBeNavigatedToEmployeeList() throws Exception {
        mockMvc.perform(get("/employee/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee-list"));
        Mockito.verify(employeeService, Mockito.times(1)).getList();
    }

    @Test
    void testCreateEmployee_WhenUserCreatesAccount_AccountShouldGetCreated() throws Exception {
        mockMvc.perform(post("/employee/save"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/employee/list"));

        Mockito.verify(employeeService,Mockito.times(1)).saveEmployee(Mockito.any(EmployeeEntity.class));
    }

    @Test
    void testCreateEmployee_WhenUserCreatesAccount_ThrowException() throws Exception {

        Mockito.doThrow(new IllegalArgumentException("Invalid data"))
                .when(employeeService).saveEmployee(Mockito.any(EmployeeEntity.class));

        mockMvc.perform(post("/employee/save"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee-form"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attributeExists("employee"));

        Mockito.verify(employeeService,Mockito.times(1)).saveEmployee(Mockito.any(EmployeeEntity.class));
    }

    @Test
    void testUpdateEmployee_WhenUserUpdatesAccount_AccountShouldGetUpdated() throws Exception {
        EmployeeEntity employee = employee();

        mockMvc.perform(post("/employee/save")
                        .flashAttr("employee",employee))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/employee/list"));

        Mockito.verify(employeeService,Mockito.times(1)).updateEmployee(employee.getId(), employee);
        Mockito.verify(employeeService,Mockito.times(0)).saveEmployee(employee);
    }

    @Test
    void testUpdateEmployee_WhenUserUpdatesAccount_ThrowException() throws Exception {
        EmployeeEntity employee = employee();

        Mockito.doThrow(new IllegalArgumentException("Invalid data"))
                .when(employeeService).updateEmployee(Mockito.anyLong(),
                        Mockito.any(EmployeeEntity.class));

        mockMvc.perform(post("/employee/save")
                        .flashAttr("employee", employee))
                .andExpect(status().isOk())
                .andExpect(view().name("employee-form"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attributeExists("employee"));

        Mockito.verify(employeeService,Mockito.times(1)).updateEmployee(employee.getId(), employee);
    }

    @DisplayName("Verify that when user deletes an employee, it should call service and redirect")
    @Test
    void testDeleteEmployee_WhenUserClickDelete_ShouldRemoveEmployee() throws Exception {
        EmployeeEntity employee = employee();

        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employee/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/employee/list"));

        Mockito.verify(employeeService, Mockito.times(1)).deleteEmployee(1L);
    }

    private EmployeeEntity employee(){
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1L);
        employee.setFirstName("Sayan");
        employee.setLastName("Bhattacharjee");
        employee.setAge(26);
        employee.setEmail("IT");
        return employee;
    }
}