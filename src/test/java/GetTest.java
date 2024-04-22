import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetTest {
    @Test
    public void getTest(){
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io/v2/")

                .when()
                .get("pet/findByStatus?status=available")

                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void createPet(){
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io/v2")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"OurDoggi\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"pending\"\n" +
                        "}")
                .when()
                .post("/pet")
                .then()
                .log()
                //все придет и заголовки
                //.all()
                //придет только тело ответа
                .body()
                .assertThat()
                .statusCode(200);

    }
}
