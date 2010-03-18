/**
 * Models a standard bank account
 * 
 * @author Luis F. G. Sarmenta, Ph.D.
 * @version 20040729.1518
 * @edited by Richard Locsin & Sheena dela Cruz, Feb. 25, 2005
 */
import java.io.*;

public class BankAccount implements Serializable
{
    private String accountName;
    private double balance = 0;
    private String pin;
    
    /**
     * Creates a bank account given account name, balance and pin
     */
    public BankAccount( String initAccountName, double initBalance, String initPin )
    {
        this.accountName = initAccountName;
        this.balance = initBalance;
        this.pin = initPin;
    }
    
    /**
     * Creates a bank account given account name and pin
     */
    public BankAccount( String initAccountName, String initPin )
    {
        this.accountName = initAccountName;
        this.pin = initPin;
        this.balance = 0;
    }
    
    /**
     * Checks if pin is valid
     */
    public boolean checkPin( String pin )
    {
        return pin.equals( this.pin );
    }
    
    /**
     * Checks if amount given is valid. If true, 
     * adds a specified amount to the current account's balance
     */
    public boolean deposit(double amount )
    {        
        if ( amount < 0 )
        {
            return false;
        }
        else
        {
            this.balance += amount;
            return true;
        }
    }
    
    /**
     * Checks if pin and amount given are valid. If true,
     * subtracts a specified amount from the current account's balance
     */
    public boolean withdraw( String pin, double amount )
    {
        if ( !checkPin( pin ) )
        {
            return false;
        }
        
        if ( (amount < 0) || (amount > this.balance) )
        {
            return false;
        }
        else
        {
            this.balance -= amount;
            return true;
        }
    }
    
    /**
     * Checks if pin is valid. If true,
     * retrieves and returns the balance of current account
     */
    public double getBalance( String pin ) //QQQ
    {
        if ( !checkPin( pin ) )
        {
            return Double.NaN;
        }
        
        return this.balance;
    }
    
    /** 
     * Returns account name
     */
    public String getAccountName()
    {
        return this.accountName;
    }
    
    /**
     * toString() needed for next part of project
     */
    public String toString()
    {
        // Note: this is a potential security leak since it allows you
        // to see the balance even without a pin.
        
        // That is why it's important that only the Bank has access to
        // the individual BankAccount objects.
        // That's why findAccount should be private.
        
        return "Account of: " + this.getAccountName() + ", "
             + " Balance: " + this.balance;
    }
}
