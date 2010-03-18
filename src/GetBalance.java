public class GetBalance implements Command, Cloneable {
	
	private String acctName;
	private String pin;
	
	public void setParams ( String a, String p) {
		acctName = a;
		pin = p;
	}

	public Result execute() {
		/*BankAccount account = list.findAccount( acctName );
        
		Result r = new Result();
		if ( account != null )
		{
			
			r.setD( account.getBalance( pin ) );
			return r;
		}
		else
		{ 
			r.setD( Double.NaN);
			return r;
		}*/
		return null;
	}

	
	public Object clone() 	{
		try {
			 return super.clone();
		}
		catch ( Exception e ) { return null;}
	}
}
