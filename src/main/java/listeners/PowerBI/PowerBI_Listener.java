package listeners.PowerBI;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;

public class PowerBI_Listener implements ITestListener {

    private  PowerBI_ResultSender PowerBI_ResultSender;
    private ITestContext ITC;

    @Override
    public void onTestStart(ITestResult result) {
        this.PowerBI_ResultSender = new PowerBI_ResultSender();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        this.sendStatus(result,"PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) { this.sendStatus(result,"FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        this.sendStatus(result,"SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //SKIP
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {this.sendStatus(result,"Time Out");}

    @Override
    public void onStart(ITestContext context) {
        //SKIP
        this.ITC=context;
    }

    @Override
    public void onFinish(ITestContext context) {
        //SKIP
    }

    public void sendStatus(ITestResult ITR, String status){

        long Duration = ITR.getEndMillis() - ITR.getStartMillis();

        PowerBI_Pojo p = PowerBI_Pojo.builder()
                .Suitename(ITC.getCurrentXmlTest().getSuite().getName())
                .Classname(ITR.getTestClass().getName())
                .Methodname(ITR.getMethod().getMethodName())
                .MethodDescription(ITR.getMethod().getDescription())
                .Host(System.getProperty("user.name"))
                .Result(status)
                .Duration(Duration)
                .build();

        List<PowerBI_Pojo> List = new ArrayList<PowerBI_Pojo>();
        List.add(p);
        PowerBI_Pojo_Base PB = new PowerBI_Pojo_Base();
        PB.setRows(List);
        PowerBI_ResultSender.PushData(PB);
    }

}
