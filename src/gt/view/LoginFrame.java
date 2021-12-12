package gt.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import gt.dao.UserDao;
import gt.model.User;
import gt.util.DBUtil;
import gt.util.StringUtil;
import javax.swing.JPasswordField;
/**
 * User login page
 * 
 * @author Wang, Yinuo
 *
 */
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private DBUtil dbUtil = new DBUtil();
	private UserDao userDao = new UserDao();
	private JPasswordField passwordField;
	
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/image/spl.png")));
		setResizable(false);
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("Seattle Public Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.highlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(159, 35, 161, 23);
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUsername.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/userName.png")));
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(159, 94, 161, 23);
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPassword.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/password.png")));
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		btnSignIn.setBounds(190, 158, 119, 33);
		btnSignIn.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/login.png")));
		btnSignIn.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JButton btnNewButton = new JButton("Create New Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerActionPerformed(e);
			}
		});
		btnNewButton.setBounds(270, 225, 150, 30);
		btnNewButton.setFont(new Font("Ubuntu", Font.BOLD, 11));
		
		JLabel lblNewToSeattle = new JLabel("New to Seattle Public Library?");
		lblNewToSeattle.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewToSeattle.setBounds(67, 230, 212, 18);
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(181, 120, 139, 26);
		contentPane.add(passwordField);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/spl3.png")));
		label.setBounds(12, 0, 131, 146);
		contentPane.add(label);
		

		
	}
	/*
	 * handle the create new account button event 
	 */
	private void registerActionPerformed(ActionEvent e) {
		
		dispose();
		RegisterFrame registerFrm = new RegisterFrame();
		registerFrm.setLocationRelativeTo(null);
		registerFrm.setVisible(true);
	}

	/*
	 * handle the login button event
	 */
	private void loginActionPerformed(ActionEvent e) {
		
		// get input
		String uid = this.userNameTxt.getText();
		String pass = new String(this.passwordField.getPassword());
	
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
				dispose();
				if(resultusUser.getUserType() == 2){
									
					// GO into the Administration page
					AdminFrame adminFrm = new AdminFrame(resultusUser);
					adminFrm.setLocationRelativeTo(null);
					adminFrm.setVisible(true);
				}
				else if (resultusUser.getUserType() == 1) {
					
					// GO into the Reader page
					ReaderFrame readerFrm = new ReaderFrame(resultusUser);
					readerFrm.setLocationRelativeTo(null);
					readerFrm.setVisible(true);
					
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Your username or password is wrong, please check and try again.");
				userNameTxt.setText("");
				passwordField.setText("");
			}
		} catch (Exception e1) {
			
			JOptionPane.showMessageDialog(null, "Failed to log in, please check and try again.");
			userNameTxt.setText("");
			passwordField.setText("");

		}finally {
			
			try {
				dbUtil.closeCon(con);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
	}
}
