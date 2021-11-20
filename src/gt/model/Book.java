package gt.model;

/**
 * @author Wang, Yinuo
 * User entity relation model
 */
public class Book {
	
	private long bookId;
	private String title;
	private String author;
	private String publisher;
	private String isbn;
	private String pubYear;
	private String subject;
	private int locationId;
	private int availableCount;
	
	
	
	public Book() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	
	public Book(long bookId, String title, String author, String publisher, String isbn, String pubYear, String subject,
			int locationId, int availableCount) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.pubYear = pubYear;
		this.subject = subject;
		this.locationId = locationId;
		this.availableCount = availableCount;
	}


	/*
	 * get data and set data 
	 */
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
		
		
		
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPubYear() {
		return pubYear;
	}
	public void setPubYear(String pubYear) {
		this.pubYear = pubYear;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}
	
	

}
