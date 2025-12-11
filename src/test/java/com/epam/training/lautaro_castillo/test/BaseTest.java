package com.epam.training.lautaro_castillo.test;

import com.epam.training.lautaro_castillo.driver.Driver;
import com.epam.training.lautaro_castillo.models.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    static final String BASE_URL = "https://www.saucedemo.com/";

    public LoginPage loginPage;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        Driver.createDriver(browser);
        WebDriver currentDriver = Driver.getDriver();
        currentDriver.get(BASE_URL);
        loginPage = new LoginPage(currentDriver);
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
    }
}
