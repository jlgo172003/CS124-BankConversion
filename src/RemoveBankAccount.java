public class RemoveBankAccount implements Command, Cloneable {
	private BankAccountList list;
	private String acctName;
	
	public void setParams ( BankAccountList b, String a) {
		list = b;
		acctName = a;
	}

	public Result execute() {
		boolean result = list.removeAccount(acctName);
		Result r = new Result();
		r.setB( result );
		
		return r;
	
	}

	public Object clone() 	{
		try {
			 return super.clone();
		}
		catch ( Exception e ) { return null;}
	}
}
