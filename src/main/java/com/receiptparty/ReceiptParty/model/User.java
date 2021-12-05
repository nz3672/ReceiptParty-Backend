package com.receiptparty.ReceiptParty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Document
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private List<PartyList> partyList;
    private Collection<Role> roles = new ArrayList<>();
}


