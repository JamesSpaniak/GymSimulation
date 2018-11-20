package gym_simulation;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import gym_simulation.Items.*;

/**
 * Gym class implemented fully!
 * Has semaphores for the WeightPlateSize's and the ApparatusType's alongside the executor for the pool thread.
 * @author: James Spaniak
 * @version: 1.0.0
 * @since 2018-10-15
 */
public class Gym implements Runnable {
    private static int gym_size;
    private static int gym_clients;
    private static final int APPARATUSES = 5;
    public static final int[] NUM_EACH_WEIGHT = {40, 30, 20};
    private static final int MUTEX = 1;

    // Instance variables
    private HashMap<WeightPlateSize, Integer> noOfWeights;
    private ExecutorService executor;
    public Semaphore[] apparatuses;
    private Semaphore[] weights;
    private Semaphore canGrabWeights;
    private Set<Integer> ids;
    
    /**
     * Gym class constructor
     * @param number of total clients and the gym_capacity
     * @return void
     */
    public Gym(int clients, int gym_size_in) {
    	this.gym_clients = clients;
    	this.gym_size = gym_size_in;
        int i;
        int numApparatusTypes = ApparatusType.SIZE;
        int numWeightTypes = WeightPlateSize.SIZE;

        this.executor = Executors.newFixedThreadPool(Gym.gym_size);
        this.apparatuses = new Semaphore[numApparatusTypes];
        for(ApparatusType a: ApparatusType.VALUES){
            this.apparatuses[a.index] = new Semaphore(Gym.APPARATUSES);
        }
        this.weights = new Semaphore[numWeightTypes];
        this.noOfWeights = new HashMap();
        for(WeightPlateSize w: WeightPlateSize.VALUES){
            noOfWeights.put(w, NUM_EACH_WEIGHT[w.index]);
            weights[w.index] = new Semaphore(Gym.NUM_EACH_WEIGHT[w.index]);
        }

        this.canGrabWeights = new Semaphore(1);
        this.ids = new HashSet<Integer>();
    }


    /**
     * Retrieves weights until all needed weights are acquired. (Blocking when needed)
     * @param numWeights Hashmap for plate types and their quantities.
     * @return void
     */
    public void grabWeights(Map<WeightPlateSize, Integer> numWeights){
        int i;
        try{
            this.canGrabWeights.acquire();
            for(i = 0; i < numWeights.get(WeightPlateSize.SMALL_3KG); ++i)
                this.weights[WeightPlateSize.SMALL_3KG.index].acquire();
            for(i = 0; i < numWeights.get(WeightPlateSize.MEDIUM_5KG); ++i)
                this.weights[WeightPlateSize.MEDIUM_5KG.index].acquire();
            for(i = 0; i < numWeights.get(WeightPlateSize.LARGE_10KG); ++i)
                this.weights[WeightPlateSize.LARGE_10KG.index].acquire();
        } catch(InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
            this.canGrabWeights.release();
        }
    }

    /**
     * Release weight semaphores.
     * @param numWeights Hashmap for plate types and their quantities.
     * @return void
     */
    public void releaseWeights(Map<WeightPlateSize, Integer> numWeights){
        int i;
        for(i = 0; i < numWeights.get(WeightPlateSize.SMALL_3KG); ++i)
            this.weights[WeightPlateSize.SMALL_3KG.index].release();
        for(i = 0; i < numWeights.get(WeightPlateSize.MEDIUM_5KG); ++i)
            this.weights[WeightPlateSize.MEDIUM_5KG.index].release();
        for(i = 0; i < numWeights.get(WeightPlateSize.LARGE_10KG); ++i)
            this.weights[WeightPlateSize.LARGE_10KG.index].release();
    }

    /**
     * Overriding of default Thread run
     * Creates clients and runs them on the gym simulation
     * @param null
     * @return void
     */
    @Override
    public void run(){
        int id;
        Random r = new Random();
        int i = 1;
        System.out.println("The gym is open and accepting clients.");
        while(i <= Gym.gym_clients){
            do {
                id = i;
            } while (!ids.add(id));
            Client c = Client.generateRandom(id, this);
            executor.execute(c);
            ++i;
        }
        executor.shutdown();
        while(!executor.isTerminated()){}
        System.out.println("All clients completed their gym visits.");
    }
}