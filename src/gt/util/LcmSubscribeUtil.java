/**
 * 
 */
package gt.util;

import java.io.IOException;

import javax.swing.JOptionPane;

import gt.util.lcmtypes.robot_state_t;
import gt.view.NavigationDialog;
import gt.util.lcmtypes.destination_t;
import lcm.lcm.LCM;
import lcm.lcm.LCMDataInputStream;
import lcm.lcm.LCMSubscriber;

/**
 * @author Wang, Yinuo
 *
 */
public class LcmSubscribeUtil extends Thread implements LCMSubscriber {

	private float[] pvState = new float[4];
	private static String channel = null;
	private static float[] destination = new float[2];
	private static boolean navFlag = false;
//	private static Navigation navigation = null;// = new Navigation();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		LcmSubscribeUtil t1 = new LcmSubscribeUtil("RobotState");
		t1.start();
	}
	
	public LcmSubscribeUtil(String channelStr) {
		super();
//		this.navigation = nav;
		this.channel = channelStr;
	}

	public void run() 
	{
		LCM myLcm;
		
		try {
			myLcm = LCM.getSingleton();
			myLcm.subscribe(channel, new LcmSubscribeUtil(channel));

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		  while (true)
		    {
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException ex) {
	            }
		    }
		
		
	}
	/**
	 * callback function to receive the data
	 */
	 public void messageReceived(LCM lcm, String channel, LCMDataInputStream ins)
	 {
		 	
//	        System.out.println("Received message on channel " + channel);

	        try {
//	        	lcm = new LCM();
//	            if (channel.equals(this.channel)) {
//	                robot_state_t state_t = new robot_state_t(ins);
////	                destination_t des_t = new destination_t(ins);
////	                pvState[0] = des_t.des_x;
////	                pvState[1] = des_t.des_y;
//	                
//	                pvState[0] = state_t.robo_x;
//	                pvState[1] = state_t.robo_y;
//	                pvState[2] = state_t.robo_vx;
//	                pvState[3] = state_t.robo_vy;
//	                System.out.println("Current position: (" + pvState[0] + ", " + pvState[1]+ "), Current velocity: (" + pvState[2] + "," +pvState[3] + ").");
//
//	            }
	        	System.out.println(destination[0]+ " "+pvState[0]+ " "+ navFlag);
	        	
	            if (navFlag && Math.abs(destination[0] - pvState[0])<0.1 && Math.abs(destination[1] - pvState[1])<0.1
	            		&& Math.abs(pvState[3]) <= 0.01 && Math.abs(pvState[2]) <=0.2) {
	            	System.out.println("arrive");
	            	JOptionPane.showMessageDialog(null, "Robot has reach the destination!");
	            	navFlag = false;
	            	
					
				}else {
	                robot_state_t state_t = new robot_state_t(ins);
	                
	                pvState[0] = state_t.robo_x;
	                pvState[1] = state_t.robo_y;
	                pvState[2] = state_t.robo_vx;
	                pvState[3] = state_t.robo_vy;
//	                System.out.println("Current position: (" + navigation.roboState[0] + ", " + navigation.roboState[1]+ "), Current velocity: (" + navigation.roboState[2] + "," +navigation.roboState[3] + ").");
				}

	        } catch (Exception e) {
//	            System.out.println("Exception: " + ex);
	        }

	        
	    }
	 /*
	  * Get the robot states
	  */
	 public float[] getRoboState() {
		 
		 return pvState;
	 }
	 public static void setDestination(float[] des) {
		 
		 destination[0] = des[0];
		 destination[1] = des[1];
	 }
	 public static void setNavFlag() {
		 
		 navFlag = true;
	 }
}
