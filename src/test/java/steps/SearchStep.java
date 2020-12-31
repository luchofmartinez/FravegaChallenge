package steps;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.SearchPage;

import java.util.concurrent.TimeUnit;

public class SearchStep {

    private static String DRIVER_PATH = "src/test/resources/drivers/";
    private WebDriver driver;

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

    @Test
    public void search_test() throws InterruptedException {
        driver.get("https://www.fravega.com");
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        homePage.writeSearch("Heladera");
        homePage.search();
        searchPage.selectFilterCategory();
        Thread.sleep(2000);
        searchPage.selectFilter_brand();
        Assert.assertEquals(searchPage.getTotalProducts(), searchPage.getQuantityProducts());
        Thread.sleep(3000);
        Assert.assertTrue("Error", searchPage.checkTitles());
    }

    @After
    public void close(){
        driver.close();
    }
}
