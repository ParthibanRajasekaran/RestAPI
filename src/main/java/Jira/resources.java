package Jira;

public class resources {

    public static String authenticationAccess = "/rest/auth/1/session";

    public static String addNewIssue = "/rest/api/2/issue/";
    public static String deleteIssue = "/rest/api/2/issue/{key}";

    public static String addComment = "/rest/api/2/issue/{id}/comment";
    public static String updateComment = "/rest/api/2/issue/{id}/comment/{commentId}";

}
