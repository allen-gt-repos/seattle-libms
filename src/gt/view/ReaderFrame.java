package gt.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.w3c.dom.events.MouseEvent;

import gt.dao.ActivityDao;
import gt.dao.BookDao;
import gt.model.Activity;
import gt.model.ActivityLoc;
import gt.model.Book;
import gt.model.BookLoc;
import gt.util.DBUtil;
import gt.util.StringUtil;
import java.awt.event.MouseAdapter;


public class ReaderFrame extends JFrame {

	private JPanel search;
	private JPanel info;
	private JTabbedPane tabPane;
	private JScrollPane scrollPane;
	private JTable ResultTable;
	private JRadioButton rdbtnBook, rdbtnActivity;
	private JTextField searchTxt;
	private JLabel lblNewLabel;
	
	private DBUtil dbUtil = new DBUtil();
	private BookDao bookDao = new BookDao();
	private ActivityDao activityDao = new ActivityDao();
	
	private long recordNum = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderFrame frame = new ReaderFrame();
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
	public ReaderFrame() {
		setTitle("Seattle Public Library Reader System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 500);
		// create tab panel
		tabPane = new JTabbedPane();
		
		// create panel
		buildSearchPanel();
		buildInfoPanel();
		
		tabPane.add("Search",search);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 164, 613, 247);
		scrollPane.setBackground(Color.white);
		search.add(scrollPane);
		
		// set table header
		Vector bookColumn = new Vector<>();
		bookColumn.add("Book Title");
		bookColumn.add("ISBN");
		bookColumn.add("Author");
//		bookColumn.add("Location");
//		bookColumn.add("More Info");
		
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
		
		// add button to table
//        ResultTable.getColumnModel().getColumn(4).setCellEditor(new MyRender());
//        ResultTable.getColumnModel().getColumn(4).setCellRenderer(new MyRender());
		
		// set table contents center
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		ResultTable.setDefaultRenderer(Object.class, dtcr);
		scrollPane.setViewportView(ResultTable);
		

		tabPane.add("Account Info",info);
		tabPane.setSelectedIndex(0); 

		// add tab panel to JFrame
		getContentPane().add(tabPane);
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
		lblSeattlePublicLibrary.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeattlePublicLibrary.setFont(new Font("Century Schoolbook L", Font.BOLD, 22));
		lblSeattlePublicLibrary.setBounds(174, 45, 310, 27);
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
		rdbtnBook = new JRadioButton("Book");
		
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
		rdbtnActivity.setBackground(Color.WHITE);
		rdbtnActivity.setBounds(354, 133, 149, 23);
		search.add(rdbtnActivity);
		
		
		rdbtnBook.setSelected(true);
		rdbtnBook.setBackground(Color.WHITE);
		rdbtnBook.setBounds(174, 133, 149, 23);
		search.add(rdbtnBook);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(58, 416, 223, 15);
		lblNewLabel.setText(String.valueOf(recordNum)+" Records in Total");
		search.add(lblNewLabel);
	}


	/*
	 * 
	 * The Account Info page
	 */
	private void buildInfoPanel() {
		
		info = new JPanel();
		info.setBackground(new Color(255, 255, 255));
		info.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(37, 67, 125, 15);
		info.add(lblUsername);
		
		JLabel lblReaderName = new JLabel("Reader Name:");
		lblReaderName.setBounds(37, 108, 125, 15);
		info.add(lblReaderName);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(37, 161, 125, 15);
		info.add(lblEmail);
		
		JLabel lblAvailableQuantity = new JLabel("Available Quantity:");
		lblAvailableQuantity.setBounds(37, 258, 156, 15);
		info.add(lblAvailableQuantity);
		
		JLabel lblEmail_1_1 = new JLabel("Borrowed Quantity:");
		lblEmail_1_1.setBounds(37, 209, 156, 15);
		info.add(lblEmail_1_1);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 705, 30);
		info.add(toolBar);
		
		JButton btnNewButton_1 = new JButton("Update Info");
		toolBar.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/edit.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateInfoActionPerformed(e);
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton btnNewButton = new JButton("Change Password");
		toolBar.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/password.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePwdActionPerformed(e);
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		
		JButton btnNewButton_2 = new JButton("Log Out");
		toolBar.add(btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon(ReaderFrame.class.getResource("/image/exit.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logoutActionPerformed(e);
			}
		});
	}
	
	protected void updateInfoActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}

	protected void changePwdActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
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
//		return recordNum;
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
//		return recordNum;
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
				BookInfoFrame bookInfoFrm = new BookInfoFrame(bookLoc);
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