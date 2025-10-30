package com.pluralsight;

public class SalesContract extends Contract{
    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean isFinanced;

    public SalesContract(String date, String name, String email, Vehicle vehicleSold, boolean isFinanced) {
        super(date, name, email, vehicleSold);
        this.salesTax = 0.05;
        this.recordingFee = 100;
        this.isFinanced = isFinanced;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        double price = getVehicleSold().getPrice();

        if (price < 10000){
            return 295;
        } else { return 495;}
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicleSold().getPrice();

        return price + (price * getSalesTax()) + getProcessingFee() + getRecordingFee();
    }

    @Override
    public double getMonthlyPayment() {
        double total = getTotalPrice();
        if (isFinanced) {
            if (total >= 10000) {
                return (total * (1 + .0425)) / 48;
            } else {
                return (total * (1 + .0525)) / 24;
            }
        } else{ return 0;}
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nSales Tax: " + String.format("%.2f", getSalesTax()) +
                "\nRecording Fee: " + String.format("%.2f", getRecordingFee()) +
                "\nProcessing Fee: " + String.format("%.2f", getProcessingFee()) +
                "\nFinanced: " + isFinanced ;
    }
}
