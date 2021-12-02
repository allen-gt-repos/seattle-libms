package gt.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import gt.dao.ActivityDao;
import gt.dao.BookDao;
import gt.dao.UserDao;
import gt.model.Activity;
import gt.model.ActivityLoc;
import gt.model.Book;
import gt.model.BookLoc;
import gt.model.BookLog;
import gt.model.NewBook;
import gt.model.User;
import gt.util.DBUtil;
import gt.util.StringUtil;


public class ReaderFrame extends JFrame {

	private JPanel search;
	private JPanel info;
	private JPanel bookshelf;
	private JPanel recommend;
	private JTabbedPane tabPane;
	private JScrollPane scrollPane, scrollPane2, scrollPane3;
	private JTable ResultTable, HistoryTable, RecommendTable;
	private JRadioButton rdbtnBook, rdbtnActivity;
	private JTextField searchTxt;
	private JLabel lblNewLabel;

	private DBUtil dbUtil = new DBUtil();
	private BookDao bookDao = new BookDao();
	private UserDao userDao = new UserDao();
	private ActivityDao activityDao = new ActivityDao();
	private User currentUser = null;
	private long recordNum = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderFrame frame = new ReaderFrame(null);
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
	public ReaderFrame(User user) {
		
		// set current user
		this.currentUser = user;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReaderFrame.class.getResource("/image/spl3.png")));
		setTitle("Seattle Public Library Reader System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 500);
		// create tab panel
		tabPane = new JTabbedPane();
		
		// create panel
		buildSearchPanel();
		buildInfoPanel();
		buildBookshelf();
		buildRecommend();
		
		// add panel to tab panel
		tabPane.add("Search",search);
		tabPane.add("My Bookshelf",bookshelf);
		tabPane.add("My Recommand",recommend);
		tabPane.add("Account Info",info);
		tabPane.setSelectedIndex(0); 
		
		// add tab panel to JFrame
		getContentPane().add(tabPane);
	}
	
	/*
	 * Create the recommend panel
	 */
	private void buildRecommend() {
		recommend = new JPanel();
		recommend.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("My Recommended Book");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Lato Black", Font.BOLD, 16));
		lblNewLabel_1.setBounds(226, 52, 217, 15);
		recommend.add(lblNewLabel_1);
		
		scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(52, 79, 600, 327);
		scrollPane3.setBackground(Color.white);
		recommend.add(scrollPane3);
		
		Vector bookColumn = new Vector<>();
		bookColumn.add("No.");
		bookColumn.add("Book Title");
		bookColumn.add("ISBN");
		bookColumn.add("Author");
//		bookColumn.add("Expected Return Date");
		
		RecommendTable = new JTable(0,4);
		RecommendTable.setSize(650, 350);
		RecommendTable.setLocation(50, 60);
		RecommendTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				recommendTableMousePressed(e);
			}
		});
		RecommendTable.setBackground(Color.WHITE);
		RecommendTable.setShowVerticalLines(false);
		// set table header
		DefaultTableModel dtm = new DefaultTableModel(null,bookColumn);
		RecommendTable.setModel(dtm);
		RecommendTable.getTableHeader().setReorderingAllowed(false);
		RecommendTable.getTableHeader().setResizingAllowed(false);
		//set row height and column width
		RecommendTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		RecommendTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		RecommendTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		RecommendTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		RecommendTable.setRowHeight(30);
		// set table contents center
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		RecommendTable.setDefaultRenderer(Object.class, dtcr);
//		bookshelf.add(HistoryTable);
		scrollPane3.setViewportView(RecommendTable);
		
		// fill the table with already borrowed book
		this.fillRecommendTable();
	
		// resize the icon
		ImageIcon icon1 = new ImageIcon(ReaderFrame.class.getResource("/image/refresh.png"));
		Image img = icon1.getImage();
		Image newimg = img.getScaledInstance(20,20, Image.SCALE_DEFAULT); 
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 705, 30);
		recommend.add(toolBar);
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillRecommendTable();
			}
		});
		toolBar.add(btnRefresh_1);
		btnRefresh_1.setIcon(new ImageIcon(newimg));
		
		JButton btnNewButton_3 = new JButton("Recommend Book");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewBookActionPerformed(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/add.png")));
		toolBar.add(btnNewButton_3);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/Picture1.png")));
		label.setBounds(0, 27, 705, 416);
		recommend.add(label);
		
	}
	/*
	 * handle the add new recommend book event
	 */
	protected void addNewBookActionPerformed(ActionEvent e) {
		
		AddNewRcmdBookFrame addNewRcmdBookFrame = new AddNewRcmdBookFrame(currentUser);
		addNewRcmdBookFrame.setLocationRelativeTo(null);
		addNewRcmdBookFrame.setVisible(true);
		
		
	}
	/*
	 * fill the recommend book table
	 */
	private void fillRecommendTable() {
		// TODO 自动生成的方法存根
		DefaultTableModel dtm = (DefaultTableModel) RecommendTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			int n = 0;
			con = dbUtil.getCon();
			ResultSet rs = bookDao.getRecommendBook(con, currentUser);
			if(rs.next()) {
			do {
				n+=1;
				Vector v = new Vector();
				v.add(n);
				v.add(rs.getString("Title"));
				v.add(rs.getString("Isbn"));
				v.add(rs.getString("Author"));
			
				dtm.addRow(v);
			} while (rs.next());
			}
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(JLabel.CENTER);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		}
	}

	private void recommendTableMousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		int rowIndex = RecommendTable.getSelectedRow();
		String FirstColTxt = (String)RecommendTable.getValueAt(rowIndex, 1);
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			NewBook newBook= bookDao.getRecommendBookInfo(con, FirstColTxt, currentUser);
			NewBookInfoFrame newBookInfoFrm = new NewBookInfoFrame(newBook);
			newBookInfoFrm.setLocationRelativeTo(null);
			newBookInfoFrm.setVisible(true);
						
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	}

	/*
	 * Create the bookshelf panel
	 */
	private void buildBookshelf() {
		
		bookshelf = new JPanel();
		bookshelf.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Current Borrowed Book");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Lato Black", Font.BOLD, 16));
		lblNewLabel_1.setBounds(226, 52, 217, 15);
		bookshelf.add(lblNewLabel_1);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(52, 79, 600, 327);
		scrollPane2.setBackground(Color.white);
		bookshelf.add(scrollPane2);
		
		Vector bookColumn = new Vector<>();
		bookColumn.add("No.");
		bookColumn.add("Book Title");
		bookColumn.add("ISBN");
		bookColumn.add("Borrow Date");
		bookColumn.add("Expected Return Date");
		
		HistoryTable = new JTable(0,5);
		HistoryTable.setSize(650, 350);
		HistoryTable.setLocation(50, 60);
		HistoryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				historyTableMousePressed(e);
			}
		});
		HistoryTable.setBackground(Color.WHITE);
		HistoryTable.setShowVerticalLines(false);
		// set table header
		DefaultTableModel dtm = new DefaultTableModel(null,bookColumn);
		HistoryTable.setModel(dtm);
		HistoryTable.getTableHeader().setReorderingAllowed(false);
		HistoryTable.getTableHeader().setResizingAllowed(false);
		//set row height and column width
		HistoryTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		HistoryTable.getColumnModel().getColumn(1).setPreferredWidth(290);
		HistoryTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		HistoryTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		HistoryTable.getColumnModel().getColumn(4).setPreferredWidth(160);
		HistoryTable.setRowHeight(30);
		// set table contents center
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		HistoryTable.setDefaultRenderer(Object.class, dtcr);
//		bookshelf.add(HistoryTable);
		scrollPane2.setViewportView(HistoryTable);
		
		// fill the table with already borrowed book
		this.fillHistoryTable();
	
		// resize the icon
		ImageIcon icon1 = new ImageIcon(ReaderFrame.class.getResource("/image/refresh.png"));
		Image img = icon1.getImage();
		Image newimg = img.getScaledInstance(20,20, Image.SCALE_DEFAULT); 
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 705, 30);
		bookshelf.add(toolBar);
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillHistoryTable();
			}
		});
		toolBar.add(btnRefresh_1);
		btnRefresh_1.setIcon(new ImageIcon(newimg));
		
		JButton btnNewButton_3 = new JButton("History");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHistoryActionPerformed(e);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/edit.png")));
		toolBar.add(btnNewButton_3);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/Picture1.png")));
		label.setBounds(0, 27, 705, 416);
		bookshelf.add(label);
		
	}

	/*
	 * Show the borrow history frame
	 */
	private void showHistoryActionPerformed(ActionEvent e) {
		
		BorrowHistoryFrame borrowHistoryFrame = new BorrowHistoryFrame(currentUser);
		borrowHistoryFrame.setLocationRelativeTo(null);
		borrowHistoryFrame.setVisible(true);
		
	}

	/*
	 * The Searching page
	 * 
	 */
	private void buildSearchPanel() {
		
		search = new JPanel();
		
		JTable table;
		search.setBackground(new Color(255, 255, 255));
		search.setLayout(null);
		
		JLabel lblSeattlePublicLibrary = new JLabel("Seattle Public Library");
		lblSeattlePublicLibrary.setForeground(Color.WHITE);
		lblSeattlePublicLibrary.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeattlePublicLibrary.setFont(new Font("Century Schoolbook L", Font.BOLD, 24));
		lblSeattlePublicLibrary.setBounds(193, 59, 310, 27);
		search.add(lblSeattlePublicLibrary);
		
		searchTxt = new JTextField();
		searchTxt.setColumns(10);
		searchTxt.setBounds(118, 98, 385, 27);
		search.add(searchTxt);
		
		// set table header
		Vector bookColumn = new Vector<>();
		bookColumn.add("Book Title");
		bookColumn.add("ISBN");
		bookColumn.add("Author");
//		bookColumn.add("Location");
//		bookColumn.add("More Info");
		
		Vector activityColumn = new Vector<>();
		activityColumn.add("Activity");
		activityColumn.add("Organizer");
		activityColumn.add("Date");
//		activityColumn.add("Location");
//		activityColumn.add("More Info");
		

		scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 164, 613, 247);
		scrollPane.setBackground(Color.WHITE);
		search.add(scrollPane);
		
		ResultTable = new JTable(0,3);
		ResultTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				resultTableMousePressed(e);
			}
		});
		ResultTable.setBackground(Color.white);
		ResultTable.setShowVerticalLines(false);
		// set table header
		DefaultTableModel dtm = new DefaultTableModel(null,bookColumn);
		ResultTable.setModel(dtm);
		ResultTable.getTableHeader().setReorderingAllowed(false);
		ResultTable.getTableHeader().setResizingAllowed(false);
		// set row height
		ResultTable.setRowHeight(25);
		
		// set table contents center
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		ResultTable.setDefaultRenderer(Object.class, dtcr);
		scrollPane.setViewportView(ResultTable);
		
		//search button
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/search.png")));
		button.setBounds(501, 98, 50, 26);
		search.add(button);
		
		rdbtnActivity = new JRadioButton("Activity");
		rdbtnActivity.setForeground(Color.BLACK);
		rdbtnActivity.setFont(new Font("Lato Black", Font.PLAIN, 14));
		rdbtnBook = new JRadioButton("Book");
		rdbtnBook.setFont(new Font("Lato Black", Font.PLAIN, 14));
		rdbtnBook.setForeground(Color.BLACK);
		
		rdbtnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// set table header
				DefaultTableModel dtm = new DefaultTableModel(null,bookColumn);
				ResultTable.setModel(dtm);
				// disable activity button
				rdbtnActivity.setSelected(false);
				searchTxt.setText("");
				recordNum =0;
			}
		});
		
		rdbtnActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// disable book button
				rdbtnBook.setSelected(false);
				// set table header
				DefaultTableModel dtm = new DefaultTableModel(null,activityColumn);
				dtm.fireTableStructureChanged();
				ResultTable.setModel(dtm);
				searchTxt.setText("");
				recordNum =0;

			}
		});
		rdbtnActivity.setOpaque(false);
		rdbtnActivity.setBounds(389, 133, 149, 23);
		search.add(rdbtnActivity);
		
		
		rdbtnBook.setSelected(true);
		rdbtnBook.setOpaque(false);
		rdbtnBook.setBounds(190, 133, 149, 23);
		search.add(rdbtnBook);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Lato Black", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(515, 416, 223, 15);
		lblNewLabel.setText(String.valueOf(recordNum)+" Records in Total");
		search.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/Picture1.png")));
		label_1.setBounds(0, 0, 705, 443);
		search.add(label_1);
	}


	/*
	 * 
	 * The Account Info page
	 */
	private void buildInfoPanel() {
		
		info = new JPanel();
		info.setBackground(new Color(255, 255, 255));
		info.setLayout(null);
		
		JLabel lblUsername = new JLabel();
		lblUsername.setFont(new Font("Lato Black", Font.BOLD, 17));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(205, 84, 423, 15);
		lblUsername.setText("Username: " + currentUser.getUserId());
		info.add(lblUsername);
		
		JLabel lblReaderName = new JLabel();
		lblReaderName.setFont(new Font("Lato Black", Font.BOLD, 17));
		lblReaderName.setForeground(Color.WHITE);
		lblReaderName.setBounds(205, 128, 423, 15);
		lblReaderName.setText("Reader Name: " + currentUser.getName());
		info.add(lblReaderName);
		
		JLabel lblEmail = new JLabel();
		lblEmail.setFont(new Font("Lato Black", Font.BOLD, 17));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(205, 178, 423, 15);
		lblEmail.setText("E-mail: " + currentUser.getEmail());
		info.add(lblEmail);
		
		JLabel lblAvailableQuantity = new JLabel();
		lblAvailableQuantity.setFont(new Font("Lato Black", Font.BOLD, 17));
		lblAvailableQuantity.setForeground(Color.WHITE);
		lblAvailableQuantity.setBounds(205, 275, 423, 15);
		lblAvailableQuantity.setText("Available Quantity: " + String.valueOf(10 - currentUser.getBorrowedCount()));
		info.add(lblAvailableQuantity);
		
		JLabel lblBorrowedQuantity = new JLabel();
		lblBorrowedQuantity.setFont(new Font("Lato Black", Font.BOLD, 17));
		lblBorrowedQuantity.setForeground(Color.WHITE);
		lblBorrowedQuantity.setBounds(205, 226, 423, 15);
		lblBorrowedQuantity.setText( "Borrowed Quantity: " + String.valueOf(currentUser.getBorrowedCount()));
		info.add(lblBorrowedQuantity);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 704, 30);
		info.add(toolBar);
				
		
		JButton btnNewButton_1 = new JButton("Update Info");
		toolBar.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/edit.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerifyAccountDialog verifyAccountDialog = new VerifyAccountDialog(currentUser);
				verifyAccountDialog.setLocationRelativeTo(null);
				verifyAccountDialog.setVisible(true);
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton btnNewButton = new JButton("Change Password");
		toolBar.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/password.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				NewPasswordFrame newPasswordFrm = new NewPasswordFrame(currentUser);
				newPasswordFrm.setLocationRelativeTo(null);
				newPasswordFrm.setVisible(true);
					
				
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		// resize the icon
		ImageIcon icon1 = new ImageIcon(ReaderFrame.class.getResource("/image/refresh.png"));
		Image img = icon1.getImage();
		Image newimg = img.getScaledInstance(20,20, Image.SCALE_DEFAULT); 
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(newimg));
		toolBar.add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshActionPerformed(e);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Log Out");
		toolBar.add(btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/exit.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logoutActionPerformed(e);
			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/spl.png")));
		label.setBounds(547, 286, 157, 157);
		info.add(label);
		
		JLabel label_2 = new JLabel("");
		label_2.setFont(new Font("Lato Black", Font.BOLD, 17));
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(UIManager.getColor("TextArea.background"));
		label_2.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/Picture1.png")));
		label_2.setBounds(0, 24, 705, 419);
		info.add(label_2);
		
	}


	/*
	 * 
	 *  Refresh the user info
	 */
	private void refreshActionPerformed(ActionEvent e) {
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			//refresh the info
			currentUser = userDao.getReaderInfo(con, currentUser);

			//refresh the panel
			tabPane.remove(info);
			buildInfoPanel();
			tabPane.add("Account Info",info);
			tabPane.setSelectedIndex(3); 
			
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} finally {
			try {
				if(con!=null) {
					con.close();
				}
					
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}

	/*
	 * Handle the logout event
	 */
	private void logoutActionPerformed(ActionEvent e) {
		
		// dispose the old frame
		dispose();
		// open the new frame
		LoginFrame loginFrm = new LoginFrame();
		loginFrm.setLocationRelativeTo(null);
		loginFrm.setVisible(true);
	}


	/*
	 * Handle the reader search event
	 */
	private void searchActionPerformed(ActionEvent e) {
		
		String searchTarget = this.searchTxt.getText();
		if(StringUtil.isEmpty(searchTarget)) 
		{
			return;
		}
		if (rdbtnBook.isSelected()) {
			Book book = new Book();
			book.setTitle(searchTarget);
			book.setSubject(searchTarget);
			book.setIsbn(searchTarget);
			book.setAuthor(searchTarget);
			this.fillBookTable(book);
		}
		else if (rdbtnActivity.isSelected()) {
			Activity activity = new Activity();
			activity.setActivityName(searchTarget);
			activity.setOrganizer(searchTarget);
//			activity.setLocationId(Integer.valueOf(searchTarget));
			this.fillActivityTable(activity);
			
		}
		
	}
	
	/*
	 * Generate the book search result table
	 * 
	 */
	private void fillBookTable(Book book) {
		
		DefaultTableModel dtm = (DefaultTableModel) ResultTable.getModel();
		dtm.setRowCount(0);
		
		Connection con = null;
		try {
			con = dbUtil.getCon();
			recordNum = 0;
			ResultSet rs = bookDao.searchBook(con, book);
			if (!rs.next()) {
				String noResultStr = "Sorry, there are no result for " + searchTxt.getText() + ".";
				JOptionPane.showMessageDialog(null, noResultStr);
			}
			else 
			{
				do {
					recordNum += 1;
					Vector v = new Vector();
					v.add(rs.getString("Title"));
					v.add(rs.getString("Isbn"));
					v.add(rs.getString("Author"));
//				v.add(rs.getString("Location_id"));
					dtm.addRow(v);
				} while (rs.next());
			}
			lblNewLabel.setText(String.valueOf(recordNum)+" Records in Total");
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(JLabel.CENTER);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Generate the activity search result table
	 * 
	 */
	private void fillActivityTable(Activity activity) {

		DefaultTableModel dtm = (DefaultTableModel) ResultTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			recordNum = 0;
			ResultSet rs = activityDao.searchActivity(con, activity);
			if (!rs.next()) {
				String noResultStr = "Sorry, there are no result for " + searchTxt.getText() + ".";
				JOptionPane.showMessageDialog(null, noResultStr);
			}else {
				do{
					recordNum += 1;
					Vector v = new Vector();
					v.add(rs.getString("Activity_name"));
					v.add(rs.getString("Organizer"));
					v.add(rs.getString("Begin_date"));
					dtm.addRow(v);
				}while (rs.next());
			}
			lblNewLabel.setText(String.valueOf(recordNum)+" Records in Total");
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	}

	
	private void fillHistoryTable(){
		
		DefaultTableModel dtm = (DefaultTableModel) HistoryTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int n = 0;
			ResultSet rs = bookDao.searchBorrowedBook(con, currentUser);
			if(rs.next()) {
			do {
				n+=1;
				Vector v = new Vector();
				v.add(n);
				v.add(rs.getString("Title"));
				v.add(rs.getString("Isbn"));
				v.add(rs.getDate("Borrow_date"));
				// compute expected return date
				Calendar date = Calendar.getInstance();
				date.setTime(rs.getDate("Borrow_date"));
				date.add(Calendar.DATE, +60);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date returnDate = date.getTime();
				Date sqlReturnDate = new Date(returnDate.getTime());
				String expectedReturnDate = df.format(sqlReturnDate);
				v.add(expectedReturnDate);
				dtm.addRow(v);
			} while (rs.next());
			}
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(JLabel.CENTER);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		}
		
		
	}
	/*
	 * handle the mouse pressed event on table row
	 * @param e
	 */
	private void historyTableMousePressed(MouseEvent e) {
		
		int rowIndex = HistoryTable.getSelectedRow();
		String FirstColTxt = (String)HistoryTable.getValueAt(rowIndex, 0);
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			BookLog bookLog = bookDao.getBorrowInfo(con,FirstColTxt);
			BookReturnFrame bookReturnFrm = new BookReturnFrame(bookLog,currentUser);
			bookReturnFrm.setLocationRelativeTo(null);
			bookReturnFrm.setVisible(true);
						
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	}
		
		
	
	/**
	 * handle the mouse pressed event on table row
	 * @param e
	 */
	private void resultTableMousePressed(java.awt.event.MouseEvent e) {
		
		int rowIndex = ResultTable.getSelectedRow();
		String FirstColTxt = (String)ResultTable.getValueAt(rowIndex, 0);
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			if (rdbtnBook.isSelected()) {
				BookLoc bookLoc = bookDao.getBookInfo(con,FirstColTxt);
				BookInfoFrame bookInfoFrm = new BookInfoFrame(bookLoc,currentUser);
				bookInfoFrm.setLocationRelativeTo(null);
				bookInfoFrm.setVisible(true);
				
			}else if(rdbtnActivity.isSelected()) {
				ActivityLoc activityLoc = activityDao.getActivityInfo(con,FirstColTxt);
				ActivityInfoFrame activityInfoFrm = new ActivityInfoFrame(activityLoc);
				activityInfoFrm.setLocationRelativeTo(null);
				activityInfoFrm.setVisible(true);
			}			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	}
}




/**
 * The class handle the more info button in table
 * @author Wang, Yinuo
 *
 */
class MyRender extends AbstractCellEditor implements TableCellRenderer,ActionListener, TableCellEditor{
	 
    private static final long serialVersionUID = 1L;
    private JButton button =null;
    private long buttonId = 0;
    public MyRender(){
        button = new JButton();
        button.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/me.png")));
        button.setBackground(new Color(255, 255, 255));
        button.addActionListener(this);
    }
 
@Override
    public Object getCellEditorValue() {
        // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // TODO Auto-generated method stub
        return button;
    }
 
@Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
//        JOptionPane.showMessageDialog(null, "渲染器学期", "消息", JOptionPane.OK_OPTION);
         showMoreInfo();
    }
 
private void showMoreInfo() {
	// TODO 自动生成的方法存根
	
}

@Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        // TODO Auto-generated method stub
        return button;
    }
     
}