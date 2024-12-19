package model;

public class customer {
    private String name;
    private int queueSequenceNumber;

    public customer(String name, int queueSequenceNumber) {
        this.name = name;
        this.queueSequenceNumber = queueSequenceNumber;
    }

    public String getName() {
        return name;
    }

    public int getQueueSequenceNumber() {
        return queueSequenceNumber;
    }

    @Override
    public String toString() {
        return "Customer Name: " + name + ", Queue Number: " + queueSequenceNumber;
    }
}
