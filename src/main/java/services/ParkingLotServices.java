package services;



import entities.ParkingLot;
import repositories.ParkingLotRepository;
import repositories.ParkingLotRepositoryI;

import java.util.List;

public class ParkingLotServices implements ParkingLotServicesI {
    private ParkingLotRepositoryI parkingLotRepository = new ParkingLotRepository();

    @Override
    public List<ParkingLot> getAllParkingLotInfo() {
        return parkingLotRepository.getAllParkingLotInfo();
    }

    @Override
    public void deleteAllParkingLotInfo() {
        parkingLotRepository.deleteAllParkingLotInfo();
    }

    @Override
    public void initParkingLotInfo(String parkingLotId, int parkingSpaceNumber) {
        parkingLotRepository.initParkingLotInfo(parkingLotId,parkingSpaceNumber);
    }
}
