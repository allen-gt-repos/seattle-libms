package gt.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import gt.dao.BookDao;
import gt.model.Book;
import gt.model.BookLoc;
import gt.util.DBUtil;
import gt.util.StringUtil;

public class ManageBookFrame extends JInternalFrame {
	private JTextField searchTxt;
	private JTextField titleTxt;
	private JTextField authorTxt;
	private JTextField isbnTxt;
	private JTextField publisherTxt;
	private JTextField pubYearTxt;
	private JTextField countTxt;
	private JTextField floorTxt;
	private JTextField hallTxt;
	private JTextField bookshelfTxt;
	private JTextField columnTxt;
	private JTextField layerTxt;
	private JTextArea subjectTxt;
	
	BookLoc bookLoc = null;
	BookDao bookDao = new BookDao();
	DBUtil dbUtil = new DBUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageBookFrame frame = new ManageBookFrame();
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
	public ManageBookFrame() {
		setBounds(100, 100, 680, 399);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 670, 367);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		searchTxt = new JTextField();
		searchTxt.setBounds(141, 27, 416, 24);
		panel.add(searchTxt);
		searchTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Book");
		lblNewLabel.setBounds(48, 29, 88, 15);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ManageBookFrame.class.getResource("/image/search.png")));
		btnNewButton.setBounds(556, 27, 38, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Book Title*:");
		lblNewLabel_1.setBounds(48, 84, 101, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Please Enter Book ID or ISBN");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(151, 49, 367, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Author*:");
		lblNewLabel_3.setBounds(48, 117, 70, 15);
		panel.add(lblNewLabel_3);
		
		titleTxt = new JTextField();
		titleTxt.setBounds(141, 82, 204, 19);
		panel.add(titleTxt);
		titleTxt.setColumns(10);
		
		authorTxt = new JTextField();
		authorTxt.setColumns(10);
		authorTxt.setBounds(141, 115, 204, 19);
		panel.add(authorTxt);
		
		JLabel lblNewLabel_1_1 = new JLabel("ISBN*:");
		lblNewLabel_1_1.setBounds(363, 84, 53, 15);
		panel.add(lblNewLabel_1_1);
		
		isbnTxt = new JTextField();
		isbnTxt.setColumns(10);
		isbnTxt.setBounds(414, 82, 190, 19);
		panel.add(isbnTxt);
		
		JLabel lblNewLabel_3_1 = new JLabel("Publisher:");
		lblNewLabel_3_1.setBounds(363, 117, 88, 15);
		panel.add(lblNewLabel_3_1);
		
		publisherTxt = new JTextField();
		publisherTxt.setColumns(10);
		publisherTxt.setBounds(438, 115, 166, 19);
		panel.add(publisherTxt);
		
		JLabel lblNewLabel_3_2 = new JLabel("Publish Year:");
		lblNewLabel_3_2.setBounds(48, 152, 125, 15);
		panel.add(lblNewLabel_3_2);
		
		pubYearTxt = new JTextField();
		pubYearTxt.setBounds(151, 148, 194, 19);
		panel.add(pubYearTxt);
		pubYearTxt.setColumns(10);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Available Copy*:");
		lblNewLabel_3_1_1.setBounds(363, 152, 125, 15);
		panel.add(lblNewLabel_3_1_1);
		
		countTxt = new JTextField();
		countTxt.setColumns(10);
		countTxt.setBounds(484, 150, 120, 19);
		panel.add(countTxt);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Subjects:");
		lblNewLabel_3_2_1.setBounds(48, 187, 125, 15);
		panel.add(lblNewLabel_3_2_1);
		
		subjectTxt = new JTextArea();
		subjectTxt.setBounds(141, 187, 463, 43);
		// set border
		Border border = BorderFactory.createLineBorder(Color.GRAY); 
	    subjectTxt.setBorder(BorderFactory.createCompoundBorder(border, 
	      BorderFactory.createEmptyBorder(10, 10, 10, 10))); 
		panel.add(subjectTxt);
		
		JLabel lblNewLabel_3_2_1_1 = new JLabel("Location*:");
		lblNewLabel_3_2_1_1.setBounds(48, 242, 101, 15);
		panel.add(lblNewLabel_3_2_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Floor");
		lblNewLabel_4.setBounds(128, 242, 70, 15);
		panel.add(lblNewLabel_4);
		
		floorTxt = new JTextField();
		floorTxt.setBounds(180, 240, 88, 19);
		panel.add(floorTxt);
		floorTxt.setColumns(10);
		
		hallTxt = new JTextField();
		hallTxt.setColumns(10);
		hallTxt.setBounds(286, 240, 165, 19);
		panel.add(hallTxt);
		
		bookshelfTxt = new JTextField();
		bookshelfTxt.setColumns(10);
		bookshelfTxt.setBounds(205, 271, 88, 19);
		panel.add(bookshelfTxt);
		
		columnTxt = new JTextField();
		columnTxt.setColumns(10);
		columnTxt.setBounds(363, 271, 88, 19);
		panel.add(columnTxt);
		
		layerTxt = new JTextField();
		layerTxt.setColumns(10);
		layerTxt.setBounds(516, 271, 88, 19);
		panel.add(layerTxt);
		
		JLabel lblNewLabel_5 = new JLabel("Bookshelf");
		lblNewLabel_5.setBounds(128, 271, 70, 15);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Column");
		lblNewLabel_6.setBounds(299, 271, 70, 15);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Layer");
		lblNewLabel_7.setBounds(462, 271, 70, 15);
		panel.add(lblNewLabel_7);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		btnNewButton_1.setIcon(new ImageIcon(ManageBookFrame.class.getResource("/image/reset.png")));
		btnNewButton_1.setBounds(484, 311, 117, 25);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBookActionPerformed(e);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ManageBookFrame.class.getResource("/image/modify.png")));
		btnNewButton_2.setBounds(48, 311, 117, 25);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Delete Book");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBookActionPerformed(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(ManageBookFrame.class.getResource("/image/delete.png")));
		btnNewButton_3.setBounds(252, 311, 140, 25);
		panel.add(btnNewButton_3);

	}

	/*
	 * 
	 * Handle the delete book item
	 */
	private void deleteBookActionPerformed(ActionEvent e) {
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			Book book = new Book();
			book.setBookId(bookLoc.getBookId());
			if (!bookDao.checkAllReturned(con, book)) {
				JOptionPane.showMessageDialog(null,
						"Cannot delete this book since some copys of this book haven't been returned.");
				return;
			} else {
				int answer = JOptionPane.showConfirmDialog(null,
						"Are you sure to delete this book item?\n All users cannot find this book in system any more \nand this book's borrow records will be deleted.");
				if (answer == 0) {
					int result = bookDao.deleteBook(con, book);
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "Delete this book record successfully!");
					} else {
						JOptionPane.showMessageDialog(null, "Failed to delete the book item.");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to delete the book item.");
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

	private void updateBookActionPerformed(ActionEvent e) {
		
		Connection con = null;
		int location_id = 0;
		// check the input location info
		try {
			con = dbUtil.getCon();
			BookLoc loc = new BookLoc();
			loc.setFloor(Integer.valueOf(floorTxt.getText()));
			loc.setHallName(hallTxt.getText());
			loc.setBookshelf(Integer.valueOf(bookshelfTxt.getText()));
			loc.setColumn(Integer.valueOf(columnTxt.getText()));
			loc.setLayer(Integer.valueOf(layerTxt.getText()));
			
			location_id = bookDao.checkValidLocation(con, loc);
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		if (StringUtil.isEmpty(authorTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Author cannot be empty.");
			
		}else if (StringUtil.isEmpty(isbnTxt.getText())) {
			JOptionPane.showMessageDialog(null, "ISBN cannot be empty.");
			
		}else if (StringUtil.isEmpty(titleTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Book title cannot be empty.");
			
		}else if (StringUtil.isEmpty(countTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Total Copy cannot be empty.");
			
		}else if (StringUtil.isEmpty(floorTxt.getText()) || StringUtil.isEmpty(hallTxt.getText()) ||
				StringUtil.isEmpty(bookshelfTxt.getText()) || StringUtil.isEmpty(columnTxt.getText()) ||
				StringUtil.isEmpty(layerTxt.getText())) {
			JOptionPane.showMessageDialog(null, "Location info must be compelete.");
			
		}else if (location_id == 0) {
			JOptionPane.showMessageDialog(null, "Invalid location info.");
		}
		else {
			Book newBook = new Book();
			newBook.setBookId(bookLoc.getBookId());
			newBook.setAuthor(authorTxt.getText());
			newBook.setTitle(titleTxt.getText());
			newBook.setIsbn(isbnTxt.getText());
			newBook.setPublisher(publisherTxt.getText());
			newBook.setPubYear(pubYearTxt.getText());
			newBook.setSubject(subjectTxt.getText());
			newBook.setAvailableCount(Integer.valueOf(countTxt.getText()));
			newBook.setLocationId(location_id);
			
			try {
				con = dbUtil.getCon();
				int result = bookDao.updateBook(con, newBook);
				
				if (result ==1) {
					JOptionPane.showMessageDialog(null, "Update this book record successfully!");
				}else {
					JOptionPane.showMessageDialog(null, "Failed to update the book item.");
				}
			
			
			}catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to update the book item.");
			}finally {
				if (con!=null) {
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			
			}
		}
	}

	private void searchActionPerformed(ActionEvent e) {

		Connection con = null;
		try {
			con = dbUtil.getCon();
			// search the book
			if (StringUtil.isNumeric(searchTxt.getText())) {
				bookLoc = bookDao.getBookInfo(con, searchTxt.getText(),Integer.valueOf(searchTxt.getText()));
			}
			else if (StringUtil.isNotEmpty(searchTxt.getText())) {
				
				bookLoc = bookDao.getBookInfo(con, searchTxt.getText());
			}
			
			if (bookLoc != null) {
				
				titleTxt.setText(bookLoc.getTitle());
				authorTxt.setText(bookLoc.getAuthor());
				isbnTxt.setText(bookLoc.getIsbn());
				publisherTxt.setText(bookLoc.getPublisher());
				pubYearTxt.setText(bookLoc.getPubYear());
				countTxt.setText(String.valueOf(bookLoc.getAvailableCount()));
				floorTxt.setText(String.valueOf(bookLoc.getFloor()));
				hallTxt.setText(bookLoc.getHallName());
				bookshelfTxt.setText(String.valueOf(bookLoc.getBookshelf()));
				columnTxt.setText(String.valueOf(bookLoc.getColumn()));
				layerTxt.setText(String.valueOf(bookLoc.getLayer()));
				searchTxt.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "Can not find book record matches "+searchTxt.getText()+".");
				searchTxt.setText("");
			}
		}catch (Exception ex) {
		// TODO: handle exception
			ex.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				
			}
		}
	}
	
}
