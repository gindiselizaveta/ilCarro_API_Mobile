package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SearchScreen extends BaseScreen {
    public SearchScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.telran.ilcarro:id/title' and @text='Login']")
    WebElement btnLogin;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.telran.ilcarro:id/title' and @text='Registration']")
    WebElement btnRegistration;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    WebElement btnDots;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.telran.ilcarro:id/title' and @text='My Cars']")
    WebElement btnMyCars;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.telran.ilcarro:id/title' and @text='Logout']")
    WebElement btnLogOut;

    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Login success!']")
    WebElement popUpMessageLoginSuccess;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Registration success!']")
    WebElement popUpMessageRegSuccess;

    public void clickBtnLogin() {
        clickWait(btnLogin, 3);
    }

    public void clickBtnRegistration() {
        clickWait(btnRegistration, 3);
    }

    public void clickBtnDots() {
        clickWait(btnDots, 3);
    }

    public void clickBtnMyCars() {
        clickWait(btnMyCars, 3);
    }

    public void clickBtnLogOut() {
        clickWait(btnLogOut, 3);
    }

    public boolean validatePopUpMessageLogin(String text) {
        return isTextInElementPresent(popUpMessageLoginSuccess, text, 5);
    }

    public boolean validatePopUpMessageRegistration(String text) {
        return isTextInElementPresent(popUpMessageRegSuccess, text, 5);
    }

    public boolean isElementPresentBtnLogin(String text) {
        return isTextInElementPresent(btnLogin, text, 5);
    }
}
