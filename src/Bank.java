import java.util.*;

public class Bank {
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private Long id;
	private List<BankAccount> bal;
	private String name;
	public Bank() {
		bal=new ArrayList<BankAccount>();
	}
	public List<BankAccount> getBal() {
		return bal;
	}
	public void setBal(List<BankAccount> bal) {
		this.bal = bal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addBankAccount(BankAccount ba) {
		bal.add(ba);
	}
	public void removeBankAccount(BankAccount ba) {
		bal.remove(ba);
	}
	
}
