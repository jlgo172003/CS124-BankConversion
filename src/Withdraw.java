public class Withdraw extends Command implements Cloneable {
	//private BankAccountList list;
	private String bankName;
	private String acctName;
	private double amount;
	private String pin;
	public void setParams ( String b, String a, String p, String amt) {
		bankName = b;
		acctName = a;
		pin = p;
		amount = Double.parseDouble(amt);
	}

	public Result execute() {
		Result r = new Result();
		BankDao bd=new BankDaoImpl();
		BankAccount account=bd.getBankAccount(bankName, acctName);
		
		if (account != null)
		{
			r.setB( account.withdraw(pin, amount));
			bd.saveBankAccount(account);
		}
		else
		{
			r.setB(false);
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
