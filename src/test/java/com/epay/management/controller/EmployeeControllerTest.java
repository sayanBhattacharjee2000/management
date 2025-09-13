package com.epay.management.controller;

import com.epay.management.entity.EmployeeEntity;
import com.epay.management.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class EmployeeControllerTest {

    EmployeeService employeeService = Mockito.mock(EmployeeService.class);

    @Test
    void getAllEmployees() {
       Mockito.when(employeeService.getList()).thenReturn(employeeList());
       Assertions.assertEquals("subhajit@gmail.com",employeeList().get(0).getEmail());
    }

    private List<EmployeeEntity> employeeList(){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        employeeEntity.setFirstName("Subhajit");
        employeeEntity.setLastName("Bhattacharya");
        employeeEntity.setEmail("subhajit@gmail.com");
        employeeEntity.setAge(29);
        return List.of(employeeEntity);
    }
}