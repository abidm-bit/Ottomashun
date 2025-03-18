import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CostcoHolidayGiftsTest extends Base{
    CostcoHolidayGifts chg;

    @BeforeMethod
    void invokeBrowser(){
        getSite("https://www.costco.com/holiday-gifts.html?keyword=OFF");
        chg= PageFactory.initElements(driver,CostcoHolidayGifts.class);
    }

     /*
    GIVEN COSTCO HOLIDAY GIFTS IS LOADED
    WHEN A USER SORTS PRODUCTS BY LOW TO HIGH
    THEN THE PRICES SHOULD BE SORTED IN ASCENDING ORDER

    HAPPY PATH: The test will pass, verifying the products are sorted
    after using the dropdown
    */

    @Test(priority = 0)
    void checkIfAscendingSortingWorks(){
        chg.sortHappyPath();
    }

    /*
    GIVEN COSTCO HOLIDAY GIFTS IS LOADED
    WHEN A USER SORTS PRODUCTS BY HIGH TO LOW
    THEN THE PRICES SHOULD BE SORTED IN DESCENDING ORDER

    NEGATIVE TEST CASE: FORCED FAILURE
    The products won't be sorted in ASCENDING ORDER
    I expect a false
    Even though the test "fails", it'll be reported as a pass
    */

    @Test(priority = 1)
    void checkIfDescendingSortingWorks(){
        chg.sortNegativeTC();
    }


    @AfterMethod
    void tearDown(){
        driver.quit();
    }


}
