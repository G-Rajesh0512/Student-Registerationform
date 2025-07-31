package com.RegisterationForm.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.RegisterationForm.Dto.StudentDto;
import com.RegisterationForm.Model.StudentRegisteration;
import com.RegisterationForm.Repository.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentrepo;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String sendOtp(String email) {
        return otpService.generateOtp(email);
    }

    public String verifyOtp(String email, String otp) {
        boolean isValid = otpService.validateOtp(email, otp);
        return isValid ? "OTP verified successfully." : "Invalid or expired OTP.";
    }

    public String registerStudent(StudentDto dto, String otp, String email) {
        boolean otpValid = otpService.validateOtp(email, otp);

        StudentRegisteration student = new StudentRegisteration();
        student.setStudentName(dto.getStudentName());
        student.setStudentEmail(dto.getStudentEmail());
        student.setStudentMobileno(dto.getStudentMobileno());
        student.setPassword(passwordEncoder.encode(dto.getPassword()));
        student.setConfirmPassword(passwordEncoder.encode(dto.getConfirmPassword()));
        student.setOtpVerified(otpValid);

        studentrepo.save(student);

        if (!otpValid) {
            return "OTP Invalid. Student not verified.";
        }

        emailService.sendRegistrationSuccessMail(student);

        return "Student Registered Successfully.";
    }

    public List<StudentRegisteration> getAllUsers() {
        return studentrepo.findAll();
    }

    public String updateStudent(Long id, StudentDto dto) {
        Optional<StudentRegisteration> optional = studentrepo.findById(id);
        if (optional.isPresent()) {
            StudentRegisteration student = optional.get();
            student.setStudentName(dto.getStudentName());
            student.setStudentEmail(dto.getStudentEmail());
            student.setStudentMobileno(dto.getStudentMobileno());
            student.setPassword(passwordEncoder.encode(dto.getPassword()));
            student.setConfirmPassword(passwordEncoder.encode(dto.getConfirmPassword()));
            studentrepo.save(student);
            return "Student Updated Successfully.";
        }
        return "Student Not Found.";
    }

    public String deleteStudent(Long id) {
        Optional<StudentRegisteration> optional = studentrepo.findById(id);
        if (optional.isPresent()) {
            studentrepo.deleteById(id);
            return "Student Deleted Successfully.";
        }
        return "Student Not Found.";
    }
}
