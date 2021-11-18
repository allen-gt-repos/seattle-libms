package gt.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
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
	public AdminFrame() {
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("SPL Admin Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 354);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Book Manage");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAddNewBook = new JMenuItem("Add New Book");
		mntmAddNewBook.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/add.png")));
		mnNewMenu.add(mntmAddNewBook);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Manage Existing Book");
		mntmNewMenuItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/edit.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnActivityManage = new JMenu("Activity Manage");
		menuBar.add(mnActivityManage);
		
		JMenuItem mntmCreateNewActivity = new JMenuItem("Create New Activity");
		mntmCreateNewActivity.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/add.png")));
		mnActivityManage.add(mntmCreateNewActivity);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Manage Current Activity");
		mntmNewMenuItem_6.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/bookTypeManager.png")));
		mnActivityManage.add(mntmNewMenuItem_6);
		
		JMenu mnUserManager = new JMenu("User Manage");
		menuBar.add(mnUserManager);
		
		JMenu mnNewMenu_2 = new JMenu("Manage Reader Account");
		mnNewMenu_2.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/userName.png")));
		mnUserManager.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Create Account");
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem mntmEditExistingAccount = new JMenuItem("Edit Account");
		mnNewMenu_2.add(mntmEditExistingAccount);
		
		JMenu mnNewMenu_4 = new JMenu("Manage Admin Account");
		mnNewMenu_4.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/password.png")));
		mnUserManager.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Create Account");
		mnNewMenu_4.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Edit Account");
		mnNewMenu_4.add(mntmNewMenuItem_5);
		
		JMenu mnAboutUs = new JMenu("My Account");
		menuBar.add(mnAboutUs);
		
		JMenuItem mntmAddress = new JMenuItem("Account Info");
		mntmAddress.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/me.png")));
		mnAboutUs.add(mntmAddress);
		
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// double check the user answer
				int result = JOptionPane.showConfirmDialog(null,"Are you sure to log out?" );
				if(result == 0) {
					logoutActionPerformed(e);
				}
			}

		});
		mntmLogOut.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/reset.png")));
		mnAboutUs.add(mntmLogOut);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 622, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 378, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/*
	 * handle the log out button
	 * @param e
	 */
	private void logoutActionPerformed(ActionEvent e) {
		// dispose the old frame
		dispose();
		// open the new frame
		LoginFrame loginFrm = new LoginFrame();
		loginFrm.setLocationRelativeTo(null);
		loginFrm.setVisible(true);
	}

}



