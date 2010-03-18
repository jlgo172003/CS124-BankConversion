
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
	
	private SingletonFactory() {
		c_getbalance = new GetBalance();
		c_deposit = new Deposit();
		c_withdraw = new Withdraw();
		c_transfer = new Transfer();
		c_createbankaccount = new CreateBankAccount();
		c_removebankaccount = new RemoveBankAccount();
		c_createbank = new CreateBank();
	}

	public static SingletonFactory getInstance() {
		return factory;
	}
	
	/*public void setBAL( BankAccountList b ) {
		bal = b;
	}*/

	public Command create ( String s ) {
		String temp[] = s.split( " " );
		
		/*if( temp[0].equals( "GetBalance" ) ) {
			GetBalance gb = (GetBalance) c_getbalance.clone();
			gb.setParams( bal, temp[1], temp[2] );
			return gb;
		}
		
		else if( temp[0].equals( "Deposit" ) ) {
			Deposit d = (Deposit) c_deposit.clone();
			d.setParams( bal, temp[1], temp[2] );
			return d;		
		}
		
		else if( temp[0].equals( "Withdraw" ) ) {
			Withdraw w = (Withdraw) c_withdraw.clone();
			w.setParams( bal, temp[1], temp[2], temp[3] );
			return w;
		}
		
		else if( temp[0].equals( "Transfer" ) ) {
			Transfer t = (Transfer) c_transfer.clone();
			t.setParams( bal, temp[1], temp[2], temp[3], temp[4] );
			return t;
		}
		
		else */if( temp[0].equals( "CreateBankAccount" ) ) {
			CreateBankAccount cba = (CreateBankAccount) c_createbankaccount.clone();
			cba.setParams( temp[1], temp[2], temp[3], temp[4] );
			return cba;
		}
		
		else if ( temp[0].equals( "CreateBank" ) ) {
			CreateBank cb = (CreateBank) c_createbank.clone();
			cb.setParams( temp[1]);
			return cb;
		}
		
		/*else if( temp[0].equals( "RemoveBankAccount" ) ) {
			RemoveBankAccount rba = (RemoveBankAccount) c_removebankaccount.clone();
			rba.setParams( bal, temp[1] );
			return rba;
		}*/
		return null;
		
	}

}
