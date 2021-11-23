package gt.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gt.dao.ActivityDao;
import gt.model.Activity;
import gt.util.DBUtil;
import gt.util.StringUtil;

public class AddNewActFrame extends JInternalFrame {
	private JTextField ActivityTxt;
	private JTextField OrganizerTxt;
	private JTextField LocationTxt;
	private JTextField BeginDateTxt = new JTextField("YYYY-MM-DD");
	private JTextField BeginTimeTxt = new JTextField("HH:MM:SS");
	private JTextField EndTimeTxt = new JTextField("HH:MM:SS");

	
	DBUtil dbUtil = new DBUtil();
	ActivityDao activityDao = new ActivityDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewActFrame frame = new AddNewActFrame();
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
	public AddNewActFrame() {
		setBounds(100, 100, 681, 400);
		getContentPane().setLayout(null);
		
		JLabel lblActivityName = new JLabel("Activity Name*:");
		lblActivityName.setBounds(170, 53, 110, 15);
		getContentPane().add(lblActivityName);
		
		ActivityTxt = new JTextField();
		ActivityTxt.setBounds(323, 51, 149, 19);
		getContentPane().add(ActivityTxt);
		ActivityTxt.setColumns(10);
		
		JLabel lblOrgnaizer = new JLabel("Organizer:");
		lblOrgnaizer.setBounds(195, 93, 110, 15);
		getContentPane().add(lblOrgnaizer);
		
		JLabel lblLocation = new JLabel("Location*:");
		lblLocation.setBounds(205, 131, 110, 15);
		getContentPane().add(lblLocation);
		
		JLabel lblBegin = new JLabel("Date*:");
		lblBegin.setBounds(233, 172, 110, 15);
		getContentPane().add(lblBegin);
		
		JLabel lblEnd = new JLabel("-");
		lblEnd.setBounds(396, 216, 16, 15);
		getContentPane().add(lblEnd);
		
		OrganizerTxt = new JTextField();
		OrganizerTxt.setColumns(10);
		OrganizerTxt.setBounds(323, 91, 149, 19);
		getContentPane().add(OrganizerTxt);
		
		LocationTxt = new JTextField();
		LocationTxt.setColumns(10);
		LocationTxt.setBounds(323, 129, 149, 19);
		getContentPane().add(LocationTxt);
		
//		BeginDateTxt = new JTextField();
		BeginDateTxt.setForeground(Color.BLACK);
//		BeginDateTxt.setText("YYYY-MM-DD");
		BeginDateTxt.setColumns(10);
		BeginDateTxt.setBounds(323, 172, 149, 19);
		getContentPane().add(BeginDateTxt);
		
//		BeginTimeTxt = new JTextField();
		BeginTimeTxt.setForeground(Color.BLACK);
//		BeginTimeTxt.setText("HH:MM:SS");
		BeginTimeTxt.setColumns(10);
		BeginTimeTxt.setBounds(323, 214, 70, 19);
		getContentPane().add(BeginTimeTxt);
		
//		EndTimeTxt = new JTextField();
		EndTimeTxt.setForeground(Color.BLACK);
//		EndTimeTxt.setText("HH:MM:SS");
		EndTimeTxt.setColumns(10);
		EndTimeTxt.setBounds(405, 214, 67, 19);
		getContentPane().add(EndTimeTxt);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewActivityActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBounds(146, 282, 97, 36);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_1.setBounds(293, 282, 97, 36);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelActionPerformed(e);
			}
		});
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton_2.setBounds(442, 282, 97, 36);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblTime = new JLabel("Time*:");
		lblTime.setBounds(234, 216, 110, 15);
		getContentPane().add(lblTime);

	}
	/*
	 * Handle the submit button event
	 */
	protected void addNewActivityActionPerformed(ActionEvent e) {
		
		int confirmResult = JOptionPane.showConfirmDialog(null, "Confirm to submit?");
		if(confirmResult == 0)
		{
			//get the input
			String activityName = this.ActivityTxt.getText();
			String organizer = this.OrganizerTxt.getText();
			String location = this.LocationTxt.getText();
			String begDateStr = this.BeginDateTxt.getText();
			String begTimeStr = this.BeginTimeTxt.getText();
			String endTimeStr = this.EndTimeTxt.getText();
			Date begDate;
			Time begTime, endTime;

	
//			System.out.println(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())));
			
			// check the input
			if(StringUtil.isEmpty(activityName)) {
				
				JOptionPane.showMessageDialog(null, "Activity name can not be empty");
				return;
			}
			else if(StringUtil.isEmpty(location)) {
				
				JOptionPane.showMessageDialog(null, "Location can not be empty");
				return;
			}
			else if(StringUtil.isEmpty(begDateStr) || begDateStr == "YYYY-MM-DD") {
				
				JOptionPane.showMessageDialog(null, "Activity date can not be empty");
				return;
			}
			else if(StringUtil.isEmpty(begTimeStr) || begTimeStr == "HH:MM:SS") {
				
				JOptionPane.showMessageDialog(null, "Begin time Count can not be empty");
				return;
			}
			else if(StringUtil.isEmpty(endTimeStr) || endTimeStr == "HH:MM:SS") {
				
				JOptionPane.showMessageDialog(null, "End time Count can not be empty");
				return;
			}
			else {
				//check the date and time format
				try {
					begDate = Date.valueOf(this.BeginDateTxt.getText());
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "The date input cannot be recognized");
					resetValue();
					return;
				}
				try {
					begTime = Time.valueOf(this.BeginTimeTxt.getText());	
					endTime = Time.valueOf(this.EndTimeTxt.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "The time input cannot be recognized");
					resetValue();
					return;
				}
				
				if(begDate.before(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())))) {
					JOptionPane.showMessageDialog(null, "Activity date cannot be early than today");
					resetValue();
					return;
				}
				else if(endTime.before(begTime) || endTime.equals(begTime)) {
					JOptionPane.showMessageDialog(null, "End time cannot be early than begin time");
					resetValue();
					return;
				}
				
				// update the database
				Activity newActivity = new Activity(activityName, organizer,begDate,begTime,endTime,Integer.valueOf(location));

				Connection con = null;
				try {
					con = dbUtil.getCon();
					int addResult = activityDao.addNewBook(con, newActivity);
					if(addResult ==1)
					{
						JOptionPane.showMessageDialog(null, "A new activity item has been added successfully!");
						this.resetValue();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Failed to add the new activity item.");
						this.resetValue();
					}
				} catch (Exception e1) {
					
					e1.printStackTrace();
					
				}finally {
					try {
						
						dbUtil.closeCon(con);
						
					} catch (Exception e1) {
					
						e1.printStackTrace();
					}
					
				}
				
			}
		}
	}

	/*
	 * handle the cancel button event
	 */
	private void cancelActionPerformed(ActionEvent evt) {
		// dispose the old frame
		dispose();
	}
	/*
	 * handle the reset button event 
	 */
	private void resetActionPerformed(ActionEvent evt) {
		// reset all blanks
		this.resetValue();
	}
	/*
	 * reset all text frame
	 */
	private void resetValue() {
		
		this.ActivityTxt.setText("");
		this.OrganizerTxt.setText("");
		this.LocationTxt.setText("");
		this.BeginDateTxt.setText("YYYY-MM-DD");
		this.BeginTimeTxt.setText("HH:MM:SS");
		this.EndTimeTxt.setText("HH:MM:SS");

	}
}
