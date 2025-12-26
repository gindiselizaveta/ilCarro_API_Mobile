package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import static config.AppiumConfig.*;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MyCarsScreen extends BaseScreen {
    public MyCarsScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.telran.ilcarro:id/addCarBtn")
    WebElement btnPlus;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Car was added!']")
    WebElement popUpMessageSuccess;
    @AndroidFindBy(id = "android:id/button1")
    WebElement btnYes;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.telran.ilcarro:id/myCarSerialTxt']")
    List<WebElement> listOfCars;

    public void clickBtnAddNewCar() {
        clickWait(btnPlus, 3);
    }

    public boolean isTextInPopUpMessageSuccessPresent(String text, int time) {
        return isTextInElementPresent(popUpMessageSuccess, text, time);
    }

    public void deleteCar() {
        swipe(width / 8, height / 4, width / 8 * 7, height / 4);
    }

    public void clickBtnYes() {
        clickWait(btnYes, 3);
    }

    public List<String> readCarList() {
        List<String> list = new ArrayList<>();
        for (WebElement el : listOfCars) {
            list.add(el.getText());
        }
        return list;
    }
}
