package services;

import entities.ParkingSpace;
import repositories.ParkingSpaceRepository;
import repositories.ParkingSpaceRepositoryI;

import java.util.List;

public class ParkingSpaceServices implements ParkingSpaceServicesI {
    private ParkingSpaceRepositoryI parkingSpaceRepository=new ParkingSpaceRepository();
    @Override
    public List<ParkingSpace> getAllParkingSpaceInfo() {
        return parkingSpaceRepository.getAllParkingSpaceInfo();
    }

    @Override
    public ParkingSpace getParkingSpaceForTicket(String ticket) {
        return parkingSpaceRepository.getParkingSpaceForTicket(ticket);
    }

    @Override
    public void deleteParkingSpaceForTicket(String ticket) {
        parkingSpaceRepository.deleteParkingSpaceForTicket(ticket);
    }

    @Override
    public List<ParkingSpace> getParkingSpaceForParkingLot(String parkingLotId) {
        return parkingSpaceRepository.getParkingSpaceForParkingLot(parkingLotId);
    }

    @Override
    public void insertParkingSpaceForTicket(String ticket) {
        parkingSpaceRepository.insertParkingSpaceForTicket(ticket);
    }

    @Override
    public void deleteAllParkingSpaceInfo() {
        parkingSpaceRepository.deleteAllParkingSpaceInfo();
    }
}
