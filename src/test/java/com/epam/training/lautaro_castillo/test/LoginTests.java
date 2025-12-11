package com.epam.training.lautaro_castillo.test;

import com.epam.training.lautaro_castillo.utils.User;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests extends BaseTest {


    @Test(dataProvider = "emptyCredentialsTest", dataProviderClass = User.class, description = "UC-1: Test de login con credenciales vacias")
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

    @Test(dataProvider = "missingPasswordTest", dataProviderClass = User.class, description = "UC-2: Test de login con Username lleno y Password vacío")
    public void testUC2_MissingPassword(String username, String emptyPassword, String expectedError) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(emptyPassword);
        loginPage.clickLoginButton();
        assertThat(loginPage.getErrorMessage())
                .as("El mensaje de error de UC-2 debe ser para el Password requerido.")
                .contains(expectedError);
    }

    @Test(dataProvider = "successfulLoginTest", dataProviderClass = User.class, description = "UC-3: Login Exitoso con Credenciales Aceptadas")
    public void testUC3_ParameterizedLogin(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        assertThat(loginPage.getPageTitle())
                .as("El login debe ser exitoso y el título debe ser 'Swag Labs' para el usuario: " + username)
                .contains("Swag Labs");
    }
}