package com.receiptparty.ReceiptParty.Repository;

import com.receiptparty.ReceiptParty.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByEmail(String email);
}
