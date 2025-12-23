package mobile_tests;

import config.AppiumConfig;
import dto.CarDto;
import dto.RegistrationBodyDto;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import java.util.Random;

public class AddCarTests extends AppiumConfig {

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
    public void addCarPositiveTest() {
        int i = new Random().nextInt(1000);
        CarDto car = CarDto.builder().serialNumber("202"+i).manufacture("Toyota").model("Yaris Cross").year("2023").fuel("Hybrid").seats(4).city("Ramat Gan").pricePerDay(350).carClass("J").build() ;
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        new MyCarsScreen(driver).clickBtnAddNewCar();
        AddNewCar addNewCarScreen = new AddNewCar(driver);
        addNewCarScreen.typeAddCarForm(car);
        addNewCarScreen.clickBtnAddNewCar();
    }
}
