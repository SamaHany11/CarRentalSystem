package org.FirstTest;


import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private List<Rental>rentalHistory;

    public Customer(String id,String fullName,String email,String phoneNumber){
        this.id=id;
        this.fullName=fullName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.rentalHistory=new ArrayList<>();
    }

    public String getId(){
        return id;
    }
    public String getFullName(){
        return fullName;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public List<Rental> getRentalHistory(){
        return new ArrayList<>(rentalHistory);
    }

    public void addRental(Rental rental){
        rentalHistory.add(rental);
    }

    public boolean hasActiveRental(){
        for(Rental rental:rentalHistory){
            if(rental.getStatus()==RentalStatus.ACTIVE){
                return true;
            }
        }
        return false;
    }
}
