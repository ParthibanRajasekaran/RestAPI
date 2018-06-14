package Places;


import Utility.helpers;
import GooglePlaces.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;


public class getSample_AdvancedSearch extends helpers {
    Properties prop;

    @BeforeMethod
    public void setup() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/Utility/googlePlaces_env.properties");
        prop.load(fis);
    }

    @Test
    public void getNearByPlaces(){
        RestAssured.baseURI=prop.getProperty("HOST");

        Response res = RestAssured.given().
                log().all().
                param("location","-33.8670522,151.1957362").
                param("radius","500").
                param("key",prop.getProperty("KEY")).
        when().
                get(resources.getNearbyPlaces).
        then().
                //log().all().
                assertThat().
                statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("results[0].name",equalTo("Sydney")).and().
                body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
                extract().response();

        JsonPath js = convertRawToJson(res);

        int resultSize = js.get("results.size()");
        System.out.println(resultSize);

        for(int i=0;i<resultSize;i++){
            String resultName = js.get("results["+i+"].name");
            System.out.println(resultName);
        }
    }
}
