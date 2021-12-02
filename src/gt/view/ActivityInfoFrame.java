package gt.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gt.model.ActivityLoc;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

public class ActivityInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField DateTxt;
	private JTextField TimeTxt;
	private JTextField OrganizerTxt;
	private ActivityLoc activityLoc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityInfoFrame frame = new ActivityInfoFrame(null);
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
	public ActivityInfoFrame(ActivityLoc activityLoc) {
		
		this.activityLoc = activityLoc;
		setTitle("More Info");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 415);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel NameTxt = new JLabel("");
		NameTxt.setVerticalAlignment(SwingConstants.TOP);
		NameTxt.setHorizontalAlignment(SwingConstants.CENTER);
		NameTxt.setForeground(Color.BLACK);
		NameTxt.setFont(new Font("Dialog", Font.BOLD, 14));
		NameTxt.setBackground(Color.WHITE);
		NameTxt.setBounds(44, 0, 360, 47);
		NameTxt.setText("<html>"+activityLoc.getActivityName());
		contentPane.add(NameTxt);
		
		JButton btnNavigation = new JButton("Navigation");
		btnNavigation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigateActionPerformed(e);
			}
		});
		btnNavigation.setIcon(new ImageIcon(ActivityInfoFrame.class.getResource("/image/about.png")));
		btnNavigation.setHorizontalAlignment(SwingConstants.LEFT);
		btnNavigation.setBounds(66, 333, 130, 25);
		contentPane.add(btnNavigation);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gobackActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ActivityInfoFrame.class.getResource("/image/reset.png")));
		btnNewButton.setBounds(232, 333, 130, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(48, 59, 70, 15);
		contentPane.add(lblDate);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(48, 101, 70, 15);
		contentPane.add(lblTime);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setBounds(48, 191, 87, 15);
		contentPane.add(lblLocation);
		
		JLabel lblOrganizer = new JLabel("Organizer:");
		lblOrganizer.setBounds(48, 143, 87, 15);
		contentPane.add(lblOrganizer);
		
		JTextArea LocationTxt = new JTextArea();
		LocationTxt.setWrapStyleWord(true);
		LocationTxt.setLineWrap(true);
		LocationTxt.setBounds(134, 191, 251, 59);
		String fullLocation = "Floor " + String.valueOf(activityLoc.getFloor())+", "+ activityLoc.getHallName();
		LocationTxt.setText(fullLocation);
		contentPane.add(LocationTxt);
		
		DateTxt = new JTextField();
		DateTxt.setHorizontalAlignment(SwingConstants.LEFT);
		DateTxt.setBounds(112, 57, 150, 19);
		DateTxt.setBorder(null);
		DateTxt.setText(activityLoc.getBeginDate().toString());
		contentPane.add(DateTxt);
		DateTxt.setColumns(10);
		
		TimeTxt = new JTextField();
		TimeTxt.setHorizontalAlignment(SwingConstants.LEFT);
		TimeTxt.setColumns(10);
		TimeTxt.setBounds(112, 99, 181, 19);
		TimeTxt.setBorder(null);
		TimeTxt.setText(activityLoc.getBeginTime().toString()+" - "+activityLoc.getEndTime().toString());
		contentPane.add(TimeTxt);
		
		OrganizerTxt = new JTextField();
		OrganizerTxt.setHorizontalAlignment(SwingConstants.LEFT);
		OrganizerTxt.setColumns(10);
		OrganizerTxt.setBounds(134, 141, 251, 19);
		OrganizerTxt.setBorder(null);
		OrganizerTxt.setText(activityLoc.getOrganizer());
		contentPane.add(OrganizerTxt);
	}

	
	
	/*
	 *  handle the navigation event
	 */
	private void navigateActionPerformed(ActionEvent e) {
		// prepare for the navigation
		int answer = JOptionPane.showConfirmDialog(null,"Sure to ask the robot to take you there?" );
		if (answer == 0) {
			Navigation navigation = new Navigation(activityLoc);
//			System.out.println(activityLoc.getHallCoord());
			navigation.setLocationRelativeTo(null);
			navigation.setVisible(true);
			navigation.publishActivityCoord();
		}else {
			return;
		}
	}
	/*
	 *  handle the go back event
	 */
	private void gobackActionPerformed(ActionEvent e) {
		// close the window
		dispose();
		
	}
}
