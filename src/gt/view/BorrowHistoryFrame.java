package gt.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gt.dao.BookDao;
import gt.model.User;
import gt.util.DBUtil;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrowHistoryFrame extends JFrame {

	private JPanel contentPane;
	private JTable HistoryTable;
	
	
	private User currentUser = null;
	private DBUtil dbUtil = new DBUtil();
	private BookDao bookDao = new BookDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowHistoryFrame frame = new BorrowHistoryFrame(null);
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
	public BorrowHistoryFrame(User user) {
		
		currentUser = user;
		setTitle("Borrow History");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 540, 310);
		contentPane.add(scrollPane);

		Vector bookColumn = new Vector<>();
		bookColumn.add("Book Title");
		bookColumn.add("Borrow Date");
		bookColumn.add("Expected Return Date");
		bookColumn.add("Return Date");
		
		HistoryTable = new JTable(0,3);
		HistoryTable.setShowGrid(false);
		HistoryTable.setSize(650, 350);
		HistoryTable.setLocation(50, 60);

		HistoryTable.setBackground(Color.WHITE);
		HistoryTable.setShowVerticalLines(false);
		// set table header
		DefaultTableModel dtm = new DefaultTableModel(null,bookColumn);
		HistoryTable.setModel(dtm);
		HistoryTable.getTableHeader().setReorderingAllowed(false);
		HistoryTable.getTableHeader().setResizingAllowed(false);
		//set row height and column width
		HistoryTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		HistoryTable.getColumnModel().getColumn(1).setPreferredWidth(90);
		HistoryTable.getColumnModel().getColumn(2).setPreferredWidth(160);
		HistoryTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		HistoryTable.setRowHeight(25);
		// set table contents center
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		HistoryTable.setDefaultRenderer(Object.class, dtcr);
//		bookshelf.add(HistoryTable);
		HistoryTable.setShowVerticalLines(false);
		HistoryTable.setShowGrid(false);
		scrollPane.setViewportView(HistoryTable);
		
		this.fillTable();
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnGoBack.setIcon(new ImageIcon(BorrowHistoryFrame.class.getResource("/image/reset.png")));
		btnGoBack.setBounds(213, 334, 117, 25);
		contentPane.add(btnGoBack);
	}

	private void fillTable() {
		DefaultTableModel dtm = (DefaultTableModel) HistoryTable.getModel();
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			ResultSet rs = bookDao.searchBorrowedHistory(con, currentUser);
			if(rs.next()) {
			do {
				Vector v = new Vector();
				v.add(rs.getString("Title"));
				v.add(rs.getDate("Borrow_date"));
				Calendar date = new GregorianCalendar();
				date.add(Calendar.DATE, +60);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date returnDate = date.getTime();
				Date sqlReturnDate = new Date(returnDate.getTime());
				String expectedReturnDate = df.format(sqlReturnDate);
				v.add(expectedReturnDate);
				v.add(rs.getDate("Return_date"));
				dtm.addRow(v);
			} while (rs.next());
			}
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(JLabel.CENTER);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			// TODO: handle finally clause
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

}
