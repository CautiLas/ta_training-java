package com.epam.training.lautaro_castillo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private static final String PAGE_URL = "https://www.saucedemo.com/";

    private final By usernameField = By.cssSelector("#user-name"); // ID
    private final By passwordField = By.cssSelector("#password"); // ID
    private final By loginButton = By.cssSelector(".submit-button"); // Clase
    private final By errorContainer = By.cssSelector("h3[data-test='error']"); // Atributo

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(PAGE_URL);
    }


    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }


    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }


    public void clearInputs() {
        driver.findElement(usernameField).clear();
        driver.findElement(passwordField).clear();
    }


    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorContainer)).getText();
    }


    public String getPageTitle() {
        return driver.getTitle();
    }

}