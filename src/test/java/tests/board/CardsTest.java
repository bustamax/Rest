package tests.board;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Board;
import pojo.Card;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CardsTest extends BaseTest{

    private final HashMap<String,String> dataMap = new HashMap<>();
    Card card = new Card();

    @Test
    @Description("Создаем карту в доске с айди")
    public void createCard(){
        card.setIdList("6601bd4201d1b43716574566");
        card.setName("ThisIsNewNameCard");
        card.setDesc("Description");
        card.setToken(token);
        card.setKey(apikey);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(card)
                .when()
                .post(constants.CARDS)
                .then()
                .log()
                .all()
                .extract()
                .response();

        dataMap.put("idCard", response.jsonPath().getString("id"));
        Assert.assertEquals(response.statusCode(), 200);

    }
}
