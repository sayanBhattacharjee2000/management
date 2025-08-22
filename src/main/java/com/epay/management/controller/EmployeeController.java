package com.epay.management.controller;
import com.epay.management.entity.EmployeeEntity;
import com.epay.management.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping(path = "/info")
    public ResponseEntity<List<EmployeeEntity>> get(){
        return ResponseEntity.ok(employeeService.getList());
    }

    @PostMapping(path = "/info")
    public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeEntity employee) {
        try {
            EmployeeEntity employeeEntity = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(employeeEntity,HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping(path = "/info/{id}")
    public ResponseEntity<EmployeeEntity> update(@PathVariable("id") Long id, @RequestBody EmployeeEntity employeeEntity){
        try{
            EmployeeEntity employee = employeeService.updateEmployee(id,employeeEntity);
            return new ResponseEntity<>(employee,HttpStatus.OK);
        }catch (IllegalArgumentException exception){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping(path = "/info/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(NoSuchElementException exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
