package com.example.CaseStudyApp.Dao;

import com.example.CaseStudyApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User,String> {
}
