import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Operations implements OperationsIF {


	public static void addTextCustomer(Bank bank,String name, String surname, String identityNumber, int age, String pw) {
		
		try {
			FileWriter fileWritter = new FileWriter("customerInfo.txt", true);
			BufferedWriter bw = new BufferedWriter(fileWritter);
			String fullInfo = identityNumber + " " + pw + " " + name + " " + surname + " " + 0;
			bw.write("\n");
			bw.write(fullInfo);
			bw.close();


		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		}
	public static void addCustomer(Bank bank,String name, String surname, String identityNumber, int age, String pw)
	{
		Customer customer = new Customer(name, surname, identityNumber, age, pw);
		for (int i = 0; i < bank.getCustomers().length; i++) {
			if (bank.getCustomers()[i] == null) {
				bank.setCustomers(i, customer);
				break;
			}
		}
	}
	public static void updateCustomer(Customer customer)
	{
		File myObj = new File("customerInfo.txt");
	    Scanner sc = null;
		try {
			sc = new Scanner(myObj);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		int lineNumber = 0;
	    while (sc.hasNextLine()) 
	    {
	    	String [] info = sc.nextLine().split(" ");
	    	if(info[0].equals(customer.getIdentityNumber()))
	    	{
	    		break;
	    	}
	    	lineNumber++;
	    }
		
		try {
			Path path = Paths.get("customerInfo.txt");
		    java.util.List<String> lines = Files.readAllLines(path);
		    lines.set(lineNumber,customer.getIdentityNumber() + " " + customer.getPassword() + " " + customer.getName() + " " + customer.getSurname() + " " + customer.getBalance());
		    Files.write(path, lines);
			String id = customer.getIdentityNumber();	
			String fullInfo = customer.getIdentityNumber() + " " + customer.getPassword() + " " + customer.getName() + " " + customer.getSurname() + " " + customer.getBalance();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();

		}
	}
	

}
