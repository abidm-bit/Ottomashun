import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eBayTests extends Base{
eBay eb;


    @BeforeMethod
    void invoke(){
        navToSite("https://www.ebay.com/");
        eb= PageFactory.initElements(driver,eBay.class);
    }

    @Test(enabled = false)
    void scrapeFooter(){
        eb.cleanDB(); // delete previously scraped links
        eb.scrapeCallus();
    }

    @Test(enabled = false)
    void indecisiveWorkflow(){
        eb.indecisive(); // book? ipod? no - Macgyver dvd set xD
    }


    @AfterMethod()
    void tearDown(){
        driver.quit();
    }



}
