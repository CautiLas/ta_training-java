package com.epam.training.lautaro_castillo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

    protected static WebDriver create(String browserName) {
        BrowserType type = BrowserType.CHROME; // default

        if (browserName != null && !browserName.trim().isEmpty()) {
            try {
                type = BrowserType.valueOf(browserName.trim().toUpperCase());
            } catch (IllegalArgumentException ignored) {
            } //si no se reconoce el browserName, se usa el default
        }

        switch (type) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case EDGE:
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        } //podria ser como case un chrome en vez de default y que el default lance una exception
    }

    public static void quit(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}