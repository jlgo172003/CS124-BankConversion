public class RemoveBankAccount implements Command, Cloneable {
	private BankAccountList list;
	private String bankName;
	private String acctName;
	
	public void setParams ( String b, String a) {
		bankName = b;
		acctName = a;
	}

	/**
	 * returns true if an account was deleted
	 */
	
	public Result execute() {
		//boolean result = list.removeAccount(acctName);
		Result r=new Result();
		
		BankDao bd=new BankDaoImpl();
		BankAccount bankAcct=bd.getBankAccount(bankName, acctName);
		
		if (bankAcct!=null) {
			System.out.println(bankAcct.getAccountName());
			
			bd.deleteBankAcct(bankAcct);
		}
		
		r.setB(bankAcct!=null);

		return r;
	}

	public Object clone() 	{
		try {
			 return super.clone();
		}
		catch ( Exception e ) { return null;}
	}
}
