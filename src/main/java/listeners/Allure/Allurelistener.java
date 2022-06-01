package listeners.Allure;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Base64;

public class Allurelistener implements ITestListener  {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    //Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(Page page) {
        byte[] buffer = page.screenshot();
        return  Base64.getEncoder().encode(buffer);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        Page page = null;
        Object TestObject = iTestResult.getInstance();
        Class CurrentClass = iTestResult.getTestClass().getRealClass();
        page = (Page) CurrentClass.getDeclaredField("page").get(TestObject);
        saveScreenshotPNG(page);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }
}

