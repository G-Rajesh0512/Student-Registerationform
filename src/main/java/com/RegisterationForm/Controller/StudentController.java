package com.RegisterationForm.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.RegisterationForm.Dto.StudentDto;
import com.RegisterationForm.Model.StudentRegisteration;
import com.RegisterationForm.Service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        return ResponseEntity.ok(studentService.sendOtp(email));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(
    		@RequestParam String email,
    		@RequestParam String otp) {
        return ResponseEntity.ok(studentService.verifyOtp(email, otp));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(
    		@RequestParam String otp, @RequestParam String email, 
    		@RequestBody StudentDto dto) {
        return ResponseEntity.ok(studentService.registerStudent(dto, otp, email));
    }

    @GetMapping("/get")
    public List<StudentRegisteration> getAllStudents() {
        return studentService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(
    		@PathVariable Long id, 
    		@RequestBody StudentDto dto) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}
