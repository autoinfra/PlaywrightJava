package org.AutoInfra;

import com.epam.reportportal.message.ReportPortalMessage;
import com.microsoft.playwright.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.testng.annotations.Test;
import rp.com.google.common.io.BaseEncoding;
import rp.com.google.common.io.Files;
import rp.com.google.common.io.Resources;

import java.io.File;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ReportPortal_Logging {

    private static final Logger LOGGER = LogManager.getLogger(ReportPortal_Logging.class);
    public static final String JSON_FILE_PATH = "files/file.json";
    public static final String XML_FILE_PATH = "Suites/DuckDuckGo.xml";

/*    @Test
    public void getStatus() {
        String githubIssueStatus = "READY_TO_TEST";
        Response response = given()
                .header("Accept","application/json")
                .auth().preemptive().basic("bhargav.udemy@gmail.com","eMTQ5AYdDUTJAOlYW5lG8158")
                .baseUri("https://bhargavm65.atlassian.net/rest/api/3/issue/")
                .when()
                .get("/AUTOINFRA-6")
                .then()
                .statusCode(200).extract().response();

System.out.println(response.jsonPath().getString("fields.status.name"));

                given().log().all()
                .header("X-Atlassian-Token","no-check")
                .auth().preemptive().basic("bhargav.udemy@gmail.com","eMTQ5AYdDUTJAOlYW5lG8158")
                .queryParam("fields","status")
                .baseUri("https://bhargavm65.atlassian.net/rest/api/3/issue/")
                .multiPart(new File("example2.png"))
                .when()
                .post("/AUTOINFRA-6/attachments")
                .then()
                        .log().all()
                .statusCode(200).extract().response();

    }*/

    @Test
    public  void testMethod(){

        Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
        LOGGER.info(page.title());
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", "example.png", "Example Screenshot");

        // Create a new incognito browser context
        BrowserContext context = browser.newContext();
// Create a new page inside context.
        Page contextpage = context.newPage();
        contextpage.navigate("https://playwright.dev/java/docs/core-concepts");
        contextpage.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example2.png")));
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", "example2.png", "Example2 Screenshot");
// Dispose context once it"s no longer needed.
        context.close();
        browser.close();
    }

    @SneakyThrows
    @Test
    public void logXmlFile() {
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", XML_FILE_PATH, "test Message");
    }

    @SneakyThrows
    @Test
    public void logJsonBase64() {
        /* here we are logging some binary data as BASE64 string */
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}",
                BaseEncoding.base64().encode(Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).read()),
                "I'm logging content via BASE64");
    }

    @SneakyThrows
    @Test
    public void logJsonFile()  {
        /* here we are logging some binary data as file (useful for selenium) */
        File file = File.createTempFile("rp-test", ".json");
        Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).copyTo(Files.asByteSink(file));

        for (int i = 0; i < 1; i++) {
            LOGGER.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), "I'm logging content via temp file");
        }
        Thread.sleep(5000L);

    }

    @SneakyThrows
    @Test
    public void logRepMessage() {
        /* here we are logging some binary data as file (useful for selenium) */
        File file = File.createTempFile("rp-test", ".json");
        LOGGER.info(new ObjectMessage(new ReportPortalMessage(file, "File message")));
    }


}