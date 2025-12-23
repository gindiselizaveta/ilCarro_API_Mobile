package screens;

import dto.RegistrationBodyDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class RegistrationScreen extends BaseScreen {
    public RegistrationScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.telran.ilcarro:id/editRegName")
    WebElement inputName;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editRegLastName")
    WebElement inputLastName;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editRegEmail")
    WebElement inputEmail;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editRegPassword")
    WebElement inputPassword;
    @AndroidFindBy(id = "com.telran.ilcarro:id/checkBoxAgree")
    WebElement checkBox;
    @AndroidFindBy(id = "com.telran.ilcarro:id/regBtn")
    WebElement btnYalla;

    public void typeRegistrationForm(RegistrationBodyDto user) {
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickCheckBox() {
        checkBox.click();
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }
}
