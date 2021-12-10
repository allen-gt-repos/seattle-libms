package gt.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gt.dao.BookDao;
import gt.model.ActivityLoc;
import gt.model.Book;
import gt.model.BookLoc;
import gt.model.NewBook;
import gt.util.DBUtil;
import gt.util.StringUtil;

public class ManageRecommandFrame extends JInternalFrame {
	private JTextField searchTxt;
	private JScrollPane scrollPane;
	private JTable ResultTable;
	
	private DBUtil dbUtil = new DBUtil();
	private BookDao bookDao = new BookDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageRecommandFrame frame = new ManageRecommandFrame();
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
	public ManageRecommandFrame() {
		setBounds(100, 100, 681, 399);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search Book");
		lblNewLabel.setBounds(51, 32, 88, 15);
		getContentPane().add(lblNewLabel);
		
		searchTxt = new JTextField();
		searchTxt.setColumns(10);
		searchTxt.setBounds(144, 30, 416, 24);
		getContentPane().add(searchTxt);
		
		// set table header
		Vector bookColumn = new Vector<>();
		bookColumn.add("Book Title");
		bookColumn.add("ISBN");
		bookColumn.add("Author");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 80, 613, 275);
		scrollPane.setBackground(Color.WHITE);
		getContentPane().add(scrollPane);
		
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
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ManageRecommandFrame.class.getResource("/image/search.png")));
		btnNewButton.setBounds(559, 30, 38, 23);
		getContentPane().add(btnNewButton);
		

	}
	/*
	 * show the search result
	 */
	private void searchActionPerformed(ActionEvent e) {
		String searchTarget = this.searchTxt.getText();
		if(StringUtil.isEmpty(searchTarget)) 
		{
			return;
		}
		else 
		{
			NewBook book = new NewBook();
			book.setTitle(searchTarget);
			book.setSubject(searchTarget);
			book.setIsbn(searchTarget);
			book.setAuthor(searchTarget);
			this.fillBookTable(book);
		}
	}

	/*
	 * fill the result table
	 */
	private void fillBookTable(NewBook book) {
		
		DefaultTableModel dtm = (DefaultTableModel) ResultTable.getModel();
		dtm.setRowCount(0);
		
		Connection con = null;
		try {
			con = dbUtil.getCon();

			ResultSet rs = bookDao.searchBook(con, book);
			if (!rs.next()) {
				String noResultStr = "Sorry, there are no result for " + searchTxt.getText() + ".";
				JOptionPane.showMessageDialog(null, noResultStr);
			}
			else 
			{
				do {
					
					Vector v = new Vector();
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
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	/*
	 * show import panel
	 */
	private void resultTableMousePressed(MouseEvent e) {
		
		int rowIndex = ResultTable.getSelectedRow();
		String TitleStr = (String) ResultTable.getValueAt(rowIndex, 0);
		String IsbnTxt = (String) ResultTable.getValueAt(rowIndex, 1);
		String AuthorTxt = (String) ResultTable.getValueAt(rowIndex, 2);
		
		Connection con = null;
		
		try {
			con = dbUtil.getCon();
			
				NewBook book = bookDao.getBookInfo(con,IsbnTxt, TitleStr,AuthorTxt);
				if (book != null) {
					ImportNewBookFrame importNewBookFrm = new ImportNewBookFrame(book);
					importNewBookFrm.setLocationRelativeTo(null);
					importNewBookFrm.setVisible(true);
				}else {
					System.out.println("Load book info failed.");
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
