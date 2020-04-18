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
        System.out.println("NotNull assertion PASSED for PLAYERS_URL");
        Assert.assertTrue(!body.isEmpty());
        System.out.println("NotEmpty assertion PASSED for PLAYERS_URL");
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
        System.out.println("NotNull assertion PASSED for TEAMS_URL");
        Assert.assertTrue(!body.isEmpty());
        System.out.println("NotEmpty assertion PASSED for TEAMS_URL");
    }

    //2
//    {
//        "id": 3,
//            "fullName": "Mohamed Elneny",
//            "position": "MF",
//            "teamName": "Arsenal"
//    }
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
        System.out.println("Expected result:");
        System.out.println("{\n" +
                "  \"id\": 3,\n" +
                "  \"fullName\": \"Mohamed Elneny\",\n" +
                "  \"position\": \"MF\",\n" +
                "  \"teamName\": \"Arsenal\"\n" +
                "}");
        System.out.println("Actual result:");
        System.out.println(actualBody);
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
        System.out.println("===============================================");
        System.out.println("Actual result:");
        System.out.println(actualFullName);
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
