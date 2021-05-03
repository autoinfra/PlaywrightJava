package listeners.PowerBI;

import com.google.gson.Gson;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class PowerBI_ResultSender {

    private static final String APIKEY = "APIKEY";
    private static final String PowerBiServer = "https://api.powerbi.com";
    private static final String UPN_VALUE = "UPN_VALUE";
    private static final String Tenant_Value ="TENENT_VALUE";
    private static final String DataSet ="PUSH_DATA_SET";

    public void PushData(PowerBI_Pojo_Base PowerBI_Json_Result)  {

        RestAssured.baseURI = PowerBiServer;
        RestAssured.useRelaxedHTTPSValidation();
        //RestAssured.proxy("YOUR_PROXY", 8080);

        String Json_Payload = new Gson().toJson(PowerBI_Json_Result);
        try {
            given()
                    .header("Content-type", "application/json")
                    .queryParam("tenant", Tenant_Value)
                    .queryParam("UPN", UPN_VALUE)
                    .queryParam("Key", APIKEY)
                    .urlEncodingEnabled(false)
                    .body(Json_Payload)
                    .when().log().all().post(DataSet)
                    .then().log().all().assertThat().statusCode(200);
        }
        catch (Exception e) {
            //e.printStackTrace();
        }

    }
}
