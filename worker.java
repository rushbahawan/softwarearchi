package controller;

import model.customer;
import model.parcel;

public class worker {
    public float calculateFee(parcel parcel) {
        return (parcel.getWeight() * 2.0f) + (parcel.getDaysInDepot() * 5.0f);
    }

    public void processCustomer(customer customer, parcel parcel) {
        System.out.println("Processing Customer: " + customer);
        System.out.println("Parcel Details: " + parcel);
        float fee = calculateFee(parcel);
        System.out.println("Calculated Fee: $" + fee);
    }
}
