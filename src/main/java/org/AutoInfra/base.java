package org.AutoInfra;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.nio.file.Paths;

public class base {
    protected Page page;
    protected Browser browser;
    private static final Logger LOGGER = LogManager.getLogger(base.class);

    @BeforeClass
    public void SetupDriver()
    {
        browser = Playwright
                            .create()
                            .chromium()
                            .launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();
        this.page=page;
    }

    @AfterClass
    public void tearDown(){
        browser.close();
    }

    public void getScreenshot()
    {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
        LOGGER.info(page.title());
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", "example.png", "Example Screenshot");
    }
}
