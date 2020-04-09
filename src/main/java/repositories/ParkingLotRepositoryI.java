package repositories;

import entities.ParkingLot;

import java.util.List;

public interface ParkingLotRepositoryI {
    List<ParkingLot> getAllParkingLotInfo();
    void deleteAllParkingLotInfo();
    void initParkingLotInfo(String parkingLotId,int parkingSpaceNumber);
}
