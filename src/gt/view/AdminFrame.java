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
import javax.swing.JLabel;

/**
 * The main page for admin user
 * @author Wang, Yinuo
 *
 */
public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane = new JDesktopPane();
	private User loginUser = null;
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
		
		this.loginUser = user;
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
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageBookActionPerformed(e);
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Manage Recommend Book");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importRecommandBookActionPerformed(e);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/about.png")));
		mnNewMenu.add(mntmNewMenuItem_1);
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
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageActivityActionPerformed(e);
			}
		});
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
		
		JMenuItem mntmEditExistingAccount = new JMenuItem("Manage Account");
		mntmEditExistingAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageReaderActionPerformed(e);
			}
		});
		mnNewMenu_2.add(mntmEditExistingAccount);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Create Admin Account");
		mntmNewMenuItem_4.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/password.png")));
		mnUserManager.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewAdminActionPerformed(e);
			}
		});
		
		JMenu mnAboutUs = new JMenu("My Account");
		menuBar.add(mnAboutUs);
		
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
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Manage Account");
		mntmNewMenuItem_5.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/me.png")));
		mnAboutUs.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manageAdminActionPerformed(e);
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
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 12204, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 8, Short.MAX_VALUE)
		);
		desktopPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/spl.png")));
		lblNewLabel.setBounds(538, 254, 150, 153);
		desktopPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AdminFrame.class.getResource("/image/Picture1.png")));
		label.setBounds(0, 0, 700, 432);
		desktopPane.add(label);
		contentPane.setLayout(gl_contentPane);
	}
	private void importRecommandBookActionPerformed(ActionEvent e) {
		
		ManageRecommandFrame managerRecommandFrm = new ManageRecommandFrame();
		managerRecommandFrm.setLocation(10,10);
		managerRecommandFrm.setVisible(true);
		desktopPane.add(managerRecommandFrm);
		desktopPane.setComponentZOrder(managerRecommandFrm, 0);
	}

	/*
	 * Handle the manage reader account event
	 */
	private void manageReaderActionPerformed(ActionEvent e) {
	
		ManageReaderFrame manageReaderFrm = new ManageReaderFrame();
		manageReaderFrm.setLocation(10,10);
		manageReaderFrm.setVisible(true);
		desktopPane.add(manageReaderFrm);
		desktopPane.setComponentZOrder(manageReaderFrm, 0);
	}
	/*
	 * Handle the manage admin account event
	 */
	private void manageAdminActionPerformed(ActionEvent e) {
		
		ManageAdminFrame manageAdminFrm = new ManageAdminFrame(loginUser);
		manageAdminFrm.setLocation(10,10);
		manageAdminFrm.setVisible(true);
		desktopPane.add(manageAdminFrm);
		desktopPane.setComponentZOrder(manageAdminFrm, 0);
	}

	/*
	 * Handle the manage activity event
	 */
	private void manageActivityActionPerformed(ActionEvent e) {
		
		ManageActivityFrame manageActivityFrm = new ManageActivityFrame();
		manageActivityFrm.setLocation(10,10);
		manageActivityFrm.setVisible(true);
		desktopPane.add(manageActivityFrm);
		desktopPane.setComponentZOrder(manageActivityFrm, 0);
		
	}

	/*
	 * Handle the manage Book event
	 */
	private void manageBookActionPerformed(ActionEvent e) {
		
		ManageBookFrame manageBookFrm = new ManageBookFrame();
		manageBookFrm.setLocation(10,10);
		manageBookFrm.setVisible(true);
		desktopPane.add(manageBookFrm);
		desktopPane.setComponentZOrder(manageBookFrm, 0);
		
	}

	/*
	 * Handle the add new administrator event
	 */
	private void addNewAdminActionPerformed(ActionEvent e) {
		
		AddNewUserFrame addUserFrm = new AddNewUserFrame(2);
		addUserFrm.setLocation(10,10);
		addUserFrm.setVisible(true);
		desktopPane.add(addUserFrm);
		desktopPane.setComponentZOrder(addUserFrm, 0);
	}

	/*
	 * Handle the create new activity button
	 */	
	private void addNewActivityActionPerformed(ActionEvent e) {
		
		AddNewActFrame addNewActFrm = new AddNewActFrame();
		addNewActFrm.setLocation(10,10);
		addNewActFrm.setVisible(true);
		desktopPane.add(addNewActFrm);
		desktopPane.setComponentZOrder(addNewActFrm, 0);
	}

	/*
	 * Handle the create new reader button
	 */
	private void addNewReaderActionPerformed(ActionEvent e) {
		
		AddNewUserFrame addUserFrm = new AddNewUserFrame(1);
		addUserFrm.setLocation(10,10);
		addUserFrm.setVisible(true);
		desktopPane.add(addUserFrm);
		desktopPane.setComponentZOrder(addUserFrm, 0);
	}

	/*
	 * Handle the create new book button
	 */
	private void addNewBookActionPerformed(ActionEvent e) {
		
		AddNewBookFrame addBookFrm = new AddNewBookFrame();
		addBookFrm.setLocation(10,10); //set relative location to desktop panel
		addBookFrm.setVisible(true);
		desktopPane.add(addBookFrm);
		desktopPane.setComponentZOrder(addBookFrm, 0);
		
	}

	/*
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



