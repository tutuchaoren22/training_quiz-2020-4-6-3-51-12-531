package repositories;

import entities.ParkingLot;
import util.QueryTable;
import util.UpdateTable;

import java.util.List;

public class ParkingLotRepository implements ParkingLotRepositoryI{

    @Override
    public List<ParkingLot> getAllParkingLotInfo() {
        String sql = "SELECT id,parking_lot_id,parking_space_number FROM parking_lot_info";
        return QueryTable.queryTable(ParkingLot.class,sql);
    }

    @Override
    public void deleteAllParkingLotInfo() {
        String sql = "TRUNCATE TABLE parking_lot_info";
        UpdateTable.update(sql);
    }

    @Override
    public void initParkingLotInfo(String parkingLotId,int parkingSpaceNumber) {
        String sql = "INSERT INTO parking_lot_info (parking_lot_id,parking_space_number) VALUES(?,?)";
        UpdateTable.update(sql,parkingLotId,parkingSpaceNumber);
    }
}
