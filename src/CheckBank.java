import java.util.*;

public class CheckBank extends Command implements Cloneable {

	private String bankName;
	public void setParams ( String b) {
		bankName = b;
	}

	public Result execute() {
		Result r=new Result();

		BankDao bd=new BankDaoImpl();
		Bank bank=bd.getBank(bankName);

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
