package org.AutoInfra;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTest extends base {

    public Page page;

    private static final Logger LOGGER = LogManager.getLogger(SearchTest.class);

    @BeforeTest
    public void initializeDriver()
    {
        page = SetupDriver();
    }

    @Test //(description = "DuckDuckSearch" , retryAnalyzer= retry.class)
    @Parameters({"keywordToSearch"})
    public void search(String keywordfromTestNG) {
        SearchPage SP = new SearchPage(page);
        SP.navigate();
        SP.doSearch(keywordfromTestNG);
        SP.goToVideos();
        int size = SP.printResult();
        Assert.assertTrue(size < 100);
/*        LOGGER.info(page.title());
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", "example.png", "Example Screenshot");*/
    }

    public void Playwrightexercise()
    {
/*// Create a new incognito browser context
        BrowserContext context = browser.newContext();
// Create a new page inside context.
        Page contextpage = context.newPage();
        contextpage.navigate("https://playwright.dev/java/docs/core-concepts");
        contextpage.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example2.png")));
              LOGGER.info("RP_MESSAGE#FILE#{}#{}", "example2.png", "Example2 Screenshot");
// Dispose context once it"s no longer needed.
        context.close();*/
    }

}
