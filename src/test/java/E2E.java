import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class E2E {

    private final HashMap<String,String> dataMap = new HashMap<>();
    private String body = "{\n" +
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
            "}";

    @Test
    public void createPet(){
      Response responce =  given()
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io/v2")
                .body(body)
                .when()
                .post("/pet")
                .then()
                .log()
                //все придет и заголовки
                .all()
                .extract()
                .response();
/**
 * проверяем статус код
 */
        Assert.assertEquals(200, responce.statusCode());
        Assert.assertEquals("pending", responce.jsonPath().getString("status"));
        dataMap.put("id", responce.jsonPath().getString("id"));

    }
/**
 * пишем что метод зависит от метода потому что там сохраняется айди созданного объекта
 */
    @Test(dependsOnMethods = "createPet")
    public void deletePet(){
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .delete("/pet/"+dataMap.get("id"))
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }
}

