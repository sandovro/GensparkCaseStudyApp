package com.example.CaseStudyApp.Service;

import com.example.CaseStudyApp.Dao.IItemDao;
import com.example.CaseStudyApp.Dao.IOrderDao;
import com.example.CaseStudyApp.Dao.IUserDao;
import com.example.CaseStudyApp.Entity.Item;
import com.example.CaseStudyApp.Entity.Order;
import com.example.CaseStudyApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IItemDao itemDao;

    public UserService(IUserDao userDao, IOrderDao orderDao, IItemDao itemDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.itemDao = itemDao;
    }

    public User getUser(String userEmail, String userPassword){
        User user = null;
        Optional<User> u = this.userDao.findById(userEmail);
        if(u.isPresent()&&u.get().getPassword().equals(userPassword))
            user = u.get();
        else
            throw new RuntimeException("User could not be authenticated");
        return user;
    }

    public List<User> getAllUsers(){
        return this.userDao.findAll();
    }

    public User getUserInfo(String userEmail){
        User user = null;
        Optional<User> u = this.userDao.findById(userEmail);
        if(u.isPresent())
            user = u.get();
        else
            throw new RuntimeException("User could not be found");
        return user;
    }

    public String addUser(String userEmail, String userPassword){
        User user = new User(userEmail,userPassword);

        this.userDao.save(user);

        return "User signed up successfully";
    }

    public String deleteUser(String userEmail){
        this.userDao.deleteById(userEmail);

        return "User successfully deleted";
    }

    public String addOrderForUser(String userEmail, Order order){
        User user = this.getUserInfo(userEmail);
        List<Order> orderList = user.getUserOrders();

        orderList.add(order);
        user.setUserOrders(orderList);

        this.userDao.save(user);

        return "Order successfully added";
    }

    public String deleteOrderForUser(String userEmail, Integer orderId){
        User user = getUserInfo(userEmail);
        List<Order> orderList = user.getUserOrders();

        for(var o:orderList) {
            if(o.getOrderId().equals(orderId))
                orderList.remove(o);
        }

        user.setUserOrders(orderList);

        this.userDao.deleteById(userEmail);

        this.userDao.save(user);

        return "Order successfully deleted";
    }

    public List<Order> getOrdersForUser(String userEmail) {
        User user = getUserInfo(userEmail);

        return user.getUserOrders();
    }

    public List<Item> getItemsForOrder(String userEmail, Integer orderId){
        User user = getUserInfo(userEmail);
        List<Order> orders = user.getUserOrders();
        List<Item> items = new ArrayList<>();

        for(var o:orders){
            if(o.getOrderId().equals(orderId))
                items=o.getItemList();
        }

        return items;
    }

    public String addItemToOrder(String userEmail, Integer orderId, String itemName){
        User user = this.getUserInfo(userEmail);
        List<Order> orderList = user.getUserOrders();
        List<Item> itemList = new ArrayList<>();
        Item item = getItemByName(itemName);

        for(var o:orderList){
            if(o.getOrderId().equals(orderId)) {
                Order order = o;
                itemList = o.getItemList();
                itemList.add(item);
                order.setItemList(itemList);
                this.orderDao.save(order);
                break;
            }
        }

        return "Item successfully added to order";
    }

    public String addItemToInventory(Item item){
        this.itemDao.save(item);

        return "Item successfully added to inventory";
    }

    public Item getItemByName(String itemName){
        Item item = null;
        Optional<Item> i = this.itemDao.findById(itemName);
        if(i.isPresent())
            item = i.get();
        else
            throw new RuntimeException("Item could not be found in database");
        return item;
    }

}
