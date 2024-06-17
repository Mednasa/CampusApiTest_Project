package campus.country;

import campus.loginPositive.LoginPositive;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CampusCountryTest extends LoginPositive {

    Faker randomCreate = new Faker();
    String randomName = "";
    String randomCode = "";


    @Test
    public void CreateCountry() {
        randomName = randomCreate.address().country() + randomCreate.address().countryCode();
        randomCode = randomCreate.address().countryCode();

        Map<String, Object> newCountry = new HashMap<>();
        newCountry.put("name", randomName);
        newCountry.put("code", randomCode);
        newCountry.put("hasState",true);


        given()
                .spec(reqSpec)
                .body(newCountry)
                .when()
                .post("/school-service/api/countries")

                .then()
                .log().body()
                .statusCode(201)
                .body("hasState",equalTo(true));

    }
}
