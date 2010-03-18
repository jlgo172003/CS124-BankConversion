public class CreateBankAccount implements Command, Cloneable {
	private BankAccountList list;
	private String acctName;
	private double balance;
	private String pin;
	public void setParams ( BankAccountList b, String a, String amt, String p) {
		list = b;
		acctName = a;
		pin = p;
		balance = Double.parseDouble(amt);
	}

	public Result execute() {
		Result r=new Result();
		boolean result = list.addAccount(new BankAccount(acctName, balance, pin));
		r.setB(result);
		return r;
	
	}

	public Object clone() 	{
		try {
			 return super.clone();
		}
		catch ( Exception e ) { return null;}
	}
}
