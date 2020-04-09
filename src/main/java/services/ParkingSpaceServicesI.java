package services;

import entities.ParkingSpace;

import java.util.List;

public interface ParkingSpaceServicesI {
    List<ParkingSpace> getAllParkingSpaceInfo();

    ParkingSpace getParkingSpaceForTicket(String ticket);

    void deleteParkingSpaceForTicket(String ticket);

    List<ParkingSpace> getParkingSpaceForParkingLot(String parkingLotId);

    void insertParkingSpaceForTicket(String ticket);

    void deleteAllParkingSpaceInfo();
}
