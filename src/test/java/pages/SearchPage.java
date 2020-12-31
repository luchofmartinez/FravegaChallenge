package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//ul[@name='categoriesAggregation']//li//h4[contains(text(),'Heladeras')]")
    private WebElement heladeras_filter;

    @FindBy(how = How.XPATH, using = "//ul[@name='categoriesAggregation']//li[@name='brandsFilter']//li[@name='brandAggregation']")
    private List<WebElement> brands_filter;

    @FindBy(how = How.XPATH, using = "//ul[contains(@class, 'PaginationWrapper')]//li[@title != 'Previous Page' and @title != 'Next Page']")
    private List<WebElement> pagination;

    @FindBy(how = How.XPATH, using = "//ul[@name='itemsGrid']//li")
    private List<WebElement> products;

    @FindBy(how = How.XPATH, using = "//li[@title='Next Page']")
    private WebElement nextPageButton;

    @FindBy(how = How.XPATH, using = "//li[@name='totalResult']//span")
    private WebElement totalProducts;

    @FindBy(how = How.XPATH, using = "//ul[@name='itemsGrid']//li//h4[contains(@class, 'PieceTitle')]")
    private List<WebElement> productNames;

    private int currentPage = 0;
    private int totalPages = 0;
    private String brandSelected;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
        brands_filter.get(0).click();
    }

    public int getQuantityProducts(){
        int totalProducts = 0;

        if(pagination.size() == 0){
            totalProducts = products.size();
        }else{
            if(pagination.size() > 0){
                currentPage = 1;
                totalPages = pagination.size();
                for (int i = 0; i < totalPages; i++){

                    totalProducts += products.size();
                    currentPage++;
                    if(currentPage <= totalPages) {
                        nextPageButton.click();
                    }

                }
            }
        }
        return totalProducts;
    }

    public int getTotalProducts(){
        return Integer.parseInt(totalProducts.getText());
    }

    public boolean checkTitles(){
        if(pagination.size() == 0){
            for(int i = 0; i < productNames.size(); i++){
                if(!productNames.get(i).getText().contains(brandSelected)){
                    return false;
                }
            }
        }else{
            if(pagination.size() > 0){
                currentPage = 1;
                totalPages = pagination.size();
                for (int i = 0; i < totalPages; i++){

                    if(!productNames.get(i).getText().contains(brandSelected)){
                        return false;
                    }
                    currentPage++;
                    if(currentPage <= totalPages) {
                        nextPageButton.click();
                    }

                }
            }
        }
        return true;
    }
}