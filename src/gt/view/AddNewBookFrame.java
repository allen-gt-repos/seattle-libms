package gt.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gt.dao.BookDao;
import gt.model.Book;
import gt.util.DBUtil;
import gt.util.StringUtil;
import javax.swing.SwingConstants;

public class AddNewBookFrame extends JInternalFrame {
	private JTextField TitleTxt;
	private JTextField AuthorTxt;
	private JTextField ISBNTxt;
	private JTextField PublisherTxt;
	private JTextField PubYearTxt;
	private JTextField CountTxt;
	private JTextField BookIDTxt;
	private JTextField LocationTxt;
	private JTextField SubjectTxt;

	private DBUtil dbUtil = new DBUtil();
	private BookDao bookDao = new BookDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewBookFrame frame = new AddNewBookFrame();
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
	public AddNewBookFrame() {
		setNormalBounds(new Rectangle(9, 9, 0, 0));
		getContentPane().setFont(new Font("Dialog", Font.BOLD, 12));
		setBounds(100, 100, 680, 400);
		
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
		PublisherTxt.setBounds(471, 48, 172, 21);
		PublisherTxt.setColumns(10);
		
		PubYearTxt = new JTextField();
		PubYearTxt.setBounds(471, 86, 172, 21);
		PubYearTxt.setColumns(10);
		
		CountTxt = new JTextField();
		CountTxt.setBounds(471, 129, 172, 21);
		CountTxt.setColumns(10);
		
		JLabel lblPublisher = new JLabel("Publisher:");
		lblPublisher.setHorizontalAlignment(SwingConstants.LEFT);
		lblPublisher.setBounds(343, 51, 130, 15);
		lblPublisher.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblPublicationYear = new JLabel("Publication Year:");
		lblPublicationYear.setHorizontalAlignment(SwingConstants.LEFT);
		lblPublicationYear.setBounds(343, 89, 130, 15);
		lblPublicationYear.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblCount = new JLabel("Total Count*:");
		lblCount.setHorizontalAlignment(SwingConstants.LEFT);
		lblCount.setBounds(343, 132, 130, 15);
		lblCount.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblNewLabel_2_1 = new JLabel("Book ID*:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setBounds(35, 178, 77, 15);
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Location*:");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setBounds(343, 178, 130, 15);
		lblNewLabel_2_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		
		BookIDTxt = new JTextField();
		BookIDTxt.setBounds(102, 175, 183, 21);
		BookIDTxt.setColumns(10);
		
		LocationTxt = new JTextField();
		LocationTxt.setBounds(471, 175, 172, 21);
		LocationTxt.setColumns(10);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Subjects:");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_2.setBounds(35, 222, 77, 15);
		lblNewLabel_2_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		
		SubjectTxt = new JTextField();
		SubjectTxt.setBounds(102, 219, 541, 21);
		SubjectTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(146, 282, 97, 36);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitAdditionActionPerformed(e);
				
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(293, 282, 97, 36);
		btnReset.setFont(new Font("Dialog", Font.BOLD, 14));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetActionPerformed(e);
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(450, 283, 97, 35);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelActionPerformed(e);
				
			}
		});
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().setLayout(null);
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
		getContentPane().add(lblCount);
		getContentPane().add(CountTxt);
		getContentPane().add(lblNewLabel_2_1);
		getContentPane().add(BookIDTxt);
		getContentPane().add(lblNewLabel_2_1_1);
		getContentPane().add(LocationTxt);
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
			String bookId =this.BookIDTxt.getText();
			String title = this.TitleTxt.getText();
			String author =this.AuthorTxt.getText();
			String isbn = this.ISBNTxt.getText();
			String publisher =this.PublisherTxt.getText();
			String pubYear = this.PubYearTxt.getText();
			String location =this.LocationTxt.getText();
			String subject = this.SubjectTxt.getText();
			String count =this.CountTxt.getText();
			
			// check the input
			if(StringUtil.isEmpty(bookId)) {
				
				JOptionPane.showMessageDialog(null, "Book ID can not be empty");
				return;
			}
			else if(StringUtil.isEmpty(title)) {
				
				JOptionPane.showMessageDialog(null, "Title can not be empty");
				return;
			}
			else if(StringUtil.isEmpty(isbn)) {
				
				JOptionPane.showMessageDialog(null, "ISBN can not be empty");
				return;
			}
			else if(StringUtil.isEmpty(location)) {
				
				JOptionPane.showMessageDialog(null, "Location can not be empty");
				return;
			}
			else if(StringUtil.isEmpty(count)) {
				
				JOptionPane.showMessageDialog(null, "Total Book Count can not be empty");
				return;
			}
			else {
				
				Book newBook = new Book(Integer.valueOf(bookId), title, author, isbn, pubYear, publisher, subject, Integer.valueOf(count), Integer.valueOf(location));
				Connection con = null;
				try {
					con = dbUtil.getCon();
					int addResult = bookDao.addNewBook(con, newBook);
					if(addResult ==1)
					{
						JOptionPane.showMessageDialog(null, "A new book item has been added successfully!");
						this.resetValue();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Failed to add the new book item.");
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
		
		this.TitleTxt.setText("");
		this.AuthorTxt.setText("");
		this.ISBNTxt.setText("");
		this.BookIDTxt.setText("");
		this.SubjectTxt.setText("");
		this.PubYearTxt.setText("");
		this.PublisherTxt.setText("");
		this.LocationTxt.setText("");
		this.CountTxt.setText("");
	}
}
