package campus.incidentType;

import campus.entranceExam.model.Exam;
import campus.loginPositive.LoginPositive;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CampusIncidentTypeTests extends LoginPositive {

    Faker createFaker = new Faker();
    String incidentTypeName = "";
    String incidentTypeId = "";


    @Test
    public void getList() {
        Exam e = new Exam();
        Map<String, String> getRequestBody = new HashMap<>();
        getRequestBody.put("schoolId", e.getSchoolId());

        given()
                .spec(reqSpec)
                .body(getRequestBody)
                .when()
                .post("school-service/api/incident-type/search")
                .then()
                .log().body()
                .statusCode(200);

    }

    @Test
    public void addIncidentType() {
        incidentTypeName = createFaker.harryPotter().character() + " - " + createFaker.harryPotter().spell();

        Exam e = new Exam();
        Map<String, Object> addRequestBody = new HashMap<>();

        addRequestBody.put("name", incidentTypeName);
        addRequestBody.put("active", true);
        addRequestBody.put("schoolId", e.getSchoolId());
        addRequestBody.put("minPoint", 3);
        addRequestBody.put("maxPoint", 17);
        addRequestBody.put("academicBased", false);
        addRequestBody.put("permissions", new ArrayList<>());

        incidentTypeId =
                given()
                        .spec(reqSpec)
                        .body(addRequestBody)
                        .when()
                        .post("school-service/api/incident-type")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");

    }

    @Test(dependsOnMethods = "addIncidentType")
    public void editIncidentType() {
        String editIncidentTypeName = createFaker.harryPotter().character() + " - " +
                createFaker.harryPotter().spell() + " - " + createFaker.harryPotter().house();

        Exam e = new Exam();
        Map<String, Object> editRequestBody = new HashMap<>();
        editRequestBody.put("id", incidentTypeId);
        editRequestBody.put("name", editIncidentTypeName);
        editRequestBody.put("active", true);
        editRequestBody.put("schoolId", e.getSchoolId());
        editRequestBody.put("minPoint", 3);
        editRequestBody.put("maxPoint", 17);
        editRequestBody.put("academicBased", false);
        editRequestBody.put("permissions", new ArrayList<>());

        given()
                .spec(reqSpec)
                .body(editRequestBody)
                .when()
                .put("school-service/api/incident-type")
                .then()
                .log().body()
                .statusCode(200);


    }

    @Test(dependsOnMethods = "editIncidentType")
    public void deleteIncidentTypePositive() {
        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/incident-type/" + incidentTypeId)
                .then()
                .statusCode(200);


    }

    @Test(dependsOnMethods = "deleteIncidentTypePositive")
    public void deleteIncidentTypeNegative() {
        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/incident-type/" + incidentTypeId)
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Incident Type Not Found"));


    }
}
