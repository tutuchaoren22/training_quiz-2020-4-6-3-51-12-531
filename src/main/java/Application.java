import controllers.ParkingController;
import exception.InvalidInputException;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;

import java.util.Scanner;

public class Application {
    private static ParkingController parkingController = new ParkingController();

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
        switch (choice) {
            case "1":
                System.out.println("请输入初始化数据\n格式为\"停车场编号1：车位数,停车场编号2：车位数\" 如 \"A:8,B:9\"：");
                String initInfo = scanner.next();
                init(initInfo);
                break;
            case "2":
                System.out.println("请输入车牌号\n格式为\"车牌号\" 如: \"A12098\"：");
                String carInfo = scanner.next();
                try {
                    String ticket = park(carInfo);
                    String[] ticketDetails = ticket.split(",");
                    System.out.format("已将您的车牌号为%s的车辆停到%s停车场%s号车位，停车券为：%s，请您妥善保存。\n", ticketDetails[2], ticketDetails[0], ticketDetails[1], ticket);
                } catch (ParkingLotFullException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "3":
                System.out.println("请输入停车券信息\n格式为\"停车场编号1,车位编号,车牌号\" 如 \"A,1,8\"：");
                String ticket = scanner.next();
                try {
                    String car = fetch(ticket);
                    System.out.format("已为您取到车牌号为%s的车辆，很高兴为您服务，祝您生活愉快!\n", car);
                } catch (InvalidTicketException | InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }
    }

    public static void init(String initInfo) {
        try {
            parkingController.init(initInfo);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String park(String carNumber) {
        return parkingController.park(carNumber);
    }

    public static String fetch(String ticket) {
        return parkingController.fetch(ticket);
    }

}
