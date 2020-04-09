package services;

import entities.ParkingLot;

import java.util.List;

public interface ParkingLotServicesI {
    List<ParkingLot> getAllParkingLotInfo();

    void deleteAllParkingLotInfo();

    void initParkingLotInfo(String parkingLotId, int parkingSpaceNumber);
}
