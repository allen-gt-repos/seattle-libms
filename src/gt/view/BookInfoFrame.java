package gt.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gt.model.BookLoc;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;

public class BookInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField AuthorTxt;
	private JTextField IsbnTxt;
	private JTextField PublisherTxt;
	private JTextField PubYearTxt;
	private JTextField AvailableTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookInfoFrame frame = new BookInfoFrame(null);
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
	public BookInfoFrame(BookLoc bookLoc) {
		setResizable(false);
		
		setTitle("More Info");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 415);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gobackActionPerformed(e);
		
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookInfoFrame.class.getResource("/image/reset.png")));
		btnNewButton.setBounds(234, 335, 130, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNavigation = new JButton("Navigation");
		btnNavigation.setHorizontalAlignment(SwingConstants.LEFT);
		btnNavigation.setIcon(new ImageIcon(BookInfoFrame.class.getResource("/image/about.png")));
		btnNavigation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigateActionPerformed(e);
			}
		});
		btnNavigation.setBounds(68, 335, 130, 25);
		contentPane.add(btnNavigation);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(48, 61, 70, 15);
		contentPane.add(lblAuthor);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(48, 88, 52, 15);
		contentPane.add(lblIsbn);
		
		JLabel lblNewLabel_1 = new JLabel("Subject:");
		lblNewLabel_1.setBounds(48, 169, 70, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPublisher = new JLabel("Publisher:");
		lblPublisher.setBounds(48, 115, 88, 15);
		contentPane.add(lblPublisher);
		
		JLabel lblPublicationYear = new JLabel("Publication Year:");
		lblPublicationYear.setBounds(48, 142, 140, 15);
		contentPane.add(lblPublicationYear);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setBounds(48, 241, 70, 15);
		contentPane.add(lblLocation);
		
		
		JLabel lblAvailableCount = new JLabel("Available Count:");
		lblAvailableCount.setBounds(48, 294, 121, 15);
		contentPane.add(lblAvailableCount);
		
		AuthorTxt = new JTextField();
		AuthorTxt.setHorizontalAlignment(SwingConstants.LEFT);
		AuthorTxt.setBounds(112, 59, 280, 19);
		AuthorTxt.setBorder(null);
		AuthorTxt.setText(bookLoc.getAuthor());
		contentPane.add(AuthorTxt);
		AuthorTxt.setColumns(10);
		
		IsbnTxt = new JTextField();
		IsbnTxt.setHorizontalAlignment(SwingConstants.LEFT);
		IsbnTxt.setColumns(10);
		IsbnTxt.setBounds(112, 86, 302, 19);
		IsbnTxt.setBorder(null);
		IsbnTxt.setText(bookLoc.getIsbn());
		contentPane.add(IsbnTxt);
		
		JTextArea SubjectTxt = new JTextArea();
		SubjectTxt.setWrapStyleWord(true);
		SubjectTxt.setEditable(false);
		SubjectTxt.setLineWrap(true);
		SubjectTxt.setBounds(112, 169, 302, 60);
		SubjectTxt.setBorder(null);
		SubjectTxt.setText(bookLoc.getSubject());
		contentPane.add(SubjectTxt);
		
		JTextArea LocationTxt = new JTextArea();
		LocationTxt.setWrapStyleWord(true);
		LocationTxt.setLineWrap(true);
		LocationTxt.setBounds(117, 241, 297, 41);
		LocationTxt.setBorder(null);
		String fullLocation = "Floor " + String.valueOf(bookLoc.getFloor())+", "+ bookLoc.getHallName()
				+", Bookself "+ String.valueOf(bookLoc.getBookshelf())+", Column "+String.valueOf(bookLoc.getColumn())
				+", Layer " + String.valueOf(bookLoc.getLayer());
		LocationTxt.setText(fullLocation);
		contentPane.add(LocationTxt);
		
		PublisherTxt = new JTextField();
		PublisherTxt.setHorizontalAlignment(SwingConstants.LEFT);
		PublisherTxt.setBackground(Color.WHITE);
		PublisherTxt.setEditable(false);
		PublisherTxt.setBounds(140, 114, 258, 17);
		PublisherTxt.setBorder(null);
		PublisherTxt.setText(bookLoc.getPublisher());
		contentPane.add(PublisherTxt);
		PublisherTxt.setColumns(10);
		
		PubYearTxt = new JTextField();
		PubYearTxt.setHorizontalAlignment(SwingConstants.LEFT);
		PubYearTxt.setBackground(Color.WHITE);
		PubYearTxt.setEditable(false);
		PubYearTxt.setBounds(191, 140, 207, 19);
		PubYearTxt.setBorder(null);
		PubYearTxt.setText(bookLoc.getPubYear());
		contentPane.add(PubYearTxt);
		PubYearTxt.setColumns(10);
		
		AvailableTxt = new JTextField();
		AvailableTxt.setText((String) null);
		AvailableTxt.setEditable(false);
		AvailableTxt.setColumns(10);
		AvailableTxt.setBorder(null);
		AvailableTxt.setBackground(Color.WHITE);
		AvailableTxt.setBounds(174, 292, 207, 19);
		AvailableTxt.setText(String.valueOf(bookLoc.getAvailableCount()));
		contentPane.add(AvailableTxt);
		
		JLabel TitleTxt = new JLabel("");
		TitleTxt.setBackground(Color.WHITE);
		TitleTxt.setForeground(Color.BLACK);
		TitleTxt.setVerticalAlignment(SwingConstants.TOP);
		TitleTxt.setHorizontalAlignment(SwingConstants.CENTER);
		TitleTxt.setFont(new Font("Dialog", Font.BOLD, 14));
		TitleTxt.setBounds(48, 10, 360, 47);
		TitleTxt.setText("<html>"+bookLoc.getTitle());
		contentPane.add(TitleTxt);
	}

	// handle the navigation event
	private void navigateActionPerformed(ActionEvent e) {
		// prepare for the navigation
		JOptionPane.showConfirmDialog(null,"Sure to ask the robot to take you there?" );
		
	}
	// handle the go back event
	private void gobackActionPerformed(ActionEvent e) {
		// close the window
		dispose();
		
	}
}
