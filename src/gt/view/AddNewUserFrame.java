package gt.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gt.dao.UserDao;
import gt.model.User;
import gt.util.DBUtil;
import gt.util.StringUtil;

public class AddNewUserFrame extends JInternalFrame {
	
	private JTextField UsernameTxt;
	private JTextField PasswordTxt;
	private JTextField NameTxt;
	private JTextField EmailTxt;
	private int UserType = 0;
	private int BorrowedCount = 0;
	
	private DBUtil dbUtil = new DBUtil();
	private UserDao userDao = new UserDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewUserFrame frame = new AddNewUserFrame(1);
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
	public AddNewUserFrame(int user_type) {
		//initialize user type
		UserType = user_type;
		
		setBounds(100, 100, 681, 400);
		
		UsernameTxt = new JTextField();
		UsernameTxt.setBounds(323, 48, 146, 19);
		UsernameTxt.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username*:");
		lblUsername.setBounds(201, 50, 90, 15);
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().setLayout(null);
		getContentPane().add(UsernameTxt);
		getContentPane().add(lblUsername);
		
		JLabel lblName = new JLabel("Password*:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(201, 93, 90, 15);
		getContentPane().add(lblName);
		
		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblName_1.setBounds(239, 143, 90, 15);
		getContentPane().add(lblName_1);
		
		JLabel lblEmail = new JLabel("E-mail*:");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setBounds(235, 191, 90, 15);
		getContentPane().add(lblEmail);
		
		PasswordTxt = new JTextField();
		PasswordTxt.setColumns(10);
		PasswordTxt.setBounds(323, 91, 146, 19);
		getContentPane().add(PasswordTxt);
		
		NameTxt = new JTextField();
		NameTxt.setColumns(10);
		NameTxt.setBounds(323, 139, 146, 19);
		getContentPane().add(NameTxt);
		
		EmailTxt = new JTextField();
		EmailTxt.setColumns(10);
		EmailTxt.setBounds(323, 188, 146, 19);
		getContentPane().add(EmailTxt);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitAdditionActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBounds(146, 282, 97, 36);
		getContentPane().add(btnNewButton);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		btnReset.setFont(new Font("Dialog", Font.BOLD, 14));
		btnReset.setBounds(293, 282, 97, 36);
		getContentPane().add(btnReset);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelActionPerformed(e);
				
			}
		});
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 14));
		btnCancel.setBounds(450, 283, 97, 35);
		getContentPane().add(btnCancel);

	}

	/*
	 * Handle the submit button event
	 */
	protected void submitAdditionActionPerformed(ActionEvent e) {
		 
		int confirmResult = JOptionPane.showConfirmDialog(null, "Confirm to submit?");
		if(confirmResult == 0)
		{
			//get the input
			String username = this.UsernameTxt.getText();
			String password = this.PasswordTxt.getText();
			String name = this.NameTxt.getText();
			String email = this.EmailTxt.getText();

			
			// check the input
			if(StringUtil.isEmpty(username)) {
				
				JOptionPane.showMessageDialog(null, "Username can not be empty");
				return;
			}
			else if(StringUtil.isEmpty(password)) {
				
				JOptionPane.showMessageDialog(null, "Password can not be empty");
				return;
			}
			else if(StringUtil.isEmpty(email)) {
				
				JOptionPane.showMessageDialog(null, "Email address can not be empty");
				return;
			}
			else if(password.length() < 4 || password.length() > 8) //password bits check
			{
				JOptionPane.showMessageDialog(null, "Password need to be 4-8 characters");
				return;
			}
			else if(!StringUtil.isEmail(email)) { // valid email address check
				
				JOptionPane.showMessageDialog(null, "Please input a valid Email address");
				return;
			}
			else {
				
				User newUser = new User(username,password,name,email,UserType,BorrowedCount);
				Connection con = null;
				try 
				{
					con = dbUtil.getCon();
					int addResult = userDao.addNewUser(con, newUser);
					if(addResult ==1)
					{
						JOptionPane.showMessageDialog(null, "A new user item has been added successfully!");
						this.resetValue();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Failed to add the new user item.");
						this.resetValue();
					}
				} 
				catch (Exception e1) 
				{
					
					e1.printStackTrace();
				}
				finally 
				{
					try {
						dbUtil.closeCon(con);
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	/*
	 * handle the cancel button event
	 */
	private void cancelActionPerformed(ActionEvent evt) {
		// dispose the old frame
		dispose();
	}
	
	/*
	 * handle the reset button event 
	 */
	private void resetActionPerformed(ActionEvent evt) {
		// reset all blanks
		this.resetValue();
	}
	/*
	 * reset all text frame
	 */
	private void resetValue() {
		
		this.UsernameTxt.setText("");
		this.PasswordTxt.setText("");
		this.NameTxt.setText("");
		this.EmailTxt.setText("");

	}
}
