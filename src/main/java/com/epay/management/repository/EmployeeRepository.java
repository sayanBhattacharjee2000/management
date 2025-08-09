package com.epay.management.repository;

import com.epay.management.service.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Long, EmployeeEntity> {
}
