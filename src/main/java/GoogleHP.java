import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Objects;

public class GoogleHP extends Base{


    @FindBy(css = "[aria-label='Search']")
    WebElement searchboxinput;

    @FindBy(xpath = "//button[contains(text(),'Stay signed out')]")
    WebElement ssoButton;

    @FindBy(css = "a[aria-label='Sign in (opens a new tab)']")
    WebElement signInButton;

    @FindBy(xpath = "//span[contains(text(),'Sign in')]")
    WebElement headerSI;

    @FindBy(xpath = "//a[contains(text(),' How Search works ')]")
    WebElement hsw;

    @FindBy(xpath = "//a[contains(text(),'Store')]")
    WebElement storeButton;

void staySignedInIFrame(){
    impWait(5);
    handleiFrame(0); // iframe:nth-child(1), so I'm passing index 0
    ssoButton.click();
    switchBack();
}

void setSignInButton(){
    String startingHandle = driver.getWindowHandle();
    impWait(5);
    handleiFrame("callout"); // it can be identified by the name "callout"
    signInButton.click(); // switches to a new tab
    handleTabWindow(startingHandle); // switches to that new tab
    impWait(5);
    Assert.assertTrue(headerSI.isDisplayed());
    String secondHandle= driver.getWindowHandle();
    driver.close();
    handleTabWindow(secondHandle);
    impWait(5);
    Assert.assertEquals(driver.getCurrentUrl(),"https://www.google.com/");
}


void searchBox(String input){
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
