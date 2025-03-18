import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;


public class DemoQAWindows extends Base{


    @FindBy(tagName = "iframe")
    List<WebElement> allFrames;

    @FindBy(xpath = "//*[@id='windowButton']")
    WebElement firstWinButton;

    @FindBy(id="sampleHeading")
    WebElement firstWindowContent;

    @FindBy(xpath = "//*[@id='messageWindowButton']")
    WebElement secondWinButton;

    void deleteAnnoyingAds(){
        for(WebElement all:allFrames){
            deleteElement(all);
        }
    }

    void firstWindowButton(){
        impWait(3);
        deleteAnnoyingAds();
        String startingWindow = driver.getWindowHandle();
        firstWinButton.click();
        handleTabWindow(startingWindow);
        Assert.assertEquals(driver.getCurrentUrl(),"https://demoqa.com/sample");
        String secondWindow = driver.getWindowHandle();
        Assert.assertEquals(firstWindowContent.getText(),"This is a sample page");
        driver.close();
        handleTabWindow(secondWindow);
        Assert.assertEquals(driver.getCurrentUrl(),"https://demoqa.com/browser-windows");
    }



}
