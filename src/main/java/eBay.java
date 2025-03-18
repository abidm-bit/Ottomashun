import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class eBay extends Base {

    @FindBy(css = "input[title='Search']")
    WebElement searchBox;

    @FindBy(css=".gh-search-categories")
    WebElement allCategoriesDropDown;

    @FindBy(css = "#vlGlobalFooter")
    WebElement footerSection;

    @FindBy(css=".gf-small-links:nth-child(2) .gf-small-links__link a")
    List<WebElement> footerLinx;


    void scrapeTitleLinkIntoDB(String name, String fL) {

        String query = "INSERT INTO ebayFooterLinx (name,link) VALUES (?,?)";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/mabid/IdeaProjects/Ottomashun/src/main/resources/ebayFootScrape");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1,name);
            statement.setString(2,fL);

            // Execute the query (use executeUpdate for INSERT)
            int rowsInserted = statement.executeUpdate();

//            if (rowsInserted > 0) {
//                System.out.println("Title & Link scraped into DB xD");
//            }
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
        for(WebElement f : footerLinx){title.add(f.getDomProperty("innerText"));}
        for(WebElement f : footerLinx){footerLink.add(f.getDomProperty("href"));}
        // for each name & link from the LinkedList, insert into the DB
        for (int i = 0; i < footerLinx.size(); i++) {
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





}
