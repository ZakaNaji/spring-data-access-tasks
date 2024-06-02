package com.znaji.springdataaccesstasks.vehicle.dao;

import com.znaji.springdataaccesstasks.vehicle.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@RequiredArgsConstructor
public class PlainJdbcVehicleDao implements VehicleDao {

    private final DataSource dataSource;

    private static final String INSERT_SQL = "INSERT INTO VEHICLE (COLOR, WHEEL, SEAT,VEHICLE_NO) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE VEHICLE SET COLOR=?,WHEEL=?,SEAT=? WHERE VEHICLE_NO=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM VEHICLE";
    private static final String SELECT_ONE_SQL = "SELECT * FROM VEHICLE WHERE VEHICLE_NO = ?";
    private static final String DELETE_SQL = "DELETE FROM VEHICLE WHERE VEHICLE_NO=?";

    @Override
    public void insert(Vehicle vehicle) {
        try (var con = dataSource.getConnection(); var ps = con.prepareStatement(INSERT_SQL)) {
            prepareStatement(vehicle, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Vehicle vehicle) {
        try (var con = dataSource.getConnection(); var ps = con.prepareStatement(UPDATE_SQL)) {
            prepareStatement(vehicle, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Vehicle vehicle) {
        try (var con = dataSource.getConnection(); var ps = con.prepareStatement(DELETE_SQL)) {
            ps.setString(1, vehicle.getVehicleNo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        try (var con = dataSource.getConnection(); var ps = con.prepareStatement(SELECT_ONE_SQL)) {
            ps.setString(1, vehicleNo);
            Vehicle vehicle = null;
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    vehicle = toVehicle(rs);
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Vehicle toVehicle(ResultSet rs) throws SQLException {
        return Vehicle.builder()
                .vehicleNo(rs.getString("VEHICLE_NO"))
                .color(rs.getString("COLOR"))
                .wheel(rs.getInt("WHEEL"))
                .seat(rs.getInt("SEAT"))
                .build();
    }

    @Override
    public List<Vehicle> findAll() {
        try (var con = dataSource.getConnection(); var ps = con.prepareStatement(SELECT_ALL_SQL)) {
            var vehicles = new java.util.ArrayList<Vehicle>();
            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(toVehicle(rs));
                }
            }
            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareStatement(Vehicle vehicle, PreparedStatement ps) throws SQLException {
        ps.setString(1, vehicle.getColor());
        ps.setInt(2, vehicle.getWheel());
        ps.setInt(3, vehicle.getSeat());
        ps.setString(4, vehicle.getVehicleNo());
    }
}
