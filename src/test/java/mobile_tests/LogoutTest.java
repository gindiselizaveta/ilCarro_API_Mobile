package mobile_tests;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class LogoutTest extends AppiumConfig {

    @BeforeMethod
    public void login() {
        new SplashScreen(driver).goToSearchScreen(7);
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogin();

        RegistrationBodyDto user = RegistrationBodyDto.builder()
                .username("lizkafox@mail.ru").password("wertY!23").build();
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.typeLoginForm(user);
        loginScreen.clickBtnYalla();
    }

    @Test
    public void logOutPositiveTest() {
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnLogOut();
        searchScreen.clickBtnDots();
        Assert.assertTrue(searchScreen.isElementPresentBtnLogin("Login"));
    }
}
