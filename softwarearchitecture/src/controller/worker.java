package controller;

public class worker {

    public double calculateFee(double weight) {
        double baseRate = 10.0; // Base rate per kg
        double fee;

        //fee
        if (weight <= 5) {
            fee = weight * baseRate; // Standard fee
        } else {
            fee = weight * baseRate * 0.9; // Discounted rate for weights above 5kg
        }

        return fee;
    }
}
