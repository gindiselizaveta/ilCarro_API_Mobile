package screens;

import dto.CarDto;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static config.AppiumConfig.*;

public class AddNewCar extends BaseScreen {
    public AddNewCar(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.telran.ilcarro:id/editSerial")
    WebElement inputSerialNumber;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editMan")
    WebElement inputManufacture;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editModel")
    WebElement inputModel;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editYear")
    WebElement inputYear;
    @AndroidFindBy(id = "com.telran.ilcarro:id/text1")
    WebElement inputFuel;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editSeats")
    WebElement inputSeats;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editCarClass")
    WebElement inputCarClass;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editPrice")
    WebElement inputPricePerDay;
    @AndroidFindBy(id = "com.telran.ilcarro:id/editCity")
    WebElement inputCity;
    @AndroidFindBy(id = "com.telran.ilcarro:id/addCarBtn")
    WebElement btnAddCar;

    public void typeAddCarForm(CarDto car) {
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputCity.sendKeys(car.getCity());
        inputPricePerDay.sendKeys(String.valueOf(car.getSeats()));
        inputCarClass.sendKeys(car.getCarClass());
        //swipe(500, 1500, 500, 350);
        //System.out.println(height + "x" + width);
        swipe(width / 2, (int) (height * 0.8), width / 2, (int) (height * 0.2));
        typeFuel(car.getFuel());
        inputYear.sendKeys(car.getYear());
        inputSeats.sendKeys(Integer.toString(car.getSeats()));
    }

    private void typeFuel(String fuel) {
        inputFuel.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By
                        .xpath("//*[@text='" + fuel + "']")))
                .click();
    }

    public void clickBtnAddNewCar() {
        clickWait(btnAddCar, 3);
    }
}
