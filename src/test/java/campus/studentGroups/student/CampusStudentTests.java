package campus.studentGroups.student;

import campus.entranceExam.model.Exam;
import campus.loginPositive.LoginPositive;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class CampusStudentTests extends LoginPositive {

    List<String> studentIds;

    @Test
    public void getList() {
        Exam e = new Exam();

        Map<String, Object> getRequestBody = new HashMap<>();
        getRequestBody.put("studentId", "");
        getRequestBody.put("classId", "65770d9b8af7ce488ac69bb6");
        getRequestBody.put("schoolId", e.getSchoolId());
        getRequestBody.put("firstName", "");
        getRequestBody.put("lastName", "");
        getRequestBody.put("gender", null);
        getRequestBody.put("status", null);
        getRequestBody.put("exceptIds", new ArrayList<>());
        getRequestBody.put("schoolClassIds", new ArrayList<>());
        getRequestBody.put("gradeLevelId", "657713978af7ce488ac6a632");
        getRequestBody.put("departmentId", null);
        getRequestBody.put("pageSize", 10);
        getRequestBody.put("pageIndex", 0);
        getRequestBody.put("classStudentIds", new ArrayList<>(Arrays.asList("657711ca8af7ce488ac6a628")));
        getRequestBody.put("academicPeriodId", "6577022e8af7ce488ac69b96");

        studentIds =
                given()
                        .queryParam("page", 0)
                        .queryParam("size", 10)
                        .log().uri()
                        .spec(reqSpec)
                        .body(getRequestBody)
                        .when()
                        .post("school-service/api/classes/students/pageable")
                        .then()
                        .statusCode(200)
                        .extract().path("content.id");

    }

    @Test(dependsOnMethods = "getList")
    public void addStudents() {
        List<String> studentId = new ArrayList<>(Arrays.asList(studentIds.get(0)));

        given()
                .pathParam("groupId", "6668036d70885213e60c7f54")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .log().uri()
                .spec(reqSpec)
                .body(studentId)
                .when()
                .post("school-service/api/student-group/{groupId}/add-students")
                .then()
                .log().body();


    }

    @Test(dependsOnMethods = "addStudents")
    public void deleteStudentsPositive() {
        List<String> studentId = new ArrayList<>(Arrays.asList(studentIds.get(0)));

        given()
                .pathParam("groupId", "6668036d70885213e60c7f54")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .log().uri()
                .spec(reqSpec)
                .body(studentId)
                .when()
                .post("school-service/api/student-group/{groupId}/remove-students")
                .then()
                .log().body()
                .statusCode(200);


    }

    @Test(dependsOnMethods = "deleteStudentsPositive")
    public void deleteStudentsNegative() {
        List<String> studentId = new ArrayList<>(Arrays.asList(studentIds.get(0)));

        given()
                .pathParam("groupId", "6668036d70885213e60c7f54")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .log().uri()
                .spec(reqSpec)
                .body(studentId)
                .when()
                .post("school-service/api/student-group/{groupId}/remove-students")
                .then()
                .log().body()
                .statusCode(400);
    }
}
