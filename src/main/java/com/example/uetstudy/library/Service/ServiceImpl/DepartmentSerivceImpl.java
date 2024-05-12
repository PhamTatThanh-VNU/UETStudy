package com.example.uetstudy.library.Service.ServiceImpl;

import com.example.uetstudy.library.Model.Department;
import com.example.uetstudy.library.Repository.DepartmentRepository;
import com.example.uetstudy.library.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class DepartmentSerivceImpl implements DepartmentService {
    @Autowired
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Long id) {
       return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department department) {
        Department departmentTemp = new Department();
        departmentTemp.setDepartmentName(department.getDepartmentName());
        return departmentRepository.save(departmentTemp);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        try{
            departmentRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Can't delete department, you have to delete every knowledgeBlock with this department id.");
        } catch (Exception e) {
            throw new RuntimeException("Can't delete department", e);
        }

    }

    @Override
    public Department update(Department department) {
        Department departmentUpdate = departmentRepository.getReferenceById(department.getDepartmentId());
        departmentUpdate.setDepartmentName(department.getDepartmentName());
        return departmentRepository.save(departmentUpdate);
    }
}
