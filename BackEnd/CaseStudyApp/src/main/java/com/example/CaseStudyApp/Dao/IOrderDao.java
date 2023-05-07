package com.example.CaseStudyApp.Dao;

import com.example.CaseStudyApp.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDao extends JpaRepository<Order,Integer> {
}
