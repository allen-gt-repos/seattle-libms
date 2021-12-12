package gt.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gt.dao.UserDao;
import gt.model.User;
import gt.util.DBUtil;
import gt.util.StringUtil;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
/**
 * Change password page
 */
public class NewPasswordFrame extends JFrame {

	private JPanel contentPane;
	private JTextField OldPwdTxt;
	private JTextField NewPwdTxt1;
	private JTextField NewPwdTxt2;
 
	private DBUtil dbUtil = new DBUtil();
	private UserDao userDao = new UserDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPasswordFrame frame = new NewPasswordFrame(null);
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
	public NewPasswordFrame(User user) {
		setTitle("Set New Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 379, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Old Password:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(38, 25, 171, 15);
		contentPane.add(lblNewLabel);
		
		OldPwdTxt = new JTextField();
		OldPwdTxt.setBounds(217, 23, 108, 19);
		contentPane.add(OldPwdTxt);
		OldPwdTxt.setColumns(10);
		
		NewPwdTxt1 = new JTextField();
		NewPwdTxt1.setColumns(10);
		NewPwdTxt1.setBounds(217, 54, 108, 19);
		contentPane.add(NewPwdTxt1);
		
		NewPwdTxt2 = new JTextField();
		NewPwdTxt2.setColumns(10);
		NewPwdTxt2.setBounds(217, 85, 108, 19);
		contentPane.add(NewPwdTxt2);
		
		JLabel lblNewPassword_1 = new JLabel("Enter New Password:");
		lblNewPassword_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPassword_1.setBounds(38, 52, 171, 15);
		contentPane.add(lblNewPassword_1);
		
		JLabel lblNewPassword = new JLabel("Confirm New Password:");
		lblNewPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewPassword.setBounds(38, 87, 171, 15);
		contentPane.add(lblNewPassword);
		
		JLabel lblNewLabel_1 = new JLabel("Your password need to have 4-8 characters");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(48, 70, 307, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtil.isEmpty(OldPwdTxt.getText())) {
					JOptionPane.showMessageDialog(null, "Old password cannot be empty, please try again.");
					return;
				}
				else if (StringUtil.isEmpty(NewPwdTxt1.getText())|| StringUtil.isEmpty(NewPwdTxt2.getText())) {
					JOptionPane.showMessageDialog(null, "New password cannot be empty, please try again.");
					return;
				}
				else if(!OldPwdTxt.getText().equals(user.getPassword())) {
					JOptionPane.showMessageDialog(null, "Your old password is wrong, please try again.");
					return;
				}
				else if (OldPwdTxt.getText().equals(NewPwdTxt1.getText())) {
					JOptionPane.showMessageDialog(null, "Your new password cannot be the same as the old one, please try again.");
					return;
				}
				else if (NewPwdTxt1.getText().length()>8 || NewPwdTxt1.getText().length()<4) {
					JOptionPane.showMessageDialog(null, "Your new password need to have 4-8 characters, please try again.");
					return;
				}
				else if (!NewPwdTxt1.getText().equals(NewPwdTxt2.getText())) {
					JOptionPane.showMessageDialog(null, "Your new password is not consistent, please try again.");
					return;
				}
				else {
					int Answer = JOptionPane.showConfirmDialog(null, "Sure to change your password?");
					if (Answer == 0) {
						Connection con = null;
						try 
						{
							con = dbUtil.getCon();
							int addResult = userDao.changePassword(con, user, NewPwdTxt2.getText());
							if(addResult ==1)
							{
								JOptionPane.showMessageDialog(null, "Your password has been updated!");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Failed to update your password.");
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
								dispose();
							} catch (Exception e1) {
								
								e1.printStackTrace();
							}
						}
						
					}
					else {
						dispose();
					}
				}
				
				
			}
		});
		btnNewButton.setBounds(200, 126, 117, 25);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(38, 126, 117, 25);
		contentPane.add(btnCancel);
	}


}
