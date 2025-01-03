package model;

public class customer {
    //variables
    private String name;
    private int queueSequenceNumber;
//constructor for initiazation of the customer object
    public customer(String name, int queueSequenceNumber) {
        this.name = name;
        this.queueSequenceNumber = queueSequenceNumber;
    }
// get method for cust name
    public String getName() {
        return name;
    }
// get method for seq number
    public int getQueueSequenceNumber() {
        return queueSequenceNumber;
    }
// tostring method providing a custom string
    @Override
    public String toString() {
        return "Customer Name: " + name + ", Queue Number: " + queueSequenceNumber;
    }
}
