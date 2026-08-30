package org.FirstTest;

public abstract class Car{
    private String id;
    private String brand;
    private String model;
    private int year;
    private double basePrice;
    private CarStatus status;

    public Car(String id,String brand,String model,int year,double basePrice){
        this.id =id;
        this.brand =brand;
        this.model =model;
        this.year =year;
        this.basePrice =basePrice;
        this.status =CarStatus.AVAILABLE;
    }

    public String getId(){
        return id;
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }
    public int getYear(){
        return year;
    }
    public double getBasePrice(){
        return basePrice;
    }
    public CarStatus getStatus(){
        return status;
    }

    public void setStatus(CarStatus status){
        this.status =status;
    }

    public abstract double calculatePrice(int days);
    public abstract String getDescription();
}