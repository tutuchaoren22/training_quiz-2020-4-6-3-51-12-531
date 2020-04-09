package controllers;

import exception.InvalidInputException;
import services.*;

public class ParkingController {
    private ParkingServices parkingServices = new ParkingServices();

    public void init(String initInfo) {
        if (!(initInfo.contains(",") && initInfo.contains(":"))) {
            throw new InvalidInputException("请输入正确的初始化停车场数据");
        }
        parkingServices.init(initInfo);
    }

    public String park(String carNumber) {
        return parkingServices.park(carNumber);
    }

    public String fetch(String ticket) {
        if (ticket.split(",").length != 3) {
            throw new InvalidInputException("请输入正确的停车券");
        }
        return parkingServices.fetch(ticket);
    }
}
