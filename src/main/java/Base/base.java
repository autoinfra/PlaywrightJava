package Base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static listeners.ExtentBasic.ExtentReporterCls.ReportFolderName;

public class base {
    protected Page page;
    protected Browser browser;
    private static final Logger LOGGER = LogManager.getLogger(base.class);

    @BeforeSuite(alwaysRun = true)
    public void InitializeDriver()
    {
       page = DriverFactory.getDriver("CHROME");
    }

    @AfterSuite
    public void tearDown(){page.close(); }

    public String getScreenshot(String TestCaseName,Page page)
    {
        Date d = new Date();
        SimpleDateFormat SDF = new SimpleDateFormat("HH_mm_ss");
        String random = SDF.format(d);
        String Image=System.getProperty("user.dir")+"/HtmlReports/Extent/"+ReportFolderName+"/"+TestCaseName+random+".png";
        page.screenshot(new Page.ScreenshotOptions()
                                .setPath(Paths.get(Image)));
        return Image;
    }
}
