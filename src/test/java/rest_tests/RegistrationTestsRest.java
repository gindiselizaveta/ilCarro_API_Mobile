package rest_tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dto.ErrorMessageDto;
import dto.RegistrationBodyDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import rest_api.AuthenticationController;

import java.util.Random;

public class RegistrationTestsRest extends AuthenticationController {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder().username("qa_tester" + i + "@gmail.com").password("wertY!23").firstName("QA").lastName("Tester").build();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(), 200);
    }

    @Test
    public void registrationNegativeTest_WrongEmail() {
        int i = new Random().nextInt(1000);
        RegistrationBodyDto user = RegistrationBodyDto.builder().username("qa_tester" + i + "gmail.com").password("wertY!23").firstName("QA").lastName("Tester").build();
        Response response = registrationLogin(user, REGISTRATION_URL);
        System.out.println(response.getBody().print());
        softAssert.assertEquals(response.getStatusCode(), 400, "Validate Status Code");
        ErrorMessageDto errorMessageDto = response.getBody().as(ErrorMessageDto.class);
        softAssert.assertEquals(errorMessageDto.getError(), "Bad Request", "Validate Error Name");
        softAssert.assertTrue(errorMessageDto.getMessage().toString().contains("must be a well-formed email address"), "Validate Message");
        softAssert.assertAll();
    }
}
