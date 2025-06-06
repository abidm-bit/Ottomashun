import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoogleHPTest extends TestBase {
    private GoogleHP googleHP;

    @BeforeMethod
    void setupTest() {
        driver.get("https://www.google.com");
        googleHP = new GoogleHP(driver);
    }

    @DataProvider(name = "searchTerms")
    public Object[][] searchData() {
        return new Object[][] {
            {"selenium"},
            {"testng"}
        };
    }

    @Test(dataProvider = "searchTerms")
    void searchTest(String searchTerm) {
        googleHP.searchBox(searchTerm);
    }

    @Test(enabled=false)
    void signInTest() {
        googleHP.setSignInButton();
    }
}
