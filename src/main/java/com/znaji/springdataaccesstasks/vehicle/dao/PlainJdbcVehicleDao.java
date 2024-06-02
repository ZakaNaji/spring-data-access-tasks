package com.znaji.springdataaccesstasks.vehicle.dao;

import com.znaji.springdataaccesstasks.vehicle.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
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
        var jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(INSERT_SQL, ps -> prepareStatement(vehicle, ps));
    }


    @Override
    public void update(final Vehicle vehicle) {
        var jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(con -> {
            var ps = con.prepareStatement(UPDATE_SQL);
            prepareStatement(vehicle, ps);
            return ps;
        });
    }

    @Override
    public void delete(Vehicle vehicle) {
        var jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(DELETE_SQL, vehicle.getVehicleNo());
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        var jdbcTemplate = new JdbcTemplate(dataSource);
        var mapper = BeanPropertyRowMapper.newInstance(Vehicle.class);
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, mapper, vehicleNo);
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
        var jdbcTemplate = new JdbcTemplate(dataSource);
        var mapper = BeanPropertyRowMapper.newInstance(Vehicle.class);
        return jdbcTemplate.query(SELECT_ALL_SQL, mapper);
    }

    @Override
    public void insert(Collection<Vehicle> vehicles) {
        var jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.batchUpdate(INSERT_SQL, vehicles, vehicles.size(), (ps, vehicle) -> prepareStatement(vehicle, ps));
    }

    private void prepareStatement(Vehicle vehicle, PreparedStatement ps) throws SQLException {
        ps.setString(1, vehicle.getColor());
        ps.setInt(2, vehicle.getWheel());
        ps.setInt(3, vehicle.getSeat());
        ps.setString(4, vehicle.getVehicleNo());
    }
}
