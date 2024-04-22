package tests.board;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Board;
import pojo.List;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BoardsTest extends BaseTest {

    private final HashMap<String,String> dataMap = new HashMap<>();
    Board board = new Board();
    List list = new List();

    @Test(priority = 1)
    @Description("Создаем доску")
    public void createBoard () {
        board.setName("MyNameWithoutLists2");
        board.setKey(apikey);
        board.setToken(token);
        board.setDefaultLists(true);
        board.setDefaultLabels(true);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(board)
                .when()
                .post(constants.BOARDS)
                .then()
                .log()
                .all()
                .extract()
                .response();


        dataMap.put("id", response.jsonPath().getString("id"));
        Assert.assertEquals(response.jsonPath().getString("name"), "MyNameWithoutLists2");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 2)
    @Description("Обновляем имя доски")
    public void updateBoard() {
        board.setName("MyNameWithoutLists2");
        board.setKey(apikey);
        board.setToken(token);
        Response response = given()
                .contentType(ContentType.JSON)
                //.header("Cookie", "token=" + token)
                .body(board)
                .when()
                .put(constants.BOARDS + dataMap.get("id"))
                .then()
                .log()
                .all()
                .extract()
                .response();

        dataMap.put("id", response.jsonPath().getString("id"));
        Assert.assertEquals(response.statusCode(), 200);
    }


    @Test(priority = 4)
    @Description("Удаляем доску")
    public void deleteBoard() {
        board.setKey(apikey);
        board.setToken(token);
        Response response = given()
                .contentType(ContentType.JSON)
                //.header("Cookie", "token=" + token)
                .body(board)
                .when()
                .delete(constants.BOARDS + dataMap.get("id"))
                .then()
                .log()
                .all()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3)
    @Description("Создаем лист в доске")
    public void createList () {
        list.setName("MyFirstList");
        list.setKey(apikey);
        list.setToken(token);
        list.setIdBoard(dataMap.get("id"));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(list)
                .when()
                .post(constants.LISTS)
                .then()
                .log()
                .all()
                .extract()
                .response();

        dataMap.put("idList", response.jsonPath().getString("id"));
        Assert.assertEquals(response.statusCode(), 200);
    }
}
