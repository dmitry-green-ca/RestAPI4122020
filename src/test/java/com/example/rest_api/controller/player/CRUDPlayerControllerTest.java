package com.example.rest_api.controller.player;

import com.example.rest_api.config.Urls;
import com.example.rest_api.controller.player.model.PlayerDto;
import com.example.rest_api.data.TestData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.rest_api.config.Urls.PLAYERS_URL;

public class CRUDPlayerControllerTest {


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


    @Test
    public void createNewPlayerTest(){
        PlayerDto player=new PlayerDto.Builder().fullName("TestName").position("TestPosition").build();


        PlayerDto createdPlayer = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(player)
                .when()
                .post(PLAYERS_URL)
                .then().statusCode(201)
                .extract().body().as(PlayerDto.class);

        Assert.assertTrue(!createdPlayer.getId().isEmpty());
        Assert.assertEquals(player.getFullName(), createdPlayer.getFullName());

    }

    @Test
    public void createNewPlayerWithIdTest(){
        PlayerDto player=new PlayerDto.Builder().id("100").fullName("TestName").position("TestPosition").build();

        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(player)
                .when()
                .post(PLAYERS_URL)
                .then().statusCode(400);
    }



    @Test
    public void createPlayerWithoutBodyTest() {
        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post(PLAYERS_URL)
                .then().statusCode(400);

    }

    @Test
    public void createPlayerWithEmptytBodyTest() {
        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post(PLAYERS_URL)
                .then().statusCode(500);

    }



    @Test
    public void getFullNameThirdViaModelPlayerCheck() {


        PlayerDto actualPlayer = RestAssured.given()
                //.log().all()
                .when()
                .get(PLAYERS_URL + "/3")
                .then()
                .statusCode(200)
                .extract().body().as(PlayerDto.class);

        Assert.assertEquals(actualPlayer, TestData.playerWithThirdId);

    }




}
