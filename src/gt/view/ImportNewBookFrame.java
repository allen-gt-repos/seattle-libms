package gt.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import gt.dao.BookDao;
import gt.model.Book;
import gt.model.BookLoc;
import gt.model.NewBook;
import gt.util.DBUtil;
import gt.util.StringUtil;
import javax.swing.SwingConstants;

public class ImportNewBookFrame extends JFrame {
	private final JLabel TitleTxt = new JLabel("");
	private JTextField floorTxt;
	private JTextField HallTxt;
	private JTextField BookshelfTxt;
	private JTextField ColumnTxt;
	private JTextField LayerTxt;
	private JTextField authorTxt;
	private JTextField PublisherTxt;
	private JTextField IsbnTxt;
	private JTextField PubYearTxt;
	private JTextField CountTxt;
	private JTextField BookidTxt;
	private JTextArea SubjectTxt;
	private DBUtil dbUtil = new DBUtil();
	private BookDao bookDao = new BookDao();
	private NewBook book = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportNewBookFrame frame = new ImportNewBookFrame(null);
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
	public ImportNewBookFrame(NewBook bookInput) {
		getContentPane().setBackground(Color.WHITE);
		
		this.book = bookInput;
	
		setBounds(100, 100, 571, 402);
		getContentPane().setLayout(null);
		TitleTxt.setHorizontalAlignment(SwingConstants.CENTER);
		TitleTxt.setFont(new Font("Dialog", Font.BOLD, 13));
		TitleTxt.setBounds(31, 0, 516, 70);
		TitleTxt.setText("<html>"+book.getTitle());
		getContentPane().add(TitleTxt);
		
		JLabel lblAuthor = new JLabel("Author*: ");
		lblAuthor.setBounds(54, 82, 70, 15);
		getContentPane().add(lblAuthor);
		
		JLabel lblIsbn = new JLabel("ISBN*: ");
		lblIsbn.setBounds(54, 122, 70, 15);
		getContentPane().add(lblIsbn);
		
		JLabel lblBookId = new JLabel("Book ID*:");
		lblBookId.setBounds(54, 156, 70, 15);
		getContentPane().add(lblBookId);
		
		JLabel lblPublisher = new JLabel("Publisher: ");
		lblPublisher.setBounds(301, 82, 90, 15);
		getContentPane().add(lblPublisher);
		
		JLabel lblPublishYear = new JLabel("Publish Year: ");
		lblPublishYear.setBounds(301, 122, 102, 15);
		getContentPane().add(lblPublishYear);
		
		JLabel lblTotalCopy = new JLabel("Total Copy*:");
		lblTotalCopy.setBounds(301, 156, 90, 15);
		getContentPane().add(lblTotalCopy);
		
		JLabel lblSubjects = new JLabel("Subjects:");
		lblSubjects.setBounds(54, 198, 70, 15);
		getContentPane().add(lblSubjects);
		
		JLabel lblNewLabel_3_2_1_1 = new JLabel("Location*:");
		lblNewLabel_3_2_1_1.setBounds(48, 261, 101, 15);
		getContentPane().add(lblNewLabel_3_2_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Floor");
		lblNewLabel_4.setBounds(128, 261, 70, 15);
		getContentPane().add(lblNewLabel_4);
		
		floorTxt = new JTextField();
		floorTxt.setColumns(10);
		floorTxt.setBounds(180, 259, 88, 19);
		getContentPane().add(floorTxt);
		
		HallTxt = new JTextField();
		HallTxt.setColumns(10);
		HallTxt.setBounds(286, 259, 165, 19);
		getContentPane().add(HallTxt);
		
		JLabel lblNewLabel_6 = new JLabel("Column");
		lblNewLabel_6.setBounds(286, 290, 70, 15);
		getContentPane().add(lblNewLabel_6);
		
		BookshelfTxt = new JTextField();
		BookshelfTxt.setColumns(10);
		BookshelfTxt.setBounds(205, 290, 62, 19);
		getContentPane().add(BookshelfTxt);
		
		JLabel lblNewLabel_5 = new JLabel("Bookshelf");
		lblNewLabel_5.setBounds(128, 290, 70, 15);
		getContentPane().add(lblNewLabel_5);
		
		ColumnTxt = new JTextField();
		ColumnTxt.setColumns(10);
		ColumnTxt.setBounds(347, 288, 44, 19);
		getContentPane().add(ColumnTxt);
		
		JLabel lblNewLabel_7 = new JLabel("Layer");
		lblNewLabel_7.setBounds(409, 290, 70, 15);
		getContentPane().add(lblNewLabel_7);
		
		LayerTxt = new JTextField();
		LayerTxt.setColumns(10);
		LayerTxt.setBounds(462, 288, 44, 19);
		getContentPane().add(LayerTxt);
		
		JButton btnNewButton = new JButton("Add to Library");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ImportNewBookFrame.class.getResource("/image/add.png")));
		btnNewButton.setBounds(81, 333, 165, 25);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ImportNewBookFrame.class.getResource("/image/reset.png")));
		btnNewButton_1.setBounds(286, 333, 117, 25);
		getContentPane().add(btnNewButton_1);
		
		authorTxt = new JTextField();
		authorTxt.setBounds(128, 80, 114, 19);
		authorTxt.setText(book.getAuthor());
		getContentPane().add(authorTxt);
		authorTxt.setColumns(10);
		
		PublisherTxt = new JTextField();
		PublisherTxt.setBounds(403, 80, 114, 19);
		PublisherTxt.setText(book.getPublisher());
		getContentPane().add(PublisherTxt);
		PublisherTxt.setColumns(10);
		
		IsbnTxt = new JTextField();
		IsbnTxt.setBounds(128, 120, 114, 19);
		IsbnTxt.setText(book.getIsbn());
		getContentPane().add(IsbnTxt);
		IsbnTxt.setColumns(10);
		
		PubYearTxt = new JTextField();
		PubYearTxt.setColumns(10);
		PubYearTxt.setBounds(403, 120, 114, 19);
		PubYearTxt.setText(book.getPubYear());
		getContentPane().add(PubYearTxt);
		
		CountTxt = new JTextField();
		CountTxt.setColumns(10);
		CountTxt.setBounds(403, 154, 114, 19);
		getContentPane().add(CountTxt);
		
		BookidTxt = new JTextField();
		BookidTxt.setColumns(10);
		BookidTxt.setBounds(128, 154, 114, 19);
		getContentPane().add(BookidTxt);
		
		SubjectTxt = new JTextArea();
		SubjectTxt.setBounds(128, 198, 386, 45);
		// set border
		Border border = BorderFactory.createLineBorder(Color.GRAY); 
	    SubjectTxt.setBorder(BorderFactory.createCompoundBorder(border, 
	      BorderFactory.createEmptyBorder(10, 10, 10, 10))); 
		getContentPane().add(SubjectTxt);

	}

	private void importActionPerformed(ActionEvent e) {
		Connection con = null;
		int location_id = 0;
		// check the input location info
		try {
			con = dbUtil.getCon();
			BookLoc loc = new BookLoc();
			loc.setFloor(Integer.valueOf(floorTxt.getText()));
			loc.setHallName(HallTxt.getText());
			loc.setBookshelf(Integer.valueOf(BookshelfTxt.getText()));
			loc.setColumn(Integer.valueOf(ColumnTxt.getText()));
			loc.setLayer(Integer.valueOf(LayerTxt.getText()));
			location_id = bookDao.checkValidLocation(con, loc);
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		
		if (StringUtil.isEmpty(authorTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Author cannot be empty.");
			
		}else if (StringUtil.isEmpty(IsbnTxt.getText())) {
			JOptionPane.showMessageDialog(null, "ISBN cannot be empty.");
			
		}else if (StringUtil.isEmpty(BookidTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Book ID cannot be empty.");
			
		}else if (StringUtil.isEmpty(CountTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Total Copy cannot be empty.");
			
		}else if (StringUtil.isEmpty(floorTxt.getText()) || StringUtil.isEmpty(HallTxt.getText()) ||
				StringUtil.isEmpty(BookshelfTxt.getText()) || StringUtil.isEmpty(ColumnTxt.getText()) ||
				StringUtil.isEmpty(LayerTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Location info must be compelete.");
			
		}else if (location_id == 0) {
			JOptionPane.showMessageDialog(null, "Invalid location info.");
		}
		else {
			Book newBook = new Book();
			newBook.setTitle(book.getTitle());
			newBook.setAuthor(authorTxt.getText());
			newBook.setBookId(Integer.valueOf(BookidTxt.getText()));
			newBook.setIsbn(IsbnTxt.getText());
			newBook.setPublisher(PublisherTxt.getText());
			newBook.setPubYear(PubYearTxt.getText());
			newBook.setSubject(SubjectTxt.getText());
			newBook.setAvailableCount(Integer.valueOf(CountTxt.getText()));
			newBook.setLocationId(location_id);
			
			try {
				con = dbUtil.getCon();
				int del = bookDao.deleteRecommendBook(con, book);
				int add = bookDao.addNewBook(con, newBook);

				if (del>=1 && add==1) {
					
					JOptionPane.showMessageDialog(null, "Add a new book to library successfully!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Failed to add the new book into library system.");

				}
			}catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to add the new book into library system.");
				// TODO: handle exception
			}finally {
				try {
					con.close();
					dispose();
				} catch (SQLException e2) {
					
				
				}
			}
			
		}
	}

}
