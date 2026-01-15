package com.example.resume.repository;

import com.example.resume.model.ResumeResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<ResumeResult, Long> {
}

