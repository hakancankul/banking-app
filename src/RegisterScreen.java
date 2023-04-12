import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RegisterScreen extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField ageField;
	private JTextField idNumberField;
	private JPasswordField passwordField;
	private JLabel loginLabel = new JLabel("");

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public RegisterScreen(Bank bank) {
		setResizable(false);
		setFont(new Font("Arial", Font.BOLD, 13));
		setTitle("DEU BANK");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterScreen.class.getResource("/images/logo_size_invert.jpg")));
		getContentPane().setBackground(new Color(17, 85, 112));
		setBounds(100, 100, 676, 479);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(new Color(128,128,128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setBackground(new Color(240,240,240));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				File myObj = new File("customerInfo.txt");
			    Scanner sc = null;
				try {
					sc = new Scanner(myObj);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			    while (sc.hasNextLine()) 
			    {
			    	String [] info = sc.nextLine().split(" ");
			    	if(idNumberField.getText().equals(info[0]) && Integer.parseInt(ageField.getText())>=18)
			    	{
			    		loginLabel.setText("There is an account with this Identity number!");
			    	}
			    	else if(idNumberField.getText().equals(info[0]))
			    	{
			    		loginLabel.setText("There is an account with this Identity number!");
			    	} 
			    	else if(idNumberField.getText().equals("") || passwordField.getText().equals("") || nameField.getText().equals("") || surnameField.getText().equals("") || ageField.getText().equals(""))
			    	{
			    		loginLabel.setText("Please enter all the required information!");
			    	}
			    	else if(Integer.parseInt(ageField.getText())<18)
			    	{
			    		loginLabel.setText("You must be age of above 18!");
			    	}
			    	else
			    	{
						Operations.addTextCustomer(bank,nameField.getText(),surnameField.getText(),idNumberField.getText(),Integer.parseInt(ageField.getText()),passwordField.getText());
						JOptionPane.showMessageDialog(null,"Account has been created successfully!");
						setVisible(false);
						LoginScreen.frmDeuBank.setVisible(true);		
						break;
			    	}
			    		
			    }
			}
		});
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(368, 379, 243, 42);
		getContentPane().add(panel);
		
		JLabel lblRegster = new JLabel("REGISTER");
		lblRegster.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegster.setFont(new Font("Arial", Font.BOLD, 15));
		lblRegster.setBounds(80, 11, 94, 20);
		panel.add(lblRegster);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Arial", Font.BOLD, 13));
		nameField.setColumns(10);
		nameField.setBounds(368, 177, 243, 30);
		getContentPane().add(nameField);
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Arial", Font.BOLD, 13));
		surnameField.setColumns(10);
		surnameField.setBounds(368, 237, 243, 30);
		getContentPane().add(surnameField);
		
		ageField = new JTextField();
		ageField.setFont(new Font("Arial", Font.BOLD, 13));
		ageField.setColumns(10);
		ageField.setBounds(368, 297, 243, 30);
		getContentPane().add(ageField);
		
		idNumberField = new JTextField();
		idNumberField.setFont(new Font("Arial", Font.BOLD, 13));
		idNumberField.setColumns(10);
		idNumberField.setBounds(368, 57, 243, 30);
		getContentPane().add(idNumberField);
		
		JLabel IDNumberTxt = new JLabel("Identity Number");
		IDNumberTxt.setForeground(Color.WHITE);
		IDNumberTxt.setFont(new Font("Arial", Font.BOLD, 16));
		IDNumberTxt.setBounds(368, 20, 163, 42);
		getContentPane().add(IDNumberTxt);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
		lblPassword.setBounds(368, 80, 163, 42);
		getContentPane().add(lblPassword);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Arial", Font.BOLD, 16));
		lblName.setBounds(368, 140, 163, 42);
		getContentPane().add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setForeground(Color.WHITE);
		lblSurname.setFont(new Font("Arial", Font.BOLD, 16));
		lblSurname.setBounds(368, 200, 163, 42);
		getContentPane().add(lblSurname);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Arial", Font.BOLD, 16));
		lblAge.setBounds(368, 260, 163, 42);
		getContentPane().add(lblAge);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.BOLD, 13));
		passwordField.setBounds(368, 117, 243, 29);
		getContentPane().add(passwordField);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon(RegisterScreen.class.getResource("/gif/sonaga.gif")));
		iconLabel.setBounds(64, 134, 204, 133);
		getContentPane().add(iconLabel);
		
		loginLabel.setForeground(Color.RED);
		loginLabel.setFont(new Font("Arial", Font.BOLD, 13));
		loginLabel.setBounds(367, 349, 293, 20);
		getContentPane().add(loginLabel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(RegisterScreen.class.getResource("/images/sadece deu bank.png")));
		lblNewLabel_3.setBounds(10, 128, 328, 174);
		getContentPane().add(lblNewLabel_3);
	}
}
