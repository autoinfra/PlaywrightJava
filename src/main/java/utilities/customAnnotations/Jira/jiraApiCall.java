package utilities.customAnnotations.Jira;

import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.io.File;

import static io.restassured.RestAssured.given;

public class jiraApiCall {
    private static final String ISSUE_TRACKER_API_BASE_URL = "URL";
    private static final String Username = "USERNAME";
    public static final String Token = "PWD";

    @SneakyThrows
    public static String getWorkItemStatus(String IssueID) {
        String JiraIssueStatus = "READY_TO_TEST";

        try {
            Response response = given()
                    .header("Accept", "application/json")
                    .auth().preemptive().basic(Username, Token)
                    .baseUri(ISSUE_TRACKER_API_BASE_URL)
                    .when()
                    .get(IssueID)
                    .then()
                    .statusCode(200).extract().response();
            JiraIssueStatus = response.jsonPath().getString("fields.status.name");
                System.out.println("jira issue stats: " + JiraIssueStatus);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      return  JiraIssueStatus;

        //return IssueStatus.valueOf(githubIssueStatus);
    }

    public static void AddScreenshot(String IssueID,String filename)
    {
       given().log().all()
                .header("X-Atlassian-Token","no-check")
                .auth().preemptive().basic(Username,Token)
                .queryParam("fields","status")
                .baseUri(ISSUE_TRACKER_API_BASE_URL)
                .multiPart(new File(filename))
                .when()
                .post(IssueID+"/attachments")
                .then()
                .log().all()
                .statusCode(200).extract().response();


    }

}
