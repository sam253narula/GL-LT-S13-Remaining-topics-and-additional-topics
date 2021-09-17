package com.greatlearning.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.library.entity.StudentID;
import com.greatlearning.library.entity.StudentWithCompositeKeyWithIDClassAnnotation;

public interface StudentRepositoryIdClass extends JpaRepository<StudentWithCompositeKeyWithIDClassAnnotation, StudentID> {

}
