
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
		/**
		 * thread here?
		 */
	}
	
	public BankDriver(String macro ) {
		init();
		facade.doMacro(macro);
		/*try {
			facade.doMacro( macro );
		}
		catch (FileNotFoundException e){
			
		}
		catch (Exception e) {
			System.out.println("Error occured while doing macro \n Details:" ); 
			e.printStackTrace();
		}*/
	}

	public void init() {
		facade = BankFacade.getInstance();
	}

	
	public String getBankName()
	{
		return facade.getBankName();
	}
	
	public void createBank(String bankname)
	{
		facade.createBank(bankname);
	}
	
	public boolean createBankAccount(String bankName, String name, double balance, String pin)
	{
		return facade.createBankAccount(bankName, name, balance, pin );
	}

	public boolean removeBankAccount(String bankName, String name)
	{
		return facade.removeBankAccount(bankName, name );
	}
		
	public double getBalance(String bankName,  String accountName, String pin )
	{
		return facade.getBalance(bankName, accountName, pin );
	}
	
	public boolean deposit(String bankName,  String accountName, double amount )
	{
		return facade.deposit(bankName, accountName, amount );
	}
	
	public boolean withdraw(String bankName,  String accountName, String pin, double amount )
	{
		return facade.withdraw(bankName, accountName, pin, amount );
	}

	public boolean transfer(String bankName,  String srcAccountName, String srcPin,
		             String destAccountName, double amount )
	{
		return facade.transfer(bankName, srcAccountName, srcPin, destAccountName, amount );
	}
	
	public boolean checkAccount(String bankName, String name, String pin)
	{
		return facade.checkAccount(bankName, name, pin );

	}
	/**
	 * The main method needed to start application
	 */
		
	
	public int showMenu() {
		return facade.showMenu();
		
	}
	
    public static void main(String[] args)
    {
    	/**Todo:
    	 * setup opening of ini file
    	 */
    	
    	BankDriver bankdriver=new BankDriver("bankSetup.ini");
    	//bankdriver.
    	
    	int command=bankdriver.showMenu();
    	
    	if (command==0) System.exit(0);
    	
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