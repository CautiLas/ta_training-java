package com.epam.training.lautaro_castillo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests {

    private static final String BASE_URL = "https://www.saucedemo.com/";

    private LoginPage loginPage;

    @DataProvider(name = "emptyCredentialsTest")
    public Object[][] getEmptyData() {
        return new Object[][]{
                {"temp_user", "temp_password", "Epic sadface: Username is required"},
        };
    }

    @DataProvider(name = "missingPasswordTest")
    public Object[][] getMissingPasswordData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
        };
    }

    @DataProvider(name = "successfulLoginTest")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
        };
    }

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

    @Test(dataProvider = "emptyCredentialsTest", description = "UC-1: Test de login con credenciales vacias")
    public void testUC1_EmptyCredentials(String tempUsername, String tempPassword, String expectedError) {
        loginPage.enterUsername(tempUsername);
        loginPage.enterPassword(tempPassword);
        loginPage.clearUsername();
        loginPage.clearPassword();
        loginPage.clickLoginButton();
        assertThat(loginPage.getErrorMessage())
                .as("El mensaje de error de UC-1 debe ser para el Username requerido.")
                .contains(expectedError);
    }

    @Test(dataProvider = "missingPasswordTest", description = "UC-2: Test de login con Username lleno y Password vacío")
    public void testUC2_MissingPassword(String username, String emptyPassword, String expectedError) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(emptyPassword);
        loginPage.clickLoginButton();
        assertThat(loginPage.getErrorMessage())
                .as("El mensaje de error de UC-2 debe ser para el Password requerido.")
                .contains(expectedError);
    }

    @Test(dataProvider = "successfulLoginTest", description = "UC-3: Login Exitoso con Credenciales Aceptadas")
    public void testUC3_ParameterizedLogin(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        assertThat(loginPage.getPageTitle())
                .as("El login debe ser exitoso y el título debe ser 'Swag Labs' para el usuario: " + username)
                .contains("Swag Labs");
    }
}