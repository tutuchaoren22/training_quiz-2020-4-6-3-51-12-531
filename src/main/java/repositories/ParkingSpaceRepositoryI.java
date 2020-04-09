package repositories;

import entities.ParkingSpace;

import java.util.List;

public interface ParkingSpaceRepositoryI {
    List<ParkingSpace> getAllParkingSpaceInfo();
    ParkingSpace getParkingSpaceForTicket(String ticket);
    void deleteParkingSpaceForTicket(String ticket);
    List<ParkingSpace> getParkingSpaceForParkingLot(String parkingLotId);
    void insertParkingSpaceForTicket(String ticket);
    void deleteAllParkingSpaceInfo();
}
