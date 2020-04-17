package com.fuhe.chen.vendingmachine.common;

import com.fuhe.chen.vendingmachine.dto.CommodityInMachine;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {

    private boolean success;
    private int code;
    private String message;
    private List<CommodityInMachine> data;
}
