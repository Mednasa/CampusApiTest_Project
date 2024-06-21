package erdem;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GradingScheme {
    RequestSpecification resquestSpecification;
    ResponseSpecification responseSpecification;

    Faker randVal = new Faker();

    String gradingID;
    String gradingName;
    String schoolID;


    @BeforeClass
    public void Setup() {
        baseURI = "https://test.mersys.io";

        Map<String, String> logCredentials = new HashMap<>() {{
            put("username", "turkeyts");
            put("password", "TechnoStudy123");
            put("rememberMe", "true");
        }};
        Cookies cookies =
                given()
                        .body(logCredentials).contentType(ContentType.JSON)
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract().response().getDetailedCookies();

        resquestSpecification = new RequestSpecBuilder()
                .addCookies(cookies)
                .setContentType(ContentType.JSON)
                .build();
    }


    @Test
    public void listGradingScheme() {
        String schoolID = "6576fd8f8af7ce488ac69b89";

        given().spec(resquestSpecification)
                .when()
                .get("/school-service/api/grading-schemes/school/" + schoolID + "/search")
                .then()
                .statusCode(200);
    }

    @Test
    public void createGradingScheme() {

        gradingName = randVal.lorem().word() + randVal.country().countryCode2();

        Map<String, String> body = new HashMap<>() {{
            put("id", null);
            put("name", gradingName);
            put("active", "true");
            put("schoolId", "6576fd8f8af7ce488ac69b89");
            put("type", "POINT");
            put("minPointToPass", randVal.number().digits(2));
        }};

        Response response = given().spec(resquestSpecification)
                .body(body)
                .when()
                .post("/school-service/api/grading-schemes")
                .then().log().body()
                .statusCode(201).extract().response();

        gradingID = response.path("id");
        gradingName = response.path("name");
        schoolID = response.path("schoolId");

    }

    @Test(dependsOnMethods = "createGradingScheme")
    public void updateGradingScheme() {
        Map<String, String> body = new HashMap<>() {{
            put("id", gradingID);
            put("name", gradingName + randVal.ancient().primordial());
            put("schoolId", schoolID);
            put("type", "POINT");
            put("minPointToPass", randVal.number().digits(2));
        }};
        given().spec(resquestSpecification)
                .body(body)
                .when()
                .put("/school-service/api/grading-schemes/")
                .then().log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "updateGradingScheme")
    public void deleteGradingScheme() {
        given().spec(resquestSpecification)
               .when()
               .delete("/school-service/api/grading-schemes/" + gradingID)
               .then()
               .statusCode(200);
    }

    @Test(dependsOnMethods = "deleteGradingScheme")
    public void deleteGradingSchemeNegative() {
        given().spec(resquestSpecification)
               .when()
               .delete("/school-service/api/grading-schemes/" + gradingID)
               .then()
               .statusCode(400);
    }

}
