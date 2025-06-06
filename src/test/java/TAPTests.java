import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TAPTests extends TestBase {
    private TestAutomationBlogspot tab;

    @BeforeMethod
    void setupTest() {
        driver.get("https://testautomationpractice.blogspot.com/");
        tab = new TestAutomationBlogspot(driver);
    }

    @Test(priority = 0)
    void dnd() {
        tab.tapDragDrop();
    }

    @Test(priority = 1)
    void simpleAlertButton() {
        tab.alertButton1();
    }

    @Test(priority = 2)
    void confirmationAlertButton1() {
        tab.confirmationAlertButton1();
    }

    @Test(priority = 3)
    void confirmationAlertButton2() {
        tab.confirmationAlertButton2();
    }

    @Test(priority = 4)
    void promptAlertButton1() {
        tab.promptAlert1();
    }

    @DataProvider(name = "promptAlertNames")
    public Object[][] kw() {
        return new Object[][]{{"Bartholomew"}, {"Thomas"}, {"Cornelius"}};
    }

    @Test(priority = 5, dataProvider = "promptAlertNames")
    void promptAlertButton2(String text) {
        tab.promptAlert2(text);
    }
}
