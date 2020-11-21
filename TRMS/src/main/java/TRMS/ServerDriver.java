/**
 * 
 */
package TRMS;



import io.javalin.Javalin;

/**
 * @author Zachary Leonardo
 *
 */
public class ServerDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(9091);

	}

}
