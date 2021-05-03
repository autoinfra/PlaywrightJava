package listeners.Grafana;

import org.influxdb.dto.Point;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

public class grafanalistener implements ITestListener {

    //public void onTestStart(ITestResult iTestResult) { this.sendTestMethodStatus(iTestResult, "INITIATED"); }

    public void onTestSuccess(ITestResult iTestResult) {
        this.sendTestMethodStatus(iTestResult, "PASS");
    }

    public void onTestFailure(ITestResult iTestResult) {
        this.sendTestMethodStatus(iTestResult, "FAIL");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        this.sendTestMethodStatus(iTestResult, "SKIPPED");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {
        this.sendTestClassStatus(iTestContext);
    }

    private void sendTestMethodStatus(ITestResult iTestResult, String status) {
        Point point = Point.measurement("testmethod")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("testclass", iTestResult.getTestClass().getName())
                .tag("name", iTestResult.getName())
                .tag("description", iTestResult.getMethod().getDescription())
                .tag("result", status)
                .addField("duration", (iTestResult.getEndMillis() - iTestResult.getStartMillis()))
                .build();
                GrafanaResultSender.send(point);
    }

    private void sendTestClassStatus(ITestContext iTestContext) {
        Point point = Point.measurement("testclass")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("name", iTestContext.getAllTestMethods()[0].getTestClass().getName())
                .addField("duration", (iTestContext.getEndDate().getTime() - iTestContext.getStartDate().getTime()))
                .build();
        GrafanaResultSender.send(point);
    }

}