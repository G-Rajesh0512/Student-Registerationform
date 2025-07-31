package com.RegisterationForm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.RegisterationForm.Model.StudentRegisteration;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationSuccessMail(StudentRegisteration student) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(student.getStudentEmail());
        message.setSubject("Student Registration Success");
        message.setText("Dear " + student.getStudentName() + ",\n\nYour registration was successful.\n\nThank you!\nCYE Technology Team");
        mailSender.send(message);
    }
}
