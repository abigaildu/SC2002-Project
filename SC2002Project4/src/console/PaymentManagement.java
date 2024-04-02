package console;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentManagement {
    private List<String> paymentMethods;
    private final String filePath = "payment_methods.txt"; // File path for storing payment methods

    public PaymentManagement() {
        this.paymentMethods = new ArrayList<>();
        loadPaymentMethods(); // Load payment methods from file
    }

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

    public void saveToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            for (String method : paymentMethods) {
                out.println(method);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving payment methods file: " + e.getMessage());
        }
    }

    public void addPaymentMethod(String method) {
        if (!paymentMethods.contains(method)) {
            paymentMethods.add(method);
            System.out.println(method + " has been added successfully.");
            saveToFile(); // Save changes
        } else {
            System.out.println(method + " is already available.");
        }
    }

    public void removePaymentMethod(String method) {
        if (paymentMethods.contains(method)) {
            paymentMethods.remove(method);
            System.out.println(method + " has been removed successfully.");
            saveToFile(); // Save changes
        } else {
            System.out.println(method + " was not found.");
        }
    }

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
    
    public List<String> getPaymentMethods() {
    	return this.paymentMethods;
    }
}
