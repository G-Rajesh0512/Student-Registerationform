package com.RegisterationForm.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RegisterationForm.Model.StudentRegisteration;

@Repository
public interface StudentRepo extends JpaRepository <StudentRegisteration,Long>{


}
