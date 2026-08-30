package org.FirstTest;

public class EconomyCar extends Car{
    private double fuel_efficiency;

    public EconomyCar(String id,String brand,String model,int year,double basePrice,double fuel_efficiency){
        super(id,brand,model,year,basePrice);
        this.fuel_efficiency =fuel_efficiency;
    }

    public double getfuel_efficiency(){
        return fuel_efficiency;
    }

    @Override
    public double calculatePrice(int days){
        double total =getBasePrice()*days;
        double discount =total *0.50;
        return total-discount;
    }

    @Override
    public String getDescription(){
        return "Economy Car: " +getBrand()+ " " +getModel()+ " (" +getYear()
                + "), Fuel Efficiency: " +fuel_efficiency +" km/l";
    }
}

