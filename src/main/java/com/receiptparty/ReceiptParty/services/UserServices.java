package com.receiptparty.ReceiptParty.services;

import com.receiptparty.ReceiptParty.Repository.RoleRepository;
import com.receiptparty.ReceiptParty.Repository.UserRepository;
import com.receiptparty.ReceiptParty.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServices implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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
            user.setPassword(passwordEncoder.encode(user.getPassword()));
             userRepository.insert(user);
        }else{
            throw new IllegalStateException(user.getEmail() + " already exists.");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in database.");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

    public void updateUser(String userID, User user) {
        Optional<User> userDB = userRepository.findById(userID);
        if (userDB.isPresent() && findUserByEmail(user.getEmail()) == null) {
            user.setId( userID );
            user.setEmail( user.getEmail()==null ? userDB.get().getEmail() : user.getEmail());
            user.setName( user.getName()==null ? userDB.get().getName() : user.getName()  );
            user.setPassword( user.getPassword()==null ? userDB.get().getPassword() : user.getPassword()  );
            user.setPartyList( user.getPartyList()==null ? userDB.get().getPartyList() : user.getPartyList()  );
            userRepository.save(user);
        } else if (findUserByEmail(user.getEmail()) != null) {
            throw new IllegalStateException("This email already exists.");
        }
        else {
            throw new IllegalStateException("User not found.");
        }
    }

    public User findByID(String userID) {
        Optional<User> user = userRepository.findById(userID);
        return user.get();

    }
}
