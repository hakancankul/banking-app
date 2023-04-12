

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

public class PanelWithdraw extends JPanel {
	private JTextField withdrawAmount;
	private Clip clip;
	private Clip clip2;
	
	public PanelWithdraw(String idNumber,Bank bank) {
		
		File file = new File("withdraw.wav");
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
		panel.setBounds(343, 303, 176, 50);
		add(panel);
		panel.setLayout(null);
		
		withdrawAmount = new JTextField();
		withdrawAmount.setFont(new Font("Arial", Font.BOLD, 13));
		withdrawAmount.setBounds(10, 11, 154, 28);
		panel.add(withdrawAmount);
		withdrawAmount.setColumns(10);
		
		
		JPanel withdrawPanel = new JPanel();
		withdrawPanel.setBackground(new Color (17, 85, 112));
		withdrawPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clip.setMicrosecondPosition(0);
				clip2.setMicrosecondPosition(0);
				for (int i = 0; i < bank.getCustomers().length; i++) {
					if(bank.getCustomers()[i] !=null && bank.getCustomers()[i].getIdentityNumber().equals(idNumber))
					{
						if(bank.getCustomers()[i].getBalance()<Integer.parseInt(withdrawAmount.getText()))
						{
							clip2.start();
							JOptionPane.showMessageDialog(null,"Insufficient balance!");
							break;
						}
						
						else if (bank.getCustomers()[i].getBalance()>=Integer.parseInt(withdrawAmount.getText())) 
						{	clip.start();
							bank.getCustomers()[i].setBalance(bank.getCustomers()[i].getBalance() - Integer.parseInt(withdrawAmount.getText()));
							Operations.updateCustomer(bank.getCustomers()[i]);
							CustomerUI.updateBalance(CustomerUI.balancetxt,bank.getCustomers()[i]);
							JOptionPane.showMessageDialog(null,"You have successfully withdrew " + Integer.parseInt(withdrawAmount.getText()) + "$");
							break;
						}
					}
					
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				withdrawPanel.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				withdrawPanel.setBackground(new Color(17, 85, 112));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				withdrawPanel.setBackground(new Color(240, 240, 240));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				withdrawPanel.setBackground(new Color(17, 85, 112));

			}
		});
		withdrawPanel.setBounds(372, 363, 120, 35);
		add(withdrawPanel);
		withdrawPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WITHDRAW");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(5, 8, 116, 21);
		withdrawPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\r\n");
		lblNewLabel_1.setIcon(new ImageIcon(PanelWithdraw.class.getResource("/images/minus.png")));
		lblNewLabel_1.setBounds(382, 178, 103, 128);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(PanelWithdraw.class.getResource("/images/yasuo.png")));
		lblNewLabel_5.setBounds(-11, 32, 834, 58);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(PanelWithdraw.class.getResource("/images/money-withdraw.png")));
		lblNewLabel_7.setBounds(161, 10, 50, 50);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_6 = new JLabel("WITHDRAW");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel_6.setBounds(239, 10, 337, 50);
		add(lblNewLabel_6);
	}
}
