package loginPositive;


import utilities.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class LoginPositive {

    public static RequestSpecification reqSpec; // her yerden ulasabilmen icin public yap. default ayarı protect


    @BeforeClass
    public void loginCampusPositive() {

        baseURI = ConfigReader.getProperty("URL");

        Map<String, String> userCred = new HashMap<>();

        userCred.put("username", ConfigReader.getProperty("username"));
        userCred.put("password", ConfigReader.getProperty("password"));
        userCred.put("rememberMe", "true");

        Cookies gelenCookies =
                given()
                        .body(userCred)
                        .contentType(ContentType.JSON)
                        .when()
                        .post("auth/login")
                        .then()
//                        .log().body()
                        .statusCode(200)
                        .extract().response().getDetailedCookies();


        reqSpec = new RequestSpecBuilder()
                .addCookies(gelenCookies)
                .setContentType(ContentType.JSON)
                .build();


    }


}
