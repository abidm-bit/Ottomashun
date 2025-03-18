import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

public class TAPTests extends Base{
    TestAutomationBlogspot tab;

    @BeforeMethod
    void invokeBrowser(){
    getSite("https://testautomationpractice.blogspot.com/");
    tab = PageFactory.initElements(driver,TestAutomationBlogspot.class);
    }

    @Test(priority = 0)
    void dnd(){
        tab.tapDragDrop();
    }

    @Test(priority = 1)
    void simpleAlertButton(){
        tab.alertButton1();
    }

    @Test(priority = 2)
    void confirmationAlertButton1(){
        tab.confirmationAlertButton1();
    }

    @Test(priority = 3)
    void confirmationAlertButton2(){
        tab.confirmationAlertButton2();
    }

    @Test(priority = 4)
    void promptAlertButton1(){
        tab.promptAlert1();
    }

    @DataProvider(name ="promptAlertNames")
    public Object[][] kw(){
        return new Object[][]{{"Bartholomew"},{"Thomas"},{"Cornelius"}};
    }

    @Test(priority = 5,dataProvider = "promptAlertNames")
    void promptAlertButton2(String text){
        tab.promptAlert2(text);
    }



    @AfterMethod
    void tearDown(){
        driver.quit();
    }




}
