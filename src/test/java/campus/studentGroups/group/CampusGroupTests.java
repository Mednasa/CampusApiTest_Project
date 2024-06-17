package campus.studentGroups.group;

import campus.entranceExam.model.Exam;
import campus.loginPositive.LoginPositive;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CampusGroupTests extends LoginPositive {

    Faker randomCreate = new Faker();
    String groupName = "";
    String description = "";
    String groupId = "";


    @Test
    public void getList() {
        Exam e = new Exam();

        Map<String, String> getRequestBody = new HashMap<>();

        getRequestBody.put("schoolId", e.getSchoolId());


        given()
                .spec(reqSpec)
                .body(getRequestBody)
                .when()
                .post("school-service/api/student-group/search")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void addGroup() {
        groupName = randomCreate.artist().name() + " " + randomCreate.number().randomDigit();
        description = randomCreate.address().cityName() + "-" + randomCreate.address().streetName() + "-" + randomCreate.address().state();

        Exam e = new Exam();

        Map<String, Object> addRequestBody = new HashMap<>();
        addRequestBody.put("schoolId", e.getSchoolId());
        addRequestBody.put("name", groupName);
        addRequestBody.put("description", description);
        addRequestBody.put("active", true);
        addRequestBody.put("publicGroup", true);
        addRequestBody.put("showToStudent", true);


        Response response =
                given()
                        .spec(reqSpec)
                        .body(addRequestBody)
                        .when()
                        .post("school-service/api/student-group")
                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().response();

        groupId = response.path("id");
        String gName = response.path("name");
        String descrip = response.path("description");

        Assert.assertTrue(gName.length() < 101);
        Assert.assertTrue(descrip.length() < 5000);
    }


    @Test(dependsOnMethods = "addGroup")
    public void editGroup() {
        String editGroupName = randomCreate.lordOfTheRings().character() + " - " + randomCreate.gameOfThrones().dragon();
        String editDescription = randomCreate.superhero().name() + " - " + randomCreate.superhero().power();


        Exam e = new Exam();

        Map<String, Object> editRequestBody = new HashMap<>();
        editRequestBody.put("id", groupId);
        editRequestBody.put("schoolId", e.getSchoolId());
        editRequestBody.put("name", editGroupName);
        editRequestBody.put("description", editDescription);


        Response response =
                given()
                        .spec(reqSpec)
                        .body(editRequestBody)
                        .when()
                        .put("school-service/api/student-group")
                        .then()
                        .log().body()
                        .statusCode(200)
                        .extract().response();

        String editGrpName = response.path("name");
        String editDescrip = response.path("description");

        Assert.assertTrue(editGrpName.length() < 101);
        Assert.assertTrue(editDescrip.length() < 5000);
    }

    @Test(dependsOnMethods = "editGroup")
    public void deleteGroupPositive() {
        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/student-group/" + groupId)
                .then()
                .statusCode(200);


    }

    @Test(dependsOnMethods = "deleteGroupPositive")
    public void deleteGroupNegative() {
        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/student-group/" + groupId)
                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Group with given id does not exist!"));


    }
}
