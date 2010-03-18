import java.io.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

import java.util.*;

public class BankFacade {
	//private SessionFactory sessionFactory;
	//variables
	private String bankName;
	private SingletonFactory factory;
	
	
	
	private static BankFacade facade = new BankFacade();
	
	private BankFacade() {	
		Configuration configuration;
		configuration = new Configuration();
        configuration.configure(new File("hibernate.cfg.xml"));
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        SessionFactorySingleton.setSessionFactory(sessionFactory);
		//factory = SingletonFactory.getInstance();

	}
	
	public static BankFacade getInstance() {
		return facade;
	}
	
	//init method
	public void setBank( String bankName) {
		this.bankName=bankName;
		/*this.bankName = bankName;
		file = bankName.replaceAll(" ", "") + "Data.bnk";
		// check if you can load from a file
		try
		{
		    input = new ObjectInputStream( new FileInputStream(file) );
		    bankName = (String)input.readUTF(); //QQQ
		    this.accounts = (BankAccountList)input.readObject(); //QQQ
		    input.close();
		}
		catch( Exception e ) //FileNotFoundException fnfe ) {} catch( ClassNotFoundException cnfe ) {} catch( IOException ioe ) {}
		{
		    this.accounts = new BankAccountList();
		}

		factory = SingletonFactory.getInstance();
		factory.setBAL( accounts );*/
	}

	public boolean createBank(String bankName)
	{
		Bank bank=new Bank();
		bank.setName(bankName);
		
		BankDao bd=new BankDaoImpl();
		bd.save(bank);
		return true;
	}
	public boolean deleteBank(String bankName)
	{
		BankDao bd=new BankDaoImpl();
		Bank b=bd.getBank(bankName);
		bd.delete(b);
		return true;
	}
	/**
	* Checks if account and pin supplied is valid
	* Returns true if correct, false if otherwise
	*/
	public boolean checkAccount(String name, String pin)
	{
		/*try
		{
		    BankAccount account = accounts.findAccount(name);
		    return account.checkPin(pin);
		}
		catch(Exception e)
		{
		    return false;
		}*/
		return false;
	}

	/**
	* Saves BankAccount data into a file. 
	*/
	private void updateData()
	{
		
		/*try
		{
		    output = new ObjectOutputStream( new FileOutputStream(file) );
		    output.writeUTF(bankName);
		    output.writeObject(accounts);            
		    output.close();
		}
		catch( IOException e)
		{
		}*/
	}
	/**
	* Returns current bankName
	*/
	public String getBankName()
	{
		return bankName;
	}

	/**
	* Creates a new bank account with the ff information: name, balance and pin.
	*/
	public boolean createBankAccount(String name, double balance, String pin)
	{
		String param = String.format("CreateBankAccount %s %s %f %s",bankName,name,balance,pin);
		Command c = factory.create( param );
		boolean result = (c.execute()).getB();
		updateData();
		return result;
	}

	/**
	 * Removes an existing bank account given the ff information: name
	 **/ 
	public boolean removeBankAccount(String name)
	{
		String param = String.format("RemoveBankAccount %s %s",bankName,name);
		Command c = factory.create( param );
		boolean result = (c.execute()).getB();
		updateData();
		return result;
	}

	/**
	* Finds an existing account in the database
	*/
	private BankAccount findAccount( String accountName )
	{
		return null;
		//return this.accounts.findAccount(accountName);
	}

	/**
	* Returns the current balance of the account accountName.
	*/
	public double getBalance( String accountName, String pin )
	{
		String param = String.format( "GetBalance %s %s %s", bankName, accountName, pin );
		Command c = factory.create( param );
		return (c.execute() ).getD();

	}

	/**
	* Deposits the amount to the account with the account name accountName;
	* Returns true if successful, false if not
	*/
	public boolean deposit( String accountName, double amount )
	{

		String param = String.format( "Deposit %s %s %f", bankName, accountName, amount );
		Command c = factory.create( param );
		boolean b = (c.execute() ).getB();
		updateData();
		return b;


	}

	/**
	* Withdraws the amount to the account with the account name accountName;
	* Returns true if successful, false if not
	*/
	public boolean withdraw( String accountName, String pin, double amount )
	{
		String param = String.format( "Withdraw %s %s %s %f", bankName, accountName, pin, amount );
		Command c = factory.create( param );
		boolean b = (c.execute() ).getB();
		updateData();
		return b;
	}

	/**
	* Transfer the amount from the account srcAccountName to the account
	* destAccountName. Returns true if successful, false if not.
	*/
	public boolean transfer( String srcAccountName, String srcPin,
		             String destAccountName, double amount )
	{
		String param = String.format( "Transfer %s %s %s %s %f", bankName, srcAccountName, srcPin, destAccountName,  amount );
		Command c = factory.create( param );
		boolean b = (c.execute() ).getB();
		updateData();
		return b;
	}

	/**
	 * Retrieves all accounts in database
	 */
	public String getPrintableAccountInfo()
	{
		//System.out.print(this.accounts.printAllAccounts());
		return null;
		//return this.accounts.printAllAccounts();
	}

	
	public void doMacro( String macro ) throws Exception {
		Scanner in = new Scanner( new File( macro ) );
		ArrayList<Command> comm = new ArrayList<Command>();
		while( in.hasNextLine() ) {
			
			String param = in.nextLine();
			System.out.println( param );
			comm.add( factory.create( param ) );
		}
		
		for( Command temp : comm ) {
			temp.execute();
		}
		
		updateData();
	}
}

