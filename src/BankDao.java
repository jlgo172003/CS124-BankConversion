
public interface BankDao {
	public void save(Bank b);
	public void delete(Bank b);
	public Bank getBank(String bankName);
	public Bank getBank(Long id);
	public java.util.List<Bank> getAllBank();
	public BankAccount getBankAccount(String bankName,String accountName);
}
