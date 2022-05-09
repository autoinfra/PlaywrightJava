package Base;

import com.microsoft.playwright.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {
    private static final Map<String, Supplier<Page>> driverMap = new HashMap<>();

    private static final Supplier<Page> chromium = () -> {
        Browser browser;

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        return page;
    };

    private static final Supplier<Page> chromiumHeadless = () -> {

        Browser browser;

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(true));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        return page;
    };

    private static final Supplier<Page> firefoxDriver = () -> {
        Browser browser;

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        browser = Playwright
                .create()
                .firefox()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        return page;
    };

    static{
        driverMap.put("CHROME", chromium);
        driverMap.put("FIREFOX", firefoxDriver);
        driverMap.put("CHROME_HEADLESS",chromiumHeadless);
    }

    public static final Page getDriver(String type){
        return driverMap.get(type).get();
    }



}
