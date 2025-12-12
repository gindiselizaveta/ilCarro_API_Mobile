package screens;

import dto.RegistrationBodyDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginScreen extends BaseScreen {
    public LoginScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.telran.ilcarro:id/editLoginEmail")
    WebElement fieldLoginEmail;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editLoginPassword")
    WebElement fieldLoginPassword;
    @AndroidFindBy(id = "com.telran.ilcarro:id/loginBtn")
    WebElement btnYalla;

    public void typeLoginForm(RegistrationBodyDto registrationBodyDto) {
        fieldLoginEmail.sendKeys(registrationBodyDto.getUsername());
        fieldLoginPassword.sendKeys(registrationBodyDto.getPassword());
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }
}
