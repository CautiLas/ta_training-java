package com.epam.training.lautaro_castillo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

    private static ThreadLocal<WebDriver> driverInstance = new ThreadLocal<>();

    // 2. Constructor privado para evitar la instanciación externa (Singleton)
    private Driver() {}


    public static WebDriver getDriver() {
        return driverInstance.get();
    }


    public static void createDriver(String browserName) {
        // Solo inicializa si no hay un driver ya asignado al hilo actual
        if (driverInstance.get() == null) {
            BrowserType type = BrowserType.CHROME; // default

            if (browserName != null && !browserName.trim().isEmpty()) {
                try {
                    type = BrowserType.valueOf(browserName.trim().toUpperCase());
                } catch (IllegalArgumentException ignored) {
                }
            }

            WebDriver driver;
            switch (type) {
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
            // Asigna la instancia del driver al ThreadLocal para el hilo actual
            driverInstance.set(driver);
        }
    }


    public static void quitDriver() {
        WebDriver driver = driverInstance.get();
        if (driver != null) {
            driver.quit();
            driverInstance.remove(); // Limpia el ThreadLocal
        }
    }
}