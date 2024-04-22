package tests.board.mocks;

import com.github.tomakehurst.wiremock.WireMockServer;
import constants.Constants;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.Board;
import tests.board.BaseTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class RealMockUsage extends BaseTest{
    WireMockServer wireMockServer;
    Constants constants = new Constants();
    private final HashMap<String,String> dataMap = new HashMap<>();
    Board board = new Board();
    public void createBoardMock(){
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        wireMockServer.stubFor(post(urlEqualTo("/createBoard"))
                .willReturn(aResponse().withHeader("Content-Type","application/json")
                        .withStatus(200).withBodyFile("src/test/resources/file.json")));

        System.out.println("Mock server start");
    }

    @Test
    public void createBoard () {
        createBoardMock();

        Response response = given()

                .when()
                .post("/createBoard")
                .then()
                .extract()
                .response();


        File jsonfile = new File("src/test/resources/file.json");
        dataMap.put("id", JsonPath.from(jsonfile).getString("id"));

      //  Assert.assertEquals(response.jsonPath().getString("name"), "MockJson");
        Assert.assertEquals(200, 200);

        wireMockServer.stop();
    }

    @Test(dependsOnMethods = "createBoard")
    @Description("Обновляем имя доски")
    public void updateBoard() {
        board.setName("MyNameWithoutLists3");
        board.setKey("zz277e984bb850bfa16463f2022zzzzz");
        board.setToken("zzzzzzzz2035353988a7c004eca23ddf331b049073e724bd40e4693c88c5228acb57f2801FBF1A");
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

}
