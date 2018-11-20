package gym_simulation.Items;


import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Random code from
// https://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
/**
 * Weight plate sizes that are to be used in the simulation.
 * @author James Spaniak
 * @version 1.0.0
 * @since 2018-10-15
 */
public enum WeightPlateSize {
    SMALL_3KG(0), MEDIUM_5KG(1), LARGE_10KG(2);

    public int index;
    public static final List<WeightPlateSize> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * WeightPlateSize constructor
     * Creates the enums and assigns them a specific index for computation
     * 
     * @param index_in The index that is specified for the specific element
     * @return void
     */
    
    WeightPlateSize(int index_in){
        this.index = index_in;
    }
    
    /**
     * Returns a random WeightPlateSize
     * 
     * @param null
     * @return The randomly selected WeightPlateSize
     */
    
    public static WeightPlateSize randomApparatus() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
