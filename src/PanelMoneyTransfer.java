
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JList;

public class PanelMoneyTransfer extends JPanel {
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField idField1;
	private JTextField amount1;
	private JTextField amount2;
	private Clip clip;
	private Clip clip2;

	public PanelMoneyTransfer(String idNumber, Bank bank) {
		
		File file = new File("transfer.wav");
		File file2 = new File("error.wav");
		AudioInputStream audioStream = null;
		AudioInputStream audioStream2 = null;
		
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
			audioStream2 = AudioSystem.getAudioInputStream(file2);
		} catch (UnsupportedAudioFileException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		try {
			clip = AudioSystem.getClip();
			clip2 = AudioSystem.getClip();
			
		} catch (LineUnavailableException e1) {

			e1.printStackTrace();
		}
		try {
			clip.open(audioStream);
			clip2.open(audioStream2);
		} catch (LineUnavailableException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		
		setBackground(new Color(147, 180, 203));
		setSize(854, 578);
		setLocation(20, 11);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(38, 196, 222, 41);
		add(panel);
		panel.setLayout(null);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Arial", Font.BOLD, 13));
		nameField.setBounds(92, 7, 121, 30);
		panel.add(nameField);
		nameField.setColumns(10);
				
		JLabel lblNewLabel_4 = new JLabel("Name");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_4.setBounds(10, 15, 72, 13);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel = new JLabel("SEND BY NAME & SURNAME");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(38, 125, 262, 50);
		add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(532, 196, 222, 41);
		add(panel_1);
		
		idField1 = new JTextField();
		idField1.setFont(new Font("Arial", Font.BOLD, 13));
		idField1.setBounds(92, 7, 121, 30);
		panel_1.add(idField1);
		idField1.setColumns(10);
				
		JLabel lblNewLabel_4_2 = new JLabel("ID\r\n");
		lblNewLabel_4_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_4_2.setBounds(10, 15, 72, 13);
		panel_1.add(lblNewLabel_4_2);

		JPanel amount1Panel = new JPanel();
		amount1Panel.setBounds(38, 298, 222, 41);
		add(amount1Panel);
		amount1Panel.setLayout(null);

		amount1 = new JTextField();
		amount1.setFont(new Font("Arial", Font.BOLD, 13));
		amount1.setBounds(92, 7, 121, 30);
		amount1Panel.add(amount1);
		amount1.setColumns(10);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Amount");
		lblNewLabel_4_1_1.setForeground(Color.RED);
		lblNewLabel_4_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_4_1_1.setBounds(10, 15, 109, 13);
		amount1Panel.add(lblNewLabel_4_1_1);

		JPanel amount2Panel = new JPanel();
		amount2Panel.setLayout(null);
		amount2Panel.setBounds(532, 247, 222, 41);
		add(amount2Panel);

		amount2 = new JTextField();
		amount2.setFont(new Font("Arial", Font.BOLD, 13));
		amount2.setColumns(10);
		amount2.setBounds(92, 7, 121, 30);
		amount2Panel.add(amount2);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Amount");
		lblNewLabel_4_1_1_1.setForeground(Color.RED);
		lblNewLabel_4_1_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_4_1_1_1.setBounds(10, 15, 109, 13);
		amount2Panel.add(lblNewLabel_4_1_1_1);

		JPanel send1Panel = new JPanel();
		send1Panel.setBackground(new Color(17, 85, 112));
		send1Panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clip.setMicrosecondPosition(0);
				clip2.setMicrosecondPosition(0);
				for (int i = 0; i < bank.getCustomers().length; i++) {

					if (bank.getCustomers()[i] != null && bank.getCustomers()[i].getIdentityNumber().equals(idNumber)) {
						if (bank.getCustomers()[i].getBalance() < Integer.parseInt(amount1.getText())) 
						{
							clip2.start();
							JOptionPane.showMessageDialog(null, "Insufficient balance!");
							break;
						} 
						else if (bank.getCustomers()[i].getBalance() >= Integer.parseInt(amount1.getText()))
						{					
							for (int j = 0; j < bank.getCustomers().length; j++) 
							{
								if (bank.getCustomers()[j] !=null && bank.getCustomers()[j].getName().equals(nameField.getText()) && bank.getCustomers()[j].getSurname().equals(surnameField.getText()))
								{
									clip.start();
									bank.getCustomers()[i].setBalance(bank.getCustomers()[i].getBalance() - Integer.parseInt(amount1.getText()));
									Operations.updateCustomer(bank.getCustomers()[i]);
									CustomerUI.updateBalance(CustomerUI.balancetxt, bank.getCustomers()[i]);								
									bank.getCustomers()[j].setBalance(bank.getCustomers()[j].getBalance() + Integer.parseInt(amount1.getText()));
									Operations.updateCustomer(bank.getCustomers()[j]);
									JOptionPane.showMessageDialog(null,amount1.getText() + "$ Has been sent to " + nameField.getText() + " " + surnameField.getText() + "!");
									return;
								}
							}
							clip2.setMicrosecondPosition(0);
							clip2.start();
							JOptionPane.showMessageDialog(null, "Customer with this name and surname doesn't exist in the system!");
							break;
						}
					}
				}

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				send1Panel.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				send1Panel.setBackground(new Color(17, 85, 112));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				send1Panel.setBackground(new Color(240, 240, 240));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				send1Panel.setBackground(new Color(17, 85, 112));

			}
		}
		);
		
		
		send1Panel.setBounds(88, 380, 129, 58);
		add(send1Panel);
		send1Panel.setLayout(null);
				
		JLabel lblNewLabel_3 = new JLabel("SEND");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(10, 12, 67, 36);
		send1Panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
						
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PanelMoneyTransfer.class.getResource("/images/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA.png")));
		lblNewLabel_1.setBounds(69, 6, 50, 50);
		send1Panel.add(lblNewLabel_1);

		JPanel send2Panel = new JPanel();
		send2Panel.setBackground(new Color(17, 85, 112));
		send2Panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clip.setMicrosecondPosition(0);
				clip2.setMicrosecondPosition(0);
				for (int i = 0; i < bank.getCustomers().length; i++) {

					if (bank.getCustomers()[i] != null && bank.getCustomers()[i].getIdentityNumber().equals(idNumber)) {
						if (bank.getCustomers()[i].getBalance() < Integer.parseInt(amount2.getText())) 
						{
							clip2.start();
							JOptionPane.showMessageDialog(null, "Insufficient balance!");
							break;
						} 
						else if (bank.getCustomers()[i].getBalance() >= Integer.parseInt(amount2.getText()))
						{		
							
							for (int j = 0; j < bank.getCustomers().length; j++) 
							{
								if (bank.getCustomers()[j] !=null && bank.getCustomers()[j].getIdentityNumber().equals(idField1.getText()))
								{
									clip.start();
									bank.getCustomers()[j].setBalance(bank.getCustomers()[j].getBalance() + Integer.parseInt(amount2.getText()));
									Operations.updateCustomer(bank.getCustomers()[j]);
									bank.getCustomers()[i].setBalance(bank.getCustomers()[i].getBalance() - Integer.parseInt(amount2.getText()));
									Operations.updateCustomer(bank.getCustomers()[i]);
									CustomerUI.updateBalance(CustomerUI.balancetxt, bank.getCustomers()[i]);
									JOptionPane.showMessageDialog(null,amount2.getText() + "$ Has been sent to account with ID " + idField1.getText() + "!");
									return;
								}
								
						
							}
							clip2.setMicrosecondPosition(0);
							clip2.start();
							JOptionPane.showMessageDialog(null, "Customer with this ID doesn't exist in the system!");
							break;
						}
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				send2Panel.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				send2Panel.setBackground(new Color(17, 85, 112));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				send2Panel.setBackground(new Color(240, 240, 240));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				send2Panel.setBackground(new Color(17, 85, 112));

			}
		});
		send2Panel.setLayout(null);
		send2Panel.setBounds(581, 380, 129, 58);
		add(send2Panel);
		
				JLabel lblNewLabel_1_1 = new JLabel("");
				lblNewLabel_1_1.setBounds(135, -4, 59, 64);
				send2Panel.add(lblNewLabel_1_1);
				lblNewLabel_1_1.setIcon(new ImageIcon(PanelMoneyTransfer.class.getResource("/images/depositt.png")));
				
				JLabel lblNewLabel_3_1 = new JLabel("SEND");
				lblNewLabel_3_1.setForeground(Color.WHITE);
				lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 20));
				lblNewLabel_3_1.setBounds(10, 12, 67, 36);
				send2Panel.add(lblNewLabel_3_1);
				
				JLabel lblNewLabel_2 = new JLabel("");
				lblNewLabel_2.setIcon(new ImageIcon(PanelMoneyTransfer.class.getResource("/images/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA.png")));
				lblNewLabel_2.setBounds(69, 6, 50, 50);
				send2Panel.add(lblNewLabel_2);
						
						JPanel panel_2 = new JPanel();
						panel_2.setLayout(null);
						panel_2.setBounds(38, 247, 222, 41);
						add(panel_2);
						
								surnameField = new JTextField();
								surnameField.setFont(new Font("Arial", Font.BOLD, 13));
								surnameField.setBounds(92, 7, 121, 30);
								panel_2.add(surnameField);
								surnameField.setColumns(10);
								
								JLabel lblNewLabel_4_1 = new JLabel("Surname");
								lblNewLabel_4_1.setFont(new Font("Arial", Font.BOLD, 16));
								lblNewLabel_4_1.setBounds(10, 15, 109, 13);
								panel_2.add(lblNewLabel_4_1);
								
								JLabel lblSendById = new JLabel("SEND BY ID NUMBER");
								lblSendById.setForeground(Color.WHITE);
								lblSendById.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
								lblSendById.setBounds(528, 125, 226, 50);
								add(lblSendById);
								
								JLabel lblNewLabel_5 = new JLabel("");
								lblNewLabel_5.setIcon(new ImageIcon(PanelMoneyTransfer.class.getResource("/images/yasuo.png")));
								lblNewLabel_5.setBounds(-11, 32, 834, 58);
								add(lblNewLabel_5);
								
								JLabel lblNewLabel_6 = new JLabel("MONEY TRANSFER");
								lblNewLabel_6.setForeground(Color.WHITE);
								lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 40));
								lblNewLabel_6.setBounds(239, 10, 435, 50);
								add(lblNewLabel_6);
								
								JLabel lblNewLabel_7 = new JLabel("");
								lblNewLabel_7.setIcon(new ImageIcon(PanelMoneyTransfer.class.getResource("/images/flyingmooney.png")));
								lblNewLabel_7.setBounds(161, 10, 50, 50);
								add(lblNewLabel_7);
	}
}
