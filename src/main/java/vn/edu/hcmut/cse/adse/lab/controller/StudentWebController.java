package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService service;

    @GetMapping
    public String getAllStudents(
            @RequestParam(required = false) String keyword,
            Model model) {

        List<Student> students = (keyword != null && !keyword.isBlank())
                ? service.searchByName(keyword)
                : service.getAll();

        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        if (student == null) return "redirect:/students";
        model.addAttribute("student", student);
        return "student-detail";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "student-form";
    }

    @PostMapping("/new")
    public String createStudent(@ModelAttribute Student student) {
        service.create(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = service.getById(id);
        if (student == null) return "redirect:/students";
        model.addAttribute("student", student);
        model.addAttribute("isEdit", true);
        return "student-form";
    }

    @PostMapping("/{id}/edit")
    public String updateStudent(@PathVariable String id,
                                @ModelAttribute Student student) {
        student.setId(id);
        service.update(id, student);
        return "redirect:/students";
    }

    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable String id) {
        service.delete(id);
        return "redirect:/students";
    }
}