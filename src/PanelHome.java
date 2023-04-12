

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class PanelHome extends JPanel {

	JLabel creditPanel = new JLabel();
	public PanelHome(String idNumber,Bank bank) {
		setBackground(new Color(147, 180, 203));
		setSize(854, 578);
		setLocation(20, 11);
		setLayout(null);
		for (int i = 0; i < bank.getCustomers().length; i++) {
			if (bank.getCustomers()[i]!= null && idNumber.equals(bank.getCustomers()[i].getIdentityNumber())) {
				creditPanel.setText("Your credit score: " + (double)bank.getCustomers()[i].getBalance()/25);
			}
		}
		
		creditPanel.setForeground(Color.WHITE);
		creditPanel.setFont(new Font("Arial", Font.BOLD, 40));
		creditPanel.setBounds(136, 34, 696, 126);
		add(creditPanel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(PanelHome.class.getResource("/images/receipt.png")));
		lblNewLabel_1.setBounds(46, 34, 111, 126);
		add(lblNewLabel_1);
		
		JLabel lblFinanceYourDream = new JLabel();
		lblFinanceYourDream.setText("Finance your dream home with us.");
		lblFinanceYourDream.setForeground(Color.WHITE);
		lblFinanceYourDream.setFont(new Font("Arial", Font.BOLD, 40));
		lblFinanceYourDream.setBounds(24, 199, 696, 126);
		add(lblFinanceYourDream);
		
		JLabel lblNewLabel_2 = new JLabel("<html>Whether you are buying,building or refinancing,<br/> we have a dedication team of <br/>trusted advisors to assist you every step of way.<html>");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel_2.setBounds(24, 281, 763, 185);
		add(lblNewLabel_2);
		
		JLabel teamlogo = new JLabel("");
		teamlogo.setIcon(new ImageIcon(PanelHome.class.getResource("/images/teamwork.png")));
		teamlogo.setBounds(679, 313, 204, 118);
		add(teamlogo);
		setVisible(true);
		
	}
}
