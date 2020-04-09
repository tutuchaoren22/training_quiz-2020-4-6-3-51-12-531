package services;

import entities.ParkingLot;
import entities.ParkingSpace;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;
import repositories.ParkingLotRepository;
import repositories.ParkingLotRepositoryI;
import repositories.ParkingSpaceRepository;
import repositories.ParkingSpaceRepositoryI;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingServices {
    private ParkingLotRepositoryI parkingLotRepository = new ParkingLotRepository();
    private ParkingSpaceRepositoryI parkingSpaceRepository = new ParkingSpaceRepository();

    public void init(String initInfo) {
        parkingLotRepository.deleteAllParkingLotInfo();
        parkingSpaceRepository.deleteAllParkingSpaceInfo();
        for (String parkingLotInfo : initInfo.split(",")) {
            String[] parkingLotInfoArr = parkingLotInfo.split(":");
            parkingLotRepository.initParkingLotInfo(parkingLotInfoArr[0], Integer.parseInt(parkingLotInfoArr[1]));
        }
    }

    public String park(String carNumber) {
        String ticket = "";
        List<ParkingLot> allParkingLot = parkingLotRepository.getAllParkingLotInfo().stream()
                .sorted(Comparator.comparing(ParkingLot::getParkingLotId))
                .collect(Collectors.toList());
        int allSpaceNumbers = allParkingLot.stream()
                .map(ParkingLot::getParkingSpaceNumber)
                .mapToInt(Integer::intValue)
                .sum();
        List<ParkingSpace> allPackingSpace = parkingSpaceRepository.getAllParkingSpaceInfo();

        if (0 == allPackingSpace.size()) {
            ticket = allParkingLot.get(0).getParkingLotId() + ",1," + carNumber;
            parkingSpaceRepository.insertParkingSpaceForTicket(ticket);
        } else if (allPackingSpace.size() == allSpaceNumbers) {
            throw new ParkingLotFullException("非常抱歉，由于车位已满，暂时无法为您停车！");
        } else {
            int parkingSpaceId;
            for (ParkingLot parkingLotInfo : allParkingLot) {
                List<ParkingSpace> parkingSpaceInfoForParkingLot = parkingSpaceRepository.getParkingSpaceForParkingLot(parkingLotInfo.getParkingLotId());
                parkingSpaceId = findParkingSpace(parkingLotInfo.getParkingSpaceNumber(), parkingSpaceInfoForParkingLot);
                if (parkingSpaceId != 0) {
                    ticket = parkingLotInfo.getParkingLotId() + "," + parkingSpaceId + "," + carNumber;
                    break;
                }
            }
            parkingSpaceRepository.insertParkingSpaceForTicket(ticket);
        }
        return ticket;
    }

    public int findParkingSpace(int parkingSpaceNumber, List<ParkingSpace> parkingSpaceInfoForParkingLot) {
        int[] hasParkedSpace = parkingSpaceInfoForParkingLot.stream()
                .map(ParkingSpace::getParkingSpaceId)
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
        int[] canParkSpace = new int[parkingSpaceNumber + 1];
        for (int value : hasParkedSpace) {
            canParkSpace[value] = value;
        }
        for (int i = 1; i < canParkSpace.length; i++) {
            if (canParkSpace[i] != i) {
                return i;
            }
        }
        return 0;
    }

    public String fetch(String ticket) {
        ParkingSpace car = parkingSpaceRepository.getParkingSpaceForTicket(ticket);
        if (car != null) {
            parkingSpaceRepository.deleteParkingSpaceForTicket(ticket);
            return car.getCarNumbers();
        } else {
            throw new InvalidTicketException("很抱歉，无法通过您提供的停车券为您找到相应的车辆，请您再次核对停车券是否有效！");
        }
    }
}
