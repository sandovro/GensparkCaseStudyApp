package com.example.CaseStudyApp.Controller;

import com.example.CaseStudyApp.Entity.File;
import com.example.CaseStudyApp.Entity.Item;
import com.example.CaseStudyApp.Entity.Order;
import com.example.CaseStudyApp.Entity.User;
import com.example.CaseStudyApp.Message.ResponseFile;
import com.example.CaseStudyApp.Message.ResponseMessage;
import com.example.CaseStudyApp.Service.FileService;
import com.example.CaseStudyApp.Service.OrderService;
import com.example.CaseStudyApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public String home() {return "Welcome";}

    @PostMapping("/user/{userEmail}/{userPassword}")
    public String addUser(@PathVariable String userEmail, @PathVariable String userPassword){
        return this.userService.addUser(userEmail, userPassword);
    }

    @GetMapping("/login/{userEmail}/{userPassword}")
    public User logIn(@PathVariable String userEmail, @PathVariable String userPassword){
        return this.userService.getUser(userEmail, userPassword);
    }

    @GetMapping("/login")
    public List<User> getUsers(){
        return this.userService.getAllUsers();
    }

    @DeleteMapping("/user/{userEmail}")
    public String deleteUser(@PathVariable String userEmail){
        return this.userService.deleteUser(userEmail);
    }

    @GetMapping("/user/{userEmail}")
    public User getUser(@PathVariable String userEmail){
        return this.userService.getUserInfo(userEmail);
    }

    @GetMapping("/user/order/{userEmail}")
    public List<Order> getOrdersForUser(@PathVariable String userEmail) {
        return this.userService.getOrdersForUser(userEmail);
    }

    @DeleteMapping("/user/order/{userEmail}/{orderId}")
    public String deleteOrder(@PathVariable String userEmail,@PathVariable Integer orderId){
        return this.userService.deleteOrderForUser(userEmail,orderId);
    }

    @PostMapping("/user/order/{userEmail}")
    public String addOrder(@PathVariable String userEmail,@RequestBody Order order){
        return userService.addOrderForUser(userEmail,order);
    }

    @PostMapping("/item/{userEmail}/{orderId}/{itemName}")
    public String addItemToOrder(@PathVariable String userEmail,@PathVariable Integer orderId, @PathVariable String itemName){
        return userService.addItemToOrder(userEmail,orderId,itemName);
    }

    @PostMapping("/item")
    public String addItemToInventory(@RequestBody Item item){
        return userService.addItemToInventory(item);
    }

    @GetMapping("/item/{itemName}")
    public Item getItemByName(@PathVariable String itemName){
        return userService.getItemByName(itemName);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            fileService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = fileService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        File file = fileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }


    //authenticate user

}
