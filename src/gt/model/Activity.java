package gt.model;

import java.sql.Date;
import java.sql.Time;

/**
 * @author Wang, Yinuo
 * Activity entity relation model
 */
public class Activity {
	
	private String activityName;
	private String organizer;
	private Date beginDate;
	private Time beginTime;
	private Time endTime;
	private int locationId;
	
	// default constructor
	public Activity() {
		super();
	}
		
	// parameter constructor
	public Activity(String activityName, String organizer, Date beginDate, 
			Time beginTime, Time endTime, int locationId) {
		super();
		this.activityName = activityName;
		this.organizer = organizer;
		this.beginDate = beginDate;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.locationId = locationId;
	}
		
	// get and set function of class members
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
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	
	
	
	
}
