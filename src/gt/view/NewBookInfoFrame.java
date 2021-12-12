package gt.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

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
import gt.model.NewBook;
import gt.model.User;
import gt.util.DBUtil;
/**
 * Show info about new recommend book
 * @author Wang, Yinuo
 *
 */
public class NewBookInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField AuthorTxt;
	private JTextField IsbnTxt;
	private JTextField PublisherTxt;
	private JTextField PubYearTxt;

	private UserDao userDao = new UserDao();
	private BookDao bookDao = new BookDao();
	private DBUtil dbUtil = new DBUtil();
	
	private NewBook newBook = null;
	private JTextField RcmdDateTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBookInfoFrame frame = new NewBookInfoFrame(null);
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
	public NewBookInfoFrame(NewBook newBookInput) {
		
		// set book
		this.newBook = newBookInput;
		
		
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
				dispose();
		
			}
		});
		btnNewButton.setIcon(new ImageIcon(BookInfoFrame.class.getResource("/image/reset.png")));
		btnNewButton.setBounds(231, 335, 120, 25);
		contentPane.add(btnNewButton);

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
		
		AuthorTxt = new JTextField();
		AuthorTxt.setHorizontalAlignment(SwingConstants.LEFT);
		AuthorTxt.setBounds(112, 59, 280, 19);
		AuthorTxt.setBorder(null);
		AuthorTxt.setText(newBook.getAuthor());
		contentPane.add(AuthorTxt);
		AuthorTxt.setColumns(10);
		
		IsbnTxt = new JTextField();
		IsbnTxt.setHorizontalAlignment(SwingConstants.LEFT);
		IsbnTxt.setColumns(10);
		IsbnTxt.setBounds(112, 86, 302, 19);
		IsbnTxt.setBorder(null);
		IsbnTxt.setText(newBook.getIsbn());
		contentPane.add(IsbnTxt);
		
		JTextArea SubjectTxt = new JTextArea();
		SubjectTxt.setWrapStyleWord(true);
		SubjectTxt.setEditable(false);
		SubjectTxt.setLineWrap(true);
		SubjectTxt.setBounds(112, 169, 302, 60);
		SubjectTxt.setBorder(null);
		SubjectTxt.setText(newBook.getSubject());
		contentPane.add(SubjectTxt);
		
		PublisherTxt = new JTextField();
		PublisherTxt.setHorizontalAlignment(SwingConstants.LEFT);
		PublisherTxt.setBackground(Color.WHITE);
		PublisherTxt.setEditable(false);
		PublisherTxt.setBounds(140, 114, 258, 17);
		PublisherTxt.setBorder(null);
		PublisherTxt.setText(newBook.getPublisher());
		contentPane.add(PublisherTxt);
		PublisherTxt.setColumns(10);
		
		PubYearTxt = new JTextField();
		PubYearTxt.setHorizontalAlignment(SwingConstants.LEFT);
		PubYearTxt.setBackground(Color.WHITE);
		PubYearTxt.setEditable(false);
		PubYearTxt.setBounds(191, 140, 207, 19);
		PubYearTxt.setBorder(null);
		PubYearTxt.setText(newBook.getPubYear());
		contentPane.add(PubYearTxt);
		PubYearTxt.setColumns(10);
		
		JLabel TitleTxt = new JLabel("");
		TitleTxt.setBackground(Color.WHITE);
		TitleTxt.setForeground(Color.BLACK);
		TitleTxt.setVerticalAlignment(SwingConstants.TOP);
		TitleTxt.setHorizontalAlignment(SwingConstants.CENTER);
		TitleTxt.setFont(new Font("Dialog", Font.BOLD, 14));
		TitleTxt.setBounds(48, 10, 360, 47);
		TitleTxt.setText("<html>"+newBook.getTitle());
		contentPane.add(TitleTxt);
		
		JButton btnBorrow = new JButton("Delete");
		btnBorrow.setIcon(new ImageIcon(NewBookInfoFrame.class.getResource("/image/delete.png")));
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		btnBorrow.setBounds(68, 335, 120, 25);
		contentPane.add(btnBorrow);
		
		JLabel lblRecommendDate = new JLabel("Recommend Date:");
		lblRecommendDate.setBounds(48, 241, 150, 15);
		contentPane.add(lblRecommendDate);
		
		RcmdDateTxt = new JTextField();
		RcmdDateTxt.setText((String) null);
		RcmdDateTxt.setHorizontalAlignment(SwingConstants.LEFT);
		RcmdDateTxt.setEditable(false);
		RcmdDateTxt.setColumns(10);
		RcmdDateTxt.setBorder(null);
		RcmdDateTxt.setBackground(Color.WHITE);
		RcmdDateTxt.setBounds(185, 239, 207, 19);
		RcmdDateTxt.setText(newBook.getRecommendDate().toString());
		contentPane.add(RcmdDateTxt);
	}

	/*
	 * Handle the delete recommend book 
	 */
	protected void deleteActionPerformed(ActionEvent e) {
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			
			int deleteFlag = bookDao.deleteRecommendBook(con, newBook);
			if (deleteFlag == 1) {
				JOptionPane.showMessageDialog(null, "Delete this recommend book successfully.");
				
			}else {
				JOptionPane.showMessageDialog(null, "Failed to delete the record.");
			}
		} catch (Exception e1) {
			
			e1.printStackTrace();
		} finally {
			if (con!= null) {
				try {
					con.close();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		}
		
		
	}

}
