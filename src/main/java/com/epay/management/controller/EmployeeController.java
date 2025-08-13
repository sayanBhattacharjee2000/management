package com.epay.management.controller;

import com.epay.management.entity.EmployeeEntity;
import com.epay.management.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping(path = "/info")
    public ResponseEntity<List<EmployeeEntity>> get(){
        List<EmployeeEntity> employeeEntities = employeeService.getList();
        if(employeeEntities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(employeeEntities,HttpStatus.OK);
        }
    }

    @PostMapping(path = "/info")
    public void addEmployee(@RequestBody EmployeeEntity employee){
        employeeService.saveEmployee(employee);
    }

    @PutMapping(path = "/info/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody EmployeeEntity employeeEntity){
        employeeService.updateEmployee(id,employeeEntity);
    }

    @DeleteMapping(path = "/info/{id}")
    public void delete(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
    }
}
