import entities.ParkingLot;
import entities.ParkingSpace;
import exception.InvalidInputException;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;
import repositories.ParkingLotRepository;
import repositories.ParkingSpaceRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
    private static ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
    private static ParkingSpaceRepository parkingSpaceRepository = new ParkingSpaceRepository();

    public static void main(String[] args) {
        operateParking();
    }

    public static void operateParking() {
        while (true) {
            System.out.println("1. 初始化停车场数据\n2. 停车\n3. 取车\n4. 退出\n请输入你的选择(1~4)：");
            Scanner printItem = new Scanner(System.in);
            String choice = printItem.next();
            if (choice.equals("4")) {
                System.out.println("系统已退出");
                break;
            }
            handle(choice);
        }
    }

    private static void handle(String choice) {
        Scanner scanner = new Scanner(System.in);
        if (choice.equals("1")) {
            System.out.println("请输入初始化数据\n格式为\"停车场编号1：车位数,停车场编号2：车位数\" 如 \"A:8,B:9\"：");
            String initInfo = scanner.next();
            try {
                init(initInfo);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } else if (choice.equals("2")) {
            System.out.println("请输入车牌号\n格式为\"车牌号\" 如: \"A12098\"：");
            String carInfo = scanner.next();
            try {
                String ticket = park(carInfo);
                String[] ticketDetails = ticket.split(",");
                System.out.format("已将您的车牌号为%s的车辆停到%s停车场%s号车位，停车券为：%s，请您妥善保存。\n", ticketDetails[2], ticketDetails[0], ticketDetails[1], ticket);
            } catch (ParkingLotFullException e) {
                System.out.println(e.getMessage());
            }
        } else if (choice.equals("3")) {
            System.out.println("请输入停车券信息\n格式为\"停车场编号1,车位编号,车牌号\" 如 \"A,1,8\"：");
            String ticket = scanner.next();
            try {
                String car = fetch(ticket);
                System.out.format("已为您取到车牌号为%s的车辆，很高兴为您服务，祝您生活愉快!\n", car);
            } catch (InvalidTicketException e) {
                System.out.println(e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void init(String initInfo) {
        if (!(initInfo.contains(",") && initInfo.contains(":"))) {
            throw new InvalidInputException("请输入正确的初始化停车场数据");
        }
        parkingLotRepository.deleteAllParkingLotInfo();
        parkingSpaceRepository.deleteAllParkingSpaceInfo();
        for (String parkingLotInfo : initInfo.split(",")) {
            String[] parkingLotInfoArr = parkingLotInfo.split(":");
            parkingLotRepository.initParkingLotInfo(parkingLotInfoArr[0], Integer.parseInt(parkingLotInfoArr[1]));
        }
    }

    public static String park(String carNumber) {
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
            int parkingSpaceId = 0;
            for (ParkingLot parkingLotInfo : allParkingLot) {
                List<ParkingSpace> parkingSpaceInfoForParkingLot = parkingSpaceRepository.getParkingSpaceForParkingLot(parkingLotInfo.getParkingLotId());
                parkingSpaceId = findParkingSpace(parkingLotInfo.getParkingSpaceNumber(), parkingSpaceInfoForParkingLot);
                if (0 == parkingSpaceId) {
                    continue;
                } else {
                    ticket = parkingLotInfo.getParkingLotId() + "," + parkingSpaceId + "," + carNumber;
                    break;
                }
            }
            parkingSpaceRepository.insertParkingSpaceForTicket(ticket);
        }
        return ticket;
    }

  public static int findParkingSpace(int parkingSpaceNumber, List<ParkingSpace> parkingSpaceInfoForParkingLot) {
    int[] hasParkedSpace = parkingSpaceInfoForParkingLot.stream()
            .map(ParkingSpace::getParkingSpaceId)
            .sorted()
            .mapToInt(Integer::intValue)
            .toArray();
    int[] canParkSpace = new int[parkingSpaceNumber + 1];
    for (int i = 0; i < hasParkedSpace.length; i++) {
      canParkSpace[hasParkedSpace[i]] = hasParkedSpace[i];
    }
    for (int i = 1; i < canParkSpace.length; i++) {
      if (canParkSpace[i] != i) {
        return i;
      }
    }
    return 0;
  }

  public static String fetch(String ticket) {
        return "";
    }

}
