
public class CreateBank implements Command,Cloneable {

	private String bankName;
	public void setParams ( String b) {
		//list = b;
		bankName = b;
	}

	public Result execute() {
		Result r=new Result();

		BankDao bd=new BankDaoImpl();
		Bank bank=bd.getBank(bankName);
		if (bank==null) {
			bank=new Bank();
			bank.setName(bankName);
			bd.save(bank);
		}
		
		r.setB(bank==null);

		return r;
	
	}

	public Object clone() 	{
		try {
			 return super.clone();
		}
		catch ( Exception e ) { return null;}
	}

}
