package tests.board.mocks;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockGet {

    WireMockServer wireMockServer;

    @BeforeTest
    public void setup(){
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        wireMockServer.stubFor(get(urlEqualTo("/testWireMock"))
                .willReturn(aResponse().withHeader("Content-Type","text/plain")
                        .withStatus(200)));

        System.out.println("Mock server start");
    }

    @Test
    public void positiveTest(){
        given()
                .when()
                .get("/testWireMock")
                .then()
                .assertThat().statusCode(200);
    }

    @AfterTest
    public void TearDown(){
        wireMockServer.stop();
        System.out.println("Mock server stopped");
    }
}
