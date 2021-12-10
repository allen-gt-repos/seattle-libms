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
import gt.model.NewBook;
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
//		System.out.println(sql.toString());
		return pstate.executeQuery();
		
	}
	/*
	 * handle the search book events
	 * @return all results
	 */
	public ResultSet searchBook(Connection con, NewBook book) throws Exception{
		
		StringBuffer sql = new StringBuffer("select * from new_book where Rcmd_date in (select min(Rcmd_date) from new_book group by Title,Author,Isbn) ");
		if (StringUtil.isNotEmpty(book.getTitle())) {
			sql.append(" and (Title like '%"+book.getTitle()+"%'");
			
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
		sql.append(")");
		
		String sqlStr = sql.toString();
		PreparedStatement pstate = con.prepareStatement(sqlStr);
		return pstate.executeQuery();
		
	}
	/*
	 * Handle the get book info details event
	 * 
	 */
	public BookLoc getBookInfo(Connection con, String IsbnStr) throws Exception{
		
		BookLoc resultBookLoc = null;
		
		String sql = "select book.*, location.* from book, location "
				+ "where book.Isbn=? and book.Location_id=location.Location_id";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, IsbnStr);
		
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
			
			resultBookLoc.setFloor(rs.getInt("Floor"));
			resultBookLoc.setHallName(rs.getString("Hall_name"));
			resultBookLoc.setHallCoord(rs.getString("Hall_coordinate"));
			resultBookLoc.setBookshelf(rs.getInt("Bookshelf"));
			resultBookLoc.setBookShelfCoord(rs.getString("Bookshelf_coordinate"));
			resultBookLoc.setColumn(rs.getInt("Column"));
			resultBookLoc.setLayer(rs.getInt("Layer"));
		}
		return resultBookLoc;
	}
	/*
	 * Handle the get book info details event
	 * 
	 */
	public BookLoc getBookInfo(Connection con, String IsbnStr, int bookId) throws Exception{
		
		BookLoc resultBookLoc = null;
		
		String sql = "select book.*, location.* from book, location "
				+ "where (book.Isbn=? or book.Book_id=?) and book.Location_id=location.Location_id";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, IsbnStr);
		pstate.setInt(2, bookId);
		
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
			
			resultBookLoc.setFloor(rs.getInt("Floor"));
			resultBookLoc.setHallName(rs.getString("Hall_name"));
			resultBookLoc.setHallCoord(rs.getString("Hall_coordinate"));
			resultBookLoc.setBookshelf(rs.getInt("Bookshelf"));
			resultBookLoc.setBookShelfCoord(rs.getString("Bookshelf_coordinate"));
			resultBookLoc.setColumn(rs.getInt("Column"));
			resultBookLoc.setLayer(rs.getInt("Layer"));
		}
		return resultBookLoc;
	}
	/*
	 * Handle the get recommend book info details event
	 * 
	 */
	public NewBook getBookInfo(Connection con, String IsbnStr, String TitleStr, String AuthorStr) throws Exception{
		
		NewBook resultBook = null;
		
		String sql = "select * from new_book where Rcmd_date in (select min(Rcmd_date) from new_book group by Title,Author,Isbn) "
				+ "and Title=? and Isbn=? and Author=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, TitleStr);
		pstate.setString(2, IsbnStr);
		pstate.setString(3, AuthorStr);
		
		ResultSet rs = pstate.executeQuery();
		if (rs.next()) {
			resultBook = new NewBook();
			
			resultBook.setTitle(rs.getString("Title"));
			resultBook.setAuthor(rs.getString("Author"));
			resultBook.setIsbn(rs.getString("Isbn"));
			resultBook.setPublisher(rs.getString("Publisher"));
			resultBook.setPubYear(rs.getString("Pub_Year"));
			resultBook.setSubject(rs.getString("Subject"));
		}
		return resultBook;
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
		
		String sql = "update book set Title=?, Author=?, Isbn=?, Pub_year=?, Publisher=?, Subject=?, Available_count=?, Location_id=? where Book_id=?";
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
	
	/*
	 * get the user's recommend book
	 */
	public ResultSet getRecommendBook(Connection con, User user) throws Exception{
		
		String sql = "select * from new_book where User_id=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, user.getUserId());
//		pstate.setString(2, user.getUserId());
		return pstate.executeQuery(); 
		
	}
	
	/*
	 * get recommend book details
	 */
	public NewBook getRecommendBookInfo(Connection con, String title, User user) throws Exception{
		
		String sql = "select * from new_book where Title=? and User_id=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, title);
		pstate.setString(2, user.getUserId());
		ResultSet rs = pstate.executeQuery(); 
		NewBook resultBook = null;
		if (rs.next()) {
			resultBook = new NewBook();
			resultBook.setTitle(rs.getString("Title"));
			resultBook.setAuthor(rs.getString("Author"));
			resultBook.setIsbn(rs.getString("Isbn"));
			resultBook.setPublisher(rs.getString("Publisher"));
			resultBook.setPubYear(rs.getString("Pub_Year"));
			resultBook.setSubject(rs.getString("Subject"));
			resultBook.setUserId(rs.getString("User_id"));
			resultBook.setRecommendDate(rs.getDate("Rcmd_date"));
		}
		return resultBook;
		
		
	}
	
	/*
	 * delete the recommend book record
	 */
	public int deleteRecommendBook(Connection con , NewBook newBook) throws Exception{
		
		
		String sql = "delete from new_book where Title=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, newBook.getTitle());

		
		return pstate.executeUpdate();
		
	}
	
	/*
	 * add new recommend book
	 */
	public int addRecommendBook(Connection con , NewBook newBook) throws Exception{
		
		String sql = "insert into new_book values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, newBook.getUserId());
		pstate.setString(2, newBook.getIsbn());
		pstate.setString(3, newBook.getTitle());
		pstate.setString(4, newBook.getAuthor());
		pstate.setString(5, newBook.getPubYear());
		pstate.setString(6, newBook.getPublisher());
		pstate.setString(7, newBook.getSubject());
		pstate.setDate(8, newBook.getRecommendDate());
		
		
		return pstate.executeUpdate();
		
	}
	/*
	 * Check this book has been recommended by the same reader or not
	 */
	public boolean checkRecommendedBook(Connection con, NewBook newBook) throws Exception{
		
		String sql = "select * from new_book where User_id=? and Isbn=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, newBook.getUserId());
//		pstate.setString(2, newBook.getTitle());
		pstate.setString(2, newBook.getIsbn());
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
	 * check the book has been in the library or not
	 */
	public boolean checkExistBook(Connection con, NewBook newBook) throws Exception{
		
		StringBuffer sql = new StringBuffer("select * from book");
		if (StringUtil.isNotEmpty(newBook.getTitle())) {
			sql.append(" and Title like '%"+newBook.getTitle()+"%'");
			
		}
		if (StringUtil.isNotEmpty(newBook.getAuthor())) {
			sql.append(" and Author like '%"+newBook.getAuthor()+"%'");
			
		}
		if (StringUtil.isNotEmpty(newBook.getSubject())) {
			sql.append(" and Subject like '%"+newBook.getSubject()+"%'");
			
		}
		if (StringUtil.isNotEmpty(newBook.getIsbn())) {
			sql.append(" and Isbn like '%"+newBook.getIsbn()+"%'");
			
		}
		if (StringUtil.isNotEmpty(newBook.getPubYear())) {
			sql.append(" and Isbn like '%"+newBook.getPubYear()+"%'");
		}
		if (StringUtil.isNotEmpty(newBook.getPublisher())) {
			sql.append(" and Isbn like '%"+newBook.getPublisher()+"%'");
		}
		PreparedStatement pstate = con.prepareStatement(sql.toString().replaceFirst("and", "where"));
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
	 * Check if the input location info is valid, return the location_id if existed or return 0 
	 */
	public int checkValidLocation(Connection con, BookLoc loc) throws Exception{
		
		String sql = "select location.Location_id from location "
				+ "where Floor=? and Hall_name=? and Bookshelf=? and location.Column=? and location.Layer=?";
		
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setInt(1, loc.getFloor());
		pstate.setString(2, loc.getHallName());
		pstate.setInt(3, loc.getBookshelf());
		pstate.setInt(4, loc.getColumn());
		pstate.setInt(5, loc.getLayer());
		ResultSet rs = pstate.executeQuery();
		if (!rs.next()) {
			return 0;
		}else {
			return rs.getInt("Location_id");
		}
		
	}
	 
	/*
	 * Check if all book copys have been returned before delete a book item
	 */
	public boolean checkAllReturned(Connection con, Book book) throws Exception{
		
		String sql = "select * from borrow_return where borrow_return.Book_id=? and Return_date is null";
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setInt(1, book.getBookId());
		ResultSet rs = pstate.executeQuery();
		if (rs.next()) {
			return false;
		}else {
			return true;
		}
	}
}
