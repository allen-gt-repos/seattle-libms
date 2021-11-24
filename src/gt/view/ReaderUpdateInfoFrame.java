package gt.view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gt.dao.UserDao;
import gt.model.User;
import gt.util.DBUtil;
import gt.util.StringUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class ReaderUpdateInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField EmailTxt;
	private JTextField NameTxt;
	private JTextField UsernameTxt;

	private UserDao userDao = new UserDao();
	private DBUtil dbUtil = new DBUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderUpdateInfoFrame frame = new ReaderUpdateInfoFrame(null);
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
	public ReaderUpdateInfoFrame(User user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(68, 43, 110, 15);
		contentPane.add(lblUsername);
		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(68, 87, 110, 15);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("E-mail: ");
		lblEmail.setBounds(68, 133, 110, 15);
		contentPane.add(lblEmail);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con =null;
				try {
					con = dbUtil.getCon();
//					int existedNum = ;
					
					if (StringUtil.isEmpty(UsernameTxt.getText())) {
						JOptionPane.showMessageDialog(null, "Username cannot be empty.");
						return;
						
					}else if (StringUtil.isEmpty(EmailTxt.getText())) {
						JOptionPane.showMessageDialog(null, "Email address cannot be empty.");
						return;
						
					}else if (!StringUtil.isEmail(EmailTxt.getText())) {
						JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
						return;
						
					}else if (UsernameTxt.getText().equals(user.getUserId()) && EmailTxt.getText().equals(user.getEmail()) && NameTxt.getText().equals(user.getName())) {
						JOptionPane.showMessageDialog(null, "You didn't change any infomation.");
						return;
						
					}else if (!UsernameTxt.getText().equals(user.getUserId()) && userDao.searchExistedUsername(con, UsernameTxt.getText())) {
						JOptionPane.showMessageDialog(null, "This username has been used, please enter another one.");
						return;
						
					}else {
						
						String userOldId = user.getUserId();
						user.setEmail(EmailTxt.getText());
						user.setName(NameTxt.getText());
						user.setUserId(UsernameTxt.getText());;
						int result  = userDao.updateInfo(con, user, userOldId);
						if (result == 1) {
							JOptionPane.showMessageDialog(null, "Update your account info successfully, please login again to update your account info.");
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Failed to update your account info.");
							dispose();
						}
						
					}

				} catch (Exception e1) {
			
					e1.printStackTrace();
				}finally {
					if (con!=null) {
						try {
							dbUtil.closeCon(con);
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
					}
					
				}
				
				
				
			}
		});
		btnSubmit.setIcon(new ImageIcon(ReaderUpdateInfoFrame.class.getResource("/image/about.png")));
		btnSubmit.setBounds(82, 191, 117, 25);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setIcon(new ImageIcon(ReaderUpdateInfoFrame.class.getResource("/image/reset.png")));
		btnCancel.setBounds(236, 191, 117, 25);
		contentPane.add(btnCancel);
		
		EmailTxt = new JTextField();
		EmailTxt.setBounds(161, 131, 192, 19);
		EmailTxt.setText(user.getEmail());
		contentPane.add(EmailTxt);
		EmailTxt.setColumns(10);
		
		NameTxt = new JTextField();
		NameTxt.setBounds(161, 85, 192, 19);
		NameTxt.setText(user.getName());
		contentPane.add(NameTxt);
		NameTxt.setColumns(10);
		
		UsernameTxt = new JTextField();
		UsernameTxt.setBounds(161, 41, 192, 19);
		UsernameTxt.setText(user.getUserId());
		contentPane.add(UsernameTxt);
		UsernameTxt.setColumns(10);
	}

}
