package tests.board;

import constants.Constants;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {


    String token = "чтототутещеAe42035353988a7c004eca23ddf331b049073e724bd40e4693c88c5228acb57f2801FBF1A";
    String apikey = "b88277e984bb850bfa16463f2итут";
    Constants constants = new Constants();


    @BeforeTest
    public static void setup() { baseURI = "https://api.trello.com/1/";}

}
