package com.receiptparty.ReceiptParty.services;

import com.receiptparty.ReceiptParty.Repository.UserRepository;
import com.receiptparty.ReceiptParty.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServices {

    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User findUserByEmail(String email){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return userRepository.findUserByEmail(email);

    }

    public void addUser(User user){
        User userDB = findUserByEmail(user.getEmail());
        if(userDB == null){
             userRepository.insert(user);
        }else{
            throw new IllegalStateException(user.getEmail() + " already exists.");
        }
    }
}
