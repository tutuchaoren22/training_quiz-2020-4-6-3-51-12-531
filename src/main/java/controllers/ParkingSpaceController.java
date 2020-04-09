package controllers;

import entities.ParkingSpace;
import services.ParkingSpaceServices;
import services.ParkingSpaceServicesI;

import java.util.List;

public class ParkingSpaceController {
    private ParkingSpaceServicesI parkingSpaceServices=new ParkingSpaceServices();
    public List<ParkingSpace> getAllParkingSpaceInfo() {
        return parkingSpaceServices.getAllParkingSpaceInfo();
    }

    public ParkingSpace getParkingSpaceForTicket(String ticket) {
        return parkingSpaceServices.getParkingSpaceForTicket(ticket);
    }

    public void deleteParkingSpaceForTicket(String ticket) {
        parkingSpaceServices.deleteParkingSpaceForTicket(ticket);
    }

    public List<ParkingSpace> getParkingSpaceForParkingLot(String parkingLotId) {
        return parkingSpaceServices.getParkingSpaceForParkingLot(parkingLotId);
    }

    public void insertParkingSpaceForTicket(String ticket) {
        parkingSpaceServices.insertParkingSpaceForTicket(ticket);
    }

    public void deleteAllParkingSpaceInfo() {
        parkingSpaceServices.deleteAllParkingSpaceInfo();
    }


}
