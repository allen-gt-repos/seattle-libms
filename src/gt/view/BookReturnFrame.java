
package gt.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gt.dao.BookDao;
import gt.dao.UserDao;
import gt.model.BookLog;
import gt.model.User;
import gt.util.DBUtil;

public class BookReturnFrame extends JFrame {

	private JPanel contentPane;
	private JTextField AuthorTxt;
	private JTextField IsbnTxt;
	private JTextField PublisherTxt;
	private JTextField PubYearTxt;

	private UserDao userDao = new UserDao();
	private BookDao bookDao = new BookDao();
	private DBUtil dbUtil = new DBUtil();
	
	private BookLog bookLog = null;
	private User currentUser = null;
	private Date expectedReturnDate = null;
	
	private JTextField DateTxt;
	private JTextField ExpectedDateTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookReturnFrame frame = new BookReturnFrame(null,null);
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
	public BookReturnFrame(BookLog bookLogInput, User userInput) {
		
		// set book location user
		this.bookLog = bookLogInput;
		this.currentUser = userInput;
		
		
		setResizable(false);
		
		setTitle("Return Book");
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
		btnNewButton.setBounds(235, 335, 120, 25);
		contentPane.add(btnNewButton);

		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(48, 61, 70, 15);
		contentPane.add(lblAuthor);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(48, 88, 52, 15);
		contentPane.add(lblIsbn);
		
		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setBounds(48, 169, 70, 15);
		contentPane.add(lblSubject);
		
		JLabel lblPublisher = new JLabel("Publisher:");
		lblPublisher.setBounds(48, 115, 88, 15);
		contentPane.add(lblPublisher);
		
		JLabel lblPublicationYear = new JLabel("Publication Year:");
		lblPublicationYear.setBounds(48, 142, 140, 15);
		contentPane.add(lblPublicationYear);
		
		JLabel lblBorrowDate = new JLabel("Borrow Date:");
		lblBorrowDate.setBounds(48, 241, 103, 15);
		contentPane.add(lblBorrowDate);
		
		AuthorTxt = new JTextField();
		AuthorTxt.setHorizontalAlignment(SwingConstants.LEFT);
		AuthorTxt.setBounds(112, 59, 280, 19);
		AuthorTxt.setBorder(null);
		AuthorTxt.setText(bookLog.getAuthor());
		contentPane.add(AuthorTxt);
		AuthorTxt.setColumns(10);
		
		IsbnTxt = new JTextField();
		IsbnTxt.setHorizontalAlignment(SwingConstants.LEFT);
		IsbnTxt.setColumns(10);
		IsbnTxt.setBounds(112, 86, 302, 19);
		IsbnTxt.setBorder(null);
		IsbnTxt.setText(bookLog.getIsbn());
		contentPane.add(IsbnTxt);
		
		JTextArea SubjectTxt = new JTextArea();
		SubjectTxt.setWrapStyleWord(true);
		SubjectTxt.setEditable(false);
		SubjectTxt.setLineWrap(true);
		SubjectTxt.setBounds(112, 169, 302, 60);
		SubjectTxt.setBorder(null);
		SubjectTxt.setText(bookLog.getSubject());
		contentPane.add(SubjectTxt);
		
		PublisherTxt = new JTextField();
		PublisherTxt.setHorizontalAlignment(SwingConstants.LEFT);
		PublisherTxt.setBackground(Color.WHITE);
		PublisherTxt.setEditable(false);
		PublisherTxt.setBounds(140, 114, 258, 17);
		PublisherTxt.setBorder(null);
		PublisherTxt.setText(bookLog.getPublisher());
		contentPane.add(PublisherTxt);
		PublisherTxt.setColumns(10);
		
		PubYearTxt = new JTextField();
		PubYearTxt.setHorizontalAlignment(SwingConstants.LEFT);
		PubYearTxt.setBackground(Color.WHITE);
		PubYearTxt.setEditable(false);
		PubYearTxt.setBounds(191, 140, 207, 19);
		PubYearTxt.setBorder(null);
		PubYearTxt.setText(bookLog.getPubYear());
		contentPane.add(PubYearTxt);
		PubYearTxt.setColumns(10);
		
		DateTxt = new JTextField();
		DateTxt.setText((String) null);
		DateTxt.setHorizontalAlignment(SwingConstants.LEFT);
		DateTxt.setEditable(false);
		DateTxt.setColumns(10);
		DateTxt.setBorder(null);
		DateTxt.setBackground(Color.WHITE);
		DateTxt.setBounds(151, 239, 207, 19);
		DateTxt.setText(String.valueOf(bookLog.getBorrowDate()));
		contentPane.add(DateTxt);
		
		JLabel TitleTxt = new JLabel("");
		TitleTxt.setBackground(Color.WHITE);
		TitleTxt.setForeground(Color.BLACK);
		TitleTxt.setVerticalAlignment(SwingConstants.TOP);
		TitleTxt.setHorizontalAlignment(SwingConstants.CENTER);
		TitleTxt.setFont(new Font("Dialog", Font.BOLD, 14));
		TitleTxt.setBounds(48, 10, 360, 47);
		TitleTxt.setText("<html>"+bookLog.getTitle());
		contentPane.add(TitleTxt);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setIcon(new ImageIcon(BookInfoFrame.class.getResource("/image/bookManager.png")));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnActionPerformed(e);
			}
		});
		btnReturn.setBounds(86, 335, 120, 25);
		contentPane.add(btnReturn);
		
		JLabel lblExpectedReturnDate = new JLabel("Expected Return Date:");
		lblExpectedReturnDate.setBounds(48, 278, 174, 15);
		contentPane.add(lblExpectedReturnDate);
		
		ExpectedDateTxt = new JTextField();
		ExpectedDateTxt.setText((String) null);
		ExpectedDateTxt.setHorizontalAlignment(SwingConstants.LEFT);
		ExpectedDateTxt.setEditable(false);
		ExpectedDateTxt.setColumns(10);
		ExpectedDateTxt.setBorder(null);
		ExpectedDateTxt.setBackground(Color.WHITE);
		ExpectedDateTxt.setBounds(210, 276, 165, 19);
		Calendar date = Calendar.getInstance();
		date.setTime(bookLog.getBorrowDate());
		date.add(Calendar.DATE, +60);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date returnDate = date.getTime();
		Date sqlReturnDate = new Date(returnDate.getTime());
		String expectedReturnDate = df.format(sqlReturnDate);
		ExpectedDateTxt.setText(expectedReturnDate);
		contentPane.add(ExpectedDateTxt);
		

	}

	/*
	 * Handle the borrow book event
	 * 
	 */
	private void returnActionPerformed(ActionEvent e) {
		Connection con = null;
		try {

			con = dbUtil.getCon();
			
			currentUser.setBorrowedCount(currentUser.getBorrowedCount()-1);
			bookLog.setAvailableCount(bookLog.getAvailableCount()+1);
			bookLog.setReturnDate(new Date(System.currentTimeMillis()));
			
			int userFlag = userDao.borrowReturnBook(con, currentUser);
			int logFlag = userDao.updateBorrowLog(con, currentUser, bookLog);
			int bookFlag = bookDao.updateBook(con, bookLog);
			
			if (userFlag == 1 && logFlag == 1 && bookFlag == 1) {
				JOptionPane.showMessageDialog(null, "Book has been returned successfully.");
			}
			else {
				JOptionPane.showMessageDialog(null, "Failed to return the book.");
				currentUser.setBorrowedCount(currentUser.getBorrowedCount()+1);
				bookLog.setAvailableCount(bookLog.getAvailableCount()-1);
				bookLog.setReturnDate(null);
			}
			
		} catch (Exception e1) {

			e1.printStackTrace();
		} finally {
			try {
				if (con != null) {
					dbUtil.closeCon(con);
				}
				dispose();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	// handle the go back event
	private void gobackActionPerformed(ActionEvent e) {
		// close the window
		dispose();
		
	}
}
