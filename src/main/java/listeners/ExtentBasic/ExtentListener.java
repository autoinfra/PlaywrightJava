package listeners.ExtentBasic;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.microsoft.playwright.Page;
import lombok.SneakyThrows;
import Base.base;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListener extends base implements ITestListener, ISuiteListener {

    ExtentReports extent = ExtentReporterCls.ReportGenerator("AutoInfraHTMLReport");
    ExtentTest test;
    ExtentTest node;
    ITestContext ITC;

    //creating thread for 'test' object for parallel execution
   private static final ThreadLocal<ExtentTest> LocalThread = new ThreadLocal<ExtentTest>();

   public void logInfo(String message)
   {
        LocalThread.get().info(message);
   }

    public void logJsoninfo(String json)
    {
        LocalThread.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }


    @Override
    public void onTestStart(ITestResult result) {
        node = test.createNode(result.getMethod().getMethodName());
        LocalThread.set(node);
    }

    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult result) {

        LocalThread.get().log(Status.PASS,"------------TEST CASE PASSED------------");

        Page page = null;
        Object TestObject = result.getInstance();
        Class CurrentClass = result.getTestClass().getRealClass();
        page = (Page) CurrentClass.getDeclaredField("page").get(TestObject);
        LocalThread.get().addScreenCaptureFromPath(getScreenshot(ITC.getName(),page));

    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {

        LocalThread.get().fail(result.getThrowable());

        Page page = null;
        Object TestObject = result.getInstance();
        Class CurrentClass = result.getTestClass().getRealClass();
        page = (Page) CurrentClass.getDeclaredField("page").get(TestObject);
        LocalThread.get().addScreenCaptureFromPath(getScreenshot(ITC.getName(),page));

    }

    @Override
    public void onTestSkipped(ITestResult result) {
       LocalThread.get().skip(result.getThrowable());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        test= extent.createTest(context.getName());
        this.ITC = context;
    }

    @Override
    public void onFinish(ITestContext context) {
extent.flush();
    }
}
