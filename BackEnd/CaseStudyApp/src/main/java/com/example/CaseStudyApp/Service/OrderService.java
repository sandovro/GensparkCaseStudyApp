package com.example.CaseStudyApp.Service;

import com.example.CaseStudyApp.Dao.IItemDao;
import com.example.CaseStudyApp.Dao.IOrderDao;
import com.example.CaseStudyApp.Entity.Item;
import com.example.CaseStudyApp.Entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private IItemDao itemDao;

    public OrderService(IOrderDao orderDao, IItemDao itemDao) {
        this.orderDao = orderDao;
        this.itemDao = itemDao;
    }



}
