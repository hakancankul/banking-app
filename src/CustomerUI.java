import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerUI extends JFrame {

	public static JLabel welcomeLabel;
	public static JLabel balancetxt;
	private JPanel contentPane;
	private PanelHome PanelHome;
	private PanelMoneyTransfer PanelMoneyTransfer;
	private PanelDeposit PanelDeposit;
	private PanelWithdraw PanelWithdraw;
	private PanelSettings PanelSettings;

	public void menuClicked(JPanel panel) {
		PanelHome.setVisible(false);
		PanelMoneyTransfer.setVisible(false);
		PanelDeposit.setVisible(false);
		PanelWithdraw.setVisible(false);
		PanelSettings.setVisible(false);
		panel.setVisible(true);
	}

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

	public static void updateBalance(JLabel label, Customer customer) {
		label.setText(customer.getBalance() + " $");
	}

	public CustomerUI(String idNumber, Bank bank) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerUI.class.getResource("/images/logo_size_invert.jpg")));
		setFont(new Font("Arial", Font.BOLD, 13));
		setTitle("DEU BANK");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(17, 85, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		PanelHome = new PanelHome(idNumber, bank);
		PanelHome.setSize(829, 578);
		PanelHome.setLocation(20, 11);
		PanelMoneyTransfer = new PanelMoneyTransfer(idNumber, bank);
		PanelMoneyTransfer.setSize(829, 578);
		PanelMoneyTransfer.setLocation(20, 11);
		PanelDeposit = new PanelDeposit(idNumber, bank);
		PanelDeposit.setSize(829, 578);
		PanelDeposit.setLocation(20, 11);
		PanelWithdraw = new PanelWithdraw(idNumber, bank);
		PanelWithdraw.setSize(829, 578);
		PanelWithdraw.setLocation(20, 11);
		PanelSettings = new PanelSettings(idNumber, bank);
		PanelSettings.setSize(829, 578);
		PanelSettings.setLocation(20, 11);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(17, 85, 112));
		menuPanel.setBounds(0, 0, 288, 860);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		JPanel homeMenu = new JPanel();
		JPanel moneyTransferMenu = new JPanel();
		JPanel withdrawMenu = new JPanel();
		JPanel logoutMenu = new JPanel();
		JPanel depositMenu = new JPanel();
		JPanel settingsMenu = new JPanel();

		JLabel iconDeuBank = new JLabel("");
		iconDeuBank.setIcon(new ImageIcon(CustomerUI.class.getResource("/gif/sonaga.gif")));
		iconDeuBank.setBounds(59, 11, 219, 140);
		menuPanel.add(iconDeuBank);

		homeMenu.setForeground(Color.WHITE);
		homeMenu.setBackground(new Color(17, 85, 112));
		homeMenu.setBounds(0, 223, 288, 63);
		menuPanel.add(homeMenu);
		homeMenu.setLayout(null);

		JLabel txtHomeMenu = new JLabel("HOME");
		txtHomeMenu.setForeground(Color.WHITE);
		txtHomeMenu.setFont(new Font("Arial", Font.BOLD, 18));
		txtHomeMenu.setBounds(100, 20, 142, 25);
		homeMenu.add(txtHomeMenu);

		JLabel iconHomeMenu = new JLabel("");
		iconHomeMenu.setIcon(new ImageIcon(CustomerUI.class.getResource("/images/HOMME.png")));
		iconHomeMenu.setBounds(10, 7, 50, 50);
		homeMenu.add(iconHomeMenu);

		moneyTransferMenu.setLayout(null);
		moneyTransferMenu.setForeground(Color.WHITE);
		moneyTransferMenu.setBackground(new Color(17, 85, 112));
		moneyTransferMenu.setBounds(0, 285, 288, 64);
		menuPanel.add(moneyTransferMenu);

		JLabel txtMoneyTransferMenu = new JLabel("MONEY TRANSFER");
		txtMoneyTransferMenu.setForeground(Color.WHITE);
		txtMoneyTransferMenu.setFont(new Font("Arial", Font.BOLD, 18));
		txtMoneyTransferMenu.setBounds(100, 20, 178, 25);
		moneyTransferMenu.add(txtMoneyTransferMenu);

		JLabel iconMnoeyTransferMenu = new JLabel("");
		iconMnoeyTransferMenu.setBounds(10, -7, 63, 78);
		moneyTransferMenu.add(iconMnoeyTransferMenu);
		iconMnoeyTransferMenu.setIcon(new ImageIcon(CustomerUI.class.getResource("/images/flyingmooney.png")));

		depositMenu.setLayout(null);
		depositMenu.setForeground(Color.WHITE);
		depositMenu.setBackground(new Color(17, 85, 112));
		depositMenu.setBounds(0, 350, 288, 64);
		menuPanel.add(depositMenu);

		JLabel txtDepositMenu = new JLabel("DEPOSIT\r\n");
		txtDepositMenu.setForeground(Color.WHITE);
		txtDepositMenu.setFont(new Font("Arial", Font.BOLD, 18));
		txtDepositMenu.setBounds(100, 20, 142, 25);
		depositMenu.add(txtDepositMenu);

		JLabel iconDepositMenu = new JLabel("");
		iconDepositMenu.setBounds(10, -7, 63, 78);
		depositMenu.add(iconDepositMenu);
		iconDepositMenu.setIcon(new ImageIcon(CustomerUI.class.getResource("/images/depositt.png")));

		withdrawMenu.setLayout(null);
		withdrawMenu.setForeground(Color.WHITE);
		withdrawMenu.setBackground(new Color(17, 85, 112));
		withdrawMenu.setBounds(0, 415, 288, 64);
		menuPanel.add(withdrawMenu);

		JLabel txtWithdrawMenu = new JLabel("WITHDRAW");
		txtWithdrawMenu.setForeground(Color.WHITE);
		txtWithdrawMenu.setFont(new Font("Arial", Font.BOLD, 18));
		txtWithdrawMenu.setBounds(100, 20, 142, 25);
		withdrawMenu.add(txtWithdrawMenu);

		JLabel iconWithdrawMenu = new JLabel("");
		iconWithdrawMenu.setBounds(10, 0, 63, 61);
		withdrawMenu.add(iconWithdrawMenu);
		iconWithdrawMenu.setIcon(new ImageIcon(CustomerUI.class.getResource("/images/money-withdraw.png")));

		settingsMenu.setLayout(null);
		settingsMenu.setForeground(Color.WHITE);
		settingsMenu.setBackground(new Color(17, 85, 112));
		settingsMenu.setBounds(0, 480, 288, 64);
		menuPanel.add(settingsMenu);

		JLabel txtSettingsMenu = new JLabel("SETTINGS");
		txtSettingsMenu.setForeground(Color.WHITE);
		txtSettingsMenu.setFont(new Font("Arial", Font.BOLD, 18));
		txtSettingsMenu.setBounds(100, 20, 142, 25);
		settingsMenu.add(txtSettingsMenu);

		JLabel iconSettingsMenu = new JLabel("");
		iconSettingsMenu.setBounds(10, 0, 63, 61);
		settingsMenu.add(iconSettingsMenu);
		iconSettingsMenu.setIcon(new ImageIcon(CustomerUI.class.getResource("/images/setings.png")));

		logoutMenu.setLayout(null);
		logoutMenu.setForeground(Color.WHITE);
		logoutMenu.setBackground(new Color(17, 85, 112));
		logoutMenu.setBounds(0, 542, 288, 64);
		menuPanel.add(logoutMenu);

		JLabel txtLogoutMenu = new JLabel("LOG OUT");
		txtLogoutMenu.setForeground(Color.WHITE);
		txtLogoutMenu.setFont(new Font("Arial", Font.BOLD, 18));
		txtLogoutMenu.setBounds(100, 20, 142, 25);
		logoutMenu.add(txtLogoutMenu);

		JLabel iconLogoutMenu = new JLabel("");
		iconLogoutMenu.setBounds(10, 0, 63, 61);
		logoutMenu.add(iconLogoutMenu);
		iconLogoutMenu.setIcon(new ImageIcon(CustomerUI.class.getResource("/images/signout.png")));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(CustomerUI.class.getResource("/images/sadece deu bank.png")));
		lblNewLabel_1.setBounds(10, 0, 238, 184);
		menuPanel.add(lblNewLabel_1);

		ImageIcon imageIcon = new ImageIcon(CustomerUI.class.getResource("/images/transfer-money.png"));
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImageIcon = new ImageIcon(newimg);
		ImageIcon imageIcon2 = new ImageIcon(CustomerUI.class.getResource("/images/home.png"));
		Image image2 = imageIcon2.getImage();
		Image newimg2 = image2.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImageIcon2 = new ImageIcon(newimg2);

		homeMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				homeMenu.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				homeMenu.setBackground(new Color(17, 85, 112));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				homeMenu.setBackground(new Color(240, 240, 240));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				menuClicked(PanelHome);
				homeMenu.setBackground(new Color(17, 85, 112));

			}

		});

		moneyTransferMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				moneyTransferMenu.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				moneyTransferMenu.setBackground(new Color(17, 85, 112));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				moneyTransferMenu.setBackground(new Color(240, 240, 240));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				menuClicked(PanelMoneyTransfer);
				moneyTransferMenu.setBackground(new Color(17, 85, 112));

			}
		});

		depositMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				depositMenu.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				depositMenu.setBackground(new Color(17, 85, 112));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				depositMenu.setBackground(new Color(240, 240, 240));

			}

			public void mouseReleased(MouseEvent e) {

				menuClicked(PanelDeposit);
				depositMenu.setBackground(new Color(17, 85, 112));
			}
		});

		withdrawMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				withdrawMenu.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				withdrawMenu.setBackground(new Color(17, 85, 112));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				withdrawMenu.setBackground(new Color(240, 240, 240));

			}

			public void mouseReleased(MouseEvent e) {
				menuClicked(PanelWithdraw);
				withdrawMenu.setBackground(new Color(17, 85, 112));
			}
		});

		settingsMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				settingsMenu.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				settingsMenu.setBackground(new Color(17, 85, 112));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				settingsMenu.setBackground(new Color(240, 240, 240));

			}

			public void mouseReleased(MouseEvent e) {
				menuClicked(PanelSettings);
				settingsMenu.setBackground(new Color(17, 85, 112));

			}
		});

		logoutMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				logoutMenu.setBackground(new Color(58, 98, 115));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				logoutMenu.setBackground(new Color(17, 85, 112));
			}

			public void mousePressed(MouseEvent e) {
				logoutMenu.setBackground(new Color(240, 240, 240));

			}

			public void mouseReleased(MouseEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Do you really want to logout?","Log Out", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				if (choice == JOptionPane.YES_OPTION) 
				{
					System.exit(1);
				}
				logoutMenu.setBackground(new Color(17, 85, 112));
			}
		});

		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(new Color(58, 98, 115));
		infoPanel.setBounds(286, 0, 1060, 173);
		contentPane.add(infoPanel);
		infoPanel.setLayout(null);

		File myObj = new File("customerInfo.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(myObj);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String name = "";
		String surname = "";
		int balance = 0;
		while (scanner.hasNextLine()) {
			String[] info = scanner.nextLine().split(" ");
			if (idNumber.equals(info[0])) {
				name = info[2];
				surname = info[3];
				balance = Integer.parseInt(info[4]);
				break;
			}
		}

		JLabel welcomeLabel = new JLabel("Welcome,  " + name + " " + surname);
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setBackground(new Color(51, 204, 102));
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));
		welcomeLabel.setBounds(28, 22, 818, 60);
		infoPanel.add(welcomeLabel);

		SimpleDateFormat date1 = new SimpleDateFormat();
		Date date2 = new Date();
		JLabel dateLabel = new JLabel(date1.format(date2));
		dateLabel.setForeground(Color.WHITE);
		dateLabel.setFont(new Font("Arial", Font.BOLD, 20));
		dateLabel.setBounds(28, 119, 214, 30);
		infoPanel.add(dateLabel);

		balancetxt = new JLabel(balance + " $");
		balancetxt.setForeground(new Color(255, 255, 255));
		balancetxt.setFont(new Font("Arial", Font.BOLD, 25));
		balancetxt.setBounds(734, 69, 140, 49);
		infoPanel.add(balancetxt);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CustomerUI.class.getResource("/images/money-bag.png")));
		lblNewLabel.setBounds(677, 68, 50, 50);
		infoPanel.add(lblNewLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(147, 180, 203));
		mainPanel.setBounds(286, 171, 874, 600);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.add(PanelHome);
		mainPanel.add(PanelMoneyTransfer);
		PanelMoneyTransfer.setLayout(null);
		mainPanel.add(PanelDeposit);
		PanelDeposit.setLayout(null);
		mainPanel.add(PanelWithdraw);
		PanelWithdraw.setLayout(null);
		mainPanel.add(PanelSettings);
		PanelSettings.setLayout(null);

		menuClicked(PanelHome);

	}

}
