public class Transfer implements Command, Cloneable {
	private BankAccountList list;
	private String srcAccountName;
	private String srcPin;
	private String destAccountName;
	private double amount;
	
	public void setParams ( BankAccountList b, String srcAccountName, String srcPin,
                             String destAccountName, String amt ) {
		list = b;
		this.srcAccountName = srcAccountName;
		this.srcPin = srcPin;
		this.destAccountName = destAccountName;
		amount = Double.parseDouble(amt);
	}

	public Result execute() {
		BankAccount srcAccount = list.findAccount( srcAccountName );
		BankAccount destAccount = list.findAccount( destAccountName );
		Result r = new Result();
		
		if ( (srcAccount != null) && (destAccount != null) )
		{
		    boolean srcPinOK = srcAccount.checkPin( srcPin );
		    
		    if ( !srcPinOK )
		    {
		    	r.setB(false);
		        return r;
		    }
		    
		    boolean srcAccountWithdrawOK = srcAccount.withdraw( srcPin, amount );
		    if ( !srcAccountWithdrawOK )
		    {
		    	r.setB(false);
		        return r;
		    }
		    else
		    {
		        boolean result = destAccount.deposit( amount );
		        r.setB(result);
		        
		        return r;
		    }
		}
		
		else
		{
		    r.setB(false);
		    return r;
		}
	
	}

	public Object clone() 	{
		try {
			 return super.clone();
		}
		catch ( Exception e ) { return null;}
	}
}
