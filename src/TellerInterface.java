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
    public void createBankAccount(String name, 
                                     double balance, 
                                     String pin, Listener l);

    public void removeBankAccount(String name, Listener l);

    public double getBalance(String accountName, 
                             String pin );

    public void deposit(String accountName, 
                           double amount, Listener l );

    public void withdraw(String accountName, 
                            String pin, 
                            double amount, Listener l );
                            
    public void transfer(String srcAccountName, 
                            String srcPin,
                            String destAccountName, 
                            double amount, Listener l );

    public boolean transfer(String srcAccountName, 
                            String srcPin,
                            String destinationBank,
                            String destAccountName, 
                            double amount );

}
