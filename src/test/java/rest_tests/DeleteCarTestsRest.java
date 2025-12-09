package rest_tests;

import dto.CarsDto;
import dto.ErrorMessageDto;
import dto.ResponseMessageDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import rest_api.CarController;

public class DeleteCarTestsRest extends CarController {

    SoftAssert softAssert = new SoftAssert();
    CarsDto cars;

    @BeforeClass
    public void getAllCarsBeforeDelete() {
        Response response = getAllUserCars();
        if (response.getStatusCode() == 200) {
            cars = response.getBody().as(CarsDto.class);
            System.out.println(cars);
        } else
            System.out.println("Something went wrong! Status code is ->" + response.getStatusCode());
    }

    @Test
    public void deleteCarPositiveTest() {
        if (cars.getCars().length != 0) {
            String serialNumber = cars.getCars()[0].getSerialNumber();
            System.out.println(serialNumber);
            Response response = deleteCarBySerialNumber(serialNumber);
            System.out.println(response.getBody().print());
            ResponseMessageDto responseMessageDto = response.getBody().as(ResponseMessageDto.class);
            softAssert.assertEquals(response.getStatusCode(), 200, "Validation status code");
            softAssert.assertTrue(responseMessageDto.getMessage().contains("Car deleted successfully"), "Validation message");
            softAssert.assertAll();
        } else
            Assert.fail("Something went wrong! Check your cars");
    }

    @Test
    public void deleteCarNegativeTest_WrongSerialNumber() {
        Response response = deleteCarBySerialNumber("wrongSerNumb");
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        System.out.println(errorMessageDto);
        softAssert.assertEquals(response.getStatusCode(), 400, "Validation status code");
        softAssert.assertTrue(errorMessageDto.getMessage().toString().contains("not found"),"Validation message");
        softAssert.assertAll();
    }
}
