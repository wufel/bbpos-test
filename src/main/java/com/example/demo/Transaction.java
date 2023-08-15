package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {

    private String fromAccount;
    private String toAccount;
    private Integer amount;

}
