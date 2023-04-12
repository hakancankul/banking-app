import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Toolkit;

public class LoginScreen {

	static JFrame frmDeuBank;
	private JTextField idnumberField;
	private JPasswordField passwordField;
	private JLabel loginLabel = new JLabel("");
	private Clip clip;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bank bank = new Bank("DEU BANK",1500,1500);
					LoginScreen window = new LoginScreen(bank);
					window.frmDeuBank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginScreen(Bank bank) {
		initialize(bank);
	}

	
	private void initialize(Bank bank) {
		File file = new File("welcome.wav");
		AudioInputStream audioStream = null;
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {

			e1.printStackTrace();
		}
		try {
			clip.open(audioStream);
		} catch (LineUnavailableException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		frmDeuBank = new JFrame();
		frmDeuBank.setResizable(false);
		frmDeuBank.setFont(new Font("Arial", Font.BOLD, 13));
		frmDeuBank.setTitle("DEU BANK");
		frmDeuBank.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginScreen.class.getResource("/images/logo_size_invert.jpg")));
		frmDeuBank.getContentPane().setBackground(new Color(17, 85, 112));
		frmDeuBank.setBounds(100, 100, 676, 479);
		frmDeuBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDeuBank.getContentPane().setLayout(null);
		
		JPanel login_panel = new JPanel();
		login_panel.setBackground(new Color(240,240,240));
		login_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(idnumberField.getText().equals("") || passwordField.getText().equals(""))
				{
					loginLabel.setText("Please enter an ID and password!");
				}
				else
				{
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
				        if (idnumberField.getText().equals(info[0]) && passwordField.getText().equals(info[1])) 
				        {
				        	loginLabel.setText("");
				        	clip.start();
				        	JOptionPane.showMessageDialog(null,"Login Successful!");
				        	for (int i = 0; i < bank.getCustomers().length; i++) {				        	
								if (bank.getCustomers()[i]!= null && bank.getCustomers()[i].getIdentityNumber().equals(info[0])) 
								{
									break;
								}
								else
								{
									File myObj2 = new File("customerInfo.txt");
								    Scanner sc2 = null;
									try {
										sc2 = new Scanner(myObj2);
									} catch (FileNotFoundException e1) {
										
										e1.printStackTrace();
									}
									int customerCount = 0;
								    while (sc2.hasNextLine()) 
								    {
								    	 String [] info2 = sc2.nextLine().split(" ");
								    	 Customer customer2 = new Customer(info2[2], info2[3],info2[0],18,info2[1]);
										 customer2.setBalance(Integer.parseInt(info2[4]));
										 customer2.setPassword(info2[1]);										
										 bank.setCustomers(customerCount, customer2);
										 customerCount++;								
								    }
									
								}
							}
				        	frmDeuBank.setVisible(false);
				        	CustomerUI customerUI = new CustomerUI(info[0],bank);
				        	customerUI.setVisible(true);
				        	break;
						}
				        if (!sc.hasNextLine()) {
				        	loginLabel.setText("Invalid ID and password!");
				        	break;
						}
				        
				    }		     
				}	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				login_panel.setBackground(new Color(128,128,128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				login_panel.setBackground(new Color(240,240,240));
			}
		});
		login_panel.setBounds(231, 326, 200, 40);
		frmDeuBank.getContentPane().add(login_panel);
		login_panel.setLayout(null);
		
		JLabel txt_login = new JLabel("LOG IN");
		txt_login.setHorizontalAlignment(SwingConstants.LEFT);
		txt_login.setFont(new Font("Arial", Font.BOLD, 14));
		txt_login.setBounds(75, 10, 67, 20);
		login_panel.add(txt_login);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginScreen.class.getResource("/images/key.png")));
		lblNewLabel_1.setBounds(10, -5, 50, 50);
		login_panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon imageIcon = new ImageIcon(LoginScreen.class.getResource("/images/logo_size_invert.jpg")); 
		Image image = imageIcon.getImage();  
		Image newimg = image.getScaledInstance(240, 240,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon newImageIcon = new ImageIcon(newimg);
		lblNewLabel.setIcon(new ImageIcon(LoginScreen.class.getResource("/gif/sonaga.gif")));
		lblNewLabel.setBounds(179, -4, 300, 174);
		frmDeuBank.getContentPane().add(lblNewLabel);	
		loginLabel.setForeground(Color.RED);
		loginLabel.setFont(new Font("Arial", Font.BOLD, 13));
		loginLabel.setBounds(179, 302, 370, 13);
		frmDeuBank.getContentPane().add(loginLabel);
		
		JPanel registerPanel = new JPanel();
		registerPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterScreen registerFrame = new RegisterScreen(bank);
				frmDeuBank.setVisible(false);
	        	registerFrame.setVisible(true);	        	
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				registerPanel.setBackground(new Color(128,128,128));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				registerPanel.setBackground(new Color(240,240,240));
			}
		});
		registerPanel.setBounds(231, 376, 200, 40);
		frmDeuBank.getContentPane().add(registerPanel);
		registerPanel.setLayout(null);
		JLabel txt_create_account = new JLabel("CREATE ACCOUNT");
		txt_create_account.setBounds(37, 0, 163, 40);
		registerPanel.add(txt_create_account);
		txt_create_account.setFont(new Font("Arial", Font.BOLD, 14));
		
		JPanel user_panel = new JPanel();
		user_panel.setBounds(179, 181, 300, 50);
		frmDeuBank.getContentPane().add(user_panel);
		user_panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(260, 0, 50, 50);
		user_panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(LoginScreen.class.getResource("/images/user.png")));
		
		idnumberField = new JTextField();
		idnumberField.setBounds(10, 6, 200, 40);
		user_panel.add(idnumberField);
		idnumberField.setFont(new Font("Arial", Font.BOLD, 13));
		idnumberField.setColumns(10);
		
		JPanel password_panel = new JPanel();
		password_panel.setLayout(null);
		password_panel.setBounds(179, 241, 300, 50);
		frmDeuBank.getContentPane().add(password_panel);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBounds(255, 0, 50, 50);
		password_panel.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setIcon(new ImageIcon(LoginScreen.class.getResource("/images/pw.png")));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 6, 200, 40);
		password_panel.add(passwordField);
		passwordField.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(LoginScreen.class.getResource("/images/sadece deu bank.png")));
		lblNewLabel_3.setBounds(198, 11, 328, 174);
		frmDeuBank.getContentPane().add(lblNewLabel_3);
	}
}
