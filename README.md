# ParkingSystem
Simple Parking Lot System

Build command
    - mvn clean package

Execute command
    - mvn exec:java -Dexec.mainClass="gojek.Main.App"

Just execute the parking_lot script. It has automated the build and run process.

There are 4 main entities:
    - Vehicle - Details about the vehicle to be parked.
    - ParkingSlot - Details about the parking slot info.
    - ParkingSpace - Details about all the parking slot.
    - ParkingTicket - Details about status of parking slots and the vehicle and the corresponding parking slot.

Service interface for the commands:
    - createParkingLot - for creating the parking lot
    - issueTicket -  when a new vehicle arrives for parking
    - leaveParking - for the exit of vehicle
    - status - current status of parking slot;
    - getCarsRegNumOrSlotWithColor - gets the registration number of car or its corresponding parking slot based on color.
    - getSlotForCarWithRegNum - gets the parking slot of a car with a specific registration number.

App class which acts as driver program.

