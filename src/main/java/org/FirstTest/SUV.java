package org.FirstTest;

public class SUV extends Car{
    private int numberOfSeats;
    private boolean fourWheelDrive;

    public SUV(String id,String brand,String model,int year,double basePrice,
               int numberOfSeats,boolean fourWheelDrive){
        super(id,brand,model,year,basePrice);
        this.numberOfSeats=numberOfSeats;
        this.fourWheelDrive=fourWheelDrive;
    }

    public int getNumberOfSeats(){
        return numberOfSeats;
    }

    public boolean isFourWheelDrive(){
        return fourWheelDrive;
    }

    @Override
    public double calculatePrice(int days){
        double total=getBasePrice()*days;
        double surcharge =20;

        if(numberOfSeats>5){
            surcharge+=15;
        }

        return total+surcharge;
    }

    @Override
    public String getDescription(){
        return "SUV: "+getBrand()+" "+getModel()+" (" + getYear()
                +"), Seats: "+numberOfSeats
                +", 4WD: "+fourWheelDrive;
    }
}
