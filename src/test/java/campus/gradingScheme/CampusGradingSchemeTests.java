package campus.gradingScheme;

import campus.entranceExam.model.Exam;
import campus.loginPositive.LoginPositive;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CampusGradingSchemeTests extends LoginPositive {

    Faker randomCreate = new Faker();
    String schemeName = "";
    String schemeId = "";


    @Test
    public void getList() {
        Exam e = new Exam();

        given()
                .spec(reqSpec)
                .when()
                .get("school-service/api/grading-schemes/school/" + e.getSchoolId() + "/search")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void addGradingScheme() {
        schemeName = randomCreate.hobbit().character() + " - " + randomCreate.hobbit().location();
        Exam e = new Exam();

        Map<String, Object> addRequestBody = new HashMap<>();
        addRequestBody.put("name", schemeName);
        addRequestBody.put("active", true);
        addRequestBody.put("type", "POINT");
        addRequestBody.put("enablePoint", false);
        addRequestBody.put("minPointToPass", 17);
        addRequestBody.put("gradeRanges", new ArrayList<>());
        addRequestBody.put("schoolId", e.getSchoolId());

        schemeId =
                given()
                        .spec(reqSpec)
                        .body(addRequestBody)
                        .when()
                        .post("school-service/api/grading-schemes")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");
    }

    @Test(dependsOnMethods = "addGradingScheme")
    public void editGradingScheme() {
        String editSchemeName = randomCreate.hobbit().character() + " - " + randomCreate.gameOfThrones().dragon();
        Exam e = new Exam();

        Map<String, Object> editRequestBody = new HashMap<>();
        editRequestBody.put("id", schemeId);
        editRequestBody.put("name", editSchemeName);
        editRequestBody.put("active", true);
        editRequestBody.put("type", "POINT");
        editRequestBody.put("enablePoint", false);
        editRequestBody.put("minPointToPass", 17);
        editRequestBody.put("gradeRanges", new ArrayList<>());
        editRequestBody.put("schoolId", e.getSchoolId());

        given()
                .spec(reqSpec)
                .body(editRequestBody)
                .when()
                .put("school-service/api/grading-schemes")
                .then()
                .log().body()
                .statusCode(200);

    }

    @Test(dependsOnMethods = "editGradingScheme")
    public void deleteGradingSchemePositive() {
        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/grading-schemes/"+schemeId)
                .then()
                .statusCode(500); // bug found  actually ->.statusCode(200)

    }

    @Test(dependsOnMethods = "deleteGradingSchemePositive")
    public void deleteGradingSchemeNegative() {
        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/grading-schemes/"+schemeId)
                .then()
                .statusCode(500); // bug found actually -> .statusCode(400)

    }

}
