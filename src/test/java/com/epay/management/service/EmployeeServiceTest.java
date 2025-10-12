package com.epay.management.service;

import com.epay.management.entity.EmployeeEntity;
import com.epay.management.repository.EmployeeRepository;
import com.epay.management.utilities.Validator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);

    @Mock
    Validator validator;

    @Test
    void testGetEmployeeById_ValidEmployeeIsPresent_ShouldReturnEmployee(){
        EmployeeEntity employee = employee();
        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(1L);
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