package com.znaji.springdataaccesstasks.vehicle.cmd.runner;

import com.znaji.springdataaccesstasks.vehicle.dao.VehicleDao;
import com.znaji.springdataaccesstasks.vehicle.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class VehicleDaoCmdRunner implements CommandLineRunner {

    private final VehicleDao vehicleDao;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("test batch insert");
        var vehicles = List.of(
                Vehicle.builder().vehicleNo("CAR1").color("RED").wheel(4).seat(4).build(),
                Vehicle.builder().vehicleNo("CAR2").color("BLUE").wheel(4).seat(4).build(),
                Vehicle.builder().vehicleNo("CAR3").color("GREEN").wheel(4).seat(4).build()
        );
        vehicleDao.insert(vehicles);
        System.out.println("test find all");
    }
}
