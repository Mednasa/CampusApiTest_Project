package campus.states;

import campus.loginPositive.LoginPositive;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CampusStatesTest extends LoginPositive {

    Faker randomCreate = new Faker();
    String randomName = "";
    String randomShort = "";
    String stateId = "";


    @Test
    public void ListStates() {
        Map<String, String> statesNames = new HashMap<>();
        statesNames.put("name", "");

        given()
                .spec(reqSpec)
                .body(statesNames)
                .when()
                .post("school-service/api/states/search")
                .then()
                .time(lessThan(1000L))
                .statusCode(200);
    }


    @Test
    public void CreateState() {
        randomName = randomCreate.address().state() + randomCreate.address().buildingNumber();
        randomShort = randomCreate.address().countryCode();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", randomName);
        requestBody.put("shortName", randomShort);

        Map<String, String> country = new HashMap<>();
        country.put("id", "665a19562bd94a3b36cef834");
        requestBody.put("country", country);

        requestBody.put("translateName", new ArrayList<>());

        stateId =
                given()
                        .spec(reqSpec)
                        .body(requestBody)
                        .when()
                        .post("school-service/api/states")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");
    }

    @Test(dependsOnMethods = "CreateState")
    public void EditState() {
        String updateName = "Carlos Santana " + randomCreate.address().buildingNumber();

        Map<String, Object> uploadRequestBody = new HashMap<>();
        uploadRequestBody.put("id", stateId);
        uploadRequestBody.put("name", updateName);

        Map<String, String> country = new HashMap<>();
        country.put("id", "665a19562bd94a3b36cef834");
        uploadRequestBody.put("country", country);

        uploadRequestBody.put("translateName", new ArrayList<>());


        given()
                .spec(reqSpec)
                .body(uploadRequestBody)
                .when()
                .put("school-service/api/states")
                .then()
                .log().body()
                .statusCode(200);


    }

    @Test(dependsOnMethods = "EditState")
    public void DeleteState(){

        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/states/"+stateId)
                .then()
                .log().body()
                .statusCode(200);


    }
}
