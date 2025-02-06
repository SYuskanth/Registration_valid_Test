package enhanzer.test.assignment;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTestSetup {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
//<--------------Setting web driver for the testing ----------->
    @BeforeAll
    public static void driversetup() {
        System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoqa.com/automation-practice-form");
        assertEquals("https://demoqa.com/automation-practice-form", driver.getCurrentUrl(), "URL is incorrect");
    }
    
    //<to driver quite>

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
