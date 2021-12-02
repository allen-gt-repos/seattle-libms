package gt.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gt.model.ActivityLoc;
import gt.model.BookLoc;
import gt.util.LcmPublishUtil;
import gt.util.StringUtil;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Navigation extends JFrame {

	private JPanel contentPane;
	private BookLoc bookLoc;
	private ActivityLoc activityLoc;
	private Thread lcm_t;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Navigation frame = new Navigation();
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
	public Navigation() throws HeadlessException {
		super();
		// TODO 自动生成的构造函数存根
	}


	/**
	 * Create the frame for book
	 */
	public Navigation(BookLoc bookLoc) {
		
		this.bookLoc = bookLoc;
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
				try {
					lcm_t.interrupt();
				} finally {
					dispose();
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
	public Navigation(ActivityLoc activityLoc) {
		
		this.activityLoc = activityLoc;
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
				try {
					lcm_t.interrupt();
				} finally {
					dispose();
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
		
		float[] coordinate = StringUtil.getCoordinate(bookLoc.getBookShelfCoord());
		// create a new thread for lcm 
		lcm_t = new LcmPublishUtil(coordinate);
		lcm_t.start();
	}
	/*
	 * Publish destination through lcm
	 */
	public void publishActivityCoord() {
		
		float[] coordinate = StringUtil.getCoordinate(activityLoc.getHallCoord());
		// create a new thread for lcm 
		lcm_t = new LcmPublishUtil(coordinate);
		lcm_t.start();
	}
	
}
