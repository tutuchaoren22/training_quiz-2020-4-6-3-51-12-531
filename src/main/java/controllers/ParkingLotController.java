package controllers;

import entities.ParkingLot;
import services.ParkingLotServices;
import services.ParkingLotServicesI;

import java.util.List;

public class ParkingLotController {
    private ParkingLotServicesI parkingLotServices = new ParkingLotServices();

    public List<ParkingLot> getAllParkingLotInfo() {
        return parkingLotServices.getAllParkingLotInfo();
    }

    public void deleteAllParkingLotInfo() {
        parkingLotServices.deleteAllParkingLotInfo();
    }

    public void initParkingLotInfo(String parkingLotId, int parkingSpaceNumber) {
        parkingLotServices.initParkingLotInfo(parkingLotId,parkingSpaceNumber);
    }
}
