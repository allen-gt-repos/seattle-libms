package gt.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gt.dao.UserDao;
import gt.model.User;
import gt.util.DBUtil;
import gt.util.StringUtil;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Button;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JTextField passwordTxt;
	private DBUtil dbUtil = new DBUtil();
	private UserDao userDao = new UserDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/image/about.png")));
		setResizable(false);
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("Seattle Public Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.highlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(159, 35, 104, 23);
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUsername.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/userName.png")));
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(159, 94, 100, 23);
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPassword.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/password.png")));
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		btnSignIn.setBounds(190, 158, 105, 33);
		btnSignIn.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/login.png")));
		btnSignIn.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JButton btnNewButton = new JButton("Create New Account");
		btnNewButton.setBounds(292, 228, 131, 25);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 10));
		
		JLabel lblNewToSeattle = new JLabel("New to Seattle Public Library?");
		lblNewToSeattle.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewToSeattle.setBounds(83, 231, 212, 18);
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		contentPane.add(lblNewToSeattle);
		contentPane.add(lblUsername);
		contentPane.add(lblPassword);
		contentPane.add(btnSignIn);
		
		userNameTxt = new JTextField();
		userNameTxt.setBounds(180, 60, 140, 22);
		contentPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		passwordTxt = new JTextField();
		passwordTxt.setBounds(180, 115, 140, 22);
		contentPane.add(passwordTxt);
		passwordTxt.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/spl3.png")));
		label.setBounds(12, 0, 131, 146);
		contentPane.add(label);
		
	}

/*
 * handle the login button event
 */
	private void loginActionPerformed(ActionEvent e) {
		
		// get input
		String uid = this.userNameTxt.getText();
		String pass = this.passwordTxt.getText();
		// check input
		if(StringUtil.isEmpty(uid)) 
		{
			JOptionPane.showMessageDialog(null, "Username cannot be empty, please try again.");
			return;
		}
		else if(StringUtil.isEmpty(pass)) 
		{
			JOptionPane.showMessageDialog(null, "Password cannot be empty, please try again.");
			return;
		}
		// validate input
		User user = new User(uid,pass);
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			User resultusUser = userDao.login(con,user);
			if(resultusUser!=null)
			{
//				JOptionPane.showMessageDialog(null, "Successfully login!");
				dispose();
				// GO into the Administration page
				AdminFrame adminFrm = new AdminFrame();
				adminFrm.setLocationRelativeTo(null);
				adminFrm.setVisible(true);
				
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Your username or password is wrong, please check and try again.");

			}
		} catch (Exception e1) {
			
			JOptionPane.showMessageDialog(null, "Your username or password is wrong, please check and try again.");
			e1.printStackTrace();
		}
	}
	
	
	
	
}
