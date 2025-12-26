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
        CarDto car = CarDto.builder()
                .serialNumber("202" + i).manufacture("Toyota").model("Yaris")
                .year("2025").fuel("Hybrid").seats(4).city("Ramat Gan")
                .pricePerDay(350).carClass("J")
                .build();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        new MyCarsScreen(driver).clickBtnAddNewCar();
        AddNewCar addNewCarScreen = new AddNewCar(driver);
        addNewCarScreen.typeAddCarForm(car);
        addNewCarScreen.clickBtnAddNewCar();
        Assert.assertTrue(new MyCarsScreen(driver).isTextInPopUpMessageSuccessPresent("Car was added", 5));
    }

    @Test
    public void addCarNegativeDuplicateTest() {
        CarDto car = CarDto.builder()
                .serialNumber("202875").manufacture("Toyota").model("Corolla")
                .year("1999").fuel("Diesel").seats(4).city("Haifa")
                .pricePerDay(350).carClass("J")
                .build();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        new MyCarsScreen(driver).clickBtnAddNewCar();
        AddNewCar addNewCarScreen = new AddNewCar(driver);
        addNewCarScreen.typeAddCarForm(car);
        addNewCarScreen.clickBtnAddNewCar();
        Assert.assertTrue(new ErrorScreen(driver)
                .validationErrorMessage("Car with serial number " + car.getSerialNumber() + " already exists"));
    }

    @Test
    public void addCarNegativeEmptyFieldsTest() {
        CarDto car = CarDto.builder()
                .serialNumber("").manufacture("").model("")
                .year("").fuel("").seats(0).city("")
                .pricePerDay(0).carClass("")
                .build();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        new MyCarsScreen(driver).clickBtnAddNewCar();
        AddNewCar addNewCarScreen = new AddNewCar(driver);
        addNewCarScreen.typeAddCarForm(car);
        addNewCarScreen.clickBtnAddNewCar();
        Assert.assertTrue(new ErrorScreen(driver)
                .validationErrorMessage("is required!"));
    }

    @Test
    public void addCarNegativeTest_WrongYear_BUG() {
        int i = new Random().nextInt(1000);
        CarDto car = CarDto.builder()
                .serialNumber("202" + i).manufacture("Toyota").model("Rav-4")
                .year("ф").fuel("Electric").seats(6).city("Ramat Gan")
                .pricePerDay(350).carClass("J")
                .build();
        SearchScreen searchScreen = new SearchScreen(driver);
        searchScreen.clickBtnDots();
        searchScreen.clickBtnMyCars();
        new MyCarsScreen(driver).clickBtnAddNewCar();
        AddNewCar addNewCarScreen = new AddNewCar(driver);
        addNewCarScreen.typeAddCarForm(car);
        addNewCarScreen.clickBtnAddNewCar();
        //Assert.assertTrue(new MyCarsScreen(driver).isTextInPopUpMessageSuccessPresent("Car was added", 5));
    }
}
