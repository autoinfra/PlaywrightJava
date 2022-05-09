package listeners.MicrosoftTeams;

import io.restassured.RestAssured;
import org.testng.*;
import utilities.customAnnotations.AzureDevOps.AzureDevOps;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static listeners.MicrosoftTeams.AdaptiveCardReqBody.FactsItem;
import static listeners.MicrosoftTeams.AdaptiveCardReqBody.ItemsItem;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    List<ItemsItem> TestCaseID = new ArrayList<>();
    List<ItemsItem> TestCaseName = new ArrayList<>();
    List<ItemsItem> TestResult = new ArrayList<>();
    AzureDevOps azureDevOps;
IInvokedMethod invokedMethod;
    long endTime;
    long startTime;
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        invokedMethod=method;
        azureDevOps = method.getTestMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(AzureDevOps.class);

    }


    @Override
    public void onTestStart(ITestResult result) {
        TestCaseName.add(ItemsItem.builder()
                .type("TextBlock")
                .text(result.getMethod().getMethodName())
                .weight("Bolder")
                .wrap(true)
                .build());

        try {
            TestCaseID.add(ItemsItem.builder()
                    .type("TextBlock")
                    .text("[" + azureDevOps.WorkItemID() + "](https://adaptivecards.io/" + azureDevOps.WorkItemID() + ")")
                    .weight("Bolder")
                    .wrap(true)
                    .build());
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        TestResult.add(ItemsItem.builder()
                .type("TextBlock")
                .text("PASS")
                .weight("Bolder")
                .color("good")
                .wrap(true)
                .build());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        TestResult.add(ItemsItem.builder()
                .type("TextBlock")
                .text("FAIL")
                .weight("Bolder")
                .color("attention")
                .wrap(true)
                .build());
    }

    @Override
    public void onStart(ISuite suite) {
startTime=System.currentTimeMillis();
    }

    @Override
    public void onFinish(ISuite suite) {

endTime= System.currentTimeMillis();
        Date d = new Date();
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MMM-dd");
        String todayDate = SDF.format(d);

        List<FactsItem> factsItems = new ArrayList<>();

        factsItems.add(FactsItem
                .builder()
                .title("Submitted By")
                .value( "**"+System.getProperty("user.name")+"**").build());

        factsItems.add(FactsItem
                .builder()
                .title("OS")
                .value("**"+System.getProperty("os.name")+"**").build());

        factsItems.add(FactsItem
                .builder()
                .title("Run Date")
                .value("**"+todayDate+"**").build());

        factsItems.add( FactsItem
                .builder()
                .title("Total Run Time")
                .value("**"+TimeUnit.MILLISECONDS.toMinutes(startTime-endTime)+"**").build());

        Builder builder = new Builder(TestCaseName,TestCaseID,TestResult,factsItems);

        RestAssured.baseURI="https://bhargavkumar65gmail.webhook.office.com/webhookb2/df93b3ce-071f-4e0c-932c-8f148084a9b0@0486e5b4-f3e8-4386-9ab4-cf2657814955/IncomingWebhook/f4f0699bf68449248abede270bfe8e44/51337ede-bd85-472e-8bf2-4d8ae127399c";
        RestAssured.given().log().body()
                .body(builder.reqBody())
                .when()
                .post()
                .then().log().body()
                .statusCode(200);
    }

}
