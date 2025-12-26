package mobile_tests;

import config.AppiumConfig;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.MyCarsScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.ArrayList;
import java.util.List;

public class DeleteCarTests extends AppiumConfig {

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
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
    }

    @Test
    public void deleteCarPositiveTest() {
        MyCarsScreen myCarsScreen = new MyCarsScreen(driver);
        List<String> listBeforeDelete = myCarsScreen.readCarList();
        myCarsScreen.readCarList();
        myCarsScreen.deleteCar();
        myCarsScreen.clickBtnYes();
        List<String> listAfterDelete = myCarsScreen.readCarList();
        Assert.assertNotEquals(listBeforeDelete, listAfterDelete);
    }
}
