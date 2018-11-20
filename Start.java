package gym_simulation;

/** Start the simulation using the gym thread first. 
 * @author  James Spaniak
 * @version 1.0.0
 * @since   2018-10-15
 */
public class Start {
    public static void main(String[] args){
    	if(args.length<2) {
    		System.out.print("Enter gym capacity and total clients in format of <gym_capacity> <total_clients>.");
    	}
    	else {
    		int gym_cap = 0;
    		int clients = 0;
    		try {
    			gym_cap = Integer.parseInt(args[0]);
    			clients = Integer.parseInt(args[1]);
    		} catch(Exception error) {
    			error.printStackTrace();
    			return;
    		}
            Thread gym = new Thread(new Gym(clients, gym_cap));
            gym.start();
            //finish gym thread
            try {
                gym.join();
            } catch (InterruptedException error){
                error.printStackTrace();
            }
    	}
    }
}