package listeners.Elastic;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;

public class ElasticListener implements ITestListener {

    private listeners.Elastic.Elastic_Json_TestStatus Elastic_Json_TestStatus;

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        this.sendStatus(iTestResult,"PASS");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        this.sendStatus(iTestResult,"FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        this.sendStatus(iTestResult,"SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //skip
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        //skip
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        //skip
    }

    private void sendStatus(ITestResult iTestResult, String status){
        this.Elastic_Json_TestStatus= listeners.Elastic.Elastic_Json_TestStatus
                .builder()
                .status(status)
                .testClass(iTestResult.getTestClass().getName())
                .description(iTestResult.getMethod().getDescription())
                .executionTime(LocalDateTime.now().toString())
                .build();
        ElasticResultSender.send(this.Elastic_Json_TestStatus);
    }


}