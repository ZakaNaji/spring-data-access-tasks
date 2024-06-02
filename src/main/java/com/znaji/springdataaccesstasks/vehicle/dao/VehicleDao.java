package com.znaji.springdataaccesstasks.vehicle.dao;

import com.znaji.springdataaccesstasks.vehicle.model.Vehicle;

import java.util.Collection;
import java.util.List;

public interface VehicleDao {

    void insert(Vehicle vehicle);
    void update(Vehicle vehicle);
    void delete(Vehicle vehicle);
    Vehicle findByVehicleNo(String vehicleNo);
    List<Vehicle> findAll();
    default void insert(Collection<Vehicle> vehicles) {
        vehicles.forEach(this::insert);
    }

    default String getColor(String vehicleNo) {
        throw new IllegalStateException("Method is not implemented!");
    }

    default int countAll() {
        throw new IllegalStateException("Method is not implemented!");
    }
}
