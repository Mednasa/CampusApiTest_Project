package erdem;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class CustomFieldGroups {

    RequestSpecification resquestSpecification;

    Faker randVal = new Faker();

    String fieldID;
    String fieldName;
    int orderNo;
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
    public void listCustomFieldGroups() {
        Map<String, String> credentials = new HashMap<>() {{
            put("schoolId", "6576fd8f8af7ce488ac69b89");
        }};
        given().spec(resquestSpecification)
                .body(credentials)
                .when()
                .post("/school-service/api/custom-field-groups/search")
                .then()
                .statusCode(200);
    }

    @Test
    public void createCustomField() {
        Map<String, String> credentials = new HashMap<>() {{
            put("id", null);
            put("name", randVal.lorem().word() + randVal.country().countryCode2());
            put("orderNo", randVal.number().digits(4));
            put("columnSize", randVal.number().digits(2));
            put("schoolId", "6576fd8f8af7ce488ac69b89");
        }};
        Response response = given().spec(resquestSpecification)
                .body(credentials)
                .when()
                .post("/school-service/api/custom-field-groups")
                .then().log().body()
                .statusCode(201).extract().response();

        fieldID = response.path("id");
        fieldName = response.path("name");
        orderNo = response.path("orderNo");
        schoolID = response.path("schoolId");

    }

    @Test(dependsOnMethods = "createCustomField")
    public void createCustomFieldNegative() {
        Map<String, String> credentials = new HashMap<>() {{
            put("id", null);
            put("name", randVal.lorem().word());
            put("orderNo", String.valueOf(orderNo));
            put("columnSize", 5 + randVal.number().digits(2));
            put("schoolId", "6576fd8f8af7ce488ac69b89");
        }};

        given().spec(resquestSpecification)
                .body(credentials).log().body()
                .when()
                .post("/school-service/api/custom-field-groups")
                .then().log().body()
                .statusCode(400);
    }

    @Test(dependsOnMethods = "createCustomFieldNegative")
    public void updateCustomField() {
        Map<String, String> credentials = new HashMap<>() {{
            put("id", fieldID);
            put("name", randVal.lorem().word() + randVal.country().countryCode2());
            put("orderNo", randVal.number().digits(4));
            put("columnSize", "7");
            put("schoolId", schoolID);
        }};

        given().spec(resquestSpecification)
                .body(credentials).log().body()
                .when()
                .put("/school-service/api/custom-field-groups")
                .then().log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "updateCustomField")
    public void updateCustomFieldNegative() {
        Map<String, String> credentials = new HashMap<>() {{
            put("id", fieldID);
            put("name", "Zagreus733");
            put("orderNo", "195");
            put("columnSize", "7");
            put("schoolId", schoolID);
        }};
        given().spec(resquestSpecification)
                .body(credentials).log().body()
                .when()
                .put("/school-service/api/custom-field-groups")
                .then().log().body()
                .statusCode(400);
    }

    @Test(dependsOnMethods = "updateCustomFieldNegative")
    public void deleteCustomField() {
        given().spec(resquestSpecification)
                .when()
                .delete("/school-service/api/custom-field-groups/" + fieldID)
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "deleteCustomField")
    public void deleteCustomFieldNegative() {
        given().spec(resquestSpecification)
                .when()
                .delete("/school-service/api/custom-field-groups/" + fieldID)
                .then()
                .statusCode(400);
    }


}
