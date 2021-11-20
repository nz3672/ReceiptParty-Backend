package com.receiptparty.ReceiptParty.controller;

import com.receiptparty.ReceiptParty.model.User;
import com.receiptparty.ReceiptParty.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public String addUser(User user){
        try {
            userServices.addUser(user);
        }catch (IllegalStateException illegalStateException){
            return illegalStateException.getMessage();
        }
        return "Success";
    }
}
