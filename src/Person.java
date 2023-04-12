
abstract public class Person {

	private String name;
	private String surname;
	private String identityNumber;
	private int age;

	public Person(String name, String surname,String identityNumber,int age) {
		this.name = name;
		this.surname = surname;
		this.identityNumber = identityNumber;
		this.age = age;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
	public void display(String name,String surname,String gender,String identityNumber)
	{
		System.out.println(name + " " + surname + " " + gender + " ");
	}
	
	
	
}
