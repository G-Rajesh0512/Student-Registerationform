package com.RegisterationForm.Service;

import java.util.List;

import com.RegisterationForm.Dto.StudentDto;
import com.RegisterationForm.Model.StudentRegisteration;
public interface StudentServiceinterface {
	    String sendOtp(String email);
	    String StudentRegisteration(StudentDto dto, String otp, String email) throws Exception;
	    List<StudentRegisteration> getAllUsers();
	    String updateStudent(Long id, StudentDto dto);
	    String deleteStudent(Long id);

}
