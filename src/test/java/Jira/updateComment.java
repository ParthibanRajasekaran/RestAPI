package Jira;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static Utility.helpers.*;
import static io.restassured.RestAssured.given;

public class updateComment {

    @Test
    public void updateComment(){
        RestAssured.baseURI = "http://localhost:8080";
        String bugId = reusableMethods.createAndFetchBugID();
        given().
                log().all().
                header("cookie","JSESSIONID="+ reusableMethods.jiraAuthentication()+"").
                contentType(ContentType.JSON).
                body("{\n" +
                        "      \"body\": \"I hereby update my previous comment via api\",\n" +
                        "      \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "      }\n" +
                        "}").
                pathParam("id",bugId).
                pathParam("commentId", reusableMethods.createCommentsAndReturnID(bugId)).
        when().
                put(resources.updateComment).
        then().
                assertThat().statusCode(200).
                contentType(ContentType.JSON);
        log.info("Comment for Bug: "+bugId+" is updated successfully");
    }

}
