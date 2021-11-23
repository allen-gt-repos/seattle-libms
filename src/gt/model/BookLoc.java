package gt.model;

public class BookLoc {

	
	private long bookId;
	private String title;
	private String author;
	private String publisher;
	private String isbn;
	private String pubYear;
	private String subject;
//	private int locationId;
	private int availableCount;
	
//	private int LocationId;
	private int Floor;
	private String HallName;
	private String HallCoord;
	
	private int Bookshelf;
	private String BookShelfCoord;
	private int Column;
	private int Layer;
	
	
	// getter and setters
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
	public int getAvailableCount() {
		return availableCount;
	}
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}
	public int getFloor() {
		return Floor;
	}
	public void setFloor(int floor) {
		Floor = floor;
	}
	public String getHallName() {
		return HallName;
	}
	public void setHallName(String hallName) {
		HallName = hallName;
	}
	public String getHallCoord() {
		return HallCoord;
	}
	public void setHallCoord(String hallCoord) {
		HallCoord = hallCoord;
	}
	public int getBookshelf() {
		return Bookshelf;
	}
	public void setBookshelf(int bookshelf) {
		Bookshelf = bookshelf;
	}
	public String getBookShelfCoord() {
		return BookShelfCoord;
	}
	public void setBookShelfCoord(String bookShelfCoord) {
		BookShelfCoord = bookShelfCoord;
	}
	public int getColumn() {
		return Column;
	}
	public void setColumn(int column) {
		Column = column;
	}
	public int getLayer() {
		return Layer;
	}
	public void setLayer(int layer) {
		Layer = layer;
	}
	
	
}
