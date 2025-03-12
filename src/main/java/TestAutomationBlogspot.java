import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TestAutomationBlogspot extends Base {


@FindBy(xpath = "//*[contains(text(),'Drag and Drop')]")
WebElement dragdropheader;

@FindBy(css=".ui-draggable")
WebElement dragThis;

@FindBy(id="droppable")
WebElement dropHere;

@FindBy(css= "#droppable p")
WebElement dropExpectText;

 /* HAPPY PATH
    GIVEN a user has scrolled to the drag and drop header
    WHEN the user drags the source box and drops it to the target box
    THEN the target box will display "Dropped" & change to yellow
 */
void tapDragDrop(){
    scroll2Element2(dragdropheader);
    dragNdrop(dragThis,dropHere);
    impWait(3);
    Assert.assertEquals(dropExpectText.getText(),"Dropped!");
    Assert.assertEquals(dropHere.getCssValue("background-color"),"rgba(255, 250, 144, 1)");
}

@FindBy(css = "button[id='alertBtn']")
WebElement simpleAlert;

/* HAPPY PATH
   GIVEN a user clicks the simple alert button
   THEN an alert box appears, "I am an alert box!"
   WHEN the user clicks ok
   THEN the alert box is accepted
*/

void alertButton1(){
    move2Element(simpleAlert);
    simpleAlert.click();
    Assert.assertEquals(getAlertBoxText(),"I am an alert box!");
    acceptAlert();
}

@FindBy(xpath = "//button[@id='confirmBtn']")
WebElement confirmationAlert;

@FindBy(xpath = "//p[contains(text(),'You pressed OK!')]")
WebElement postConfirm;

@FindBy(xpath = "//p[contains(text(),'You pressed Cancel')]")
WebElement postDismiss;

/* HAPPY PATH
  GIVEN a user clicks the confirmation alert button
  THEN an alert box appears, "Press a button!"
  WHEN the user clicks ok
  THEN the alert box is accepted and the page displays "You pressed OK!"
*/
void confirmationAlertButton1(){
    scroll2Element(confirmationAlert);
    confirmationAlert.click();
    Assert.assertEquals(getAlertBoxText(),"Press a button!");
    acceptAlert();
    impWait(2);
    Assert.assertTrue(postConfirm.isDisplayed());
}

/* HAPPY PATH
  GIVEN a user clicks the confirmation alert button
  THEN an alert box appears, "Press a button!"
  WHEN the user clicks cancel
  THEN the alert box is dismissed and the page displays "You pressed Cancel!"
*/
void confirmationAlertButton2(){
    scroll2Element(confirmationAlert);
    confirmationAlert.click();
    Assert.assertEquals(getAlertBoxText(),"Press a button!");
    dismissAlert();
    impWait(2);
    Assert.assertTrue(postDismiss.isDisplayed());
}

@FindBy(xpath = "//button[contains(text(),'Prompt Alert')]")
WebElement promptAlert;

@FindBy(xpath = "//p[contains(text(),'User cancelled the prompt.')]")
WebElement dismissPrompt;

@FindBy(xpath = "//p[contains(text(),'How are you today?')]")
WebElement postPrompt;

 /* HAPPY PATH
  GIVEN a user clicks the prompt alert button
  THEN an alert box appears, "Please enter your name:"
  WHEN the user clicks cancel
  THEN the alert box is dismissed and the page displays "User cancelled the prompt."
*/
void promptAlert1(){
    move2Element(promptAlert);
    promptAlert.click();
    Assert.assertEquals(getAlertBoxText(), "Please enter your name:");
    dismissAlert();
    impWait(2);
    Assert.assertTrue(dismissPrompt.isDisplayed());
}

 /* HAPPY PATH
  GIVEN a user clicks the prompt alert button
  THEN an alert box appears, "Please enter your name:"
  WHEN the user types in a NAME and accepts the alert
  THEN the alert box is accepted and the page displays "Hello NAME! How are you today?"
*/
void promptAlert2(String name){
    move2Element(promptAlert);
    promptAlert.click();
    Assert.assertEquals(getAlertBoxText(), "Please enter your name:");
    sendkeysintoalertBox(name);
    acceptAlert();
    impWait(3);
    String post = postPrompt.getText();
    Assert.assertTrue(post.contains(name));
}

@FindBy(xpath = "//h2[contains(text(),'Double Click')]")
WebElement doubleClickHeader;

@FindBy(xpath = "//button[contains(text(),'Copy Text')]")
WebElement doubleClickThisButton;

@FindBy(css = "input#field2")
WebElement field2;

/* HAPPY PATH
GIVEN a user scrolls to the Double Click header
WHEN the user double-clicks the Copy Text button
THEN the text from Field1 will be copied into Field2.
*/
void doubleClickButton(){
    scroll2Element2(doubleClickHeader);
    Assert.assertEquals(field2.getText(),"");
    doubleClicc(doubleClickThisButton);
//    JavascriptExecutor js= (JavascriptExecutor) driver;
//    String field2Value = (String) js.executeScript("arguments[0].value;",field2);
//    Assert.assertEquals(field2Value,"Hello World!");
}




}
