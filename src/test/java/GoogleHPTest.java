import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoogleHPTest extends Base{

    GoogleHP googleHP;


    @BeforeMethod
    void invokeBrowser(){
        navToSite("https://www.google.com/");
        googleHP = PageFactory.initElements(driver,GoogleHP.class);
    }


    @Test(priority = 0)
    void signInSwitchTab(){
        googleHP.switchTabComeBack();
    }

    @DataProvider(name = "googleSearchKW")
    public Object[] keywords(){
        return new Object[][]{{"java"},{"abacus"},{"root"}};
    }

    @Test(dataProvider = "googleSearchKW")
    void search(String inp){
        googleHP.searchBox(inp);
    }

    @AfterMethod
    void tearDown(){
        driver.quit();
    }


}
