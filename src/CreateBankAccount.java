public class CreateBankAccount implements Command, Cloneable {
	//private BankAccountList list;
	private String bankName;
	private String acctName;
	private double balance;
	private String pin;
	public void setParams ( String b, String a, String amt, String p) {
		//list = b;
		bankName = b;
		acctName = a;
		pin = p;
		balance = Double.parseDouble(amt);
	}

	public Result execute() {
		Result r=new Result();
		
		BankDao bd=new BankDaoImpl();
		BankAccount bankAcct=bd.getBankAccount(bankName, acctName);
		if (bankAcct==null) {
			Bank b=bd.getBank(bankName);
			b.addBankAccount(new BankAccount(acctName, balance, pin));
			bd.save(b);
		}
		
		r.setB(bankAcct==null);

		return r;
	
	}

	public Object clone() 	{
		try {
			 return super.clone();
		}
		catch ( Exception e ) { return null;}
	}
}
