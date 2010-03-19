
public interface BankDao {
	public void saveBank(Bank b);
	public void deleteBank(Bank b);
	public void saveBankAccount(BankAccount ba);
	public void deleteBankAccount(BankAccount ba);
	public Bank getBank(String bankName);
	public Bank getBank(Long id);
	public java.util.List<Bank> getAllBank();
	public BankAccount getBankAccount(String bankName,String accountName);
}
