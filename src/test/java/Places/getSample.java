package Places;

import GooglePlaces.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class getSample {
    private Properties prop;

    @BeforeMethod
    public void setup() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/Utility/googlePlaces_env.properties");
        prop.load(fis);
    }

    @Test
    public void getNearByPlaces(){
        RestAssured.baseURI=prop.getProperty("HOST");

        given().
                param("location","-33.8670522,151.1957362").
                param("radius","500").
                param("key",prop.getProperty("KEY")).
        when().
                get(resources.getNearbyPlaces).
        then().assertThat().
                statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("results[0].name",equalTo("Sydney")).and().
                body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"));

    }
}
