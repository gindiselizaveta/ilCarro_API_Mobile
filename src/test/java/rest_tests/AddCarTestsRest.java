package rest_tests;

import dto.CarDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import rest_api.CarController;

import java.util.Random;

public class AddCarTestsRest extends CarController {
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

        Response response = addNewCar(car);
        System.out.println(response.getBody().print());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
