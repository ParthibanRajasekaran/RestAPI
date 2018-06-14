package Utility;

import Jira.payLoad;
import Jira.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Jira.resources.*;
import static io.restassured.RestAssured.given;


public class helpers {

    public static final Logger log = LogManager.getLogger(helpers.class);

    public static JsonPath convertRawToJson(Response rawRes){
        String responseString = rawRes.asString();

        // Response in String format has to be converted in to a Json Format
        JsonPath jsonResponse = new JsonPath(responseString);
        return jsonResponse;
    }


}
