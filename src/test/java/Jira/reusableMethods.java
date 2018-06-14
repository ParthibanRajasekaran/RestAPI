package Jira;

import Utility.helpers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Jira.resources.addNewIssue;
import static Jira.resources.authenticationAccess;
import static io.restassured.RestAssured.given;

public class reusableMethods {

    public static String createAndFetchBugID(){
        RestAssured.baseURI="http://localhost:8080";

        Response rawRes= given().
                header("cookie","JSESSIONID="+jiraAuthentication()+"").
                body(payLoad.createBugViaPOST("RES","Create a sample Bug via RestAPI")).
                header("Content-Type","application/json").
        when().
                post(addNewIssue).
        then().
                assertThat().statusCode(201).and().
                contentType(ContentType.JSON).
                header("X-Seraph-LoginReason","OK").
                extract().response();

        String bugID = helpers.convertRawToJson(rawRes).get("id");
        helpers.log.info("Bug Id created is : "+bugID);
        return bugID;
    }

    public static String createCommentsAndReturnID(String issueID){
        String commentID;
        RestAssured.baseURI = "http://localhost:8080";

        Response rawRes = given().
                header("cookie","JSESSIONID="+ jiraAuthentication()+"").
                contentType(ContentType.JSON).
                /*param("body","I have added a sample comment for the Jira bug").
                param("visibility.type","role").
                param("visibility.value","Administrators").*/
                body("{\n" +
                        "      \"body\": \"I have added a sample comment for the Jira bug\",\n" +
                        "      \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "      }\n" +
                        "}").
                pathParam("id",issueID).
        when().
                post(resources.addComment).
        then().
                assertThat().statusCode(201).
                contentType(ContentType.JSON).
                extract().response();

        commentID = helpers.convertRawToJson(rawRes).get("id");
        helpers.log.info("Comment Id created is : "+commentID);
        return commentID;
    }

    public static String jiraAuthentication(){
        String sessionValue;

        RestAssured.baseURI="http://localhost:8080";

        Response rawRes = given().
                header("Content-Type","application/json").
                body("{\"username\":\"rajasekaran.parthiban7\", \"password\":\"P@ssw0rd\"}").
        when().
                post(authenticationAccess).
        then().
                assertThat().statusCode(200).and().
                contentType(ContentType.JSON).
                extract().response();

        sessionValue = helpers.convertRawToJson(rawRes).get("session.value");

        helpers.log.info("Session Value for authentication is : "+sessionValue);

        return sessionValue;
    }
}
