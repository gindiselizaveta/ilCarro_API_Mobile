package rest_tests;

import dto.CarDto;
import dto.ErrorMessageDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import rest_api.CarController;

import java.util.Random;

public class AddCarTestsRest extends CarController {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void addNewCarPositiveTest() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("313" + i)
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(5)
                .carClass("Crossover")
                .pricePerDay(350.00)
                .city("Ramat Gan").build();
        System.out.println(car);
        Response response = addNewCar(car);
        System.out.println(response.getBody().print());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void addNewCarNegativeTest_DuplicateSerialNumber() {
        CarDto car = CarDto.builder()
                .serialNumber("3131649")
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(5)
                .carClass("Crossover")
                .pricePerDay(350.00)
                .city("Ramat Gan").build();

        Response response = addNewCar(car);
        System.out.println(response.getBody().print());
        softAssert.assertEquals(response.getStatusCode(), 400);
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        softAssert.assertEquals(errorMessageDto.getError(), "Bad Request", "Validate Error Name");
        softAssert.assertTrue(errorMessageDto.getMessage().toString().contains("already exists"), "Validate Error Message");
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeTest_EmptySerialNumber() {
        CarDto car = CarDto.builder()
                .serialNumber("")
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(5)
                .carClass("Crossover")
                .pricePerDay(350.00)
                .city("Ramat Gan").build();

        Response response = addNewCar(car);
        System.out.println(response.getBody().print());
        softAssert.assertEquals(response.getStatusCode(), 400);
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        softAssert.assertEquals(errorMessageDto.getError(), "Bad Request", "Validate Error Name");
        softAssert.assertTrue(errorMessageDto.getMessage().toString().contains("must not be blank"), "Validate Error Message");
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeTest_WrongData() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("313" + i)
                .manufacture("Toyota") // max length
                .model("Yaris Cross") // max length
                .year("2022") //2026, -1, 20.20, 20 25, yyyy,
                .fuel("Hybrid") // 'brid'
                .seats(5) // 1, 21, -1
                .carClass("Crossover") // max length
                .pricePerDay(350.00) //-0.001, 1000.001
                .city("Ramat Gan").build(); // wrong city

        Response response = addNewCar(car);
        System.out.println(response.getBody().print());
        softAssert.assertEquals(response.getStatusCode(), 400);
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        softAssert.assertEquals(errorMessageDto.getError(), "Bad Request", "Validate Error Name");
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeTest_WrongToken() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("313" + i)
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(5)
                .carClass("Crossover")
                .pricePerDay(350.00)
                .city("Ramat Gan").build();

        Response response = addNewCar_WrongToken(car,"no12to459ke439n");
        System.out.println(response.getBody().print());
        softAssert.assertEquals(response.getStatusCode(), 401,"validate status code");
        softAssert.assertTrue(response.getBody().print().contains("JWT strings must contain exactly 2 period characters"),"validate error message");
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeTest_WrongWOHeaderAuth() {
        int i = new Random().nextInt(1000) + 1000;
        CarDto car = CarDto.builder()
                .serialNumber("313" + i)
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(5)
                .carClass("Crossover")
                .pricePerDay(350.00)
                .city("Ramat Gan").build();

        Response response = addNewCar_WOToken(car);
        System.out.println(response.getBody().print());
        softAssert.assertEquals(response.getStatusCode(), 403,"validate status code");
        softAssert.assertAll();
    }
}
