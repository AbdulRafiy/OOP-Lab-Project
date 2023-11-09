import java.util.Scanner;

abstract class Vehicle {
    private String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public abstract void displayInfo();
}

class Bus extends Vehicle {
    private int seatingCapacity;

    public Bus(String registrationNumber, int seatingCapacity) {
        super(registrationNumber);
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    @Override
    public void displayInfo() {
        System.out.println("Bus Registration Number: " + getRegistrationNumber());
        System.out.println("Seating Capacity: " + seatingCapacity);
    }
}

class Car extends Vehicle {
    private String carModel;

    public Car(String registrationNumber, String carModel) {
        super(registrationNumber);
        this.carModel = carModel;
    }

    public String getCarModel() {
        return carModel;
    }

    @Override
    public void displayInfo() {
        System.out.println("Car Registration Number: " + getRegistrationNumber());
        System.out.println("Car Model: " + carModel);
    }
}

class TransportManager {
    private Vehicle[] vehicles;
    private int currentIndex;

    public TransportManager(int capacity) {
        vehicles = new Vehicle[capacity];
        currentIndex = 0;
    }

    public void addVehicle(Vehicle vehicle) {
        if (currentIndex < vehicles.length) {
            vehicles[currentIndex] = vehicle;
            currentIndex++;
            System.out.println("Vehicle added successfully.");
        } else {
            System.out.println("Vehicle capacity reached. Cannot add more vehicles.");
        }
    }

    public void displayVehicleInfo() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                vehicle.displayInfo();
            }
        }
    }

    public Vehicle findVehicleByRegistrationNumber(String registrationNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle != null && vehicle.getRegistrationNumber().equals(registrationNumber)) {
                return vehicle;
            }
        }
        return null;
    }
}

public class MUETTransportManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransportManager manager = new TransportManager(100);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Bus");
            System.out.println("2. Add Car");
            System.out.println("3. Display Vehicle Information");
            System.out.println("4. Search Vehicle by Registration Number");
            System.out.println("5. Exit");
            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    System.out.println("Enter Bus Registration Number:");
                    String busRegNumber = scanner.next();
                    System.out.println("Enter Seating Capacity:");
                    int seatingCapacity = getIntInput(scanner);
                    Bus bus = new Bus(busRegNumber, seatingCapacity);
                    manager.addVehicle(bus);
                    break;
                case 2:
                    System.out.println("Enter Car Registration Number:");
                    String carRegNumber = scanner.next();
                    System.out.println("Enter Car Model:");
                    String carModel = scanner.next();
                    Car car = new Car(carRegNumber, carModel);
                    manager.addVehicle(car);
                    break;
                case 3:
                    System.out.println("Vehicle Information:");
                    manager.displayVehicleInfo();
                    break;
                case 4:
                    System.out.println("Enter Registration Number to Search:");
                    String regNumber = scanner.next();
                    Vehicle foundVehicle = manager.findVehicleByRegistrationNumber(regNumber);
                    if (foundVehicle != null) {
                        System.out.println("Vehicle Found:");
                        foundVehicle.displayInfo();
                    } else {
                        System.out.println("Vehicle not found with registration number: " + regNumber);
                    }
                    break;
                case 5:
                    System.out.println("Exiting MUET Transport Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
