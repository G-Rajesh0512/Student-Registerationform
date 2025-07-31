package com.RegisterationForm.Dto;

public class StudentDto {
	
	private String StudentName;
	private String StudentEmail;
	private String StudentMobileno;
	private String Password;
	private String ConfirmPassword;
	private Boolean OtpVerified;
	
	public StudentDto() {
		
	}

	public StudentDto(String studentName, String studentEmail, String studentMobileno, String password,
			String confirmPassword, Boolean otpVerified) {
		super();
		StudentName = studentName;
		StudentEmail = studentEmail;
		StudentMobileno = studentMobileno;
		Password = password;
		ConfirmPassword = confirmPassword;
		OtpVerified = otpVerified;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public String getStudentEmail() {
		return StudentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		StudentEmail = studentEmail;
	}

	public String getStudentMobileno() {
		return StudentMobileno;
	}

	public void setStudentMobileno(String studentMobileno) {
		StudentMobileno = studentMobileno;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getConfirmPassword() {
		return ConfirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}

	public Boolean getOtpVerified() {
		return OtpVerified;
	}

	public void setOtpVerified(Boolean otpVerified) {
		OtpVerified = otpVerified;
	}
	
	
}