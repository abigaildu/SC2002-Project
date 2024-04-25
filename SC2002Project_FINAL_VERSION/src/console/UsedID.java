package console;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
Representing a UsedID.
*/
public class UsedID {
	/**
	* The IDs have been used.
	*/
    private static Map<Integer, Boolean> usedIDs = new HashMap<>();

    /**
     * Checking if an ID is used.
     * @param id ID to check.
     * @return State to indicate whether the ID is used.
     */
    public static boolean isIDUsed(int id) {
        return usedIDs.containsKey(id);
    }

    /**
     * Adding a new used ID.
     * @param id ID to add.
     */
    public static void addUsedID(int id) {
        usedIDs.put(id, true);
    }
}
