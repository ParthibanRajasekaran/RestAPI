package Jira;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.io.*;
import java.util.Properties;

import static Utility.helpers.convertRawToJson;
import static Jira.reusableMethods.jiraAuthentication;
import static Utility.helpers.log;
import static io.restassured.RestAssured.given;

public class deleteIssue {

    private Properties prop;

    @BeforeMethod
    public void setup() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/Utility/jira_env.properties");
        prop.load(fis);
    }

    @Test
    public void deleteIssue(){
        RestAssured.baseURI=prop.getProperty("HOST");

        Response rawRes = given().
                header("cookie","JSESSIONID="+jiraAuthentication()+"").
                body(payLoad.createBugViaPOST("RES","Create a sample Bug via RestAPI")).
                header("Content-Type","application/json").
        when().
                post(resources.addNewIssue).
        then().
                assertThat().statusCode(201).and().
                contentType(ContentType.JSON).
                header("X-Seraph-LoginReason","OK").
                extract().response();

        // response is in Raw format and has to be converted into a String
//        String responseString = rawRes.asString();
//
//        // Response in String format has to be converted in to a Json Format
//        JsonPath jsonResponse = new JsonPath(responseString);
        String bugKey = convertRawToJson(rawRes).get("key");
        log.info("Bug created : "+bugKey);

        given().
                header("cookie","JSESSIONID="+jiraAuthentication()+"").
                pathParam("key",bugKey).
        when().
                delete(resources.deleteIssue).
        then().
                assertThat().statusCode(204).
                log().all();
        log.info("Bug Id : "+bugKey+" deleted successfully");
    }
}
