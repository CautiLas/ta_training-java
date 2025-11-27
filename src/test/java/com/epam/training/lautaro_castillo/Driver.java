    package com.epam.training.lautaro_castillo;

    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.firefox.FirefoxDriver;
    import org.openqa.selenium.edge.EdgeDriver;
    import io.github.bonigarcia.wdm.WebDriverManager;

    public class Driver {

        public static WebDriver create(String browserName) {
            BrowserType type;
            try {
                type = BrowserType.valueOf(browserName.toUpperCase());
            } catch (Exception e) {
                type = BrowserType.CHROME;
            }

            switch (type) {
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    return new EdgeDriver();
                default:
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();
            }
        }

        public static void quit(WebDriver driver) {
            if (driver != null) {
                driver.quit();
            }
        }
    }