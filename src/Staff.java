
abstract public class Staff extends Person implements OperationsIF{
	
	private String authority;
	
	public Staff(String name, String surname, String gender, String identityNumber,String authority,int age) {
		super(name, surname,identityNumber,age);
		this.authority = authority;
	}
	public String getAuthority() {
		return authority;		
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	//method overriding
	public void display(String name,String surname,String gender,String identityNumber)
	{
		System.out.println(name + " " + surname + " " + gender + " " + identityNumber + " " + authority);
	}
}
