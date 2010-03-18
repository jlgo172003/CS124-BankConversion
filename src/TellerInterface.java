/**
 * Interface for TellerGUI
 * 
 * @author (Richard Locsin) 
 * @author (Sheena dela Cruz)
 * @version (Feb. 25, 2005)
 */
public interface TellerInterface
{
    public String getTellerId();
    public boolean createBankAccount(String name, 
                                     double balance, 
                                     String pin);

    public boolean removeBankAccount(String name);

    public double getBalance(String accountName, 
                             String pin );

    public boolean deposit(String accountName, 
                           double amount );

    public boolean withdraw(String accountName, 
                            String pin, 
                            double amount );
                            
    public boolean transfer(String srcAccountName, 
                            String srcPin,
                            String destAccountName, 
                            double amount );

    public boolean transfer(String srcAccountName, 
                            String srcPin,
                            String destinationBank,
                            String destAccountName, 
                            double amount );

}
