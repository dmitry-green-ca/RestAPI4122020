package com.example.rest_api.controller.player.model;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static com.example.rest_api.config.Urls.PLAYERS_URL;

public class PlayerController {

    public ValidatableResponse getOnePlayer(String id){
        return RestAssured.given()
                .log().all()
                .when()
                .get(PLAYERS_URL + "/" + id)
                .then();

    }

    public ValidatableResponse createPlayer(PlayerDto player){
        return RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(player)
                .when()
                .post(PLAYERS_URL)
                .then();

    }




}
