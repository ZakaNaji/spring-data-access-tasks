package com.znaji.springdataaccesstasks.vehicle.cmd.runner;

import com.znaji.springdataaccesstasks.vehicle.dao.VehicleDao;
import com.znaji.springdataaccesstasks.vehicle.exception.MyDuplicateKeyException;
import com.znaji.springdataaccesstasks.vehicle.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class VehicleDaoCmdRunner implements CommandLineRunner {

    private final VehicleDao vehicleDao;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("inserting vehicle");
        try {
            vehicleDao.insert(Vehicle.builder().vehicleNo("Fe-345").color("Red").wheel(4).seat(2).build());
        } catch (MyDuplicateKeyException e) {
            SQLException cause = (SQLException) e.getCause();
            System.out.println("error instance: " + e.getClass().getName());
            System.out.println("Error code: " + cause.getErrorCode());
            System.out.println("SQL state: " + cause.getSQLState());
        }
    }
}
