import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TestAutomationBlogspot extends Base {
    @FindBy(xpath = "//*[contains(text(),'Drag and Drop')]")
    private WebElement dragdropheader;

    @FindBy(css = ".ui-draggable")
    private WebElement dragThis;

    @FindBy(id = "droppable")
    private WebElement dropHere;

    @FindBy(css = "#droppable p")
    private WebElement dropExpectText;

    @FindBy(css = "button[id='alertBtn']")
    private WebElement simpleAlert;

    @FindBy(xpath = "//button[@id='confirmBtn']")
    private WebElement confirmationAlert;

    @FindBy(xpath = "//p[contains(text(),'You pressed OK!')]")
    private WebElement postConfirm;

    @FindBy(xpath = "//p[contains(text(),'You pressed Cancel')]")
    private WebElement postDismiss;

    @FindBy(xpath = "//button[contains(text(),'Prompt Alert')]")
    private WebElement promptAlert;

    @FindBy(xpath = "//p[contains(text(),'User cancelled the prompt.')]")
    private WebElement dismissPrompt;

    @FindBy(xpath = "//p[contains(text(),'How are you today?')]")
    private WebElement postPrompt;

    public TestAutomationBlogspot(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void tapDragDrop() {
        scroll2Element2(dragdropheader);
        dragNdrop(dragThis, dropHere);
        impWait(3);
        Assert.assertEquals(dropExpectText.getText(), "Dropped!");
        Assert.assertEquals(dropHere.getCssValue("background-color"), "rgba(255, 250, 144, 1)");
    }

    public void alertButton1() {
        move2Element(simpleAlert);
        simpleAlert.click();
        Assert.assertEquals(getAlertBoxText(), "I am an alert box!");
        acceptAlert();
    }

    public void confirmationAlertButton1() {
        scroll2Element(confirmationAlert);
        confirmationAlert.click();
        Assert.assertEquals(getAlertBoxText(), "Press a button!");
        acceptAlert();
        impWait(2);
        Assert.assertTrue(postConfirm.isDisplayed());
    }

    public void confirmationAlertButton2() {
        scroll2Element(confirmationAlert);
        confirmationAlert.click();
        Assert.assertEquals(getAlertBoxText(), "Press a button!");
        dismissAlert();
        impWait(2);
        Assert.assertTrue(postDismiss.isDisplayed());
    }

    public void promptAlert1() {
        move2Element(promptAlert);
        promptAlert.click();
        Assert.assertEquals(getAlertBoxText(), "Please enter your name:");
        dismissAlert();
        impWait(2);
        Assert.assertTrue(dismissPrompt.isDisplayed());
    }

    public void promptAlert2(String name) {
        move2Element(promptAlert);
        promptAlert.click();
        Assert.assertEquals(getAlertBoxText(), "Please enter your name:");
        sendkeysintoalertBox(name);
        acceptAlert();
        impWait(3);
        String post = postPrompt.getText();
        Assert.assertTrue(post.contains(name));
    }
}
