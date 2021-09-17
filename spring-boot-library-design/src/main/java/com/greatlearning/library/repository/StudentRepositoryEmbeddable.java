package com.greatlearning.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.library.entity.StudentID;
import com.greatlearning.library.entity.StudentIDWithEmbeddableAnnotation;
import com.greatlearning.library.entity.StudentWithCompositeKeyWithEmbeddableAnnotation;
import com.greatlearning.library.entity.StudentWithCompositeKeyWithIDClassAnnotation;

public interface StudentRepositoryEmbeddable extends JpaRepository<StudentWithCompositeKeyWithEmbeddableAnnotation, StudentIDWithEmbeddableAnnotation> {

}
