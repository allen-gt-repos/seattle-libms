package gt.model;

import java.sql.Date;

public class NewBook {
	
	private String userId;
	private String title;
	private String author;
	private String publisher;
	private String isbn;
	private String pubYear;
	private String subject;
	private Date recommendDate;
	
	// default constructor
	public NewBook() {
		super();
		// TODO 自动生成的构造函数存根
	}
	// parameter constructor
	public NewBook(String userId, String title, String author, String publisher, String isbn, String pubYear,
			String subject, Date recommendDate) {
		super();
		this.userId = userId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.pubYear = pubYear;
		this.subject = subject;
		this.recommendDate = recommendDate;
	}
	// setters and getters
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
	public Date getRecommendDate() {
		return recommendDate;
	}
	public void setRecommendDate(Date recommendDate) {
		this.recommendDate = recommendDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
