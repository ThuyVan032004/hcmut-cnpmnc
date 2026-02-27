package vn.edu.hcmut.cse.adse.lab.controller;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.create(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) {
        return service.update(id, student);
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable String id) {
        return service.delete(id);
    }
}