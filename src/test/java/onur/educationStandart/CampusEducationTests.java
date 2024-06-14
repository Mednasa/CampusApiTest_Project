package onur.educationStandart;

import loginPositive.LoginPositive;
import onur.entranceExam.model.Exam;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CampusEducationTests extends LoginPositive {

    Faker createFaker =new Faker();
    String educationName="";
    String educationId="";


    @Test
    public void getList(){

        Exam e = new Exam();

        given()
                .spec(reqSpec)
                .when()
                .get("school-service/api/education-standard/school/"+e.getSchoolId())
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void addEducation(){

        Exam e =new Exam();
        educationName =createFaker.animal().name()+createFaker.gameOfThrones().dragon();

        Map<String,String> addRequestBody = new HashMap<>();

        addRequestBody.put("name",educationName);
        addRequestBody.put("description","Red Phoenix (Dranzer)");
        addRequestBody.put("schoolId",e.getSchoolId());

        Response response=
        given()
                .spec(reqSpec)
                .body(addRequestBody)
                .when()
                .post("school-service/api/education-standard")
                .then()
                .log().body()
                .statusCode(201)
                .extract().response();


        educationId=response.path("id");
        String name =response.path("name");
        String description =response.path("description");

        Assert.assertTrue(name.length()<100);
        Assert.assertTrue(description.length()<5000);



    }

    @Test(dependsOnMethods = "addEducation")
    public void editEducation(){

        Exam e =new Exam();
        String editEducationName= createFaker.pokemon().name()+createFaker.number().randomNumber();

        Map<String,String> editRequestBody = new HashMap<>();
        editRequestBody.put("id",educationId);
        editRequestBody.put("name",editEducationName);
        editRequestBody.put("description","Red Phoenix (Dranzer)");
        editRequestBody.put("schoolId",e.getSchoolId());



        given()
                .spec(reqSpec)
                .body(editRequestBody)
                .when()
                .put("school-service/api/education-standard")
                .then()
                .log().body()
                .statusCode(200);

    }

    @Test(dependsOnMethods = "editEducation")
    public void deleteEducationPositive(){

        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/education-standard/"+educationId)
                .then()
                .statusCode(204);


    }

    @Test(dependsOnMethods = "deleteEducationPositive")
    public void deleteEducationNegative(){

        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/education-standard/"+educationId)
                .then()
                .log().body()
                .statusCode(400)
                .body("message",equalTo("Education Standard not found"));
    }



}
