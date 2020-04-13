package com.example.rest_api.data;

import com.example.rest_api.controller.player.model.PlayerDto;

public class TestData {

    public static PlayerDto playerWithThirdId = new PlayerDto.Builder()
            .id("3")
            .fullName("Mohamed Elneny")
            .position("MF")
            .teamName("Arsenal")
            .build();

}
