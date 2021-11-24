package gt.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import gt.model.User;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdminInfoFrame extends JInternalFrame {
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminInfoFrame frame = new AdminInfoFrame(null);
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
	public AdminInfoFrame(User user) {
		getContentPane().setBackground(Color.WHITE);
		setEnabled(false);
		setBounds(100, 100, 680, 400);
		getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel();
		lblUsername.setBounds(75, 46, 343, 33);
		lblUsername.setText("Username: "+ user.getUserId());
		getContentPane().add(lblUsername);
		
		JLabel lblName = new JLabel();
		lblName.setBounds(75, 100, 343, 15);
		lblName.setText("Name: "+ user.getName());
		getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel();
		lblEmail.setBounds(75, 144, 329, 15);
		lblEmail.setText("E-mail: " + user.getEmail());
		getContentPane().add(lblEmail);
		
		JLabel lblType = new JLabel("User Type: Administrator");
		lblType.setBounds(75, 193, 343, 15);
		getContentPane().add(lblType);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setIcon(new ImageIcon(AdminInfoFrame.class.getResource("/image/delete.png")));
		btnClose.setBounds(256, 272, 117, 25);
		getContentPane().add(btnClose);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AdminInfoFrame.class.getResource("/image/spl3.png")));
		label.setBounds(521, 37, 137, 135);
		getContentPane().add(label);

	}

}
