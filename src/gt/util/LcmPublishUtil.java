/**
 * 
 */
package gt.util;
import java.io.*;
import gt.view.NavigationDialog;
import lcm.lcm.*;
import gt.util.lcmtypes.*;
/**
 * @author Wang, Yinuo
 * The LCM message publish class
 */
public class LcmPublishUtil extends Thread {

	private float pvState[] = null;
	private static LCM lcm = null;

//	private static float[] coordBias = {(float) -2.5, (float) 0.55};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		float coord[] = {0.00f,0.11f};
		LcmPublishUtil t1 = new LcmPublishUtil(coord);
		t1.start();
		
	}
	
	public LcmPublishUtil() {
		super();
	}

	public LcmPublishUtil(float[] pvState) {
		super();
		this.pvState = pvState;
	}

	/**
	 * The thread start function for LCM
	 */
	public void run() {
		
			lcm = LCM.getSingleton();
			while (true) {
				try {
					try {
						sendMessage(pvState, "Destination");
					} catch (IOException e) {
						e.printStackTrace();
					}
			         Thread.sleep(10);
				} catch (InterruptedException e) {
				// TODO: handle exception
					break;
				}
			}
	}
	 
	 /**
	  * callback function for publish thread
	  * @param coordFloat
	  */
	public static void sendMessage(float[] coordFloat, String channel) throws IOException 
	{
		
		destination_t des_t = new destination_t();
		des_t.des_x = coordFloat[0];// + coordBias[0];
		des_t.des_y = coordFloat[1];// + coordBias[1];
		lcm.publish (channel, des_t);         

	}

}
