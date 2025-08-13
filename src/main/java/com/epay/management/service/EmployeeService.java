package com.epay.management.service;

import com.epay.management.entity.EmployeeEntity;
import com.epay.management.repository.EmployeeRepository;
import jakarta.persistence.Id;
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
    
    public void saveEmployee(EmployeeEntity employee){
        employeeRepository.save(employee);
    }

    public void deleteEmployee(long id){
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(long id, EmployeeEntity employee){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow();
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setAge(employee.getAge());
        employeeRepository.save(employeeEntity);
    }
}
