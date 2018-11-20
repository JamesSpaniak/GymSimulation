package gym_simulation.Items;

import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * ApparatusType to be used in the simulation. Randomly selected via randomApparatus.
 * @author  James Spaniak
 * @version 1.0.0
 * @since   2018-10-15
 */
public enum ApparatusType {
    LEGPRESSMACHINE(0),
    BARBELL(1),
    HACKSQUATMACHINE(2),
    LEGEXTENSIONMACHINE(3),
    LEGCURLMACHINE(4),
    LATPULLDOWNMACHINE(5),
    PECDECKMACHINE(6),
    CABLECROSSOVERMACHINE(7);

    public int index;
    public static final List<ApparatusType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    public static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * ApparatusType constructor
     * @param index_in is the index in the enum to be applied
     * @return void
     */
    ApparatusType(int index_in){
        this.index = index_in;
    }

    /**
     * Returns a random ApparatusType
     * @param null
     * @return The randomly selected ApparatusType
     */
    public static ApparatusType randomApparatus() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}