public class Deposit implements Command, Cloneable {
	private BankAccountList list;
	private String acctName;
	private double amount;
	
	public void setParams ( BankAccountList b, String a, String p) {
		list = b;
		acctName = a;
		amount = Double.parseDouble(p);
	}

	public Result execute() {
		BankAccount account = list.findAccount( acctName );
		Result r = new Result();
		if ( account != null )
		{
		    r.setB( account.deposit( amount ) );
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
