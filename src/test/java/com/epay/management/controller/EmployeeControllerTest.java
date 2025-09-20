package com.epay.management.controller;

import com.epay.management.service.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

        Mockito.verify(employeeService,Mockito.times(1)).getList();
    }
}