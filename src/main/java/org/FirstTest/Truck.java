package org.FirstTest;

public class Truck extends Car{

    private double cargoCapacity;
    private boolean specialLicenseRequired;

    public Truck(String id,String brand,String model,int year,double basePrice,
                 double cargoCapacity,boolean specialLicenseRequired){
        super(id,brand,model,year,basePrice);
        this.cargoCapacity=cargoCapacity;
        this.specialLicenseRequired=specialLicenseRequired;
    }

    public double getCargoCapacity(){
        return cargoCapacity;
    }

    public boolean isSpecialLicenseRequired(){
        return specialLicenseRequired;
    }

    @Override
    public double calculatePrice(int days){
        double total=getBasePrice()*days;
        double surcharge=cargoCapacity * 3;
        return total+(surcharge*days);
    }

    @Override
    public String getDescription(){
        return "Truck: "+getBrand()+" " +getModel()+" (" + getYear()
                +"), Cargo Capacity: "+cargoCapacity+" tons"
                +", Special License Required: "+specialLicenseRequired;
    }
}

