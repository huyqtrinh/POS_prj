package manager;

import enums.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import utils.Constants;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class DriverManager {
    private WebDriver driver;
    private Properties properties;
    private final String propertyFilePath = String.format("%s%s%s",System.getProperty(Constants.USER_DIR), Constants.CONFIGURATION_PROPERTIES_PATH, Constants.CONFIGURATION_PROPERTIES_FILE);

    public void ConfigFileReader(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot find config");
        }
    }
    private String getBrowserName(){
        ConfigFileReader();
        String browserName = properties.getProperty(Constants.BROWSER_NAME).toLowerCase();
        if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME.toString();
        else if (browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX.toString();
        else if (browserName.equals("edge")) return DriverType.EDGE.toString();
        else throw new RuntimeException("Invalid browser name");
    }
    private String getBrowserVer(){
        ConfigFileReader();
        return properties.getProperty(Constants.BROWSER_VER).toLowerCase();
    }
    public WebDriver createWebDriver() {
        String driverType = getBrowserName();
        String browserVer = getBrowserVer();
        switch (driverType) {
            case "CHROME":
                WebDriverManager.chromedriver().browserVersion(browserVer).setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("disable-infobars");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "EDGE":
                WebDriverManager.iedriver().browserVersion(browserVer).setup();
                driver = new EdgeDriver();
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().browserVersion(browserVer).setup();
                FirefoxProfile profile = new FirefoxProfile();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(profile);
                driver = new FirefoxDriver(firefoxOptions);
                break;
        }
        driver.manage().window().maximize();
        return driver;
    }

}
