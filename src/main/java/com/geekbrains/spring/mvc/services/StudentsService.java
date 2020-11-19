package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.exceptions.StudentNotFoundException;
import com.geekbrains.spring.mvc.model.Student;
import com.geekbrains.spring.mvc.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Student> getAll() {
        return studentsRepository.findAll();
    }

    public Student saveOrUpdate(Student student) {
        return studentsRepository.save(student);
    }

    public Student findById(Long id) {
        return studentsRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Can't found student with id = " + id));
    }

    public Student findByName(String name) {
        return studentsRepository.findOneByName(name);
    }

    public List<Student> findByMinScore(int minScore) {
        return studentsRepository.findAllByScoreGreaterThan(minScore);
    }

    public Page<Student> findByPage(int pageNumber, int pageSize) {
        return studentsRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    public Page<Student> findAll(Specification<Student> spec, Integer page) {
        if (page < 1L) {
            page = 1;
        }
        return studentsRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }
}
