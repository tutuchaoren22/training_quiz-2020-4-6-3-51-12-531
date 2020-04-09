package entities;

public class ParkingLot {
    private int id;
    private String parkingLotId;
    private int parkingSpaceNumber;

    public ParkingLot() {
    }

    public ParkingLot(String parkingLotId, int parkingSpaceNumber) {
        this.parkingLotId = parkingLotId;
        this.parkingSpaceNumber = parkingSpaceNumber;
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

    public int getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public void setParkingSpaceNumber(int parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id=" + id +
                ", parkingLotId='" + parkingLotId + '\'' +
                ", parkingSpaceNumber='" + parkingSpaceNumber + '\'' +
                '}';
    }
}
