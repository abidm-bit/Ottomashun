import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;
import java.util.Objects;

public class GoogleHP extends Base {
    @FindBy(css = "[aria-label='Search']")
    private WebElement searchboxinput;

    @FindBy(xpath = "//button[contains(text(),'Stay signed out')]")
    private WebElement ssoButton;

    @FindBy(xpath = "(//span[contains(text(),'Sign in')])[3]")
    private WebElement signInButton;

    @FindBy(xpath = "//span[contains(text(),'Sign in')]")
    private WebElement headerSI;

    @FindBy(xpath = "//a[contains(text(),' How Search works ')]")
    private WebElement hsw;

    @FindBy(xpath = "//a[contains(text(),'Store')]")
    private WebElement storeButton;

    @FindBy(tagName = "iframe")
    private List<WebElement> allIframes;

    public GoogleHP(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void staySignedInIFrame() {
        try {
            impWait(5);
            handleiFrame(0);
            ssoButton.click();
            switchBack();
        } catch (Exception _) {}
    }

    public void setSignInButton() {
        try {
            String startingHandle = driver.getWindowHandle();
            impWait(5);

            // Try to find the sign in button directly first
            if (signInButton.isDisplayed()) {
                signInButton.click();
            } else {
                // If not found, try each iframe until we find it
                boolean found = false;
                for (int i = 0; i < allIframes.size() && !found; i++) {
                    try {
                        handleiFrame(i);
                        if (signInButton.isDisplayed()) {
                            signInButton.click();
                            found = true;
                        }
                    } catch (Exception e) {
                        // If element not found in this iframe, continue to next
                        switchBack();
                    }
                }
                
                if (!found) {
                    throw new RuntimeException("Sign in button not found in any iframe");
                }
            }

            handleTabWindow(startingHandle);
            impWait(5);
            Assert.assertTrue(headerSI.isDisplayed());
            String secondHandle = driver.getWindowHandle();
            driver.close();
            handleTabWindow(secondHandle);
            impWait(5);
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com/");
        } catch (Exception e) {
            System.out.println("Error in setSignInButton: " + e.getMessage());
            throw e;
        }
    }

    public void searchBox(String input) {
        staySignedInIFrame();
        searchboxinput.sendKeys(input, Keys.ENTER);
        impWait(5);
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains(input));
    }

    void switchTabComeBack(){
        setSignInButton();
        Assert.assertTrue(hsw.isDisplayed());
        hsw.click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com/search/howsearchworks/?fg=1");
        driver.navigate().back();
        impWait(3);
        Assert.assertTrue(storeButton.isDisplayed());
        storeButton.click();
        impWait(3);
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("store.google.com"));
    }
}
