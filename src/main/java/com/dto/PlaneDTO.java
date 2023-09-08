package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PlaneDTO {
    private long id;
    private String mark;
    private String model;
    private String type;
    private int mileage;
    private int price;
    private long idProfile;
}
