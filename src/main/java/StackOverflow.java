import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class StackOverflow extends Base {

    @FindBy(css = "[id='onetrust-reject-all-handler']")
    WebElement necessaryCookiesOnly;

    @FindBy(css = "[aria-label='Cookie banner']")
    WebElement cookieBanner;

    @FindBy(xpath = "//*[contains(text(),'Jobs')]")
    WebElement jobsCat;

    @FindBy(xpath = "//span[contains(text(),'Companies')]/parent::div/parent::a")
    WebElement companiesCat;

    /* GIVEN a user visits stackoverflow
       WHEN the user clicks the necessary cookies only button
       THEN the cookie banner disappears
       WHEN the user clicks the jobs category
       THEN the jobs page loads
    */
    void clickLeastCookies(){
        explWaitElementVisibile(necessaryCookiesOnly,5);
        Assert.assertTrue(cookieBanner.isDisplayed());
        necessaryCookiesOnly.click();
        jobsCat.click();
        impWait(3);
        Assert.assertEquals(driver.getCurrentUrl(),"https://stackoverflowjobs.com/?source=so-left-nav");
    }

    /* GIVEN a user visits stackoverflow
       WHEN the user deletes the cookieBanner via inspect tools
       THEN the cookie banner disappears
       WHEN the user clicks the companies category
       THEN the companies page loads
    */

    void deleteBannerGo2Companies(){
        explWaitElementVisibile(cookieBanner,3);
        Assert.assertTrue(cookieBanner.isDisplayed());
        deleteElement(cookieBanner);
        companiesCat.click();
        impWait(3);
        Assert.assertEquals(driver.getCurrentUrl(),"https://stackoverflow.com/jobs/companies");
    }


}
