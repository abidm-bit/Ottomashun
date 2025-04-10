import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

public class Base {
    static WebDriver driver;

    void getSite(String url){
        driver=new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(url);
    }

    void navToSite(String url){
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
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

    // drag and drop: dragSource + dropTarget
    void dragNdrop(WebElement source, WebElement target){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source,target).perform();
    }

    // this mimics scrolling to an element (Chrome)
    // won't bring the element into the top view, scrolls just enough
    void scroll2Element(WebElement element){
        Actions actions  = new Actions(driver);
        actions.scrollToElement(element).perform();
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

    void handleTabWindow(String startingHandle){
        Set<String> allHandles = driver.getWindowHandles();
        for(String aH: allHandles){
            if(aH != startingHandle){
                driver.switchTo().window(aH);
            }
        }
    }

    void closeWindow(){
        driver.close();
    }

    // iframes , overloading
    void handleiFrame(String identifier){
        driver.switchTo().frame(identifier);
    }
    void handleiFrame(int index){
        driver.switchTo().frame(index);
    }
    void handleiFrame(WebElement element){
        driver.switchTo().frame(element);
    }

    // switchBack after interacting with an iframe
    void switchBack(){
        driver.switchTo().defaultContent();
    }

    //dropdown : Select class
    void handleDropDown(WebElement element, int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }
    void handleDropDownV(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }
    void handleDropDownVT(WebElement element, String visibleText){
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    boolean checkIfPricesAreSorted(ArrayList<String> input){
        ArrayList<Double>process = new ArrayList<>();
        for(String all:input){
            all=all.replaceAll("\\$","");
            process.add(Double.parseDouble(all));
        }
        for(int i=1;i<process.size();i++){
            if(process.get(i-1).compareTo(process.get(i))>0){ return false;}
        }return true;
    }

}
