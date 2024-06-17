package campus.cities;

import campus.loginPositive.LoginPositive;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CampusCitiesTest extends LoginPositive {

    Faker randomCreate = new Faker();
    String randomName = "";
    String cityName = "";
    String cityId = "";


    @Test
    public void listCities() {
        Map<String, String> citiesList = new HashMap<>();
        citiesList.put("name", "");

        Response response =
                given()
                        .spec(reqSpec)
                        .body(citiesList)
                        .when()
                        .post("school-service/api/cities/search")
                        .then()
                        .time(lessThan(1000L))
                        .statusCode(200)
                        .extract().response();

        ArrayList<String> names = response.path("name");
        cityName = names.get((int) (Math.random() * 10));

    }

    @Test(dependsOnMethods = "listCities")
    public void getCity() {
        Map<String, String> getCityCredentials = new HashMap<>();
        getCityCredentials.put("name", cityName);

        given()
                .spec(reqSpec)
                .body(getCityCredentials)
                .when()
                .post("school-service/api/cities/search")
                .then()
                .log().body()
                .time(lessThan(300L))
                .statusCode(200);
    }

    @Test
    public void addCities() {
        randomName = randomCreate.address().cityName();

        Map<String, Object> addCitiesCredentials = new HashMap<>();
        addCitiesCredentials.put("name", randomName);
        addCitiesCredentials.put("state", null);

        Map<String, String> country = new HashMap<>();
        country.put("id", "6391d228f9990d1dd4544663");
        addCitiesCredentials.put("country", country);

        addCitiesCredentials.put("translateName", new ArrayList<>());

        cityId =
                given()
                        .spec(reqSpec)
                        .body(addCitiesCredentials)
                        .when()
                        .post("school-service/api/cities")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");
    }


    @Test(dependsOnMethods = "addCities")
    public void editCity() {
        String editCityName = "Kingston " + randomCreate.address().streetName();

        Map<String, Object> editCityCredentials = new HashMap<>();
        editCityCredentials.put("id", cityId);
        editCityCredentials.put("name", editCityName);

        Map<String, String> country = new HashMap<>();
        country.put("id", "6391d228f9990d1dd4544663");
        editCityCredentials.put("country", country);

        editCityCredentials.put("translateName", new ArrayList<>());


        given()
                .spec(reqSpec)
                .body(editCityCredentials)
                .when()
                .put("school-service/api/cities")
                .then()
                .log().body()
                .statusCode(200);

    }

    @Test(dependsOnMethods = "editCity")
    public void deleteCity() {
        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/cities/" + cityId)
                .then()
                .statusCode(200);
    }


}
