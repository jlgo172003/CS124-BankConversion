
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
	//constructor
	public void setBank(String bankname) {
		facade.setBank(bankName);
	
	}
	/*public BankDriver(String bankName)
	{
		this.bankName = bankName;
	}*/
	
	public void init() {
		facade = BankFacade.getInstance();
		//facade.init(bankName);
	}
	
	public BankDriver( String bankName, String macro ) {
		/*facade = BankFacade.getInstance();
		facade.init(bankName);
		try {
			facade.doMacro( macro );
		}
		catch (Exception e) { System.out.println( "ERROR OCCURED" ); }*/
		
	}
	public String getBankName()
	{
		return facade.getBankName();
	}
	
	public boolean createBank(String bankname)
	{
		return facade.createBank(bankname);
	}
	
	public boolean createBankAccount(String name, double balance, String pin)
	{
		return facade.createBankAccount( name, balance, pin );
	}

	public boolean removeBankAccount(String name)
	{
		return facade.removeBankAccount( name );
	}
		
	public double getBalance( String accountName, String pin )
	{
		return facade.getBalance( accountName, pin );
	}
	
	public boolean deposit( String accountName, double amount )
	{
		return facade.deposit( accountName, amount );
	}
	
	public boolean withdraw( String accountName, String pin, double amount )
	{
		return facade.withdraw( accountName, pin, amount );
	}

	public boolean transfer( String srcAccountName, String srcPin,
		             String destAccountName, double amount )
	{
		return facade.transfer( srcAccountName, srcPin, destAccountName, amount );
	}
	
	public boolean checkAccount(String name, String pin)
	{
		return facade.checkAccount( name, pin );

	}
	/**
	 * The main method needed to start application
	 */
    public static void main(String[] args)
    {
		// instantiate a Bank
		String name = null;
		try
		{
		    name = args[0];
		}
		catch(ArrayIndexOutOfBoundsException aoobe)
		{
		    name = "default";
		}
		//Bank bank = new Bank(name);
		BankDriver bank = new BankDriver(name, "bankSetup.ini" );
		
		// add 2 Tellers     //QQQ fix the ID
		bank.addTeller(new TellerGUI("teller1", bank));
		bank.addTeller(new TellerGUI("teller2", bank));
		// add 2 Atms
		bank.addATM(new ATMGUI("atm1", bank));
		bank.addATM(new ATMGUI("atm2", bank));
		// make GUIs visible
		showGUIs(bank.tellers);
		showGUIs(bank.atms);
		showMessage();
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
    private static void showMessage()
    {
    	JOptionPane.showMessageDialog( current, "Welcome to Expressnet!\n Please be reminded that multiple banks are not yet supported.\n Current bank in use is 'default' unless otherwise changed.", 
                                       "Welcome!", JOptionPane.INFORMATION_MESSAGE );
    }
}