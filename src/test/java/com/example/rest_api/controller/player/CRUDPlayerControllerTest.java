package com.example.rest_api.controller.player;

import com.example.rest_api.controller.player.model.PlayerController;
import com.example.rest_api.controller.player.model.PlayerDto;
import com.example.rest_api.controller.shared.ErrorMessage;
import com.example.rest_api.data.TestData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.rest_api.config.Urls.PLAYERS_URL;

public class CRUDPlayerControllerTest {

    private PlayerController playerController = new PlayerController();


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


        PlayerDto createdPlayer = playerController.createPlayer(player)
                .statusCode(201)
                .extract().body().as(PlayerDto.class);

        Assert.assertTrue(!createdPlayer.getId().isEmpty());
        Assert.assertEquals(player.getFullName(), createdPlayer.getFullName());

    }

    @Test
    public void createNewPlayerWithIdTest(){
        PlayerDto player=new PlayerDto.Builder().id("100").fullName("TestName").position("TestPosition").build();

        playerController
                .createPlayer(player)
                .statusCode(400);
    }



    @Test
    public void createPlayerWithoutBodyTest() {
        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
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
        String playerId = "3";
        PlayerDto actualPlayer = playerController.getOnePlayer(playerId)
                .statusCode(200)
                .extract().body().as(PlayerDto.class);

        Assert.assertEquals(actualPlayer, TestData.playerWithThirdId);

    }

//===============================================
//    Santa Clara Suite
//    4/17/2020
//===============================================

    @Test
    public void getV1PlayerId() {
        //String id="10000";
        //+> id, +10
        String playerId = "5";

        PlayerDto expectedPlayer = new PlayerDto.Builder()
                .id(playerId)
                .fullName("Laurent Koscielny")
                .position("DF")
                .teamName("Arsenal")
                .build();

        PlayerDto actualPlayer = playerController.getOnePlayer(playerId)
                .statusCode(200)
                .extract().body().as(PlayerDto.class);

        Assert.assertEquals(actualPlayer, expectedPlayer);


    }
//===============================================
//    Mountain View suite
//    4/18/2020
//===============================================

    private String expectedResponseNotFoundPattern = "Player with id: [%s] not found";

    @Test
    public void getV1PlayerIdNotFound() {

        String id="23";
        String expectedErrorMessage = String.format(expectedResponseNotFoundPattern, id);

        ErrorMessage actualErrorMessage = playerController.getOnePlayer(id)
                .statusCode(404)
                .extract().body().as(ErrorMessage.class);

        Assert.assertEquals(actualErrorMessage.getMessage(), expectedErrorMessage);
        Assert.assertNotNull(actualErrorMessage.getTimestamp());
        Assert.assertTrue(!actualErrorMessage.getTimestamp().isEmpty());
    }
}
