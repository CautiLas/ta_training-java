package com.epam.training.lautaro_castillo.utils;

import org.testng.annotations.DataProvider;

public class User {
    public String username;
    public String password;
    public boolean isValid;

    public User(String username, String password, boolean isValid) {
        this.username = username;
        this.password = password;
        this.isValid = isValid;
    }

    @DataProvider(name = "emptyCredentialsTest")
    public static Object[][] getEmptyData() {
        return new Object[][]{
                {"temp_user", "temp_password", "Epic sadface: Username is required"},
        };
    }

    @DataProvider(name = "missingPasswordTest")
    public static Object[][] getMissingPasswordData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
        };
    }

    @DataProvider(name = "successfulLoginTest")
    public static Object[][] getLoginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
        };
    }
}