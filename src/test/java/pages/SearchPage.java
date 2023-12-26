package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//li[@data-test-id='sub-category-aggregation']/h4/a[contains(text(),'Heladeras')]")
    private WebElement heladeras_filter;

    @FindBy(how = How.XPATH, using = "//li[@name='brandAggregation']")
    private List<WebElement> brands_filter;

    @FindBy(how = How.XPATH, using = "//ul/li[@data-type= 'page']")
    private List<WebElement> pagination;

    //h4[contains(@class,'PieceTitle')]
    @FindBy(how = How.XPATH, using = "//ul[@data-test-id='results-list']/li/article/a/a/div/div/span")
    private List<WebElement> products;
    private List<String> listNameProducts = new ArrayList<String>();

    @FindBy(how = How.XPATH, using = "//a[@data-type='next']")
    private WebElement nextPageButton;

    @FindBy(how = How.XPATH, using = "//span[@data-test-id='resultsNumber']")
    private WebElement totalProducts;

    @FindBy(how = How.XPATH, using = "//ol/li[@itemprop][3]/a/span")
    private WebElement breadcrumb;

    private int currentPage = 0;
    private int totalPages = 0;
    private String brandSelected;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.findElement(By.xpath("//button[@data-test-id='close-modal-button']")).click();
    }

    public void selectFilterCategory(){
        Actions action = new Actions(driver);
        action.moveToElement(heladeras_filter).perform();
        heladeras_filter.click();
    }

    public void selectFilter_brand(){
        Actions action = new Actions(driver);
        action.moveToElement(brands_filter.get(0)).perform();
        brandSelected = brands_filter.get(0).getText();
        brandSelected = brandSelected.substring(0, brandSelected.indexOf("(")).trim();
        brands_filter.get(0).click();
    }

    public void getAllProducts(){
        if(pagination.size() == 0){
            if(products.size() > 0){
                for(int p = 0; p < products.size(); p++){
                    listNameProducts.add(products.get(p).getText());
                }
            }// Controlar que si no devuelve resultados blabla...
        }else{
            if(pagination.size() > 0){
                totalPages = pagination.size();
                for (int i = 0; i < totalPages; i++){
                    for(int j = 0; j < products.size(); j++){
                        listNameProducts.add(products.get(j).getText());
                        if(j == (products.size() - 1) && i < (totalPages - 1)){
                            nextPageButton.click();
                        }
                    }
                }
            }
        }
    }

    public int getQuantityProducts(){
        return listNameProducts.size();
    }

    public int getTotalProducts(){
        return Integer.parseInt(totalProducts.getText());
    }

    public boolean checkTitles(){
        for (String productName : listNameProducts) {
            if (!productName.contains(brandSelected)) {
                System.out.println(productName);
                return false;
            }
        }
        return true;
    }

    public boolean checkbreadcrumb(String word){
        System.out.println(breadcrumb.getText());
        if(breadcrumb.getText().contains(word)) {
            return true;
        }
        return false;
    }
}