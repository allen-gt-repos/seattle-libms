/**
 * 
 */
package gt.util;

import java.io.IOException;
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
public class testlcm extends Thread implements LCMSubscriber {

	private static float[] pvState = new float[4];
	private static String channel = null;
//	private static Navigation navigation;// = new Navigation();
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		testlcm t1 = new testlcm("RobotState");
		t1.start();
//		run("RobotState");
	}
	
	public testlcm(String channelStr) {
		super();
//		this.navigation = nav;
		this.channel = channelStr;
	}

	public void run() 
	{
		
		LCM myLcm = LCM.getSingleton();
		myLcm.subscribe(channel, new testlcm(channel));
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
		 	
	        System.out.println("Received message on channel " + channel);

	        try {
//	        	LCM lcm = new LCM();
	            if (channel.equals(this.channel)) {
	                robot_state_t state_t = new robot_state_t(ins);
//	                destination_t des_t = new destination_t(ins);
//	                pvState[0] = des_t.des_x;
//	                pvState[1] = des_t.des_y;
	                
	                pvState[0] = state_t.robo_x;
	                pvState[1] = state_t.robo_y;
	                pvState[2] = state_t.robo_vx;
	                pvState[3] = state_t.robo_vy;
	                System.out.println("Current position: (" + pvState[0] + ", " + pvState[1]+ "), Current velocity: (" + pvState[2] + "," +pvState[3] + ").");

	            }
//	            if (Math.abs(navigation.coordinate[0] - navigation.roboState[0])<0.02 && Math.abs(navigation.coordinate[1] - navigation.roboState[1])<0.02
//	            		&& Math.abs(navigation.roboState[3]) <= 0.01 && Math.abs(navigation.roboState[4]) <=0.2) {
//					navigation.endNav();
//				}
//	            try {
////					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO 自动生成的 catch 块
//					e.printStackTrace();
//				}
	        } catch (IOException ex) {
	            System.out.println("Exception: " + ex);
	        }
//			return pvState;
	        
	    }
	 /*
	  * Get the robot states
	  */
	 public static float[] getRoboState() {
		 
		 return pvState;
	 }
}
