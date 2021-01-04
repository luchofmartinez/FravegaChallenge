package steps;

import functions.TestBase;
import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;
import pages.SearchPage;

public class FE_Search_Test extends TestBase {

    private static String WORD = "Heladeras";

    @Test
    public void search_test() throws InterruptedException {
        driver.get("https://www.fravega.com");
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        homePage.writeSearch("Heladera");
        homePage.search();
        searchPage.selectFilterCategory();
        searchPage.selectFilter_brand();
        searchPage.getAllProducts();
        Assert.assertEquals(searchPage.getTotalProducts(), searchPage.getQuantityProducts());
        Assert.assertTrue("Error: Uno de los titulos de los productos no contiene la marca seleccionada", searchPage.checkTitles());
        Assert.assertTrue("Error: El elemento breadcrumb no contiene la palabra '" + WORD + "'",searchPage.checkbreadcrum(WORD));
    }
}
