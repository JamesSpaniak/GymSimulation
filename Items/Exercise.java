package gym_simulation.Items;

import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import gym_simulation.Gym;

/**
 * Exercises to be used in the simulation
 * Each Exercise has the attributes: # of weights, duration, and the AppartusType
 * @author  James Spaniak
 * @version 1.0.0
 * @since   2018-10-15
 */
public class Exercise {
    public ApparatusType at;
    public Map<WeightPlateSize, Integer> weights;
    public int duration;
    private static final Random RANDOM = new Random();
    private static final int DURATION_VARIANCE = 2;

    /**
     * Exercise constructor
     * @param at_in ApparatusType
     * @param weights_in Hashmap of WeightPlateSizes and their quantities
     * @param duration_in length of exercise denoted in milliseconds.
     * @return void
     */
    public Exercise(ApparatusType at_in, Map<WeightPlateSize, Integer> weights_in, int duration_in){
        this.at = at_in;
        this.weights = weights_in;
        this.duration = duration_in;
    }
    /**
     * Return a random Exercise
     * @return The randomly selected Exercise
     * @param null
     */
    public static Exercise generateRandom(){
        //Get a random ApparatusType
        ApparatusType at = ApparatusType.randomApparatus();
        //Generate a random duration for each exercise to be executed
        int duration = RANDOM.nextInt(Exercise.DURATION_VARIANCE) + 1; // min val of 1
        //Hashmap to represent the weights of WeightPlateSize and their quantities.
        HashMap numWeights = new HashMap();
        //Weight count total and the individual counts of each weight.
        int total_count = 0;
        int small_weights, medium_weights, large_weights;
        // Generate random weights ensuring the total count is non-zero.
        while(total_count == 0){
            small_weights = RANDOM.nextInt(Gym.NUM_EACH_WEIGHT[WeightPlateSize.SMALL_3KG.index]);
            total_count += small_weights;
            numWeights.put(WeightPlateSize.SMALL_3KG, small_weights);

            medium_weights = RANDOM.nextInt(Gym.NUM_EACH_WEIGHT[WeightPlateSize.MEDIUM_5KG.index]);
            total_count += medium_weights;
            numWeights.put(WeightPlateSize.MEDIUM_5KG, medium_weights);

            large_weights = RANDOM.nextInt(Gym.NUM_EACH_WEIGHT[WeightPlateSize.LARGE_10KG.index]);
            total_count += large_weights;
            numWeights.put(WeightPlateSize.LARGE_10KG, large_weights);
        }
        Exercise exercise = new Exercise(at, numWeights, duration);
        return exercise;
    }

    /**
     * toString implementation
     * @param null
     * @return A String representation of an exercise
     */
    @Override
    public String toString(){
        String ret_string = this.at.name() + " using";
        for(WeightPlateSize weight: WeightPlateSize.VALUES)
        	ret_string += " " + weights.get(weight) + " " + weight.name();
        ret_string += " in " + duration + " second(s)";
      
        return ret_string;
    }
}