package Jira;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static Jira.reusableMethods.createAndFetchBugID;
import static Jira.reusableMethods.jiraAuthentication;
import static io.restassured.RestAssured.given;

public class createIssue {

    private Properties prop;

    @BeforeMethod
    public void setup() throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/Utility/jira_env.properties");
        prop.load(fis);
    }

    @Test
    public void createBug(){
       /* RestAssured.baseURI=prop.getProperty("HOST");

        given().
                log().all().
                header("cookie",prop.getProperty("COOKIE")).
                body(payLoad.createBugViaPOST("RES","Create a sample Bug via RestAPI")).
                header("Content-Type","application/json").
        when().
                post(resources.addNewIssue).
        then().
                assertThat().statusCode(201).and().
                contentType(ContentType.JSON).
                header("X-Seraph-LoginReason","OK");*/
        createAndFetchBugID();
    }

    @Test
    public void createTask(){
        RestAssured.baseURI=prop.getProperty("HOST");

        given().
                log().all().
                header("cookie","JSESSIONID="+jiraAuthentication()+"").
                body(payLoad.createTaskViaPOST("RES","Create a sample Task via RestAPI")).
                header("Content-Type","application/json").
                when().
                post(resources.addNewIssue).
                then().
                assertThat().statusCode(201).and().
                contentType(ContentType.JSON).
                header("X-Seraph-LoginReason","OK");
    }
}
