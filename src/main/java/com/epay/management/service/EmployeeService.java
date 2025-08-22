package com.epay.management.service;

import com.epay.management.entity.EmployeeEntity;
import com.epay.management.repository.EmployeeRepository;
import com.epay.management.utilities.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService{

    private EmployeeRepository employeeRepository;
    private Validator validator;

    public List<EmployeeEntity> getList(){
        return employeeRepository.findAll();
    }
    
    public EmployeeEntity saveEmployee(EmployeeEntity employee){
        if(validator.isValidEmail(employee)){
            throw new IllegalArgumentException("Only Gmail addresses allowed");
        }else{
            return employeeRepository.save(employee);
        }
    }

    public void deleteEmployee(long id){
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            employeeRepository.deleteById(id);
        }else {
            throw new NoSuchElementException("employee not found on database");
        }
    }

    public EmployeeEntity updateEmployee(long id, EmployeeEntity employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow();

        if (validator.isValidEmail(employee)) {
            throw new IllegalArgumentException("Only Gmail addresses allowed");
        }else{
            employeeEntity.setFirstName(employee.getFirstName());
            employeeEntity.setLastName(employee.getLastName());
            employeeEntity.setAge(employee.getAge());
            employeeEntity.setEmail(employee.getEmail());
            return employeeRepository.save(employeeEntity);
        }
    }

}
