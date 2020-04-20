package com.example.rest_api;

import com.example.rest_api.controller.player.model.PlayerDto;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MtnViewCheckTest {

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
            System.out.println(body);

        }


        //1
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



        //2
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
            System.out.println(actualFullName);
        }

    //3
    //http://localhost:8088/v1/player/find?name=Mohamed%20Elneny
    private String expectedfindByName = "Mohamed Elneny";
    @Test
    public void getFindByNamePlayerCheck() {

        String actualFullName = RestAssured.given()
                .log().all()
                .when()
                .get(PLAYERS_URL + "/find?name=" + expectedfindByName)
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getString("fullName");

        Assert.assertEquals(expectedFullName, actualFullName);
        System.out.println(actualFullName);
    }

    //4
    //http://localhost:8088/v1/player/lazy
    @Test
    public void getAllLazyPlayersCheck() {

        String body = RestAssured
                .when()
                .get(PLAYERS_URL + "/lazy")
                .then()
                .statusCode(200)
                .extract().body().asString();

        Assert.assertNotNull(body);
        Assert.assertTrue(!body.isEmpty());
        System.out.println(body);

    }



    }
