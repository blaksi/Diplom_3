package ru.yandex.practicum.utils;


import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private final String name;
    private final String password;
    private final String email;

    public UserApi(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Step("Удаление пользователя по accessToken")
    public static void deleteUser(String accessToken) {
        RestAssured.baseURI = BASE_URI;
        given().header("Authorization", "Bearer " + accessToken)
                .delete("/api/auth/user")
                .then().statusCode(202);
    }

    @Step("Регистрация нового пользователя")
    public String registerNewUser() {
        RestAssured.baseURI = BASE_URI;
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}", email, password, name);

        Response response = given()
                .header("Content-type", "application/json")
                .body(body)
                .post("/api/auth/register");

        return response.then().extract().path("accessToken").toString().replace("Bearer ", "");
    }
}