package com.example.CaseStudyApp.Dao;

import com.example.CaseStudyApp.Entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileDao extends JpaRepository<File, String> {
}
