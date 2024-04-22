import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FileUpload {
    @Test
    public void uploadImage(){
        Response response = given()
                .contentType(ContentType.MULTIPART)
                .multiPart("file", new File("src/main/resources/bg.png"))
                .when()
                .post("https://petstore.swagger.io/v2/pet/1/uploadImage")
                .then()
                .log()
                .all()
                .extract()
                .response();
        Assert.assertEquals(response.statusCode(), 200);
    }
}
