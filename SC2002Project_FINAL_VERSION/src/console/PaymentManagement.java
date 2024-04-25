package console;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
Representing a PaymentManagement.
*/
public class PaymentManagement {
	/**
	* The payment methods.
	*/
    private List<String> paymentMethods;
    /**
	* The file path of text file storing payment methods.
	*/
    private final String filePath = "payment_methods.txt";
    

    /**
     * Creating a new PaymentManagement.
     */
    public PaymentManagement() {
        this.paymentMethods = new ArrayList<>();
        loadPaymentMethods(); // Load payment methods from file
    }
    
    /**
     * Loading payment methods from text file.
     */
    private void loadPaymentMethods() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No existing payment methods file found. A new one will be created.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                paymentMethods.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Payment methods file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the payment methods file: " + e.getMessage());
        }
    }

    /**
     * Saving payment methods to text file.
     */
    public void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            for (String method : paymentMethods) {
                out.println(method);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving payment methods file: " + e.getMessage());
        }
    }

    /**
     * Adding a new payment method.
     * @param method New payment method.
     */
    public void addPaymentMethod(String method) {
        if (!paymentMethods.contains(method)) {
            paymentMethods.add(method);
            System.out.println(method + " has been added successfully.");
            saveToFile(); // Save changes
        } else {
            System.out.println(method + " is already available.");
        }
    }

    /**
     * Removing a payment method.
     * @param method Payment method to remove.
     */
    public void removePaymentMethod(String method) {
        if (paymentMethods.contains(method)) {
            paymentMethods.remove(method);
            System.out.println(method + " has been removed successfully.");
            saveToFile(); // Save changes
        } else {
            System.out.println(method + " was not found.");
        }
    }

    /**
     * Displaying all payment methods.
     */
    public void displayPaymentMethods() {
        System.out.println("Available Payment Methods:");
        if (paymentMethods.isEmpty()) {
            System.out.println("No payment methods available.");
        } else {
            for (String method : paymentMethods) {
                System.out.println("- " + method);
            }
        }
    }
    
    /**
     * Getting all payment methods.
     * @return List of all payment methods.
     */
    public List<String> getPaymentMethods() {
    	return this.paymentMethods;
    }
}