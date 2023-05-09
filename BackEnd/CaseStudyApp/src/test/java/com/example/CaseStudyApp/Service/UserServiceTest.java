package com.example.CaseStudyApp.Service;

import com.example.CaseStudyApp.Dao.IItemDao;
import com.example.CaseStudyApp.Dao.IOrderDao;
import com.example.CaseStudyApp.Dao.IUserDao;
import com.example.CaseStudyApp.Entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private IUserDao userDao = mock(IUserDao.class);

    @Mock
    private IOrderDao orderDao = mock(IOrderDao.class);

    @Mock
    private IItemDao itemDao = mock(IItemDao.class);


    @Test
    void getUser() {
        // Arrange
        String userId = "alex@email.com";
        String userPassword = "102030";
        User expectedUser = new User(userId,userPassword);
        UserService userService = new UserService(userDao,orderDao,itemDao);
        when(userDao.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Act
        User actualUser = userService.getUser(userId,userPassword);

        // Assert
        assertEquals(expectedUser,actualUser);
    }

    @Test
    void getAllUsers() {
        // Arrange
        List<User> expectedList = new ArrayList<>();
        User newUser = new User("alex@email.com","102030");
        UserService userService = new UserService(userDao,orderDao,itemDao);
        expectedList.add(newUser);
        when(userDao.findAll()).thenReturn(expectedList);

        // Act
        List<User> actualList= userService.getAllUsers();

        // Assert
        assertEquals(expectedList,actualList);

    }

    @Test
    void getUserInfo() {
        // Arrange
        String userId = "alex@email.com";
        String userPassword = "102030";
        User expectedUser = new User(userId,userPassword);
        UserService userService = new UserService(userDao,orderDao,itemDao);
        when(userDao.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Act
        User actualUser = userService.getUserInfo(userId);

        // Assert
        assertEquals(expectedUser,actualUser);

    }

    @Test
    void addUser() {
        // Arrange
        String userId = "alex@email.com";
        String userPassword = "102030";
        User expectedUser = new User(userId,userPassword);
        String expectedOutput = "User signed up successfully";
        UserService userService = new UserService(userDao,orderDao,itemDao);

        when(userDao.save(expectedUser)).thenReturn(expectedUser);

        // Act
        String actualOutput = userService.addUser(userId,userPassword);

        // Assert
        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void deleteUser() {
        // Arrange
        String expectedOutput = "User successfully deleted";
        String userId = "alex@email.com";
        User newUser = new User(userId,"102030");
        UserService userService = new UserService(userDao,orderDao,itemDao);

        // Act
        String actualOuput = userService.deleteUser(userId);

        // Assert
        assertEquals(expectedOutput,actualOuput);
    }

    @Test
    void addOrderForUser() {
    }

    @Test
    void deleteOrderForUser() {
    }

    @Test
    void getOrdersForUser() {
    }

    @Test
    void getItemsForOrder() {
    }

    @Test
    void addItemToOrder() {
    }

    @Test
    void addItemToInventory() {
    }

    @Test
    void getItemByName() {
    }
}