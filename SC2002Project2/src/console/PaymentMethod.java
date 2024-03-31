package console;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class PaymentMethod {
    private Set<String> paymentMethods;

    public PaymentMethod() {
        paymentMethods = new HashSet<>();
    }

    public void addPayment(Scanner scanner) {
        System.out.println("Enter the payment method to add:");
        String payment = scanner.nextLine().trim(); // Read user input

        if (!payment.isEmpty()) {
            if (!paymentMethods.contains(payment)) {
                paymentMethods.add(payment);
                System.out.println("Payment method '" + payment + "' added successfully.");
            } else {
                System.out.println("Payment method '" + payment + "' already exists.");
            }
        } else {
            System.out.println("Invalid payment method. Please enter a valid payment method.");
        }
    }

    public void removePayment(String payment) {
        if (paymentMethods.contains(payment)) {
            paymentMethods.remove(payment);
            System.out.println("Payment method '" + payment + "' removed successfully.");
        } else {
            System.out.println("Payment method '" + payment + "' does not exist.");
        }
    }

    public void displayPaymentMethods() {
        System.out.println("Existing Payment Methods:");
        if (paymentMethods.isEmpty()) {
            System.out.println("No payment methods found.");
        } else {
            for (String payment : paymentMethods) {
                System.out.println(payment);
            }
        }
    }
}