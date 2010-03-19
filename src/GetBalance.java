public class GetBalance implements Command, Cloneable {
	private String bankName;
	private String acctName;
	private String pin;
	
	public void setParams ( String b, String a, String p) {
		bankName = b;
		acctName = a;
		pin = p;
	}
	
	/**
	 * returns the balance, NaN if otherwise
	 */
	
	public Result execute() {
		Result r=new Result();
		
		BankDao bd=new BankDaoImpl();
		BankAccount bankAcct=bd.getBankAccount(bankName, acctName);
		
		if (bankAcct!=null) {
			r.setD(bankAcct.getBalance(pin));
			return r;
		}
		else {
			r.setD(Double.NaN);
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
