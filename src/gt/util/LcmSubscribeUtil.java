/**
 * 
 */
package gt.util;

import java.io.IOException;

import gt.util.lcmtypes.destination_t;
import lcm.lcm.LCM;
import lcm.lcm.LCMDataInputStream;
import lcm.lcm.LCMSubscriber;

/**
 * @author Wang, Yinuo
 *
 */
public class LcmSubscribeUtil implements LCMSubscriber{

	private float[] pvState = new float[2];
	private static String channel = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		subscribeMessage("Destination");
	}
	
	public LcmSubscribeUtil(String channelStr) {
		super();
		this.channel = channelStr;
	}

	public static void subscribeMessage(String channelStr) 
	{
		
		LCM myLcm = LCM.getSingleton();
		myLcm.subscribe(channelStr, new LcmSubscribeUtil(channelStr));
		  while (true)
		    {
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException ex) {
	            }
		    }
		
		
	}
	/**
	 * 
	 */
	 public void messageReceived(LCM lcm, String channel, LCMDataInputStream ins)
	 {
		 	
	        System.out.println("Received message on channel " + channel);
//	        float 
	        try {
//	        	LCM lcm = new LCM();
	            if (channel.equals(this.channel)) {
//	                robot_state_t state_t = new robot_state_t(ins);
	                destination_t des_t = new destination_t(ins);
	                pvState[0] = des_t.des_x;
	                pvState[1] = des_t.des_y;
	                
//	                pvState[0] = state_t.robo_x;
//	                pvState[1] = state_t.robo_y;
//	                pvState[2] = state_t.robo_vx;
//	                pvState[3] = state_t.robo_vy;
	                System.out.println("Current position: (" + pvState[0] + ", " + pvState[1]+")");
	                		//+ "), Current velocity: (" + pvState[2] + "," +pvState[3] + ").");
//	                System.out.println("  timestamp    = " + msg.timestamp);
//	                System.out.println("  position     = [ " + msg.position[0] +
//	                                   ", " + msg.position[1] + ", " + msg.position[2] + " ]");
//	                System.out.println("  orientation  = [ " + msg.orientation[0] +
//	                                   ", " + msg.orientation[1] +
//	                                   ", " + msg.orientation[2] +
//	                                   ", " + msg.orientation[3] + " ]");
//
//	                System.out.print("  ranges       = [ ");
//	                for (int i=0; i<msg.num_ranges; i++) {
//	                    System.out.print("" + msg.ranges[i]);
//	                    if (i < msg.num_ranges-1)
//	                        System.out.print (", ");
//	                }
//	                System.out.println (" ]");
//	                System.out.println("  name         = '" + msg.name + "'");
//	                System.out.println("  enabled      = '" + msg.enabled + "'");
	            }
	           
	        } catch (IOException ex) {
	            System.out.println("Exception: " + ex);
	        }
//			return pvState;
	        
	    }
}
