
public class Customer extends Person{
	private Account account;
	private String password;
	private int balance;
	
	public Customer(String name,String surname,String identityNumber,int age,String password) {
		super(name, surname,identityNumber,age);
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
