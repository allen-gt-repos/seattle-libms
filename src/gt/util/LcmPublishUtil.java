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
 *
 */
public class LcmPublishUtil extends Thread {

	private float pvState[] = null;
	private static LCM lcm = null;

//	private static float[] coordBias = {(float) -2.5, (float) 0.55};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		float coord[] = {0.00f,0.11f};
		LcmPublishUtil t1 = new LcmPublishUtil(coord);
		t1.start();
		
	}
	
	public LcmPublishUtil() {
		super();
		// TODO 自动生成的构造函数存根
	}


	public LcmPublishUtil(float[] pvState) {
		super();
		this.pvState = pvState;
	}

	public void run() {
//		System.out.println("in run function");
		
			lcm = LCM.getSingleton();
			while (true) {
				try {
					try {
						sendMessage(pvState, "Destination");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
			         Thread.sleep(10);
				} catch (InterruptedException e) {
				// TODO: handle exception
					break;
				}
			}
	}
//	 public void start() {
//		 System.out.println("in start function");
//		 Thread t = new Thread(this);
//		 t.start();
//	 }
//	 
	 /**
	  * 
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
