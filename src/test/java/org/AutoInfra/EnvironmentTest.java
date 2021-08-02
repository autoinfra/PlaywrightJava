package org.AutoInfra;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.customAnnotations.notOnProd.NotOnProd;
import utilities.envPicker.Environment;

public class EnvironmentTest {

    Environment testEnvironment;

    //@NotOnProd
    @Test
    public void functinalTest() {
        System.out.println(testEnvironment.url());
        System.out.println(testEnvironment.password());
        System.out.println(testEnvironment.getDBHostname());
        System.out.println(testEnvironment.getDBPassword());
    }

    @BeforeTest
    @Parameters({"environment"})
    public void beforeTest(String environemnt) {
        //Envrn=environemnt;
        ConfigFactory.setProperty("env", environemnt);
        testEnvironment = ConfigFactory.create(Environment.class);
    }

}