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

    private WebDriver driver;
    private LoginPage loginPage;

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
        };
    }

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        driver = Driver.create(browser);
        driver.get(BASE_URL);
        loginPage = new LoginPage(driver);
    }

    @Test(description = "UC-1: Test de login con credenciales vacías")
    public void testUC1_EmptyCredentials() {
        loginPage.clearInputs();
        loginPage.clickLoginButton();

        assertThat(loginPage.getErrorMessage())
                .as("El mensaje de error de UC-1 debe ser para el Username requerido.")
                .contains("Username is required");
    }

    @Test(description = "UC-2: Test de login con Username lleno y Password vacío")
    public void testUC2_MissingPassword() {

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("");

        loginPage.clickLoginButton();

        assertThat(loginPage.getErrorMessage())
                .as("El mensaje de error de UC-2 debe ser para el Password requerido.")
                .contains("Password is required");
    }

    @Test(dataProvider = "loginCredentials", description = "UC-3: Login Exitoso con Credenciales Aceptadas")
    public void testUC3_ParameterizedLogin(String username, String password) {

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        assertThat(loginPage.getPageTitle())
                .as("El login debe ser exitoso y el título debe ser 'Swag Labs' para el usuario: " + username)
                .contains("Swag Labs");
    }

    @AfterMethod
    public void tearDown() {
        Driver.quit(driver);
    }
}