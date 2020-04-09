package entities;

public class ParkingSpace {
    private int id;
    private String parkingLotId;
    private int parkingSpaceId;
    private String carNumbers;

    public ParkingSpace() {
    }

    public ParkingSpace(String parkingLotId, int parkingSpaceId, String carNumbers) {
        this.parkingLotId = parkingLotId;
        this.parkingSpaceId = parkingSpaceId;
        this.carNumbers = carNumbers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public int getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(int parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public String getCarNumbers() {
        return carNumbers;
    }

    public void setCarNumbers(String carNumbers) {
        this.carNumbers = carNumbers;
    }
}
