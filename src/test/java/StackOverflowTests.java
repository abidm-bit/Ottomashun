import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StackOverflowTests extends TestBase {
    private StackOverflow stackOverflow;

    @BeforeMethod
    void setupTest() {
        driver.get("https://stackoverflow.com/questions");
        stackOverflow = new StackOverflow(driver);
    }

    @Test(priority = 0)
    void leastCookieButton() {
        stackOverflow.clickLeastCookies();
    }

    @Test(priority = 1)
    void deleteCookiePolicy() {
        stackOverflow.deleteBannerGo2Companies();
    }
}
