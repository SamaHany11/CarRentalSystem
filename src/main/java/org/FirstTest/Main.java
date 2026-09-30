package org.FirstTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarRentalSystem system = new CarRentalSystem();
        int choice = -1;
        while(choice != 11){
            System.out.println("\n--- Car Rental System ---");
            System.out.println("1. Register Customer");
            System.out.println("2. Add Car");
            System.out.println("3. View Available Cars");
            System.out.println("4. Search Cars");
            System.out.println("5. Rent Car");
            System.out.println("6. Return Car");
            System.out.println("7. View Customer Rentals");
            System.out.println("8. View All Rentals");
            System.out.println("9. Put Car Under Maintenance");
            System.out.println("10. Return Car From Maintenance");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
                continue;
            }
            try {
                switch(choice){
                    case 1:
                        System.out.print("ID: ");
                        String cid = scanner.nextLine();
                        System.out.print("Full Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Phone: ");
                        String phone = scanner.nextLine();
                        system.registerCustomer(new Customer(cid, name, email, phone));
                        System.out.println("Customer registered successfully.");
                        break;
                    case 2:
                        System.out.print("Car Type (economy/luxury/suv/truck): ");
                        String type = scanner.nextLine();
                        System.out.print("ID: ");
                        String carId = scanner.nextLine();
                        System.out.print("Brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("Model: ");
                        String model = scanner.nextLine();
                        System.out.print("Year: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.print("Base Price: ");
                        double basePrice = Double.parseDouble(scanner.nextLine());
                        if(type.equalsIgnoreCase("economy")) {
                            System.out.print("Fuel Efficiency: ");
                            double fuel = Double.parseDouble(scanner.nextLine());
                            system.addCar(new EconomyCar(carId, brand, model, year, basePrice, fuel));
                        }
                        else if(type.equalsIgnoreCase("suv")) {
                            System.out.print("Number of Seats: ");
                            int seats = Integer.parseInt(scanner.nextLine());
                            System.out.print("Four Wheel Drive (true/false): ");
                            boolean fwd = Boolean.parseBoolean(scanner.nextLine());
                            system.addCar(new SUV(carId, brand, model, year, basePrice, seats, fwd));
                        }
                        else if(type.equalsIgnoreCase("truck")) {
                            System.out.print("Cargo Capacity: ");
                            double cargo = Double.parseDouble(scanner.nextLine());
                            System.out.print("Special License Required (true/false): ");
                            boolean license = Boolean.parseBoolean(scanner.nextLine());
                            system.addCar(new Truck(carId, brand, model, year, basePrice, cargo, license));
                        }
                        else if(type.equalsIgnoreCase("luxury")) {
                            System.out.print("Chauffeur Available (true/false): ");
                            boolean chauffeur = Boolean.parseBoolean(scanner.nextLine());
                            System.out.print("Premium Features (comma separated, e.g. Leather seats, Sunroof): ");
                            String featuresInput = scanner.nextLine();
                            List<String> features = new java.util.ArrayList<>();
                            for(String feature : featuresInput.split(",")) {
                                if(!feature.trim().isEmpty()) {
                                    features.add(feature.trim());
                                }
                            }
                            system.addCar(new LuxuryCar(carId, brand, model, year, basePrice, chauffeur, features));
                        }
                        else {
                            System.out.println("Unknown car type.");
                            break;
                        }
                        System.out.println("Car added successfully.");
                        break;
                    case 3:
                        for(Car car : system.getAvailableCars()) {
                            System.out.println(car.getDescription());
                        }
                        break;
                    case 4:
                        System.out.println("Search by: 1-Type  2-Brand and Max Price  3-Availability Only");
                        int searchOption = Integer.parseInt(scanner.nextLine());
                        List<Car> results;
                        if(searchOption == 1) {
                            System.out.print("Car Type (economy/luxury/suv/truck): ");
                            String searchType = scanner.nextLine();
                            results = system.searchCars(searchType);
                        }
                        else if(searchOption == 2) {
                            System.out.print("Brand: ");
                            String searchBrand = scanner.nextLine();
                            System.out.print("Max Price: ");
                            double maxPrice = Double.parseDouble(scanner.nextLine());
                            results = system.searchCars(searchBrand, maxPrice);
                        }
                        else if(searchOption == 3) {
                            results = system.searchCars(true);
                        }
                        else{
                            System.out.println("Invalid search option.");
                            break;
                        }
                        for(Car car : results) {
                            System.out.println(car.getDescription());
                        }
                        break;
                    case 5:
                        System.out.print("Customer ID: ");
                        String rcid = scanner.nextLine();
                        Customer foundCustomer = findCustomer(system, rcid);
                        System.out.print("Car ID: ");
                        String rcarid = scanner.nextLine();
                        Car foundCar = findCar(system, rcarid);
                        System.out.print("Start Date (yyyy-mm-dd): ");
                        LocalDate startDate = LocalDate.parse(scanner.nextLine());
                        System.out.print("Expected Return Date (yyyy-mm-dd): ");
                        LocalDate expectedDate = LocalDate.parse(scanner.nextLine());
                        if (foundCustomer == null || foundCar == null) {
                            System.out.println("Customer or car not found.");
                            break;
                        }
                        String rentalId = "R" + (system.getAllRentals().size() + 1);
                        Rental newRental = system.rentCar(rentalId, foundCustomer, foundCar, startDate, expectedDate);
                        System.out.println("Rental created. Estimated price: " + newRental.getEstimatedPrice());
                        break;
                    case 6:
                        System.out.print("Rental ID: ");
                        String returnRentalId = scanner.nextLine();
                        Rental foundRental = findRental(system, returnRentalId);
                        if(foundRental == null) {
                            System.out.println("Rental not found.");
                            break;
                        }
                        System.out.print("Actual Return Date (yyyy-mm-dd): ");
                        LocalDate actualDate = LocalDate.parse(scanner.nextLine());
                        double finalPrice = system.returnCar(foundRental, actualDate);
                        System.out.println("Car returned. Final price: " + finalPrice);
                        break;
                    case 7:
                        System.out.print("Customer ID: ");
                        String hcid = scanner.nextLine();
                        Customer hCustomer = findCustomer(system, hcid);
                        if(hCustomer == null) {
                            System.out.println("Customer not found.");
                            break;
                        }
                        for(Rental r : system.getCustomerRentals(hCustomer)) {
                            System.out.println(r.getId() + " - " + r.getStatus());
                        }
                        break;
                    case 8:
                        for(Rental r : system.getAllRentals()) {
                            System.out.println(r.getId() + " - " + r.getStatus());
                        }
                        break;
                    case 9:
                        System.out.print("Car ID: ");
                        String mcid = scanner.nextLine();
                        Car mCar = findCar(system, mcid);
                        if(mCar == null) {
                            System.out.println("Car not found.");
                            break;
                        }
                        system.putCarUnderMaintenance(mCar);
                        System.out.println("Car put under maintenance.");
                        break;
                    case 10:
                        System.out.print("Car ID: ");
                        String rmcid = scanner.nextLine();
                        Car rmCar = findCar(system, rmcid);
                        if(rmCar == null) {
                            System.out.println("Car not found.");
                            break;
                        }
                        system.returnCarFromMaintenance(rmCar);
                        System.out.println("Car returned from maintenance.");
                        break;
                    case 11:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
            catch (CarNotAvailableException | DuplicateActiveRentalException | InvalidDateRangeException | RentalAlreadyCompletedException | DuplicateIdException e){
                System.out.println("Error: " + e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Invalid input, please try again.");
            }
        }
        scanner.close();
    }
    private static Customer findCustomer(CarRentalSystem system, String id) {
        for(Customer c : system.getAllCustomers()) {
            if(c.getId().equals(id)) return c;
        }
        return null;
    }
    private static Car findCar(CarRentalSystem system, String id) {
        for(Car c : system.getAllCars()) {
            if(c.getId().equals(id)) return c;
        }
        return null;
    }
    private static Rental findRental(CarRentalSystem system, String id) {
        for(Rental r : system.getAllRentals()) {
            if(r.getId().equals(id)) return r;
        }
        return null;
    }
}