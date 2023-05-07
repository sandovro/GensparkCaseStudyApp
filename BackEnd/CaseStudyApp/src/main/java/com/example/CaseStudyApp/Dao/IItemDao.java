package com.example.CaseStudyApp.Dao;

import com.example.CaseStudyApp.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemDao extends JpaRepository<Item, String> {
}
