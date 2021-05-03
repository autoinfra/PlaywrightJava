package org.AutoInfra;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.retry;

public class SearchTest extends base {

    private static final Logger LOGGER = LogManager.getLogger(SearchTest.class);

    @Test //(description = "DuckDuckSearch" , retryAnalyzer= retry.class)
    @Parameters({"keywordToSearch"})
    public void search(String keywordfromTestNG) {
        SearchPage SP = new SearchPage(page);
        SP.navigate();
        SP.doSearch(keywordfromTestNG);
        SP.goToVideos();
        int size = SP.printResult();
        Assert.assertTrue(size < 100);
       getScreenshot();

    }

}
