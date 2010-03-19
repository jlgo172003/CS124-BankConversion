import java.io.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

import java.util.*;

import javax.swing.JOptionPane;

public class BankFacade {
	//private SessionFactory sessionFactory;
	//variables
	private String bankName;
	private SingletonFactory factory;
	private CommandThread thread;
	//BankDao bankDao;
	
	
	private static BankFacade facade = new BankFacade();
	
	private BankFacade() {	
		thread = CommandThread.getInstance();
		thread.start();
		factory = SingletonFactory.getInstance();

	}
	
	public static BankFacade getInstance() {
		return facade;
	}
	
	//init method
	public void setBank( String bankName) {
		this.bankName=bankName;
		JOptionPane.showMessageDialog(null, "Bank tellers set to "+bankName);
	}

	@SuppressWarnings("deprecation")
	protected void finalize() throws Throwable
	{
	  //do finalization here
	  //super.finalize(); //not necessary if extending Object.
		//thread.in();
		thread.stop();
	} 

	
	/**
	* Checks if account and pin supplied is valid
	* Returns true if correct, false if otherwise
	*/
	public boolean checkAccount(String bankName,String name, String pin)
	{
		if (bankName==null) bankName=this.bankName;
		BankDao bd=new BankDaoImpl();
		BankAccount ba=bd.getBankAccount(bankName, name);
		if (ba!=null)
		{
			return ba.checkPin(pin);
		}
		return false;
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
	public void createBank(String bankName)
	{
		String param = String.format("CreateBank %s", bankName);
		Command c =  factory.create(param);
		thread.addCommand(c);
		
		//boolean result = (c.execute().getB());
		//return result;
	}
	
	public boolean removeBank(String bankName)
	{
		String param = String.format("RemoveBank %s", bankName);
		Command c =  factory.create(param);
		boolean result = (c.execute().getB());
		return result;
	}
	
	
	public boolean createBankAccount(String bankName,String name, double balance, String pin)
	{
		if (bankName==null) bankName=this.bankName;
		String param = String.format("CreateBankAccount %s %s %f %s",bankName,name,balance,pin);
		Command c = factory.create( param );
		boolean result = (c.execute()).getB();
		return result;
	}

	/**
	 * Removes an existing bank account given the ff information: name
	 **/ 
	public boolean removeBankAccount(String bankName,String name)
	{
		if (bankName==null) bankName=this.bankName;
		String param = String.format("RemoveBankAccount %s %s",bankName,name);
		Command c = factory.create( param );
		boolean result = (c.execute()).getB();
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
	public double getBalance(String bankName, String accountName, String pin )
	{
		if (bankName==null) bankName=this.bankName;
		String param = String.format( "GetBalance %s %s %s", bankName, accountName, pin );
		Command c = factory.create( param );
		return (c.execute() ).getD();
	}

	/**
	* Deposits the amount to the account with the account name accountName;
	* Returns true if successful, false if not
	*/
	public boolean deposit(String bankName, String accountName, double amount )
	{

		if (bankName==null) bankName=this.bankName;
		String param = String.format( "Deposit %s %s %f", bankName, accountName, amount );
		Command c = factory.create( param );
		boolean b = (c.execute() ).getB();
		return b;


	}

	/**
	* Withdraws the amount to the account with the account name accountName;
	* Returns true if successful, false if not
	*/
	public boolean withdraw(String bankName, String accountName, String pin, double amount )
	{
		if (bankName==null) bankName=this.bankName;
		String param = String.format( "Withdraw %s %s %s %f", bankName, accountName, pin, amount );
		Command c = factory.create( param );
		boolean b = (c.execute() ).getB();
		return b;
	}

	/**
	* Transfer the amount from the account srcAccountName to the account
	* destAccountName. Returns true if successful, false if not.
	*/
	public boolean transfer(String bankName, String srcAccountName, String srcPin,
		             String destAccountName, double amount )
	{
		if (bankName==null) bankName=this.bankName;
		String param = String.format( "Transfer %s %s %s %s %s %f", bankName, srcAccountName, srcPin, bankName, destAccountName,  amount );
		Command c = factory.create( param );
		boolean b = (c.execute() ).getB();
		//updateData();
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

	
	public void doMacro( String macro ) {
		try {
			Scanner in = new Scanner( new File( macro ) );
			//ArrayList<Command> comm = new ArrayList<Command>();
			while( in.hasNextLine() ) {
				
				String param = in.nextLine();
				System.out.println( param );
				//comm.add( factory.create( param ) );
				thread.addCommand(factory.create( param ));
			}
			
			/*for( Command temp : comm ) {
				thread.addCommand(temp);
				//temp.execute();
			}*/
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (Exception e) {
			System.out.println("Error occured while reading file.");
			e.printStackTrace();
		}
	}
	
	public int showMenu() {
		String message="What do you want to do?\n" +
		"1. Add Bank\n" +
		"2. Remove Bank\n" +
		"3. Select bank to use\n" +
		"0. Exit";
		String bankName=null;
		int command;
		do {
			try {
				command=Integer.parseInt(JOptionPane.showInputDialog(message));
			} catch (Exception e) {
				return 0;
			}
			switch (command){
			case 1:
				addBankMenu();
				break;
			case 2:
				removeBankMenu();
				break;
			case 3:
				bankName=selectBankMenu();
				if (bankName==null) command=1;
				break;
			}
		} while (command==1 || command==2);
		return command;
	}
	
	public void addBankMenu() {
		String bankName;
		do {
			bankName=JOptionPane.showInputDialog("What bank name?\n" +
					"Don't put anything to return to menu.");
			if (bankName==null) break;
			else if (!bankName.equals("")) {
				createBank(bankName);
				break;
			}
		} while (!bankName.equals(""));
	}
	public void removeBankMenu() {
		String bankName;
		BankDao bankDao=thread.getDao();
		String message="Remove which bank? (just type the id)\n" +
				"Don't put anything to return to menu.";
		List<Bank> list=bankDao.getAllBank();
		if (list.size()==0) {
			JOptionPane.showMessageDialog(null, "No bank to delete.");
			return;
		}
		for (int ctr=0;ctr<list.size();ctr++) {
			message+="\n"+list.get(ctr).getId()+":"+list.get(ctr).getName();
		}
		do {
			bankName=JOptionPane.showInputDialog(message);
			if (bankName==null) break;
			try {
				bankDao.deleteBank(bankDao.getBank(Long.parseLong(bankName)));
				break;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please put a valid id.");
			}
			
		} while (!bankName.equals(""));
	}
	public String selectBankMenu() {
		String bankName;
		BankDao bankDao=thread.getDao();
		String message="Select which bank? (just type the number)\n" +
				"Don't put anything to return to menu.";
		List<Bank> list=bankDao.getAllBank();
		if (list.size()==0) {
			JOptionPane.showMessageDialog(null, "No bank to select.");
			return null;
		}
		for (int ctr=0;ctr<list.size();ctr++) {
			message+="\n"+ctr+":"+list.get(ctr).getName();
		}
		do {
			bankName=JOptionPane.showInputDialog(message);
			if (bankName==null) break;
			try {
				setBank(list.get(Integer.parseInt(bankName)).getName());
				break;
			} 
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Please put a valid input.");
			}
			
		} while (!bankName.equals(""));
		return bankName;
	}
}

