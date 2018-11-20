package gym_simulation;

import java.util.Random;
import java.util.ArrayList;
import java.util.Map;
import gym_simulation.Items.*;


/**
 * Client object implementation
 * @author  James Spaniak
 * @version 1.0.0
 * @since   2018-10-15
 */
public class Client implements Runnable {
    private int id;
    private ArrayList<Exercise> routine;
    private Gym gym;
    private static final int EXERCISE_VARIANCE = 5;
    private static final int EXERCISE_MIN = 15;
    private static final Random RANDOM = new Random();
    
    /**
     * Generates a client with a given ID
     * @param unique ID, gym object ref
     * @return A randomly generated client
     */
    public static Client generateRandom(int id_in, Gym gym_in){
        Client client = new Client(id_in, gym_in);
        int numExercises = Client.EXERCISE_MIN + RANDOM.nextInt(Client.EXERCISE_VARIANCE);
        int counter;

        for(counter = 0; counter < numExercises; ++counter)
            client.addExercise(Exercise.generateRandom());
            
        return client;
    }

    /**
     * Constructor for Client object
     * @param id_in Client ID
     * @param gym_in Gym object for client to run one
     */
    public Client(int id_in, Gym gym_in){
        this.id = id_in;
        this.gym = gym_in;
        this.routine = new ArrayList<Exercise>();
    }

    /**
     * Adds exercise to the routine for each member
     * @param exercise The exercise to be added
     */
    public void addExercise(Exercise exercise){
        routine.add(exercise);
    }

    /**
     * Clients run method. Loops through all exercises in the clients routine and completes them based on the availability of
     * the semaphores in the gym object that the client is being run in.
     */
    @Override
    public void run(){
        for(Exercise exercise: routine){
            try{
                System.out.println("Client ID - " + this.id + " starting " + exercise + ".");
                gym.grabWeights(exercise.weights);
                gym.apparatuses[exercise.at.index].acquire();
                Thread.sleep(exercise.duration);
                gym.apparatuses[exercise.at.index].release();
                gym.releaseWeights(exercise.weights);
                System.out.println("Client ID - " + this.id + " finished " + exercise + ".");
            } catch (Exception error){
                error.printStackTrace();
            }
        }
    }
}
