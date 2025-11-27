package com.epam.training.lautaro_castillo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By usernameField = By.cssSelector("#user-name"); // ID
    private final By passwordField = By.cssSelector("#password"); // ID
    private final By loginButton = By.cssSelector(".submit-button"); // Clase
    private final By errorContainer = By.cssSelector("h3[data-test='error']"); // Atributo

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }


    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }


    public void clearInputs() {
        driver.findElement(usernameField).clear();
    }

    public void clearPassword() {
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