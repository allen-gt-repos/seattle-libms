/**
 * 
 */
package gt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import gt.model.Book;
import gt.model.BookLoc;
import gt.model.BookLog;
import gt.model.Location;
import gt.model.User;
import gt.util.StringUtil;

/**
 * @author Wang, Yinuo
 * Activity entity data access object class
 */
public class BookDao {
	
	/*
	 * handle add new book item events
	 * @return the number of book items that have been updated
	 */
	public int addNewBook(Connection con, Book book) throws Exception{
		
		String sql = "insert into book "
				+ "(Book_id, Title, Author, Isbn, Pub_year,Publisher,Subject,Available_count,Location_id)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setLong(1, book.getBookId());
		pstate.setString(2, book.getTitle());
		pstate.setString(3,	book.getAuthor());
		pstate.setString(4, book.getIsbn());
		pstate.setString(5, book.getPubYear());
		pstate.setString(6, book.getPublisher());
		pstate.setString(7, book.getSubject());
		pstate.setInt(8, book.getAvailableCount());
		pstate.setInt(9, book.getLocationId());
		
		
		return pstate.executeUpdate();
		
	}

	/*
	 * handle the search book events
	 * @return all results
	 */
	public ResultSet searchBook(Connection con, Book book) throws Exception{
		
		StringBuffer sql = new StringBuffer("select * from book");
		if (StringUtil.isNotEmpty(book.getTitle())) {
			sql.append(" or Title like '%"+book.getTitle()+"%'");
			
		}
		if (StringUtil.isNotEmpty(book.getAuthor())) {
			sql.append(" or Author like '%"+book.getAuthor()+"%'");
			
		}
		if (StringUtil.isNotEmpty(book.getSubject())) {
			sql.append(" or Subject like '%"+book.getSubject()+"%'");
			
		}
		if (StringUtil.isNotEmpty(book.getIsbn())) {
			sql.append(" or Isbn like '%"+book.getIsbn()+"%'");
			
		}
		PreparedStatement pstate = con.prepareStatement(sql.toString().replaceFirst("or", "where"));
		return pstate.executeQuery();
		
	}
	/*
	 * Handle the get book info details event
	 * 
	 */
	public BookLoc getBookInfo(Connection con, String TitleStr) throws Exception{
		
		BookLoc resultBookLoc = null;
		
		String sql = "select book.*, location.* from book, location "
				+ "where book.Title=? and book.Location_id=location.Location_id";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, TitleStr);
		
		ResultSet rs = pstate.executeQuery();
		if (rs.next()) {
			resultBookLoc = new BookLoc();
			resultBookLoc.setBookId(rs.getInt("Book_id"));
			resultBookLoc.setTitle(rs.getString("Title"));
			resultBookLoc.setAuthor(rs.getString("Author"));
			resultBookLoc.setIsbn(rs.getString("Isbn"));
			resultBookLoc.setPublisher(rs.getString("Publisher"));
			resultBookLoc.setPubYear(rs.getString("Pub_Year"));
			resultBookLoc.setSubject(rs.getString("Subject"));
			resultBookLoc.setAvailableCount(rs.getInt("Available_count"));
			
			resultBookLoc.setFloor(rs.getInt("Floor#"));
			resultBookLoc.setHallName(rs.getString("Hall_name"));
			resultBookLoc.setBookshelf(rs.getInt("Bookshelf#"));
			resultBookLoc.setColumn(rs.getInt("Column#"));
			resultBookLoc.setLayer(rs.getInt("Layer#"));
		}
		return resultBookLoc;
	}
	
	/*
	 * Handle the get book info details event
	 * 
	 */
	public BookLog getBorrowInfo(Connection con, String TitleStr) throws Exception{
		
		BookLog resultBook = null;
		
		String sql = "select book.*, borrow_return.* from book, borrow_return "
				+ "where book.Title=? and book.Book_id=borrow_return.Book_id";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, TitleStr);
		
		ResultSet rs = pstate.executeQuery();
		if (rs.next()) {
			resultBook = new BookLog();
			resultBook.setBookId(rs.getInt("Book_id"));
			resultBook.setTitle(rs.getString("Title"));
			resultBook.setAuthor(rs.getString("Author"));
			resultBook.setIsbn(rs.getString("Isbn"));
			resultBook.setPublisher(rs.getString("Publisher"));
			resultBook.setPubYear(rs.getString("Pub_Year"));
			resultBook.setSubject(rs.getString("Subject"));
			resultBook.setAvailableCount(rs.getInt("Available_count"));
			
			resultBook.setBorrowDate(rs.getDate("Borrow_date"));
			resultBook.setReturnDate(rs.getDate("Return_date"));
		}
		return resultBook;
	}
	
	/*
	 * Delete a book item
	 * @return the number of book items that have been updated
	 */
	public int deleteBook(Connection con, Book book) throws Exception{
		
		String sql = "delete from book where Book_id=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, String.valueOf(book.getBookId()));
		
		return pstate.executeUpdate();
	}
	
	/*
	 * Update the book item
	 * @return the number of book items that have been updated
	 */
	public int updateBook(Connection con, Book book) throws Exception{
		
		String sql = "update book set Title=?, Author=?, Isbn=?, Pub_year=?, Publisher=?, Subject=?, Avialable_count=?, Location_id=? where Book_id=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, book.getTitle());
		pstate.setString(2, book.getAuthor());
		pstate.setString(3, book.getIsbn());
		pstate.setString(4, book.getPubYear());
		pstate.setString(5, book.getPublisher());
		pstate.setString(6, book.getSubject());
		pstate.setInt(7, book.getAvailableCount());
		pstate.setInt(8, book.getLocationId());
		pstate.setInt(9, book.getBookId());
	
		return pstate.executeUpdate();
	}
	
	/*
	 * Update the book item
	 * @return the number of book items that have been updated
	 */
	public int updateBook(Connection con, BookLoc bookLoc) throws Exception{
		
		String sql = "update book set Available_count=? where Book_id=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setInt(1, bookLoc.getAvailableCount());
		pstate.setInt(2, bookLoc.getBookId());
	
		return pstate.executeUpdate();
	}
	/*
	 * Update the book item
	 * @return the number of book items that have been updated
	 */
	public int updateBook(Connection con, BookLog bookLog) throws Exception{
		
		String sql = "update book set Available_count=? where Book_id=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setInt(1, bookLog.getAvailableCount());
		pstate.setInt(2, bookLog.getBookId());
	
		return pstate.executeUpdate();
	}
	/*
	 * Check this book has been borrowed or not
	 */
	public boolean checkBorrowedBook(Connection con, BookLoc bookLoc, User user) throws Exception{
		
		String sql = "select * from borrow_return where Book_id=? and User_id=? and Return_date is null";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setInt(1, bookLoc.getBookId());
		pstate.setString(2, user.getUserId());
		ResultSet rs = pstate.executeQuery();
		if (rs.next()) {
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/*
	 * Search those book currently borrowed by current user
	 */
	public ResultSet searchBorrowedBook(Connection con, User user)throws Exception{
		
		String sql = "select borrow_return.*, book.* from borrow_return, book "
				+ "where User_id=? and Return_date is null and book.Book_id=borrow_return.Book_id";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, user.getUserId());
		
		
		return pstate.executeQuery();
	}
	
	/*
	 * Search those book borrowed by current user
	 */
	public ResultSet searchBorrowedHistory(Connection con, User user)throws Exception{
		
		String sql = "select borrow_return.*, book.* from borrow_return, book "
				+ "where User_id=? and book.Book_id=borrow_return.Book_id";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, user.getUserId());
		
		
		return pstate.executeQuery();
	}
}
