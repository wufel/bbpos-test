package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BankAccountBalanceCalculator {

//    Let’s say there are at most 500 bank accounts, some of their balances are above 100 and some are below.
//    How do you move money between them so that they all have at least 100?
//Just to be clear we are not looking for the optimal solution, but a working one.
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

    public List<Transaction> calculateAccountBalance(List<BankAccount> accounts) {
        if(accounts.stream().map(BankAccount::getBalance).reduce(0, Integer::sum) < 100 * accounts.size()) {
            throw new NotEnoughFundsException("There is not enough funds!");
        }
        List<Transaction> transactions = new ArrayList<>();
        List<BankAccount> above100 = new ArrayList<>();
        accounts.stream()
                .filter(account -> account.getBalance() > 100)
                .forEach(above100::add);
        List<BankAccount> below100 = accounts.stream()
                .filter(account -> account.getBalance() < 100).toList();

        below100.forEach(deficitAccount -> addTransactions(above100, deficitAccount, transactions));

        return transactions;

    }

    public List<Transaction> addTransactions(List<BankAccount> sufficients, BankAccount deficitAccount, List<Transaction> transactions) {
        if(deficitAccount.getBalance() == 100) {
            return transactions;
        }
        Integer deficit = 100 - deficitAccount.getBalance();
        BankAccount fromAccount = sufficients.get(0);
        Integer surplus = fromAccount.getBalance() - 100;
        if (deficit >= surplus) {
            //when deficit greater or equal to surplus, evict account no longer have sufficient funds
            deficitAccount.setBalance(deficitAccount.getBalance() + surplus);
            sufficients.remove(fromAccount);
            transactions.add(new Transaction(fromAccount.getName(), deficitAccount.getName(), surplus));
        } else {
            //when deficit less than surplus, reset surplus account balance only
            deficitAccount.setBalance(deficitAccount.getBalance() + deficit);
            fromAccount.setBalance(fromAccount.getBalance() - deficit);
            transactions.add(new Transaction(fromAccount.getName(), deficitAccount.getName(), deficit));
        }
        return addTransactions(sufficients, deficitAccount, transactions);
    }

}
