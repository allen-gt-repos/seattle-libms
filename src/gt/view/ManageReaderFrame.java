package gt.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import gt.dao.UserDao;
import gt.model.Book;
import gt.model.BookLoc;
import gt.model.User;
import gt.util.DBUtil;
import gt.util.StringUtil;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ManageReaderFrame extends JInternalFrame {
	private JTextField searchTxt;
	private JTextField userTxt;
	private JTextField nameTxt;
	private JTextField emailTxt;
	private JTextField borrowTxt;
	private JTextField passwordTxt;

	
	DBUtil dbUtil = new DBUtil();
	UserDao userDao = new UserDao();
	User user = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageReaderFrame frame = new ManageReaderFrame();
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
	public ManageReaderFrame() {
		setBounds(100, 100, 681, 400);
		getContentPane().setLayout(null);
		
		JLabel lblSearchReader = new JLabel("Search Reader");
		lblSearchReader.setBounds(56, 29, 104, 15);
		getContentPane().add(lblSearchReader);
		
		searchTxt = new JTextField();
		searchTxt.setColumns(10);
		searchTxt.setBounds(173, 27, 392, 24);
		getContentPane().add(searchTxt);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ManageReaderFrame.class.getResource("/image/search.png")));
		btnNewButton.setBounds(564, 27, 38, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Please Enter Username");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(159, 49, 367, 15);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUserActionPerformed(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ManageReaderFrame.class.getResource("/image/modify.png")));
		btnNewButton_2.setBounds(56, 311, 117, 25);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Delete Reader");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUserActionPerformed(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(ManageReaderFrame.class.getResource("/image/delete.png")));
		btnNewButton_3.setBounds(260, 311, 156, 25);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ManageReaderFrame.class.getResource("/image/reset.png")));
		btnNewButton_1.setBounds(492, 311, 117, 25);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Username*:");
		lblNewLabel.setBounds(56, 118, 117, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(56, 161, 117, 15);
		getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel("Email*:");
		lblEmail.setBounds(56, 209, 117, 15);
		getContentPane().add(lblEmail);
		
		userTxt = new JTextField();
		userTxt.setBounds(148, 116, 149, 19);
		getContentPane().add(userTxt);
		userTxt.setColumns(10);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(148, 159, 149, 19);
		getContentPane().add(nameTxt);
		nameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Borrowed Count*:");
		lblNewLabel_1.setBounds(344, 165, 141, 15);
		getContentPane().add(lblNewLabel_1);
		
		emailTxt = new JTextField();
		emailTxt.setColumns(10);
		emailTxt.setBounds(148, 207, 149, 19);
		getContentPane().add(emailTxt);
		
		borrowTxt = new JTextField();
		borrowTxt.setColumns(10);
		borrowTxt.setBounds(487, 161, 98, 19);
		getContentPane().add(borrowTxt);
		
		JLabel lblNewLabel_3 = new JLabel("Password*:");
		lblNewLabel_3.setBounds(346, 118, 89, 15);
		getContentPane().add(lblNewLabel_3);
		
		passwordTxt = new JTextField();
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(436, 116, 149, 19);
		getContentPane().add(passwordTxt);

	}

	/*
	 * search the reader account
	 */
	private void searchActionPerformed(ActionEvent e) {
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			// search the book
			if (StringUtil.isNotEmpty(searchTxt.getText())) {
				user = userDao.searchUser(con, searchTxt.getText());
				if (user != null) {

					userTxt.setText(user.getUserId());
					nameTxt.setText(user.getName());
					passwordTxt.setText(user.getPassword());
					borrowTxt.setText(String.valueOf(user.getBorrowedCount()));
					emailTxt.setText(user.getEmail());

					searchTxt.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Can not find reader matches " + searchTxt.getText() + ".");
					searchTxt.setText("");
				}
			}

		} catch (Exception ex) {
		// TODO: handle exception
			ex.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				
			}
		}
		
	}

	/*
	 * update the reader account info
	 */
	private void deleteUserActionPerformed(ActionEvent e) {

		Connection con = null;
		try {
			con = dbUtil.getCon();
			boolean allReturn = userDao.checkAllReturn(con, user);
			if (!allReturn) {
				JOptionPane.showMessageDialog(null,
						"Cannot delete this account since some books haven't been returned.");
			} else {
				int answer = JOptionPane.showConfirmDialog(null,
						"Are you sure to delete this reader account?\n The user cannot login this system any more, \nand all recommend and borrow records will also be deleted.");
				if (answer == 0) {
					int result = userDao.deleteUser(con, user);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Delete this reader account successfully!");
					} else {
						JOptionPane.showMessageDialog(null, "Failed to delete the reader account.");
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to delete the reader account.");
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
	 * update the reader account by admin
	 */
	private void updateUserActionPerformed(ActionEvent e) {
		
		Connection con = null;
		boolean usernameExisted=false;
		
		User newUser = new User();
		newUser.setUserId(userTxt.getText());
		newUser.setName(nameTxt.getText());
		newUser.setEmail(emailTxt.getText());
		newUser.setPassword(passwordTxt.getText());
		newUser.setBorrowedCount(Integer.valueOf(borrowTxt.getText()));
		
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
			
		}else if (StringUtil.isEmpty(passwordTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Password cannot be empty.");
			
		}else if (StringUtil.isEmpty(borrowTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Borrowed count cannot be empty.");
			
		}else if (!StringUtil.isEmail(emailTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Invalid Email format.");
		}
		else if (passwordTxt.getText().length()>8 || passwordTxt.getText().length()<4) {
			JOptionPane.showMessageDialog(null, "Password needs to be 4-8 chracters or digits");
		}
		else if (usernameExisted && !(user.getUserId().equals(newUser.getUserId()))) {
			JOptionPane.showMessageDialog(null, "This username has been used by others, please try another one.");
		}
		else {

			
			try {
				con = dbUtil.getCon();
				int result = userDao.updateReader(con, newUser,user.getUserId());
				
				if (result ==1) {
					JOptionPane.showMessageDialog(null, "Update this reader's account info successfully!");
				}else {
					JOptionPane.showMessageDialog(null, "Failed to update this reader's account info.");
				}
			
			
			}catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to update this reader's account info.");
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

}
