package gt.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gt.model.User;

import javax.swing.JDesktopPane;
import java.awt.Toolkit;


public class AdminFrame extends JFrame {

	private JPanel contentPane;
	JDesktopPane desktopPane = new JDesktopPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame(null);
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
	public AdminFrame(User user) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminFrame.class.getResource("/image/spl3.png")));
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("SPL Admin Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 480);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Book Manage");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAddNewBook = new JMenuItem("Add New Book");
		mntmAddNewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewBookActionPerformed(e);
				
			}
		});
		mntmAddNewBook.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/add.png")));
		mnNewMenu.add(mntmAddNewBook);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Manage Existing Book");
		mntmNewMenuItem.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/edit.png")));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnActivityManage = new JMenu("Activity Manage");
		menuBar.add(mnActivityManage);
		
		JMenuItem mntmCreateNewActivity = new JMenuItem("Create New Activity");
		mntmCreateNewActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewActivityActionPerformed(e);
			}
		});
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
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewReaderActionPerformed(e);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem mntmEditExistingAccount = new JMenuItem("Edit Account");
		mnNewMenu_2.add(mntmEditExistingAccount);
		
		JMenu mnNewMenu_4 = new JMenu("Manage Admin Account");
		mnNewMenu_4.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/password.png")));
		mnUserManager.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Create Account");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewAdminActionPerformed(e);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Edit Account");
		mnNewMenu_4.add(mntmNewMenuItem_5);
		
		JMenu mnAboutUs = new JMenu("My Account");
		menuBar.add(mnAboutUs);
		
		JMenuItem mntmAddress = new JMenuItem("Account Info");
		mntmAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminInfoFrame adminInfoFrm = new AdminInfoFrame(user);
				adminInfoFrm.setLocation(10,10);
				adminInfoFrm.setVisible(true);
				desktopPane.add(adminInfoFrm);
			}
		});
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
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
		);
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 696, Short.MAX_VALUE)
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 410, Short.MAX_VALUE)
		);
		desktopPane.setLayout(gl_desktopPane);
		contentPane.setLayout(gl_contentPane);
	}

	/*
	 * Handle the add new administrator event
	 */
	private void addNewAdminActionPerformed(ActionEvent e) {
		
		AddNewUserFrame addUserFrm = new AddNewUserFrame(2);
		addUserFrm.setLocation(10,10);
		addUserFrm.setVisible(true);
		desktopPane.add(addUserFrm);
		
	}

	/*
	 * Handle the create new activity button
	 */	
	private void addNewActivityActionPerformed(ActionEvent e) {
		
		AddNewActFrame addNewActFrm = new AddNewActFrame();
		addNewActFrm.setLocation(10,10);
		addNewActFrm.setVisible(true);
		desktopPane.add(addNewActFrm);
		
	}

	/*
	 * Handle the create new reader button
	 */
	private void addNewReaderActionPerformed(ActionEvent e) {
		
		AddNewUserFrame addUserFrm = new AddNewUserFrame(1);
		addUserFrm.setLocation(10,10);
		addUserFrm.setVisible(true);
		desktopPane.add(addUserFrm);
	}

	/*
	 * Handle the create new book button
	 */
	private void addNewBookActionPerformed(ActionEvent e) {
		
		AddNewBookFrame addBookFrm = new AddNewBookFrame();
		addBookFrm.setLocation(10,10); //set relative location to desktop panel
		addBookFrm.setVisible(true);
		desktopPane.add(addBookFrm);
		
		
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



