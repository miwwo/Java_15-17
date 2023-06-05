package com.java.java_15.demo.controller;

import com.java.java_15.demo.entity.Teacher;
import com.java.java_15.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @PostMapping(value = "/teachers", consumes = {"application/json"})
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher){
        System.out.println("log: " + teacher);
        teacherService.create(teacher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/teachers")
    public ResponseEntity<List<Teacher>> getTeacher(){
        List<Teacher> teacher = teacherService.readAll();
        if (teacher != null && !teacher.isEmpty()){
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/teachers/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable(name = "id") Long id){
        Teacher teacher = teacherService.read(id);
        if (teacher != null){
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/teachers/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher){
        boolean isUpdated = teacherService.update(teacher, id);
        if (isUpdated){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/teachers/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id){
        boolean isDeleted = teacherService.delete(id);
        if (isDeleted){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    // ЗАДАНИЕ 17
    @GetMapping(value = "/teachers/first_name/{first_name}")
    public ResponseEntity<List<Teacher>> getTeachersByFirstName(@PathVariable String first_name){
        return new ResponseEntity<>(teacherService.getTeachersFilteredBy("firstName", first_name),
                HttpStatus.OK);
    }

    @GetMapping(value = "/teachers/second_name/{second_name}")
    public ResponseEntity<List<Teacher>> getTeachersBySecondName(@PathVariable String second_name){
        return new ResponseEntity<>(teacherService.getTeachersFilteredBy("lastName", second_name),
                HttpStatus.OK);
    }

    @GetMapping(value = "/teachers/subject/{subject}")
    public ResponseEntity<List<Teacher>> getTeachersByPosition(@PathVariable String subject){
        return new ResponseEntity<>(teacherService.getTeachersFilteredBy("subject", subject),
                HttpStatus.OK);
    }
}
