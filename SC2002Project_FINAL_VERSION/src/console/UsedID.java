package console;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UsedID {
    private static final String CART_DETAILS_FILE_PATH = "UsedID.txt";
    private static Map<Integer, Boolean> usedIDs = new HashMap<>();

    // Method to load used IDs from file
    public static void loadUsedIDs() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CART_DETAILS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Cart ID:")) {
                    int id = Integer.parseInt(line.split(": ")[1].trim()); // Extract ID
                    usedIDs.put(id, true); // Add ID to usedIDs map
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while loading used IDs: " + e.getMessage());
        }
    }

    // Method to check if an ID is used
    public static boolean isIDUsed(int id) {
        return usedIDs.containsKey(id);
    }

    // Method to add a new used ID
    public static void addUsedID(int id) {
        usedIDs.put(id, true);
    }
}
