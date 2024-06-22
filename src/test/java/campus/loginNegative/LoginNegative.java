package campus.loginNegative;

import com.github.javafaker.Faker;
import utilities.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class LoginNegative {
    RequestSpecification reqSpec;
    Faker randomCreate = new Faker();
    String randomName = "";

    @Test
    public void loginCampusNegative() {
        randomName = randomCreate.name().username();

        baseURI = ConfigReader.getProperty("URL");

        Map<String, String> userCred = new HashMap<>();

        userCred.put("username", randomName);
        userCred.put("password", "Calm Down");
        userCred.put("rememberMe", "true");

        Cookies gelenCookies =
                given()
                        .body(userCred)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("auth/login")
                        .then()
//                      .log().body()
                        .statusCode(401)
                        .extract().response().getDetailedCookies();

        reqSpec = new RequestSpecBuilder()
                .addCookies(gelenCookies)
                .setContentType(ContentType.JSON)
                .build();
    }
}
