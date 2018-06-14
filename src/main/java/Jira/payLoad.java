package Jira;

public class payLoad {

    public static String createBugViaPOST(String projectKey,String summary){
        String createBug = "{\n" +
                "    \"fields\": {\n" +
                "       \"project\":\n" +
                "       {\n" +
                "          \"key\": \""+projectKey+"\"\n" +
                "       },\n" +
                "       \"summary\": \"Add a comment\",\n" +
                "       \"description\": \"Create an issue\",\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Bug\"\n" +
                "       }\n" +
                "   }\n" +
                "}";
        return createBug;
    }

    public static String createTaskViaPOST(String projectKey,String summary){
        String createTask = "{\n" +
                "    \"fields\": {\n" +
                "       \"project\":\n" +
                "       {\n" +
                "          \"key\": \""+projectKey+"\"\n" +
                "       },\n" +
                "       \"summary\": \"Add a comment\",\n" +
                "       \"description\": \"Create an issue\",\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Task\"\n" +
                "       }\n" +
                "   }\n" +
                "}";
        return createTask;
    }

}
