# Car Rental System

A console-based Java application that simulates the core operations of a
car rental agency: managing a fleet of cars, registering customers,
processing rentals, calculating prices (including late fees), and
tracking cars under maintenance.

## How to Run

1. Open the project in IntelliJ (or any Java IDE).
2. Run `Main.java`.
3. Follow the on-screen menu — each option asks for the information it
   needs (customer details, car details, dates, etc.).

## Project Structure

| Class | Purpose |
|---|---|
| `Car` | Abstract base class holding data shared by every car (id, brand, model, year, base price, status). |
| `EconomyCar`, `LuxuryCar`, `SUV`, `Truck` | Concrete car types. Each has its own extra fields and its own way of calculating price and describing itself. |
| `CarStatus` | Enum: `AVAILABLE`, `RENTED`, `MAINTENANCE`. |
| `Customer` | Stores customer info and their full rental history. |
| `Rental` | Stores one rental's data and handles the return process (price + late fee calculation). |
| `RentalStatus` | Enum: `ACTIVE`, `COMPLETED`. |
| `CarRentalSystem` | Central class holding all cars, customers, and rentals, and exposing the main operations (add car, register customer, rent, return, search, maintenance). |
| `Main` | Console menu that ties everything together. |
| `*Exception` classes | Custom exceptions for invalid operations (car not available, duplicate active rental, invalid date range, rental already completed, duplicate ID). |

## Price Calculation

Base price = daily price × number of days, plus a type-specific
adjustment:

- **Economy**: 5% discount off the base price.
- **Luxury**: 40% surcharge on the base price.
- **SUV**: flat $20 surcharge, plus $15 more if the car has more than 5
  seats.
- **Truck**: surcharge based on cargo capacity ($3 per unit of capacity,
  per day).

If the car is returned after the expected return date, a late fee of
**$15 per late day** is added to the total.

These specific numbers (5%, 40%, $20, $15, etc.) were design choices
made to satisfy the spec's requirement that each car type have its own
pricing adjustment — the spec itself doesn't mandate exact values.

## Business Rules Enforced

- A customer can only have one active rental at a time.
- A rental's expected return date must be after its start date.
- A rental cannot be returned twice.
- A car can't be rented if it's already rented or under maintenance.
- A car can only go under maintenance if it's currently available (not
  while rented).
- Every car and customer must have a unique ID (duplicates are
  rejected).

## OOP Concepts Used

- **Abstraction / Inheritance**: `Car` is abstract; the four car types
  extend it and share its common structure.
- **Polymorphism / Overriding**: `calculatePrice()` and
  `getDescription()` are implemented differently in each car type, and
  called generically wherever a `Car` is used.
- **Encapsulation**: fields are private; internal collections (like
  `getAllCars()`) return copies, not direct references, so external
  code can't modify the system's internal state.
- **Method Overloading**: `searchCars(...)` has three versions — by
  type, by brand and max price, or by availability only.
- **Exception Handling**: custom checked exceptions are used instead of
  letting invalid operations crash the program.
