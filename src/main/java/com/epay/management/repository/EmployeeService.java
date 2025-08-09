package com.epay.management.repository;

import com.epay.management.service.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService{

    private EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getList(){
        return employeeRepository.findAll();
    }
    
    public void saveEmployee(){
        employeeRepository.save();
    }

    public void deleteEmployee(){
        employeeRepository.deleteById();
    }

    public void updateEmployee(long id){
        employeeRepository.save();
    }
}
