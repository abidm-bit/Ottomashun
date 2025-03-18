import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class eBay extends Base {

    @FindBy(css = "input[title='Search']")
    WebElement searchBox;

    @FindBy(css=".gh-search-categories")
    WebElement allCategoriesDropDown;

    @FindBy(css = "#vlGlobalFooter")
    WebElement footerSection;

    @FindBy(css=".gf-big-links__title a")
    List<WebElement> footerLinx1;

    @FindBy(css=".gf-big-links__list-item a")
    List<WebElement> footerLinx2;

    @FindBy(css = ".srp-controls__count-heading")
    WebElement results;

    @FindBy(xpath = "//span[contains(text(),'MacGyver - The Complete Series (DVD, 2007)')]/parent::div/parent::a")
    WebElement diysuperman;


    void scrapeTitleLinkIntoDB(String name, String fL) {

        String query = "INSERT INTO ebayFooterLinx (name,link) VALUES (?,?)";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/mabid/IdeaProjects/Ottomashun/src/main/resources/ebayFootScrape");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1,name);
            statement.setString(2,fL);

            // Execute the query (use executeUpdate for INSERT)
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 0) {
                System.out.println("Title & Links scraped into DB xD");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void scrapeCallus(){
        move2Element(footerSection);
        LinkedList<String> title = new LinkedList<>();
        LinkedList<String> footerLink = new LinkedList<>();
        for(WebElement f : footerLinx1){title.add(f.getDomProperty("innerText"));}
        for(WebElement f : footerLinx1){footerLink.add(f.getDomProperty("href"));}
        for(WebElement f : footerLinx2){title.add(f.getDomProperty("innerText"));}
        for(WebElement f : footerLinx2){footerLink.add(f.getDomProperty("href"));}
        // for each name & link from the LinkedList, insert into the DB
        for (int i = 0; i < footerLinx1.size()+ footerLinx2.size(); i++) {
            String name = title.get(i);
            String fL = footerLink.get(i);
            scrapeTitleLinkIntoDB(name, fL);
        }
    }

    void cleanDB(){
        String query = "Delete from ebayFooterLinx";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/mabid/IdeaProjects/Ottomashun/src/main/resources/ebayFootScrape");
             PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false); // Start transaction
            statement.executeUpdate();
            connection.commit(); // Commit changes
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void indecisive(){
        impWait(5);
        handleDropDown(allCategoriesDropDown,4);
        searchBox.sendKeys("1984", Keys.ENTER);
        impWait(3);
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("1984"));
        driver.navigate().back();
        impWait(3);
        Assert.assertTrue(allCategoriesDropDown.isDisplayed());
        handleDropDownV(allCategoriesDropDown,"11233");
        searchBox.clear();
        searchBox.sendKeys("ipod", Keys.ENTER);
        impWait(3);
        Assert.assertTrue(results.getText().contains("ipod"));
        driver.navigate().back();
        impWait(3);
        Assert.assertTrue(allCategoriesDropDown.isDisplayed());
        searchBox.clear();
        handleDropDownVT(allCategoriesDropDown,"Movies & TV");
        searchBox.sendKeys("Macgyver", Keys.ENTER);
        impWait(3);
        Assert.assertTrue(results.getText().contains("Macgyver"));
        driver.navigate().back();
        impWait(3);
        driver.navigate().forward();
        impWait(5);
        String startingHandle = driver.getWindowHandle();
        diysuperman.click();
        handleTabWindow(startingHandle);
        Assert.assertTrue(Objects.requireNonNull(driver.getTitle()).contains("MacGyver - The Complete Series (DVD, 2007)"));
    }




}
