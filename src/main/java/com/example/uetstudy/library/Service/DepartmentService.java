package com.example.uetstudy.library.Service;

import com.example.uetstudy.library.Model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Department save(Department department);
    Department update(Department department);
    List<Department> findAll();
    Optional<Department> findById(Long id);
    void deleteDepartmentById(Long id);
}
