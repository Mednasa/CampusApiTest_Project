package campus.customFieldGroup;

import campus.entranceExam.model.Exam;
import campus.loginPositive.LoginPositive;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;


public class CampusCustomFieldGroupTests extends LoginPositive {

    Faker createFaker = new Faker();
    String fieldGroupName = "";
    int orderNo = 0;
    String groupId = "";
    ArrayList<String> groupIds;
    String editName = "";


    @Test
    public void getList() {
        Exam e = new Exam();

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("schoolId", e.getSchoolId());

        groupIds =
                given()
                        .spec(reqSpec)
                        .body(requestBody)
                        .when()
                        .post("school-service/api/custom-field-groups/search")
                        .then()
                        .statusCode(200)
                        .extract().path("id");

    }

    @Test(dependsOnMethods = "getList")
    public void addFieldGroupPositive() {
        Exam e = new Exam();
        fieldGroupName = createFaker.animal().name() + createFaker.color().name() + createFaker.number().numberBetween(1, 17);
        orderNo = createFaker.number().numberBetween(1, 1000);

        Map<String, Object> addRequestBody = new HashMap<>();

        addRequestBody.put("name", fieldGroupName);
        addRequestBody.put("orderNo", orderNo);
        addRequestBody.put("columnSize", 3);
        addRequestBody.put("translateName", new ArrayList<>());
        addRequestBody.put("schoolId", e.getSchoolId());


        groupId =
                given()
                        .spec(reqSpec)
                        .body(addRequestBody)
                        .when()
                        .post("school-service/api/custom-field-groups")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");

    }

    @Test(dependsOnMethods = "addFieldGroupPositive")
    public void addFieldGroupNegative() {
        Exam e = new Exam();
        orderNo = createFaker.number().numberBetween(1, 100);

        Map<String, Object> addRequestBody = new HashMap<>();

        addRequestBody.put("name", fieldGroupName);
        addRequestBody.put("orderNo", orderNo);
        addRequestBody.put("columnSize", 3);
        addRequestBody.put("translateName", new ArrayList<>());
        addRequestBody.put("schoolId", e.getSchoolId());


        given()
                .spec(reqSpec)
                .body(addRequestBody)
                .when()
                .post("school-service/api/custom-field-groups")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", containsString("already exists."));


    }

    @Test(dependsOnMethods = "addFieldGroupNegative")
    public void editFieldGroupPositive() {
        Exam e = new Exam();
        editName = "Zagreus" + createFaker.animal().name() + createFaker.number().numberBetween(1, 500);

        Map<String, Object> editRequestBodyPositive = new HashMap<>();
        editRequestBodyPositive.put("id", groupId);
        editRequestBodyPositive.put("name", editName);
        editRequestBodyPositive.put("orderNo", orderNo);
        editRequestBodyPositive.put("columnSize", 3);
        editRequestBodyPositive.put("translateName", new ArrayList<>());
        editRequestBodyPositive.put("schoolId", e.getSchoolId());


        given()
                .spec(reqSpec)
                .body(editRequestBodyPositive)
                .when()
                .put("school-service/api/custom-field-groups")
                .then()
                .log().body()
                .statusCode(200);


    }

    @Test(dependsOnMethods = "editFieldGroupPositive")
    public void editFieldGroupNegative() {
        Exam e = new Exam();

        Map<String, Object> editRequestBodyNegative = new HashMap<>();
        editRequestBodyNegative.put("id", groupIds.get(5));
        editRequestBodyNegative.put("name", editName);
        editRequestBodyNegative.put("orderNo", orderNo);
        editRequestBodyNegative.put("columnSize", 3);
        editRequestBodyNegative.put("translateName", new ArrayList<>());
        editRequestBodyNegative.put("schoolId", e.getSchoolId());


        given()
                .spec(reqSpec)
                .body(editRequestBodyNegative)
                .when()
                .put("school-service/api/custom-field-groups")
                .then()
                .log().body()
                .statusCode(400)
                .body("message", containsString("already exists."));
    }

    @Test(dependsOnMethods = "editFieldGroupNegative")
    public void deleteFieldGroupPositive() {
        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/custom-field-groups/" + groupId)
                .then()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "deleteFieldGroupPositive")
    public void deleteFieldGroupNegative() {
        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/custom-field-groups/" + groupId)
                .then()
                .log().body()
                .statusCode(400)
                .body("detail", containsString("CustomFieldGroup doesn't exist"));
    }


}
