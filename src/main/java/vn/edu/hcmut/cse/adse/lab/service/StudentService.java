package vn.edu.hcmut.cse.adse.lab.service;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(String id, Student student) {
        if (repository.existsById(id)) {
            student.setId(id);
            return repository.save(student);
        }
        return null;
    }

    public boolean delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Student> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }
}