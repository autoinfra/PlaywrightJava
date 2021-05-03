package listeners.ExtentBasic;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.AutoInfra.base;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporterCls  {
    static ExtentReports extent;
    public static String ReportFolderName;

    public static ExtentReports ReportGenerator(String Testname)
    {
        Date d = new Date();
        SimpleDateFormat SDF = new SimpleDateFormat("MMMM-dd-yyyy");
        ReportFolderName = SDF.format(d);
        String path = "HtmlReports/Extent/"+ReportFolderName+"/"+Testname+"Report.html";
        ExtentSparkReporter ESR = new ExtentSparkReporter(path);
        ESR.config().setReportName("Test Automation Results");
        ESR.config().setDocumentTitle(SDF+"Test Results");
        extent = new ExtentReports();
        extent.attachReporter(ESR);
        extent.setSystemInfo("Tester",System.getProperty("user.name"));
        return extent;

    }
    public void ChildPrent()
    {

    }
}
