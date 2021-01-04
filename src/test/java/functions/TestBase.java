package functions;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestBase {

    private static String DRIVER_PATH = "src/test/resources/drivers/";
    public static WebDriver driver;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH+"chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @After
    public void close(){
        driver.close();
    }
}