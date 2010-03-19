
public class RemoveBank implements Command,Cloneable {

	private String bankName;
	public void setParams ( String b) {
		//list = b;
		bankName = b;
	}

	public Result execute() {
		Result r=new Result();

		/*Bank b=bankDao.getBank(bankName);
		bankDao.delete(b);
		return true;*/
		
		BankDao bd=new BankDaoImpl();
		Bank bank=bd.getBank(bankName);
		bd.deleteBank(bank);
		r.setB(bank!=null);

		return r;
	
	}

	public Object clone() 	{
		try {
			 return super.clone();
		}
		catch ( Exception e ) { return null;}
	}

}
