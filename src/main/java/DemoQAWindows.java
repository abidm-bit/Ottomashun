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

    @FindBy(tagName = "body") WebElement messageInWindow;
    String expectedMessage= "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.";

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

    void secondWindowButtonTC1(){
        deleteAnnoyingAds();
        String starting = driver.getWindowHandle();
        scroll2Element2(secondWinButton);
        secondWinButton.click();
        handleTabWindow(starting);
        Assert.assertEquals(messageInWindow.getText(),expectedMessage);
    }


}
