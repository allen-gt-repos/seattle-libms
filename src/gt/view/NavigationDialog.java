package gt.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gt.model.ActivityLoc;
import gt.model.BookLoc;
import gt.util.LcmPublishUtil;
import gt.util.LcmSubscribeUtil;
import gt.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class NavigationDialog extends JFrame {

	private JPanel contentPane;
	private BookLoc bookLoc;
	private ActivityLoc activityLoc;
	private Thread lcm_pub_t, lcm_sub_t;
	public float[] roboState = {0,0,0,0};
	public float[] coordinate; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NavigationDialog frame = new NavigationDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * default constructor
	 */
	public NavigationDialog() throws HeadlessException {
		super();
		// TODO 自动生成的构造函数存根
	}


	/**
	 * Create the frame for book
	 */
	public NavigationDialog(BookLoc bookLoc) {
		
		this.bookLoc = bookLoc;
		coordinate = StringUtil.getCoordinate(bookLoc.getBookShelfCoord());
		setResizable(false);
		setTitle("Navigation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("The robot is palnning path ...");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(58, 26, 268, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblPleaseFollowThe = new JLabel("Please follow the robot");
		lblPleaseFollowThe.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPleaseFollowThe.setBounds(68, 53, 222, 15);
		contentPane.add(lblPleaseFollowThe);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null,"Are you sure to end the navigation?");
				if(answer == 0) {
					try {
						lcm_pub_t.interrupt();

						System.out.println("LCM close.");
					} finally {
						dispose();
					}
				}
				
			}
		});
		btnNewButton.setBounds(116, 116, 117, 25);
		contentPane.add(btnNewButton);	
		
	}
	/*
	 * Create the frame for activity
	 * 
	 */
	public NavigationDialog(ActivityLoc activityLoc) {
		
		this.activityLoc = activityLoc;
		coordinate = StringUtil.getCoordinate(activityLoc.getHallCoord());
		setResizable(false);
		setTitle("Navigation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("The robot is palnning path ...");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(58, 26, 268, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblPleaseFollowThe = new JLabel("Please follow the robot");
		lblPleaseFollowThe.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPleaseFollowThe.setBounds(68, 53, 222, 15);
		contentPane.add(lblPleaseFollowThe);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null,"Are you sure to end the navigation?");
				if(answer == 0) {
					try {
						lcm_pub_t.interrupt();
						System.out.println("LCM close.");
					} finally {
						dispose();
					}
				}
			}
		});
		btnNewButton.setBounds(116, 116, 117, 25);
		contentPane.add(btnNewButton);	

	}
	/*
	 * Publish destination through lcm
	 */
	public void publishBookCoord() {
		
		
		// create a new thread for lcm 
		lcm_pub_t = new LcmPublishUtil(coordinate);
		lcm_pub_t.start();

	}
	/*
	 * Publish destination through lcm
	 */
	public void publishActivityCoord() {
		
		
		// create a new thread for lcm 
		lcm_pub_t = new LcmPublishUtil(coordinate);
		lcm_pub_t.start();
	}
	
	
	/*
	 * Subscribe the robot state through lcm
	 */
	public void subscribeRobotState() {
		
//		lcm_sub_t = new LcmSubscribeUtil("RobotState",this);
//		lcm_sub_t.start();
		
	}
	
	public void endNav() throws InterruptedException {
		if (!lcm_pub_t.isInterrupted()) {
			lcm_pub_t.interrupt();
		}
		JOptionPane.showMessageDialog(null,"Robot has reach the destination!");
//		lcm_sub_t.interrupt();
		dispose();
		
	}
}
