
public class Account{
	
	private double balance;
	private double creditRating;
	
	
	public Account(double balance) {

		this.balance = balance;
		
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double totalBalance) {
		this.balance = totalBalance;
	}

	public double getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(double creditRating) {
		this.creditRating = creditRating;
	}

}
