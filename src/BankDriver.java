
/**
 * Models a standard bank
 * 
 * @author (Richard Locsin) 
 * @author (Sheena dela Cruz)
 * @version (Feb. 25, 2005)
 */
 
/**
 * Edited for CS124 by <using Design Patterns>
 * Janssen Marwin L. Go (idnumber= 071557) && Ken Darione Ang Lee (idnumber= 071939)
 * Jan 4, 2009 
 */
import javax.swing.*;
import java.util.*;

public class BankDriver 
{   
	
	private static HashMap tellers = new HashMap();
	private static HashMap atms = new HashMap();
	private static JFrame current;
	
	private BankFacade facade;
	private String bankName;
	
	public BankDriver() {
		init();	
	}
	
	public BankDriver(String macro ) {
		init();
		facade.doMacro(macro);
	}

	public void init() {
		facade = BankFacade.getInstance();
	}

	
	public String getBankName()
	{
		return facade.getBankName();
	}
	
	public void createBankAccount(String bankName, String name, double balance, String pin, Listener l)
	{
		facade.createBankAccount(bankName, name, balance, pin, l );
	}

	public void removeBankAccount(String bankName, String name, Listener l)
	{
		facade.removeBankAccount(bankName, name, l );
	}
		
	public void getBalance(String bankName,  String accountName, String pin, Listener l )
	{
		facade.getBalance(bankName, accountName, pin, l );
	}
	
	public void deposit(String bankName,  String accountName, double amount, Listener l )
	{
		facade.deposit(bankName, accountName, amount, l );
	}
	
	public void withdraw(String bankName,  String accountName, String pin, double amount, Listener l )
	{
		facade.withdraw(bankName, accountName, pin, amount, l );
	}

	public void transfer(String bankName,  String srcAccountName, String srcPin,
		             String destAccountName, double amount, Listener l )
	{
		facade.transfer(bankName, srcAccountName, srcPin, destAccountName, amount, l );
	}
	
	public boolean checkAccount(String bankName, String name, String pin)
	{
		return facade.checkAccount(bankName, name, pin );

	}
	/**
	 * The main method needed to start application
	 */
		
	
	public void showMenu() {
		
		int command=facade.showMenu();
    	if (command==0) System.exit(0);
		
	}
	
    public static void main(String[] args)
    {
    	/**Todo:
    	 * setup opening of ini file
    	 */
    	
    	BankDriver bankdriver=new BankDriver("bankSetup.ini");
    	bankdriver.showMenu();
    	
    	bankdriver.addTeller(new TellerGUI("teller1", bankdriver));
    	bankdriver.addTeller(new TellerGUI("teller2", bankdriver));
		// add 2 Atms
    	bankdriver.addATM(new ATMGUI("atm1", bankdriver));
    	bankdriver.addATM(new ATMGUI("atm2", bankdriver));
		
    	showGUIs(bankdriver.tellers);
		showGUIs(bankdriver.atms);
		
		/*showMessage();*/
    }
	    
	/**
	 * Adds Teller GUI
	 */
	public void addTeller(TellerInterface ti)
	{
		// store internally
		if ( tellers.get(ti.getTellerId()) == null)
		{
		    tellers.put(ti.getTellerId(), ti);
		}
	}

	/**
	* Adds ATAM GUI
	*/
	public void addATM(AtmInterface ai)
	{
		// store internally
		if ( atms.get(ai.getATMID()) == null)
		{
		    atms.put(ai.getATMID(), ai);
		}
	}
	
    /**
     * Makes the GUIs visible
     */
    private static void showGUIs(HashMap h)
    {
        for(Iterator i = h.keySet().iterator();i.hasNext();)
        {
            current = (JFrame)h.get(i.next());
            current.show();
        }
    }
    
    /**
     * Shows welcome message
     */
    /*private static void showMessage()
    {
    	JOptionPane.showMessageDialog( current, "Welcome to Expressnet!\n Please be reminded that multiple banks are not yet supported.\n Current bank in use is 'default' unless otherwise changed.", 
                                       "Welcome!", JOptionPane.INFORMATION_MESSAGE );
    }*/
}