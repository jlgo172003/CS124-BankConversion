
public class SingletonFactory {

	private static SingletonFactory factory = new SingletonFactory();
	//private BankAccountList bal;
	private GetBalance c_getbalance;
	private Deposit c_deposit;
	private Withdraw c_withdraw;
	private Transfer c_transfer;
	private CreateBankAccount c_createbankaccount;
	private RemoveBankAccount c_removebankaccount;
	private CreateBank c_createbank;
	private RemoveBank c_removebank;
	private CheckBank c_checkbank;
	
	private SingletonFactory() {
		c_getbalance = new GetBalance();
		c_deposit = new Deposit();
		c_withdraw = new Withdraw();
		c_transfer = new Transfer();
		c_createbankaccount = new CreateBankAccount();
		c_removebankaccount = new RemoveBankAccount();
		c_createbank = new CreateBank();
		c_removebank = new RemoveBank();
		c_checkbank = new CheckBank();
	}

	public static SingletonFactory getInstance() {
		return factory;
	}
	
	/*public void setBAL( BankAccountList b ) {
		bal = b;
	}*/

	public Command create ( String s ) {
		String temp[] = s.split( " " );
		
		if( temp[0].equals( "GetBalance" ) ) {
			GetBalance gb = (GetBalance) c_getbalance.clone();
			gb.setParams( temp[1], temp[2], temp[3] );
			return gb;
		}
		
		else if( temp[0].equals( "Deposit" ) ) {
			Deposit d = (Deposit) c_deposit.clone();
			d.setParams( temp[1], temp[2], temp[3] );
			return d;		
		}
		
		else if( temp[0].equals( "Withdraw" ) ) {
			Withdraw w = (Withdraw) c_withdraw.clone();
			w.setParams( temp[1], temp[2], temp[3], temp[4] );
			return w;
		}
		
		else if( temp[0].equals( "Transfer" ) ) {
			Transfer t = (Transfer) c_transfer.clone();
			t.setParams( temp[1], temp[2], temp[3], temp[4], temp[5], temp[6] );
			return t;
		}
		
		else if( temp[0].equals( "CreateBankAccount" ) ) {
			CreateBankAccount cba = (CreateBankAccount) c_createbankaccount.clone();
			cba.setParams( temp[1], temp[2], temp[3], temp[4] );
			return cba;
		}
		
		else if ( temp[0].equals( "CreateBank" ) ) {
			CreateBank cb = (CreateBank) c_createbank.clone();
			cb.setParams( temp[1]);
			return cb;
		}
		else if ( temp[0].equals( "RemoveBank" ) ) {
			RemoveBank cb = (RemoveBank) c_removebank.clone();
			cb.setParams( temp[1]);
			return cb;
		}		
		else if( temp[0].equals( "RemoveBankAccount" ) ) {
			RemoveBankAccount rba = (RemoveBankAccount) c_removebankaccount.clone();
			rba.setParams( temp[1], temp[2] );
			return rba;
		}
		else if( temp[0].equals( "CheckBank" ) ) {
			CheckBank cb = (CheckBank) c_checkbank.clone();
			cb.setParams( temp[1] );
			return cb;
		}
		return null;
		
	}

}
