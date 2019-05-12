package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    private StudentService studentService;

    public DemoController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping(value = "/students/{studentId}")
    public ResponseEntity<Student> getStudent(
            @PathVariable("studentId") long studentId) {

        Student student = studentService.getStudent(studentId);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping(value = "/students", consumes = "application/json")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        long studentId = studentService.addStudent(student);
        student.setId(studentId);

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping(value = "/students/{studentId}", consumes = "application/json")
    public ResponseEntity<Void> updateStudent(
            @PathVariable("studentId") long studentId,
            @RequestBody Student student) {

        student.setId(studentId);
        studentService.updateStudent(student);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/students/{studentId}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable("studentId") long studentId) {

        studentService.deleteStudent(studentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
