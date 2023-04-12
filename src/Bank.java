
public class Bank {
	
	private String name;
	private double interestRate;
	private Customer customers[];
	private Staff staffs[];
	private double totalAmount;
	
	public Bank(String name, double interestRate, double totalAmount) {
		
		this.customers = new Customer[100];
		this.staffs = new Staff[10];
		this.name = name;
		this.interestRate = interestRate;
		this.totalAmount = totalAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Customer[] getCustomers() {
		return customers;
	}

	public void setCustomers(int index, Customer customer) {
	    this.customers[index] = customer;
	}

	public Staff[] getStaffs() {
		return staffs;
	}

	public void setStaffs(Staff[] staffs) {
		this.staffs = staffs;
	}
}
