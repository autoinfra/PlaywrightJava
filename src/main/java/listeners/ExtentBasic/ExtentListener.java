package listeners.ExtentBasic;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.microsoft.playwright.Page;
import org.AutoInfra.base;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListener extends base implements ITestListener, ISuiteListener {

    //base baseClass = new base();
    ITestContext ITC;
    ExtentReports extent = ExtentReporterCls.ReportGenerator("AutoInfraHTMLReport");
    ExtentTest test;
    //ExtentTest test= extent.createTest("SingleRater");
    ExtentTest node;
    ITestResult result;

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
        //getScreenshot(page);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
       // baseClass.getScreenshot();
        //Page page =null;

        LocalThread.get().log(Status.PASS,"------------TEST CASE PASSED------------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //baseClass.getScreenshot();

        LocalThread.get().fail(result.getThrowable());

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
    }

    @Override
    public void onFinish(ITestContext context) {
extent.flush();
    }
}
