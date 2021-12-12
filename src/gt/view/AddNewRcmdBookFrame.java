package gt.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gt.dao.BookDao;
import gt.model.NewBook;
import gt.model.User;
import gt.util.DBUtil;
import gt.util.StringUtil;
/**
 * Reader add new recommend book
 * @author Wang, Yinuo
 *
 */
public class AddNewRcmdBookFrame extends JFrame {

	private JTextField TitleTxt;
	private JTextField AuthorTxt;
	private JTextField ISBNTxt;
	private JTextField PublisherTxt;
	private JTextField PubYearTxt;
	private JTextField SubjectTxt;
	private JPanel contentPane;
	private DBUtil dbUtil = new DBUtil();
	private BookDao bookDao = new BookDao();
	private User currentUser = null;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewRcmdBookFrame frame = new AddNewRcmdBookFrame(null);
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
	public AddNewRcmdBookFrame(User user) {
		
		//set user
		this.currentUser = user;
		
		setTitle("Recommend New Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 615, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 12));
		setBounds(100, 100, 650, 350);
		
		JLabel lblNewLabel = new JLabel("Title*:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(35, 51, 77, 15);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblNewLabel_1 = new JLabel("Author*:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(35, 89, 77, 15);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("ISBN*:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(35, 132, 77, 15);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		
		TitleTxt = new JTextField();
		TitleTxt.setBounds(102, 48, 183, 21);
		TitleTxt.setColumns(10);
		
		AuthorTxt = new JTextField();
		AuthorTxt.setBounds(102, 86, 183, 21);
		AuthorTxt.setColumns(10);
		
		ISBNTxt = new JTextField();
		ISBNTxt.setBounds(102, 129, 183, 21);
		ISBNTxt.setColumns(10);
		
		PublisherTxt = new JTextField();
		PublisherTxt.setBounds(442, 48, 172, 21);
		PublisherTxt.setColumns(10);
		
		PubYearTxt = new JTextField();
		PubYearTxt.setBounds(442, 86, 172, 21);
		PubYearTxt.setColumns(10);
		
		JLabel lblPublisher = new JLabel("Publisher:");
		lblPublisher.setHorizontalAlignment(SwingConstants.LEFT);
		lblPublisher.setBounds(314, 51, 130, 15);
		lblPublisher.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblPublicationYear = new JLabel("Publication Year:");
		lblPublicationYear.setHorizontalAlignment(SwingConstants.LEFT);
		lblPublicationYear.setBounds(314, 89, 130, 15);
		lblPublicationYear.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Subjects:");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_2.setBounds(314, 129, 300, 15);
		lblNewLabel_2_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		
		SubjectTxt = new JTextField();
		SubjectTxt.setBounds(442, 126, 172, 21);
		SubjectTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(127, 232, 97, 36);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitAdditionActionPerformed(e);
				
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(274, 232, 97, 36);
		btnReset.setFont(new Font("Dialog", Font.BOLD, 14));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(431, 233, 97, 35);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelActionPerformed(e);
				
			}
		});
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		getContentPane().add(lblNewLabel);
		getContentPane().add(TitleTxt);
		getContentPane().add(lblPublisher);
		getContentPane().add(PublisherTxt);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(AuthorTxt);
		getContentPane().add(lblPublicationYear);
		getContentPane().add(PubYearTxt);
		getContentPane().add(lblNewLabel_2);
		getContentPane().add(ISBNTxt);
		getContentPane().add(SubjectTxt);
		getContentPane().add(lblNewLabel_2_1_2);
		getContentPane().add(btnNewButton);
		getContentPane().add(btnReset);
		getContentPane().add(btnCancel);
	}
	
	/*
	 * Handle the submit button event
	 */
	protected void submitAdditionActionPerformed(ActionEvent e) {
		 
		int confirmResult = JOptionPane.showConfirmDialog(null, "Confirm to submit?");
		if(confirmResult == 0)
		{
			//get the input
			Date today = new Date(System.currentTimeMillis());
			NewBook newBook = new NewBook();
			newBook.setTitle(TitleTxt.getText());
			newBook.setIsbn(ISBNTxt.getText());
			newBook.setUserId(currentUser.getUserId());
			newBook.setAuthor(AuthorTxt.getText());
			newBook.setPubYear(PubYearTxt.getText());
			newBook.setPublisher(PublisherTxt.getText());
			newBook.setSubject(SubjectTxt.getText());
			newBook.setRecommendDate(new java.sql.Date(today.getTime()));
	
			Connection con = null;
			try {
				// check the input
				con = dbUtil.getCon();
				if (StringUtil.isEmpty(TitleTxt.getText())) {

					JOptionPane.showMessageDialog(null, "Title can not be empty");
					return;
				} else if (StringUtil.isEmpty(ISBNTxt.getText())) {

					JOptionPane.showMessageDialog(null, "ISBN can not be empty");
					return;
				} else if (StringUtil.isEmpty(AuthorTxt.getText())) {

					JOptionPane.showMessageDialog(null, "Author can not be empty");
					return;
				} else if (bookDao.checkRecommendedBook(con, newBook)) {
					JOptionPane.showMessageDialog(null, "You have recommended this book.");
					resetValue();
					return;
				} else if (bookDao.checkExistBook(con, newBook)) {
					JOptionPane.showMessageDialog(null, "This book has been stored in the library.");
					resetValue();
					return;
				}
				else {

					int addResult = bookDao.addRecommendBook(con, newBook);
					if (addResult == 1) {
						JOptionPane.showMessageDialog(null, "A new recommend book has been added successfully!");
						resetValue();
						return;
					} 
					else {
						JOptionPane.showMessageDialog(null, "Failed to add the new recommend book.");
						resetValue();
						this.resetValue();
					}
				}
			} catch (Exception e1) {

				e1.printStackTrace();

			} finally {
				try {

					dbUtil.closeCon(con);

				} catch (Exception e1) {

					e1.printStackTrace();
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
		
		this.TitleTxt.setText("");
		this.AuthorTxt.setText("");
		this.ISBNTxt.setText("");
		this.SubjectTxt.setText("");
		this.PubYearTxt.setText("");
		this.PublisherTxt.setText("");

	}
}
