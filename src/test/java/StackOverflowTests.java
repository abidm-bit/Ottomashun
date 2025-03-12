import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StackOverflowTests extends Base{
    StackOverflow stackOverflow;

    @BeforeMethod
    void invokeBrowser(){
        getSite("https://stackoverflow.com/questions");
        stackOverflow = PageFactory.initElements(driver,StackOverflow.class);
    }


    @Test(priority = 0)
    void leastCookieButton(){
        stackOverflow.clickLeastCookies();
    }

    @Test(priority = 1)
    void deleteCookiePolicy(){
        stackOverflow.deleteBannerGo2Companies();
    }


    @AfterMethod
    void tearDown(){
        driver.quit();
    }




}
