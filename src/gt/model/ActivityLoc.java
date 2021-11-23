package gt.model;

import java.sql.Date;
import java.sql.Time;

public class ActivityLoc {
	
	
	private String activityName;
	private String organizer;
	private Date beginDate;
	private Time beginTime;
	private Time endTime;

	private int Floor;
	private String HallName;
	private String HallCoord;
	
	private int Bookshelf;
	private String BookShelfCoord;
	private int Column;
	private int Layer;
	
	
	// getters and setters
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getOrganizer() {
		return organizer;
	}
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Time getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Time beginTime) {
		this.beginTime = beginTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
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
