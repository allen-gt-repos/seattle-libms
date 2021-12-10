package gt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import gt.model.Activity;
import gt.model.ActivityLoc;
import gt.util.StringUtil;

/**
 * 
 * @author Wang, Yinuo
 * Activity entity data access object class
 */
public class ActivityDao {

	/*
	 * handle add new activity item events
	 */
	public int addNewBook(Connection con, Activity activity) throws Exception{
		
		String sql = "insert into activity "
				+ "(Activity_name, Location_id, Begin_date, Begin_time, End_time, Organizer) "
				+ "values(?,?,?,?,?,?)";
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setString(1, activity.getActivityName());
		pstate.setInt(2, activity.getLocationId());
		pstate.setDate(3, activity.getBeginDate());
		pstate.setTime(4, activity.getBeginTime());
		pstate.setTime(5, activity.getEndTime());
		pstate.setString(6, activity.getOrganizer());
		
		return pstate.executeUpdate();
		
	}

	/*
	 * handle activity search event
	 * 
	 */
	public ResultSet searchActivity(Connection con, Activity activity) throws Exception {
		
		StringBuffer sql = new StringBuffer("select * from activity");
		if (StringUtil.isNotEmpty(activity.getActivityName())) {
			sql.append(" or Activity_name like '%"+activity.getActivityName()+"%'");
			
		}
		if (StringUtil.isNotEmpty(activity.getOrganizer())) {
			sql.append(" or Organizer like '%"+activity.getOrganizer()+"%'");
			
		}
//		if (StringUtil.isNotEmpty(String.valueOf(activity.getLocationId()))) {
//			sql.append(" or Activity_name like '%"+activity.getActivityName()+"%'");
//			
//		}
		PreparedStatement pstate = con.prepareStatement(sql.toString().replaceFirst("or", "where"));
		return pstate.executeQuery();
		
	}
	
	/*
	 * Get activity more info
	 * 
	 */
	public ActivityLoc getActivityInfo(Connection con, String NameStr, String DateStr) throws Exception{
		
		ActivityLoc resultActivityLoc = null;
		String sql = "select activity.*, location.* from activity, location "
				+ "where activity.Activity_name=? and Begin_date=? and activity.Location_id=location.Location_id";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, NameStr);
		pstate.setDate(2, Date.valueOf(DateStr));
		
		ResultSet rs = pstate.executeQuery();
		if (rs.next()) {
			resultActivityLoc = new ActivityLoc();
	
			resultActivityLoc.setActivityName(rs.getString("Activity_name"));
			resultActivityLoc.setOrganizer(rs.getString("Organizer"));
			resultActivityLoc.setBeginDate(rs.getDate("Begin_date"));
			resultActivityLoc.setBeginTime(rs.getTime("Begin_time"));
			resultActivityLoc.setEndTime(rs.getTime("End_time"));
			
			resultActivityLoc.setFloor(rs.getInt("Floor"));
			resultActivityLoc.setHallName(rs.getString("Hall_name"));
			resultActivityLoc.setHallCoord(rs.getString("Hall_coordinate"));
			resultActivityLoc.setBookshelf(rs.getInt("Bookshelf"));
			resultActivityLoc.setBookShelfCoord(rs.getString("Bookshelf_coordinate"));
			resultActivityLoc.setColumn(rs.getInt("Column"));
			resultActivityLoc.setLayer(rs.getInt("Layer"));
		}
		else {
			System.out.println("[ActivityDao] No result error.");
		}
		
		return resultActivityLoc;
		
	}
	
	/*
	 * delete the activity record
	 */
	public int deleteActivity(Connection con, Activity activity) throws Exception{
		
		
		String sql = "delete from activity where Activity_name=? and Begin_date=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, activity.getActivityName());
		pstate.setDate(2, activity.getBeginDate());
		
		
		return pstate.executeUpdate();
	}
	
	/*
	 * update the activity info
	 */
	public int updateActivity(Connection con, Activity activity, ActivityLoc activityOld) throws Exception{
		
		String sql = "update activity set Activity_name=?, Location_id=?, Begin_date=?, Begin_time=?, End_time=?, Organizer=? where Activity_name=? and Begin_date=?";
		PreparedStatement pstate = con.prepareStatement(sql);
		pstate.setString(1, activity.getActivityName());
		pstate.setDate(3, activity.getBeginDate());
		pstate.setInt(2, activity.getLocationId());
		pstate.setTime(4, activity.getBeginTime());
		pstate.setTime(5, activity.getEndTime());
		pstate.setString(6, activity.getOrganizer());
		pstate.setString(7, activityOld.getActivityName());
		pstate.setDate(8, activityOld.getBeginDate());
		
		return pstate.executeUpdate();
	}
	
	/*
	 * Check if the input location info is valid, return the location_id if existed or return 0 
	 */
	public int checkValidLocation(Connection con, ActivityLoc loc) throws Exception{
		
		String sql = "select location.Location_id from location where Floor=? and Hall_name=? ";
		
		PreparedStatement pstate = con.prepareStatement(sql);
		
		pstate.setInt(1, loc.getFloor());
		pstate.setString(2, loc.getHallName());
		ResultSet rs = pstate.executeQuery();
		if (!rs.next()) {
			return 0;
		}else {
			return rs.getInt("Location_id");
		}
		
	}
}
