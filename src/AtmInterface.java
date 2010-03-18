/**
 * Interface for ATMGUI
 * 
 * @author (Richard Locsin) 
 * @author (Sheena dela Cruz)
 * @version (Feb. 25, 2005)
 */
public interface AtmInterface
{
    public String getATMID();
    public double getBalance(String bankName,
                             String accountName, 
                             String pin );

    public boolean deposit(String bankName,
                           String accountName, 
                           double amount );

    public boolean withdraw(String bankName,
                            String accountName, 
                            String pin, 
                            double amount );
                            
    public boolean transfer(String bankName,
                            String srcAccountName, 
                            String srcPin,
                            String destAccountName, 
                            double amount );

    public boolean transfer(String bankName,
                            String srcAccountName, 
                            String srcPin,
                            String destinationBank,
                            String destAccountName, 
                            double amount );

}
