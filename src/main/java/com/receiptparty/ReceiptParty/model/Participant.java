package com.receiptparty.ReceiptParty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class Participant {
    @Id
    private String id;
    private String name;
    private String email;
    private PayStatus payStatus;
    private Double balance;
    private Double totalAmount;
}
