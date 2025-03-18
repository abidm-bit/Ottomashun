import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DQAWindowTests extends Base {
    DemoQAWindows dqawindows;


    @BeforeMethod
    void invokeBrowser(){
        getSite("https://demoqa.com/browser-windows");
        dqawindows= PageFactory.initElements(driver,DemoQAWindows.class);
    }

    @Test(priority = 0)
    void firstWindowButton(){
    dqawindows.firstWindowButton();
    }

    @Test(priority = 1,enabled = false)
    void secondWindowButton1(){

    }
    @Test(priority = 2,enabled = false)
    void secondWindowButton2(){

    }

    @AfterMethod
    void tearDown(){
        driver.quit();
    }



}
