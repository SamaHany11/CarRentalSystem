package org.FirstTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {

    private static final double LATE_FEE_PER_DAY=15.0;
    private String id;
    private Customer customer;
    private Car car;
    private LocalDate startDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;
    private double finalPrice;
    private RentalStatus status;

    public Rental(String id,Customer customer,Car car,LocalDate startDate,LocalDate expectedReturnDate) throws InvalidDateRangeException {
        if (!expectedReturnDate.isAfter(startDate)){
            throw new InvalidDateRangeException("Expected return date must be after start date.");
        }
        this.id=id;
        this.customer=customer;
        this.car=car;
        this.startDate=startDate;
        this.expectedReturnDate=expectedReturnDate;
        this.actualReturnDate=null;
        this.finalPrice=0;
        this.status=RentalStatus.ACTIVE;
    }

    public String getId(){
        return id;
    }
    public Customer getCustomer(){
        return customer;
    }
    public Car getCar(){
        return car;
    }
    public LocalDate getStartDate(){
        return startDate;
    }
    public LocalDate getExpectedReturnDate(){
        return expectedReturnDate;
    }
    public LocalDate getActualReturnDate(){
        return actualReturnDate;
    }
    public double getFinalPrice(){
        return finalPrice;
    }
    public RentalStatus getStatus(){
        return status;
    }
    public double getEstimatedPrice(){
        long days=ChronoUnit.DAYS.between(startDate, expectedReturnDate);
        if(days < 1) days =1;
        return car.calculatePrice((int) days);
    }

    public double returnCar(LocalDate actualReturnDate) throws RentalAlreadyCompletedException{
        if(this.status == RentalStatus.COMPLETED){
            throw new RentalAlreadyCompletedException("This rental has already been completed.");
        }

        this.actualReturnDate=actualReturnDate;

        long days =ChronoUnit.DAYS.between(startDate,expectedReturnDate);
        if(days < 1)days =1;

        double basePrice =car.calculatePrice((int) days);

        long lateDays =ChronoUnit.DAYS.between(expectedReturnDate,actualReturnDate);
        if(lateDays < 0)lateDays =0;

        double lateFee =lateDays* LATE_FEE_PER_DAY;

        this.finalPrice =basePrice+lateFee;
        this.status= RentalStatus.COMPLETED;

        return this.finalPrice;
    }
}
