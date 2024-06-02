package com.znaji.springdataaccesstasks.vehicle.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vehicle {
    private String vehicleNo;
    private String color;
    private int wheel;
    private int seat;
}
