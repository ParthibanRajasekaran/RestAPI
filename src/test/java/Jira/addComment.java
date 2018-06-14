package Jira;

import org.testng.annotations.*;
import java.io.*;
import java.util.Properties;

public class addComment {

    private Properties prop;

    @BeforeMethod
    public void setup() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/Utility/jira_env.properties");
        prop.load(fis);
    }


    @Test
    public void addComment(){
        reusableMethods.createCommentsAndReturnID(reusableMethods.createAndFetchBugID());
    }
}
