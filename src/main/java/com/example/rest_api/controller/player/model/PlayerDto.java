package com.example.rest_api.controller.player.model;

import java.util.Objects;

public class PlayerDto {
    private String fullName;
    private String id;
    private String position;
    private String teamName;

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "fullName='" + fullName + '\'' +
                ", id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDto playerDto = (PlayerDto) o;
        return Objects.equals(fullName, playerDto.fullName) &&
                Objects.equals(id, playerDto.id) &&
                Objects.equals(position, playerDto.position) &&
                Objects.equals(teamName, playerDto.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, id, position, teamName);
    }

    public static class Builder {
        private PlayerDto player = new PlayerDto();

        public Builder fullName(String fullName) {
            player.setFullName(fullName);
            return this;
        }

        public Builder id(String id) {
            player.setId(id);
            return this;
        }

        public Builder position(String position) {
            player.setPosition(position);
            return this;
        }

        public Builder teamName(String teamName) {
            player.setTeamName(teamName);
            return this;
        }

        public PlayerDto build() {
            return player;

        }


    }


//        "fullName": "string",
//          id": 0,
//          "position": "string",
//          "teamName": "string"

}
