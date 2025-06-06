import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DQAWindowTests extends TestBase {
    private DemoQAWindows demoQAWindows;

    @BeforeMethod
    void setupTest() {
        driver.get("https://demoqa.com/browser-windows");
        demoQAWindows = new DemoQAWindows(driver);
    }

    @Test(priority = 0)
    void firstWindowButton() {
        demoQAWindows.firstWindowButton();
    }

    @Test(priority = 1)
    void secondWindowButton() {
        demoQAWindows.secondWindowButtonTC1();
    }
}
