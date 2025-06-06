import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CostcoHolidayGiftsTest extends TestBase {
    private CostcoHolidayGifts costcoHolidayGifts;

    @BeforeMethod
    void setupTest() {
        driver.get("https://www.costco.com/holiday-gifts.html?keyword=OFF");
        costcoHolidayGifts = new CostcoHolidayGifts(driver);
    }

    /*
    GIVEN COSTCO HOLIDAY GIFTS IS LOADED
    WHEN A USER SORTS PRODUCTS BY LOW TO HIGH
    THEN THE PRICES SHOULD BE SORTED IN ASCENDING ORDER
    HAPPY PATH: The test will pass, verifying the products are sorted
    after using the dropdown
    */
    @Test(enabled=false)
    void checkIfAscendingSortingWorks() {
        costcoHolidayGifts.sortHappyPath();
    }

    /*
    GIVEN COSTCO HOLIDAY GIFTS IS LOADED
    WHEN A USER SORTS PRODUCTS BY HIGH TO LOW
    THEN THE PRICES SHOULD BE SORTED IN DESCENDING ORDER

    The products won't be sorted in ASCENDING ORDER
    I expect a false
    Even though the test "fails", it'll be reported as a pass
    */
    @Test(enabled=false)
    void checkIfDescendingSortingWorks() {
        costcoHolidayGifts.sortNegativeTC();
    }
}
