package utilities.customAnnotations.AzureDevOps;

import io.restassured.response.Response;
import lombok.SneakyThrows;

import static io.restassured.RestAssured.given;

public class AzDevOpsApiCall {

    private static final String ISSUE_TRACKER_API_BASE_URL = "URL";
    private static final String Username = "USERNAME";
    public static final String Token = "PWD";

    @SneakyThrows
    public static String getWorkItemStatus(int IssueID) {

            Response response = given()
                    .header("Accept", "application/json")
                    .queryParam("api-version","6.1-preview.3")
                    .auth().preemptive().basic(Username, Token)
                    .baseUri(ISSUE_TRACKER_API_BASE_URL)
                    .when()
                    .get(IssueID+"?api-version=6.1-preview.3")
                    .then()
                    .statusCode(200).extract().response();
        return response.jsonPath().getString("fields.'System.State'");
    }

}
