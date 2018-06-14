package Places;

import GooglePlaces.payLoad;
import GooglePlaces.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class postSample {
    Properties prop;

    @BeforeMethod
    public void setup() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/Utility/googlePlaces_env.properties");
        prop.load(fis);
    }

    @Test
    public void addPlace(){
        RestAssured.baseURI=prop.getProperty("HOST");

        given().
                queryParam("key",prop.getProperty("KEY")).body(payLoad.createValidPlaceViaPOST()).
        when().
                post(resources.addNewPlace).
        then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status",equalTo("OK"));



    }
}
