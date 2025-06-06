import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class DemoQAWindows extends Base {
    @FindBy(tagName = "iframe")
    private List<WebElement> allFrames;

    @FindBy(xpath = "//*[@id='windowButton']")
    private WebElement firstWinButton;

    @FindBy(id = "sampleHeading")
    private WebElement firstWindowContent;

    @FindBy(xpath = "//*[@id='messageWindowButton']")
    private WebElement secondWinButton;

    @FindBy(tagName = "body")
    private WebElement messageInWindow;

    private final String expectedMessage = "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.";

    public DemoQAWindows(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void deleteAnnoyingAds() {
        for (WebElement all : allFrames) {
            deleteElement(all);
        }
    }

    public void firstWindowButton() {
        impWait(3);
        deleteAnnoyingAds();
        String startingWindow = driver.getWindowHandle();
        firstWinButton.click();
        handleTabWindow(startingWindow);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/sample");
        String secondWindow = driver.getWindowHandle();
        Assert.assertEquals(firstWindowContent.getText(), "This is a sample page");
        driver.close();
        handleTabWindow(secondWindow);
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/browser-windows");
    }

    public void secondWindowButtonTC1() {
        deleteAnnoyingAds();
        String starting = driver.getWindowHandle();
        scroll2Element2(secondWinButton);
        secondWinButton.click();
        handleTabWindow(starting);
        Assert.assertEquals(messageInWindow.getText(), expectedMessage);
    }
}
