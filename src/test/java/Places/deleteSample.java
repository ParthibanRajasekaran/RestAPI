package Places;

import GooglePlaces.payLoad;
import GooglePlaces.resources;
import Utility.helpers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class deleteSample extends helpers {
    Properties prop;

    @BeforeMethod
    public void setup() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/Utility/googlePlaces_env.properties");
        prop.load(fis);
    }

    @Test
    public void deletePlace(){
        RestAssured.baseURI=prop.getProperty("HOST");

        Response rawRes =  given().
                queryParam("key",prop.getProperty("KEY")).
                body(payLoad.createValidPlaceViaPOST()).
                when().
                post(resources.addNewPlace).
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status",equalTo("OK")).
                extract().response();

        // response is in Raw format and has to be converted into a String
//        String responseString = rawRes.asString();
//
//        // Response in String format has to be converted in to a Json Format
//        JsonPath jsonResponse = new JsonPath(responseString);
        String placeId = convertRawToJson(rawRes).get("place_id");
        System.out.println(placeId);

        given().
                queryParam("key",prop.getProperty("KEY")).
                body(payLoad.deletePlace(placeId)).
                when().
                post(resources.deletePlace).
                then().
                assertThat().statusCode(200).and().
                body("status",equalTo("OK"));
        }
    }

