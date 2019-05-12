package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}

@RestController
@RequestMapping("/api")
class DemoController {

    @RequestMapping(
            value = "/students/{studentId}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Student> getStudent(
            @PathVariable("studentId") String studentId) {

        Student student = new Student();
        student.setId(studentId);
        student.setFirstName("John");
        student.setLastName("Smith");

        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}

class Student {

    private String id;
    private String firstName;
    private String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" + "id='" + id + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\'' + '}';
    }
}