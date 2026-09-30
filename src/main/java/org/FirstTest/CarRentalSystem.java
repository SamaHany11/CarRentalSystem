package org.FirstTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarRentalSystem {
    private List<Car>cars;
    private List<Customer>customers;
    private List<Rental>rentals;
    public CarRentalSystem() {
        this.cars=new ArrayList<>();
        this.customers=new ArrayList<>();
        this.rentals=new ArrayList<>();
    }
    public void addCar(Car car) throws DuplicateIdException{
        for(Car c : cars){
            if(c.getId().equals(car.getId())){
                throw new DuplicateIdException("A car with this ID already exists.");
            }
        }
        cars.add(car);
    }
    public void registerCustomer(Customer customer) throws DuplicateIdException {
        for(Customer c : customers){
            if(c.getId().equals(customer.getId())){
                throw new DuplicateIdException("A customer with this ID already exists.");
            }
        }
        customers.add(customer);
    }
    public List<Car> getAllCars(){
        return new ArrayList<>(cars);
    }
    public List<Customer> getAllCustomers(){
        return new ArrayList<>(customers);
    }
    public List<Rental> getAllRentals(){
        return new ArrayList<>(rentals);
    }
    public List<Car> getAvailableCars() {
        List<Car> result=new ArrayList<>();
        for(Car car : cars){
            if(car.getStatus()==CarStatus.AVAILABLE){
                result.add(car);
            }
        }
        return result;
    }
    public List<Car> searchCars(String typeName) {
        List<Car> result = new ArrayList<>();
        for(Car car : cars){
            if(typeName.equalsIgnoreCase("economy") && car instanceof EconomyCar){
                result.add(car);
            }
            else if(typeName.equalsIgnoreCase("luxury") && car instanceof LuxuryCar){
                result.add(car);
            }
            else if(typeName.equalsIgnoreCase("suv") && car instanceof SUV){
                result.add(car);
            }
            else if(typeName.equalsIgnoreCase("truck") && car instanceof Truck){
                result.add(car);
            }
        }
        return result;
    }

    public List<Car>searchCars(String brand, double maxPrice){
        List<Car>result = new ArrayList<>();
        for(Car car : cars){
            if(car.getBrand().equalsIgnoreCase(brand) && car.getBasePrice() <= maxPrice){
                result.add(car);
            }
        }
        return result;
    }

    public List<Car> searchCars(boolean availableOnly){
        if(!availableOnly){
            return new ArrayList<>(cars);
        }
        return getAvailableCars();
    }

    public Rental rentCar(String rentalId, Customer customer, Car car, LocalDate startDate,LocalDate expectedReturnDate) throws CarNotAvailableException,DuplicateActiveRentalException,InvalidDateRangeException {
        if(car.getStatus()!=CarStatus.AVAILABLE){
            throw new CarNotAvailableException("This car is not available for rent.");
        }
        if(customer.hasActiveRental()){
            throw new DuplicateActiveRentalException("This customer already has an active rental.");
        }
        Rental rental=new Rental(rentalId,customer,car,startDate,expectedReturnDate);
        car.setStatus(CarStatus.RENTED);
        customer.addRental(rental);
        rentals.add(rental);
        return rental;
    }
    public double returnCar(Rental rental,LocalDate actualReturnDate) throws RentalAlreadyCompletedException{
        double finalPrice=rental.returnCar(actualReturnDate);
        rental.getCar().setStatus(CarStatus.AVAILABLE);
        return finalPrice;
    }
    public void putCarUnderMaintenance(Car car) throws CarNotAvailableException{
        if(car.getStatus()!= CarStatus.AVAILABLE){
            throw new CarNotAvailableException("Only available cars can be put under maintenance.");
        }
        car.setStatus(CarStatus.MAINTENANCE);
    }
    public void returnCarFromMaintenance(Car car) throws CarNotAvailableException {
        if(car.getStatus() !=CarStatus.MAINTENANCE){
            throw new CarNotAvailableException("This car is not under maintenance.");
        }
        car.setStatus(CarStatus.AVAILABLE);
    }
    public List<Rental> getCustomerRentals(Customer customer) {
        return customer.getRentalHistory();
    }
}
