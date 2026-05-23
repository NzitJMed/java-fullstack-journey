package com.nzitjmed.week1.day03;

import java.time.LocalDateTime;
import java.util.Objects;

public class Account implements Comparable<Account>{
    /*** implements Comparable <Account> this means that:
     *  Account objects can compare themselves with other accounts.
     *  this allows : Sorting, ordering(Example Collections.sort(accounts);*/

    private final String accountNumber; // final means, value (AccountNumber should never change)can't be changed after constructor.
    private String ownerName;   // Can change later(For example customer changes legal name)
    private double balance; // Stores money
    private final LocalDateTime  createAt; //Create timestamp using localDateTime.

    //Constructor, that will create objects
    public Account(String accountNumber, String ownerName, double balance) {
        if(accountNumber == null || accountNumber.isBlank()){
            throw new IllegalArgumentException("Invalid account required");
        }

        if(ownerName == null || ownerName.isBlank()){
            throw new IllegalArgumentException("owner name required");
        }

        if(balance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }


        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.createAt = LocalDateTime.now();

    }



    public  void deposit(double amount)
    {
        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        balance += amount; // The same as balance=balance +amount.
    }


    /**
     * To remember: throws insufficientFundsException
     */


    /**
     * To remember: throws insufficientFundsException */
    public void withdraw(double amount) throws InsufficientFundsException{
        if(amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if(amount > balance){
            throw new InsufficientFundsException("Not enough balance");
        }
        balance -= amount;

    }


    public void transferTo(Account other, double amount)throws InsufficientFundsException{
        /**
         * This is the most interesting method. Moves money 1- Withdraw from this account and deposit into
         * other account*/
        if(other == null){
            throw new IllegalArgumentException("Cannot transfer from null account");
        }
        if(amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.withdraw(amount);
        try
            {
            other.deposit(amount);
            }catch (Exception e){
            System.out.println(e.getMessage());
            this.deposit(amount); // This restores original state.
            throw e;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public boolean equals(Object o) {// Determines if two account are logically same.
        if (this == o) return true;
        if (!(o instanceof    Account))return false;
Account account = (Account) o;
return accountNumber.equals(account.accountNumber);
        }



   public  int hashCode() {
        /** Must match equal logic*/
        return Objects.hash(accountNumber);
    }




    @Override
    public int compareTo(Account other) {

        return  this.createAt.compareTo(other.createAt);
    }



    public String  toString(){
        return accountNumber;
    }


}
