package com.test.encapsulate;

import com.test.encapsulate.entities.BankAccount;
public class MainBankAccount {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        account.setAccountNumber("A4445645620");
        account.setBalance(44590.66);

        System.out.println("Account Number is: " + account.getAccountNumber());
        System.out.println("Balance is: " + account.getBalance());
    }
}
