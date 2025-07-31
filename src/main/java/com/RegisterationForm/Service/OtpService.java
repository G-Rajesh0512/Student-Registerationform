package com.RegisterationForm.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
    @Autowired
    private JavaMailSender mailSender;

    private final Map<String, OtpEntry> otpMap = new HashMap<>();

    private static class OtpEntry {
        String otp;
        LocalDateTime expiry;

        OtpEntry(String otp, LocalDateTime expiry) {
            this.otp = otp;
            this.expiry = expiry;
        }
    }

    public String generateOtp(String email) {
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);
        otpMap.put(email, new OtpEntry(otp, LocalDateTime.now().plusMinutes(10)));
        sendOtpEmail(email, otp);
        return "OTP sent to " + email;
    }

    private void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP for registration is: " + otp);
        mailSender.send(message);
    }

    public boolean validateOtp(String email, String otp) {
        OtpEntry entry = otpMap.get(email);
        if (entry == null) return false;
        boolean valid = otp.equals(entry.otp) && LocalDateTime.now().isBefore(entry.expiry);
        if (valid) otpMap.remove(email);
        return valid;
    }

    public void sendConfirmationEmail(String student) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(student);
        message.setSubject("Student Registration Successful");
        message.setText("Dear "+ student +",\n\nYour registration was successful.\n\nThank you!");
        mailSender.send(message);
    }
}
