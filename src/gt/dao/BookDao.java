/**
 * 
 */
package gt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import gt.model.Book;

/**
 * @author Wang, Yinuo
 *
 */
public class BookDao {
	
	
	public BookDao() {
		super();
		// TODO 自动生成的构造函数存根
	}

	
	
	public int addNewBook(Connection con, Book book) throws Exception{
		
		String sql = "insert into book (Book_id, Title, Isbn,Author,Pub_year,Publisher,Subject,Available_count,Location_id) values(null,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setLong(1,book.getBookId());
		pstate.setString(2, book.getTitle());
		pstate.setString(3,book.getIsbn());
		pstate.setString(4, book.getAuthor());
		pstate.setString(5, book.getPubYear());
		pstate.setString(6, book.getPublisher());
		pstate.setString(7, book.getSubject());
		pstate.setInt(8,book.getAvailableCount());
		pstate.setInt(9, book.getLocationId());
		
		return pstate.executeUpdate();
		
	}

}
