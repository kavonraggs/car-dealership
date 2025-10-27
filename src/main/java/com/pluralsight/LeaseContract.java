package com.pluralsight;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String name, String email, Vehicle vehicleSold) {
        super(date, name, email, vehicleSold);
        this.leaseFee = 0.07 * getVehicleSold().getPrice();
        this.expectedEndingValue = getVehicleSold().getPrice() * 0.5;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicleSold().getPrice();
        return price + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        return (getTotalPrice() * (1 + 0.04)) / 36;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nExpected Ending Value: " + expectedEndingValue +
                "\nLease Fee: " + leaseFee;
    }
}
