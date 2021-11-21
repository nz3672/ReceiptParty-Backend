package com.receiptparty.ReceiptParty.controller;

import com.receiptparty.ReceiptParty.model.User;
import com.receiptparty.ReceiptParty.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private final UserServices userServices;

    @GetMapping
    public List<User> getAllUser() {
        return userServices.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(value = "id") String userID) {
        return userServices.findByID(userID);
    }

    @PostMapping
    public String addUser(User user){
        try {
            userServices.addUser(user);
        }catch (IllegalStateException illegalStateException){
            return illegalStateException.getMessage();
        }
        return "Success";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable(value = "id") String userID,@RequestBody User user) {
        try {
            userServices.updateUser(userID, user);
        } catch (IllegalStateException illegalStateException) {
            return illegalStateException.getMessage();
        }
        return "Update successfully";
    }
}
