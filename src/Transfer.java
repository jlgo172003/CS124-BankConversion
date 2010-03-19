public class Transfer extends Command implements Cloneable {
	//private BankAccountList list;
	private String srcBankName;
	private String srcAccountName;
	private String srcPin;
	private String destBankName;
	private String destAccountName;
	private double amount;
	
	public void setParams ( String srcBankName, String srcAccountName, String srcPin,
                             String destBankName, String destAccountName, String amt ) {
		this.srcBankName = srcBankName;
		this.srcAccountName = srcAccountName;
		this.srcPin = srcPin;
		this.destBankName = destBankName;
		this.destAccountName = destAccountName;
		amount = Double.parseDouble(amt);
	}

	public Result execute() {
		BankDao bd = new BankDaoImpl();
		BankAccount srcAccount = bd.getBankAccount(srcBankName, srcAccountName);
		BankAccount destAccount = bd.getBankAccount(destBankName, destAccountName);

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
		        bd.saveBankAccount(srcAccount);
		        bd.saveBankAccount(destAccount);
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
