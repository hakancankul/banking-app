

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class PanelSettings extends JPanel {
	private JPasswordField oldpw;
	private JPasswordField newpw;
	private Clip clip;

	File file = new File("error.wav");
	AudioInputStream audioStream = null;
	
	
	
	public PanelSettings(String idNumber,Bank bank) {
		
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
		
		setBackground(new Color(147, 180, 203));
		setSize(854, 578);
		setLocation(20, 11);
		setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(PanelSettings.class.getResource("/images/setings.png")));
		lblNewLabel_7.setBounds(161, 10, 50, 50);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(PanelSettings.class.getResource("/images/yasuo.png")));
		lblNewLabel_5.setBounds(-11, 32, 834, 58);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("SETTINGS\r\n");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel_6.setBounds(239, 10, 435, 50);
		add(lblNewLabel_6);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(47, 167, 299, 41);
		add(panel);
		
		JLabel text1 = new JLabel("Old Password");
		text1.setFont(new Font("Arial", Font.BOLD, 16));
		text1.setBounds(10, 16, 108, 13);
		panel.add(text1);
		
		oldpw = new JPasswordField();
		oldpw.setHorizontalAlignment(SwingConstants.CENTER);
		oldpw.setBounds(128, 11, 151, 20);
		panel.add(oldpw);
		oldpw.setFont(new Font("Arial", Font.BOLD, 13));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(433, 167, 299, 41);
		add(panel_1);
		
		JLabel text2 = new JLabel("New Password");
		text2.setFont(new Font("Arial", Font.BOLD, 16));
		text2.setBounds(8, 16, 132, 13);
		panel_1.add(text2);
		
		newpw = new JPasswordField();
		newpw.setHorizontalAlignment(SwingConstants.CENTER);
		newpw.setFont(new Font("Arial", Font.BOLD, 13));
		newpw.setBounds(128, 11, 151, 20);
		panel_1.add(newpw);
		
		JPanel changePanel = new JPanel();
		changePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				for (int i = 0; i < bank.getCustomers().length; i++) {
					clip.setMicrosecondPosition(0);
					if (bank.getCustomers()[i]!= null && bank.getCustomers()[i].getIdentityNumber().equals(idNumber)) 
					{
						if (!(oldpw.getText().equals(newpw.getText())) && oldpw.getText().equals(bank.getCustomers()[i].getPassword())) 
						{
							bank.getCustomers()[i].setPassword(newpw.getText());
							JOptionPane.showMessageDialog(null, "Your new password has been set successfully!");
							Operations.updateCustomer(bank.getCustomers()[i]);							
							break;
						}
						else if(oldpw.getText().equals(newpw.getText()))
						{
							clip.start();
							JOptionPane.showMessageDialog(null, "Old password and new password can not be the same!");
							break;
						}
						else 
						{
							clip.start();
							JOptionPane.showMessageDialog(null, "Your current password is wrong!");
							break;
						}
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				changePanel.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				changePanel.setBackground(new Color(17, 85, 112));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				changePanel.setBackground(new Color(240, 240, 240));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
				changePanel.setBackground(new Color(17, 85, 112));

			}
		}
		);
		changePanel.setLayout(null);
		changePanel.setBackground(new Color(17, 85, 112));
		changePanel.setBounds(329, 233, 129, 58);
		add(changePanel);
		
		JLabel x = new JLabel("CHANGE");
		x.setForeground(Color.WHITE);
		x.setFont(new Font("Arial", Font.BOLD, 15));
		x.setBounds(10, 12, 67, 36);
		changePanel.add(x);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PanelSettings.class.getResource("/images/key.png")));
		lblNewLabel_1.setBounds(89, 5, 50, 50);
		changePanel.add(lblNewLabel_1);
		
		JLabel lblChangePassword = new JLabel("CHANGE PASSWORD");
		lblChangePassword.setForeground(Color.WHITE);
		lblChangePassword.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblChangePassword.setBackground(Color.BLACK);
		lblChangePassword.setBounds(291, 106, 262, 50);
		add(lblChangePassword);
		
		JLabel faq = new JLabel("If you have any problems, please visit deubank.com/FAQ");
		faq.setForeground(Color.WHITE);
		faq.setFont(new Font("Arial", Font.BOLD, 20));
		faq.setBounds(140, 370, 685, 33);
		add(faq);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelSettings.class.getResource("/images/faq.png")));
		lblNewLabel.setBounds(47, 380, 99, 84);
		add(lblNewLabel);
		
		JLabel lblDidntFindYour = new JLabel("Didn't find your own question? deubank.com/contact or use live chat!");
		lblDidntFindYour.setForeground(Color.WHITE);
		lblDidntFindYour.setFont(new Font("Arial", Font.BOLD, 20));
		lblDidntFindYour.setBounds(140, 437, 685, 33);
		add(lblDidntFindYour);
	}
}
