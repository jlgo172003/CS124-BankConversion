/**
 * Contains the list of bank accouts and other methods associated to it.
 * 
 * @author (Richard Locsin) 
 * @author (Sheena dela Cruz)
 * @version (Feb. 25, 2005)
 */
import java.io.*;
import java.util.*;

public class BankAccountList implements Serializable
{
    private HashMap accounts;
    
    /**
     * Initializes a hashmap
     */
    public BankAccountList()
    {
        accounts = new HashMap();
    }
    
    /**
     * Finds a existing bank account
     */
    public BankAccount findAccount(String name)
    {
        return (BankAccount) accounts.get(name);
    }
    
    /**
     * Checks for existing bank accounts of the same name.
     * If none, creates a new bank account
     */
    public boolean addAccount(BankAccount b)
    {
        if (b!=null)
        {       
            BankAccount temp = this.findAccount(b.getAccountName());
            if (temp==null)
            {
                accounts.put(b.getAccountName(), b);
                return true;
            }
        }
        
        return false;        
    }

	/**
	 * Checks if account exists. If true,
	 * removes account from hashmap
	 */
    public boolean removeAccount(String name)
    {
        if (name!=null)
        {       
            BankAccount temp = this.findAccount(name);
            if (temp!=null)
            {
                accounts.remove(name);
                return true;
            }
        }
        
        return false;        
    }

	/**
 	 * Prints all bank accounts
 	 */
    public String printAllAccounts()
    {
        String temp = "";
        for (Iterator i = accounts.values().iterator(); i.hasNext(); )
        {
            BankAccount b = (BankAccount) i.next();
            temp = temp + b.toString() + "\n";
        }
        return temp;
    }

}
