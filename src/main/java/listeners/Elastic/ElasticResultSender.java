package listeners.Elastic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ElasticResultSender {

    public static void send(final Elastic_Json_TestStatus testStatus){
        try {
            RestAssured.baseURI="http://localhost:9200";
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(testStatus)
                    .when()
                    .post("/app/suite");

        } catch (Exception e) {
            //System.out.println("Unable to connect to Elastic Search Node");
          //  e.printStackTrace();
        }
    }


}