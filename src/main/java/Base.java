import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {
    static WebDriver driver;

    void getSite(String url){
        driver=new ChromeDriver();
        driver.get(url);
    }

    void navToSite(String url){
        driver = new ChromeDriver();
        driver.navigate().to(url);
    }

    // sets a wait time for a page to load, if the page loads before, then there is no static wait
    void impWait(int seconds){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    // set an explicit wait for a condition: visibility of an element or isDisplayed
    void explWaitElementVisibile(WebElement element,int seconds){
        Wait<WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
        wait.until(e->element.isDisplayed());
    }

    // this mimics a mouse hover/ mouse over an element
    void move2Element(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    // this mimics scrolling to an element (Chrome)
    // won't bring the element into the top view, scrolls just enough
    void scroll2Element(WebElement element){
        Actions actions  = new Actions(driver);
        actions.scrollToElement(element).perform();
    }

    // drag and drop: dragSource + dropTarget
    void dragNdrop(WebElement source, WebElement target){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source,target).perform();
    }

    // doubleClick
    void doubleClicc(WebElement element){
        Actions actions = new Actions(driver);
        actions.doubleClick(element);
    }

    // JavaScript workaround for other browsers to scroll to an element
    // actually brings the element into view
    void  scroll2Element2(WebElement element){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView('alignToTop')",element);
    }

    void deleteElement(WebElement element){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        javascriptExecutor.executeScript("arguments[0].remove()",element);
    }

    // Alerts.accept , dismiss , getText , sendKeys
    void acceptAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    void dismissAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    void sendkeysintoalertBox(String text){
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    String getAlertBoxText(){
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    void switchBack(){
        driver.switchTo().defaultContent();
    }


    // screenshot for a confirmation

    //iframes

    //dropdown : Select class


}
