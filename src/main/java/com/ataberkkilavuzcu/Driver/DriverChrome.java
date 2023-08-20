package com.ataberkkilavuzcu.Driver;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverChrome {
    private static ChromeDriver driver;

    public DriverChrome() {
        initializeDriver();
    }

    public static ChromeDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation", "disable-infobars"));
        options.addArguments("disable-extensions");
        options.addArguments("disable-popup-blocking");
        driver = new ChromeDriver(options);
    }
}

