public class Withdraw implements Command, Cloneable {
	private BankAccountList list;
	private String acctName;
	private double amount;
	private String pin;
	public void setParams ( BankAccountList b, String a, String p, String amt) {
		list = b;
		acctName = a;
		pin = p;
		amount = Double.parseDouble(amt);
	}

	public Result execute() {
		BankAccount account = list.findAccount( acctName );
		Result r = new Result();
		if ( account != null )
		{
		    r.setB( account.withdraw( pin, amount ));
		    //updateData();
		}
		else
		{
		    r.setB( false );
		}
		return r;
	
	}

	public Object clone() 	{
		try {
			 return super.clone();
		}
		catch ( Exception e ) { return null;}
	}
}
