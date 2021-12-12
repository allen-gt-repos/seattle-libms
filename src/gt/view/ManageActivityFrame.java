package gt.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gt.dao.ActivityDao;
import gt.model.Activity;
import gt.model.ActivityLoc;
import gt.model.Book;
import gt.model.BookLoc;
import gt.util.DBUtil;
import gt.util.StringUtil;
/**
 * Admin manage activity page
 * @author Wang, Yinuo
 *
 */
public class ManageActivityFrame extends JInternalFrame {
	private JTextField searchTxt;
	private JTextField searchDateTxt;
	private JTextField nameTxt;
	private JTextField organizerTxt;
	private JTextField dateTxt;
	private JTextField floorTxt;
	private JTextField hallTxt;
	private JTextField beginTxt;
	private JTextField endTxt;

	
	private DBUtil dbUtil = new DBUtil();
	private ActivityDao activityDao = new ActivityDao();
	private ActivityLoc activityLoc = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageActivityFrame frame = new ManageActivityFrame();
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
	public ManageActivityFrame() {
		setBounds(100, 100, 681, 400);
		getContentPane().setLayout(null);
		
		searchTxt = new JTextField();
		searchTxt.setColumns(10);
		searchTxt.setBounds(165, 25, 396, 24);
		getContentPane().add(searchTxt);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ManageActivityFrame.class.getResource("/image/search.png")));
		btnNewButton.setBounds(560, 25, 38, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblSearchActivity = new JLabel("Search Activity");
		lblSearchActivity.setBounds(52, 27, 117, 15);
		getContentPane().add(lblSearchActivity);
		
		JLabel lblNewLabel_2 = new JLabel("Please Enter Both Activity Name and Date");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(134, 77, 367, 15);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateActivityActionPerformed(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ManageActivityFrame.class.getResource("/image/modify.png")));
		btnNewButton_2.setBounds(52, 309, 117, 25);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Delete Book");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActivityActionPerformed(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(ManageActivityFrame.class.getResource("/image/delete.png")));
		btnNewButton_3.setBounds(256, 309, 140, 25);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ManageActivityFrame.class.getResource("/image/reset.png")));
		btnNewButton_1.setBounds(488, 309, 117, 25);
		getContentPane().add(btnNewButton_1);
		
		searchDateTxt = new JTextField("YYYY-MM-DD");
		searchDateTxt.setBounds(359, 61, 114, 19);
		getContentPane().add(searchDateTxt);
		searchDateTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Condition*: Activity Date");
		lblNewLabel.setBounds(124, 63, 249, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Acitivity Name*:");
		lblNewLabel_1.setBounds(52, 136, 114, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Organizer*: ");
		lblNewLabel_1_1.setBounds(52, 172, 114, 15);
		getContentPane().add(lblNewLabel_1_1);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(165, 134, 194, 19);
		getContentPane().add(nameTxt);
		nameTxt.setColumns(10);
		
		organizerTxt = new JTextField();
		organizerTxt.setColumns(10);
		organizerTxt.setBounds(165, 170, 194, 19);
		getContentPane().add(organizerTxt);
		
		dateTxt = new JTextField();
		dateTxt.setText("YYYY-MM-DD");
		dateTxt.setColumns(10);
		dateTxt.setBounds(453, 134, 145, 19);
		getContentPane().add(dateTxt);
		
		JLabel lblNewLabel_3_2_1_1 = new JLabel("Location*:");
		lblNewLabel_3_2_1_1.setBounds(52, 223, 101, 15);
		getContentPane().add(lblNewLabel_3_2_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Floor");
		lblNewLabel_4.setBounds(132, 223, 70, 15);
		getContentPane().add(lblNewLabel_4);
		
		floorTxt = new JTextField();
		floorTxt.setColumns(10);
		floorTxt.setBounds(184, 221, 88, 19);
		getContentPane().add(floorTxt);
		
		hallTxt = new JTextField();
		hallTxt.setColumns(10);
		hallTxt.setBounds(290, 221, 165, 19);
		getContentPane().add(hallTxt);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Time*:");
		lblNewLabel_1_1_1.setBounds(387, 172, 65, 15);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Date*: ");
		lblNewLabel_1_1_2.setBounds(387, 136, 65, 15);
		getContentPane().add(lblNewLabel_1_1_2);
		
		beginTxt = new JTextField("HH:MM:SS");
		beginTxt.setForeground(Color.BLACK);
		beginTxt.setColumns(10);
		beginTxt.setBounds(453, 170, 70, 19);
		getContentPane().add(beginTxt);
		
		endTxt = new JTextField("HH:MM:SS");
		endTxt.setForeground(Color.BLACK);
		endTxt.setColumns(10);
		endTxt.setBounds(535, 170, 67, 19);
		getContentPane().add(endTxt);
		
		JLabel lblEnd = new JLabel("-");
		lblEnd.setBounds(525, 172, 16, 15);
		getContentPane().add(lblEnd);

	}

	/*
	 * search activity
	 */
	private void searchActionPerformed(ActionEvent e) {
		

		Connection con = null;
		try {
			con = dbUtil.getCon();
			// search the activity
			if (StringUtil.isEmpty(searchTxt.getText()) || StringUtil.isEmpty(searchDateTxt.getText())) {
				JOptionPane.showMessageDialog(null, "You must specify both activity name and date.");
			}else if (!StringUtil.isValidDate(searchDateTxt.getText())) {
				JOptionPane.showMessageDialog(null, "You must specify a valid date.");
			}
			else 
			{
				activityLoc = activityDao.getActivityInfo(con, searchTxt.getText(),searchDateTxt.getText());
				if (activityLoc != null) {
					
					dateTxt.setText(activityLoc.getBeginDate().toString());
					nameTxt.setText(activityLoc.getActivityName());
					organizerTxt.setText(activityLoc.getOrganizer());
					beginTxt.setText(activityLoc.getBeginTime().toString());
					endTxt.setText(activityLoc.getEndTime().toString());
					floorTxt.setText(String.valueOf(activityLoc.getFloor()));
					hallTxt.setText(activityLoc.getHallName());
					searchTxt.setText("");
					searchDateTxt.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "Can not find activity matches "+searchTxt.getText()+"-"+searchDateTxt.getText()+".");
					searchTxt.setText("");
					searchDateTxt.setText("");
				}
			}
			
			
		}catch (Exception ex) {
		// TODO: handle exception
			ex.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	}

	/*
	 * Update the activity records
	 */
	private void updateActivityActionPerformed(ActionEvent e) {
		
		Connection con = null;
		int location_id = 0;
		// check the input location info
		try {
			con = dbUtil.getCon();
			ActivityLoc loc = new ActivityLoc();
			loc.setFloor(Integer.valueOf(floorTxt.getText()));
			loc.setHallName(hallTxt.getText());
			
			location_id = activityDao.checkValidLocation(con, loc);
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		if (StringUtil.isEmpty(nameTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Activity name cannot be empty.");
			
		}else if (StringUtil.isEmpty(organizerTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Organizer cannot be empty.");
			
		}else if (StringUtil.isEmpty(dateTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Activity date cannot be empty.");
			
		}else if (StringUtil.isEmpty(beginTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Activity begin time cannot be empty.");
			
		}else if (StringUtil.isEmpty(endTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Activity end time cannot be empty.");
			
		}else if (StringUtil.isEmpty(floorTxt.getText()) || StringUtil.isEmpty(hallTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Location info must be compelete.");
			
		}else if (location_id == 0) {
			JOptionPane.showMessageDialog(null, "Invalid location info.");
		}
		else {
			Activity activity = new Activity();
			activity.setLocationId(location_id);
			activity.setActivityName(nameTxt.getText());
			activity.setOrganizer(organizerTxt.getText());
			activity.setBeginDate(Date.valueOf(dateTxt.getText()));
			activity.setBeginTime(Time.valueOf(beginTxt.getText()));
			activity.setEndTime(Time.valueOf(endTxt.getText()));
			
			try {
				con = dbUtil.getCon();
				int result = activityDao.updateActivity(con, activity, activityLoc);
				
				if (result ==1) {
					JOptionPane.showMessageDialog(null, "Update this activity info successfully!");
				}else {
					JOptionPane.showMessageDialog(null, "Failed to update the activity.");
				}
			
			
			}catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to update the activity.");
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
	 * delete the activity records
	 */
	private void deleteActivityActionPerformed(ActionEvent e) {

		Connection con = null;
		try {
			con = dbUtil.getCon();
			Activity activity = new Activity();
			activity.setActivityName(nameTxt.getText());
			activity.setBeginDate(Date.valueOf(dateTxt.getText()));

			int answer = JOptionPane.showConfirmDialog(null,
					"Are you sure to delete this activity?\n All users cannot find it in system any more.");
			if (answer == 0) {
				int result = activityDao.deleteActivity(con, activity);
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "Delete this activity record successfully!");
				} else {
					JOptionPane.showMessageDialog(null, "Failed to delete the activity.");
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to delete the activity.");
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
}
