package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    @FindBy(how= How.XPATH, using = "//input[@name='keyword' and @placeholder='Buscar productos']")
    private WebElement search_textBox;

    @FindBy(how = How.XPATH, using  = "//fieldset//button[@type='submit']")
    private WebElement search_button;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void writeSearch(String busqueda){
        search_textBox.sendKeys(busqueda);
    }

    public void search(){
        search_button.sendKeys(Keys.ENTER);
    }
}
