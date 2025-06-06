import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CostcoHolidayGifts extends Base {

    @FindBy(css = "select#sort_by")
    private WebElement sortBy;

    @FindBy(css = ".product-tile-set .thumbnail .price")
    private List<WebElement> prices;

    public CostcoHolidayGifts(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*
    Happy Path test case
    1. Go to site
    2. Scroll to drop down
    3. Move mouse over drop down
    4. Select drop down , index 2 for lowest to highest (ascending)
    5. loop thru prices, .getText() & add it to a ArrayList<String> prices
    6. AssertTrue: Use the checkSorted method

    */
    public void sortHappyPath() {
        impWait(5);
        handleDropDown(sortBy, 2);
        ArrayList<String> process = new ArrayList<>();
        for (WebElement all : prices) {
            process.add(all.getText());
        }
        Assert.assertTrue(checkIfPricesAreSorted(process));
    }


    /*
    Negative test case, shouldn't be sorted
    1. Go to site
    2. Scroll to drop down
    3. Move mouse over drop down
    4. Select drop down , index 1 for highest to lowest (descending)
    5. loop thru prices, .getTexT() & add it to a ArrayList<String> prices
    6. AssertFalse: Use the checkSorted method
    */
    public void sortNegativeTC() {
        impWait(5);
        handleDropDown(sortBy, 1);
        ArrayList<String> process = new ArrayList<>();
        for (WebElement all : prices) {
            process.add(all.getText());
        }
        Assert.assertFalse(checkIfPricesAreSorted(process));
    }


}
