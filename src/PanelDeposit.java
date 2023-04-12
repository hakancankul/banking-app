
import java.awt.Color;
import javax.sound.sampled.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class PanelDeposit extends JPanel {
	private JTextField depositAmount;
	private Clip clip;
	private Clip clip2;

	public PanelDeposit(String idNumber, Bank bank) {

		File file = new File("deposit.wav");
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

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PanelDeposit.class.getResource("/images/plus.png")));
		lblNewLabel_1.setBounds(382, 178, 103, 128);
		add(lblNewLabel_1);

		JPanel amount1Panel = new JPanel();
		amount1Panel.setLayout(null);
		amount1Panel.setBounds(343, 303, 176, 50);
		add(amount1Panel);

		depositAmount = new JTextField();
		depositAmount.setFont(new Font("Arial", Font.BOLD, 13));
		depositAmount.setColumns(10);
		depositAmount.setBounds(10, 11, 154, 28);
		amount1Panel.add(depositAmount);

		JPanel deposit = new JPanel();
		deposit.setBackground(new Color(17, 85, 112));
		deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clip.setMicrosecondPosition(0);
				clip2.setMicrosecondPosition(0);
				int amount = Integer.parseInt(depositAmount.getText());
				for (int i = 0; i < bank.getCustomers().length; i++) {

					if (bank.getCustomers()[i] != null && bank.getCustomers()[i].getIdentityNumber().equals(idNumber)) {
						clip.start();
						bank.getCustomers()[i].setBalance(bank.getCustomers()[i].getBalance() + amount);
						Operations.updateCustomer(bank.getCustomers()[i]);
						CustomerUI.updateBalance(CustomerUI.balancetxt, bank.getCustomers()[i]);
						JOptionPane.showMessageDialog(null,"You have successfully deposited " + amount + "$");
					}
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				deposit.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				deposit.setBackground(new Color(17, 85, 112));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				deposit.setBackground(new Color(240, 240, 240));

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				deposit.setBackground(new Color(17, 85, 112));

			}
		});
		deposit.setBounds(388, 363, 97, 35);
		add(deposit);
		deposit.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("DEPOSIT");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(5, 8, 92, 21);
		deposit.add(lblNewLabel_2);
		
		JLabel lblNewLabel_6 = new JLabel("DEPOSIT");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel_6.setBounds(239, 10, 337, 50);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(PanelDeposit.class.getResource("/images/yasuo.png")));
		lblNewLabel_5.setBounds(-11, 32, 834, 58);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(PanelDeposit.class.getResource("/images/depositt.png")));
		lblNewLabel_7.setBounds(161, 8, 50, 50);
		add(lblNewLabel_7);
	}
}
