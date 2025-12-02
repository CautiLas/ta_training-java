package com.epam.training.lautaro_castillo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);
    private final WebDriver driver;
    private final By usernameField = By.cssSelector("#user-name"); // id
    private final By passwordField = By.cssSelector("#password"); // id
    private final By loginButton = By.cssSelector(".submit-button"); // Clase
    private final By errorContainer = By.cssSelector("h3[data-test='error']"); // Atributo

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        // Espera hasta que el elemento sea visible en el DOM y en la página
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public void enterUsername(String username) {
        waitForElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        waitForElement(passwordField).sendKeys(password);
    }

    public void clearUsername() {
        waitForElement(usernameField).sendKeys(Keys.CONTROL + "a");
        waitForElement(usernameField).sendKeys(Keys.DELETE);
    }

    public void clearPassword() {
        waitForElement(passwordField).sendKeys(Keys.CONTROL + "a");
        waitForElement(passwordField).sendKeys(Keys.DELETE);
    }

    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }


    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorContainer)).getText();
    }


    public String getPageTitle() {
        return driver.getTitle();
    }
}