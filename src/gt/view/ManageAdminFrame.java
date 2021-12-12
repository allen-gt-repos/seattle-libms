package gt.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
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
/**
 * Admin manage admin account page
 * @author Wang, Yinuo
 *
 */
public class ManageAdminFrame extends JInternalFrame {
	private JTextField emailTxt;
	private JTextField nameTxt;
	private JTextField userTxt;
	private User user = null;
	
	private DBUtil dbUtil = new DBUtil();
	private UserDao userDao = new UserDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageAdminFrame frame = new ManageAdminFrame(null);
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
	public ManageAdminFrame(User userInput) {
		
		this.user = userInput;
		setBounds(100, 100, 680, 401);
		getContentPane().setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAccountActionPerformed(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ManageAdminFrame.class.getResource("/image/modify.png")));
		btnNewButton_2.setBounds(120, 235, 117, 25);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Delete Account");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAdminActionPerformed(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(ManageAdminFrame.class.getResource("/image/delete.png")));
		btnNewButton_3.setBounds(337, 309, 193, 25);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ManageAdminFrame.class.getResource("/image/reset.png")));
		btnNewButton_1.setBounds(413, 235, 117, 25);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Username*:");
		lblNewLabel.setBounds(62, 117, 117, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(374, 119, 117, 15);
		getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel("Email*:");
		lblEmail.setBounds(62, 162, 117, 15);
		getContentPane().add(lblEmail);
		
		emailTxt = new JTextField();
		emailTxt.setColumns(10);
		emailTxt.setBounds(154, 160, 149, 19);
		emailTxt.setText(user.getEmail());
		getContentPane().add(emailTxt);
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(466, 117, 149, 19);
		nameTxt.setText(user.getName());
		getContentPane().add(nameTxt);
		
		userTxt = new JTextField();
		userTxt.setColumns(10);
		userTxt.setBounds(154, 115, 149, 19);
		userTxt.setText(user.getUserId());
		getContentPane().add(userTxt);
		
		JLabel lblNewLabel_2 = new JLabel("Manage My Account");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(233, 46, 225, 20);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Change Password");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePasswdActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ManageAdminFrame.class.getResource("/image/password.png")));
		btnNewButton.setBounds(121, 309, 187, 25);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_4 = new JButton("Refresh");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshActionPerformed(e);
			}
		});
		// resize the icon
		ImageIcon icon1 = new ImageIcon(ReaderFrame.class.getResource("/image/refresh.png"));
		Image img = icon1.getImage();
		Image newimg = img.getScaledInstance(20,20, Image.SCALE_DEFAULT); 
		btnNewButton_4.setIcon(new ImageIcon(newimg));
		btnNewButton_4.setBounds(267, 235, 117, 25);
		getContentPane().add(btnNewButton_4);
		
		JLabel lblUserTypeAdministrator = new JLabel("User Type:       Administrator");
		lblUserTypeAdministrator.setBounds(374, 162, 343, 15);
		getContentPane().add(lblUserTypeAdministrator);

	}

	/*
	 * update account info
	 */
	private void updateAccountActionPerformed(ActionEvent e) {
		
		Connection con = null;
		boolean usernameExisted=false;
		
		User newUser = new User();
		newUser.setUserId(userTxt.getText());
		newUser.setName(nameTxt.getText());
		newUser.setEmail(emailTxt.getText());
		
		try {
			con = dbUtil.getCon();
			 usernameExisted = userDao.searchExistedUsername(con, newUser.getUserId());
		}catch (Exception e1) {
			// TODO: handle exception
		}
		if (StringUtil.isEmpty(userTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Username cannot be empty.");
			
		}else if (StringUtil.isEmpty(emailTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Email address cannot be empty.");
	
		}else if (!StringUtil.isEmail(emailTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Invalid Email format.");
		}
		else if (usernameExisted && !(user.getUserId().equals(newUser.getUserId()))) {
			JOptionPane.showMessageDialog(null, "This username has been used by others, please try another one.");
		}
		else {

			try {
				con = dbUtil.getCon();
				int result = userDao.updateInfo(con, newUser,user.getUserId());
				
				if (result ==1) {
					JOptionPane.showMessageDialog(null, "Update this account info successfully!");
					user.setEmail(emailTxt.getText());
					user.setName(nameTxt.getText());
					user.setUserId(userTxt.getText());
				}else {
					JOptionPane.showMessageDialog(null, "Failed to update this account info.");
				}
			
			
			}catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to update this account info.");
			}finally {
				if (con!=null) {
					try {
						con.close();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
			
			}
		}
		
	}

	/*
	 * delete an admin account
	 */
	private void deleteAdminActionPerformed(ActionEvent e) {
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
				int answer = JOptionPane.showConfirmDialog(null,
						"Are you sure to delete this admin account?\n The administrator cannot login this system any more.");
				if (answer == 0) {
					int result = userDao.deleteUser(con, user);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Delete this account successfully!");
					} else {
						JOptionPane.showMessageDialog(null, "Failed to delete the account.");
					}
				}
			

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to delete the account.");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}

		}
		
		
	}

	/*
	 * change the account password
	 */
	private void changePasswdActionPerformed(ActionEvent e) {
		

		NewPasswordFrame newPasswordFrm = new NewPasswordFrame(user);
		newPasswordFrm.setLocationRelativeTo(null);
		newPasswordFrm.setVisible(true);
		
	}

	/*
	 * refresh the account info
	 */
	private void refreshActionPerformed(ActionEvent e) {
		
		Connection con =null;
		try {
			
			con = dbUtil.getCon();
			
			User newUser  = new User();
			
			newUser = userDao.getReaderInfo(con, user);
			nameTxt.setText(newUser.getName());
			userTxt.setText(newUser.getUserId());
			emailTxt.setText(newUser.getEmail());
		
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}

}
