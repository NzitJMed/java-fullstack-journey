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
    private final LocalDateTime  createdAt; //Create timestamp using localDateTime.

    //Constructor, that will create objects
    public Account( String accountNumber, String ownerName, double balance) {
        if(accountNumber == null || accountNumber.isBlank()){
            throw new IllegalArgumentException("Account number cannot be null or blank");
        }
        if(ownerName == null || ownerName.isBlank()){
            throw new IllegalArgumentException("owner name cannot be null or blank");
        }
        if(balance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
    }
    public  void deposit(double amount)
    {
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.balance += amount; // The same as balance=balance +amount.
    }



    public void withdraw(double amount) throws InsufficientFundsException{
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
        if(amount > this.balance){
            throw new InsufficientFundsException(String.format("Insufficient funds balace=%.2f,requestedd=%.2f", this.balance, amount));
        }
        this.balance -= amount;
    }


    public void transferTo(Account other, double amount)throws InsufficientFundsException{
        if(other == null){
            throw new IllegalArgumentException("Target account cannot be null");
        }
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
        if(amount > this.balance){
            throw new InsufficientFundsException(String.format("Insufficient funds + this.balance=%.2f, requested=%.2f",this.balance, amount));
        }
        this.balance -= amount;
        other.balance+=amount;
    }



    public double getBalance() {
        return balance;
    }
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        if(ownerName == null || ownerName.isBlank()){
            throw new IllegalArgumentException("owner name cannot be null or blank");
        }
        this.ownerName = ownerName;
    }


    public String getAccountNumber() {
        return accountNumber;
    }
    public double getCreated(){ return createdAt.getSecond();}


    @Override
    public boolean equals(Object o) {
        // Determines if two account are logically same.
        if (this == o) return true;
        if (o==null || getClass() !=o.getClass())return false;
        Account account = (Account) o;
        return  Objects.equals(accountNumber,account.accountNumber);
        }


   @Override
   public  int hashCode() {
        return Objects.hash(accountNumber);
    }
    @Override
    public int compareTo(Account other) {
        return  this.createdAt.compareTo(other.createdAt);
    }

    @Override
    public String toString() {
        return String.format("Account[%s,owner=%s,balance=%.2f,created=%s]", accountNumber,ownerName,balance, createdAt);
}




}
