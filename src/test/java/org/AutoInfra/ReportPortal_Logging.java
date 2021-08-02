package org.AutoInfra;

import com.epam.reportportal.message.ReportPortalMessage;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ObjectMessage;
import org.testng.annotations.Test;
import rp.com.google.common.io.BaseEncoding;
import rp.com.google.common.io.Files;
import rp.com.google.common.io.Resources;
import utilities.LoggingUtils;

import java.io.File;

public class ReportPortal_Logging {

    private static final Logger LOGGER = LogManager.getLogger(ReportPortal_Logging.class);

    public static final String JSON_FILE_PATH = "files/file.json";
    public static final String XML_FILE_PATH = "Suites/DuckDuckGo.xml";

    @SneakyThrows
    //@Test
    public void logXmlFile() {
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", XML_FILE_PATH, "test Message");
    }

    @SneakyThrows
    //@Test
    public void logJsonBase64() {
        /* here we are logging some binary data as BASE64 string */
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}",
                BaseEncoding.base64().encode(Resources.asByteSource(Resources.getResource(JSON_FILE_PATH)).read()),
                "I'm logging content via BASE64");
    }

    @SneakyThrows
    //@Test
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
    //@Test
    public void logRepMessage() {
        /* here we are logging some binary data as file (useful for selenium) */
        File file = File.createTempFile("rp-test", ".json");
        LOGGER.info(new ObjectMessage(new ReportPortalMessage(file, "File message")));
    }


}