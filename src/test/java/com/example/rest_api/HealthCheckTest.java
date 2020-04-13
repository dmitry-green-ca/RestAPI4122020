package com.example.rest_api;

import com.example.rest_api.controller.player.model.PlayerDto;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HealthCheckTest {
    //7:40
    private String PLAYERS_URL = "http://localhost:8088/v1/player";
    private String TEAMS_URL = "http://localhost:8088/v1/team";

    @Test
    public void getAllPlayerCheck() {

        String body = RestAssured
                .when()
                .get(PLAYERS_URL)
                .then()
                .statusCode(200)
                .extract().body().asString();

        Assert.assertNotNull(body);
        Assert.assertTrue(!body.isEmpty());
    }

    //1
    @Test
    public void getAllTeamsrCheck() {

        String body = RestAssured
                .when()
                .get(TEAMS_URL)
                .then()
                .statusCode(200)
                .extract().body().asString();

        Assert.assertNotNull(body);
        Assert.assertTrue(!body.isEmpty());
    }

    //2

    private String expectedPlayerBody = "{\"id\":3,\"fullName\":\"Mohamed Elneny\",\"position\":\"MF\",\"teamName\":\"Arsenal\"}";

    @Test
    public void getThirdPlayerCheck() {

        String actualBody = RestAssured
                .when()
                .get(PLAYERS_URL + "/3")
                .then()
                .statusCode(200)
                .extract().body().asString();

        Assert.assertEquals(expectedPlayerBody, actualBody);
    }


    //3

    private String expectedFullName = "Mohamed Elneny";

    @Test
    public void getFullNameThirdPlayerCheck() {

        String actualFullName = RestAssured.given()
                .log().all()
                .when()
                .get(PLAYERS_URL + "/3")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getString("fullName");

        Assert.assertEquals(expectedFullName, actualFullName);
    }

    //4


    @Test
    public void getFullNameThirdViaModelPlayerCheck() {
        PlayerDto expectedPlayer = new PlayerDto.Builder()
                .id("3")
                .fullName("Mohamed Elneny")
                .position("MF")
                .teamName("Arsenal")
                .build();

        PlayerDto actualPlayer = RestAssured.given()
                //.log().all()
                .when()
                .get(PLAYERS_URL + "/3")
                .then()
                .statusCode(200)
                .extract().body().as(PlayerDto.class);

        Assert.assertEquals(actualPlayer, expectedPlayer);

    }

}
