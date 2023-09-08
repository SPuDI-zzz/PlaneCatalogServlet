package com.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Plane {
    private long id;
    private String mark;
    private String model;
    private String type;
    private int mileage;
    private int price;
    private long idProfile;
}
