package repositories;

import entities.ParkingSpace;
import util.QueryTable;
import util.UpdateTable;

import java.util.List;

public class ParkingSpaceRepository implements ParkingSpaceRepositoryI {
    @Override
    public List<ParkingSpace> getAllParkingSpaceInfo() {
        String sql = "SELECT id,parking_lot_id,parking_space_id,car_numbers FROM parking_space_info";
        return QueryTable.queryTable(ParkingSpace.class, sql);
    }

    @Override
    public ParkingSpace getParkingSpaceForTicket(String ticket) {
        String[] ticketInfo = ticket.split(",");
        String sql = "SELECT id,parking_lot_id,parking_space_id,car_numbers FROM parking_space_info " +
                "WHERE parking_lot_id=? AND parking_space_id=? AND car_numbers=?";
        List<ParkingSpace> parkingInfo = QueryTable.queryTable(ParkingSpace.class, sql, ticketInfo[0], ticketInfo[1], ticketInfo[2]);
        if (null != parkingInfo && parkingInfo.size() > 0) {
            return parkingInfo.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void deleteParkingSpaceForTicket(String ticket) {
        String[] ticketInfo = ticket.split(",");
        String sql = "DELETE FROM parking_space_info WHERE parking_lot_id=? AND parking_space_id=? AND car_numbers=?";
        UpdateTable.update(sql, ticketInfo[0], ticketInfo[1], ticketInfo[2]);
    }

    @Override
    public List<ParkingSpace> getParkingSpaceForParkingLot(String parkingLotId) {
        String sql = "SELECT id,parking_lot_id,parking_space_id,car_numbers FROM parking_space_info WHERE parking_lot_id=?";
        return QueryTable.queryTable(ParkingSpace.class, sql, parkingLotId);
    }

    @Override
    public void insertParkingSpaceForTicket(String ticket) {
        String[] ticketInfo = ticket.split(",");
        String sql = "INSERT INTO parking_space_info (parking_lot_id ,parking_space_id ,car_numbers) VALUES (?,?,?) ";
        UpdateTable.update(sql, ticketInfo[0], ticketInfo[1], ticketInfo[2]);
    }

    @Override
    public void deleteAllParkingSpaceInfo() {
        String sql = "TRUNCATE TABLE parking_space_info";
        UpdateTable.update(sql);
    }
}
