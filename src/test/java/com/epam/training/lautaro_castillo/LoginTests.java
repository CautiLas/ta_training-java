package com.epam.training.lautaro_castillo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class LoginTests {

    private WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        driver = Driver.create(browser);
    }

    @Test
    public void simpleLoginTest() {
        driver.get("https://example.com");
        System.out.println("Page title: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        Driver.quit(driver);}
}
