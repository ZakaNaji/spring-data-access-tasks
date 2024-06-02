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
        System.out.println("test insert");
        vehicleDao.insert(Vehicle.builder().vehicleNo("CAR001").color("RED").wheel(4).seat(4).build());
        System.out.printf("inserted vehicle: %s%n", vehicleDao.findByVehicleNo("CAR001"));
    }
}