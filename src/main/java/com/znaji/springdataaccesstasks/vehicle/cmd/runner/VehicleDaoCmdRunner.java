package com.znaji.springdataaccesstasks.vehicle.cmd.runner;

import com.znaji.springdataaccesstasks.vehicle.dao.VehicleDao;
import com.znaji.springdataaccesstasks.vehicle.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class VehicleDaoCmdRunner implements CommandLineRunner {

    private final VehicleDao vehicleDao;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("start inserting vehicle...");
        vehicleDao.insert(new Vehicle("TEM0001", "Red", 4, 4));
        System.out.printf("Vehicle inserted: %s%n", vehicleDao.findByVehicleNo("TEM0001"));
    }
}
