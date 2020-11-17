package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Student;
import com.geekbrains.spring.mvc.repositories.specifications.StudentSpecifications;
import com.geekbrains.spring.mvc.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

// root: http://localhost:8189/app/students

@Controller
@RequestMapping("/students")
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public String showAllStudents(Model model,
                                  @RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
                                  @RequestParam(name = "min_score", required = false) Integer minScore,
                                  @RequestParam(name = "max_score", required = false) Integer maxScore) {
        Specification<Student> spec = Specification.where(null);
        if (minScore != null) {
            spec = spec.and(StudentSpecifications.scoreGEThan(minScore));
        }
        if (maxScore != null) {
            spec = spec.and(StudentSpecifications.scoreLEThan(maxScore));
        }

        List<Student> students = studentsService.findAll(spec, pageNumber).getContent();
        model.addAttribute("students", students);
        return "all_students";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_student_form";
    }

    @PostMapping("/add")
    public String saveNewStudent(@ModelAttribute Student newStudent) {
        studentsService.saveOrUpdate(newStudent);
        return "redirect:/students/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentsService.findById(id));
        return "edit_student_form";
    }

    @PostMapping("/edit")
    public String modifyStudent(@ModelAttribute Student modifiedStudent) {
        studentsService.saveOrUpdate(modifiedStudent);
        return "redirect:/students/";
    }

    @GetMapping("/info_by_name")
    @ResponseBody
    public Student infoByName(@RequestParam String name) {
        return studentsService.findByName(name);
    }

    @GetMapping("/find_by_min_score")
    @ResponseBody
    public List<Student> findStudentsByMinScore(@RequestParam int score) {
        return studentsService.findByMinScore(score);
    }
}