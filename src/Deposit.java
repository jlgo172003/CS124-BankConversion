public class Deposit extends Command implements Cloneable {
	//private BankAccountList list;
	
	private String bankName;
	private String acctName;
	private double amount;
	
	public void setParams ( String b, String a, String p) {
		bankName = b;
		acctName = a;
		amount = Double.parseDouble(p);
	}

	public Result execute() {
		Result r = new Result();
		BankDao bd=new BankDaoImpl();
		BankAccount account=bd.getBankAccount(bankName, acctName);
		
		if (account != null)
		{
			r.setB( account.deposit(amount));
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
