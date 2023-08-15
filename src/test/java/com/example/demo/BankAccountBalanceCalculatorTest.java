package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.List;

public class BankAccountBalanceCalculatorTest {
    //Example input:
//AU: 80
//US: 140
//MX: 110
//SG: 120
//FR: 70
//Output:
//from: US, to: AU, amount: 20
//from: US, to: FR, amount: 20
//from: MX, to: FR, amount: 10

    @Test
    void simpleTest() {
        BankAccountBalanceCalculator bankAccountBalanceCalculator = new BankAccountBalanceCalculator();
        List<BankAccount> bankAccounts = List.of(new BankAccount("AU", 80),
                new BankAccount("US", 140),
                new BankAccount("MX", 110),
                new BankAccount("SG", 120),
                new BankAccount("FR", 70));
        List<Transaction> transactions = bankAccountBalanceCalculator.calculateAccountBalance(bankAccounts);
        System.out.println(transactions);
    }

}
