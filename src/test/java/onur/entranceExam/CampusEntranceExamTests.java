package onur.entranceExam;

import loginPositive.LoginPositive;
import onur.entranceExam.model.*;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CampusEntranceExamTests extends LoginPositive {

    Faker fakerCreate = new Faker();
    String examId ="";
    String examName="";


    @Test
    public void addExamNegative() {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setContent("");
        emailMessage.setSubject("");

        GradeLevel gradeLevel =new GradeLevel();
        gradeLevel.setId("654a91fcc3432c0979fe03af");
        Exam e =new Exam();

        e.setName(null);
        e.setTranslateName(new ArrayList<>());
        e.setSchool("6576fd8f8af7ce488ac69b89");
        e.setGradeLevel(gradeLevel);
        e.setAcademicPeriod("6577022e8af7ce488ac69b96");
        e.setActive(true);
        e.setDescription("");
        e.setNote("");
        e.setAgreementText("");
        e.setSendSMS(false);
        e.setSms("");
        e.setSendEmailEnabled(false);
        e.setEmailMessage(emailMessage);
        e.setRegistrationStartDate(null);
        e.setRegistrationEndDate(null);
        e.setPaid(false);
        e.setPrice(0);
        e.setBankAccount(null);
        e.setSendEmailToRegistrar(false);
        e.setRegistrarEmails(new ArrayList<>());
        e.setShowDescFirst(false);
        e.setShowNoteFirst(false);
        e.setNoteEnabled(false);
        e.setDescEnabled(false);
        e.setAgreementEnabled(false);
        e.setDocuments(new ArrayList<>());




        given()
                .spec(reqSpec)
                .body(e)
                .when()
                .post("school-service/api/exams")
                .then()
                .log().body()
                .statusCode(400);


    }

    @Test
    public void addExamPositive(){

        examName = "Math"+fakerCreate.address().buildingNumber();

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setContent("");
        emailMessage.setSubject("");

        GradeLevel gradeLevel =new GradeLevel();
        gradeLevel.setId("654a91fcc3432c0979fe03af");

        Exam e =new Exam();

        e.setName(examName);
        e.setTranslateName(new ArrayList<>());
        e.setSchool("6576fd8f8af7ce488ac69b89");
        e.setGradeLevel(gradeLevel);
        e.setAcademicPeriod("6577022e8af7ce488ac69b96");
        e.setActive(true);
        e.setDescription("");
        e.setNote("");
        e.setAgreementText("");
        e.setSendSMS(false);
        e.setSms("");
        e.setSendEmailEnabled(false);
        e.setEmailMessage(emailMessage);
        e.setRegistrationStartDate(null);
        e.setRegistrationEndDate(null);
        e.setPaid(false);
        e.setPrice(0);
        e.setBankAccount(null);
        e.setSendEmailToRegistrar(false);
        e.setRegistrarEmails(new ArrayList<>());
        e.setShowDescFirst(false);
        e.setShowNoteFirst(false);
        e.setNoteEnabled(false);
        e.setDescEnabled(false);
        e.setAgreementEnabled(false);
        e.setDocuments(new ArrayList<>());


        examId=
        given()
                .spec(reqSpec)
                .body(e)
                .when()
                .post("school-service/api/exams")
                .then()
                .statusCode(201)
                .extract().path("id");

//        System.out.println(examId);
//        System.out.println(examName);

    }


    @Test(dependsOnMethods = "addExamPositive")
    public void getExam(){

        Exam e = new Exam();

        Map<String,String> examCredentials = new HashMap<>();
        examCredentials.put("name",examName);
        examCredentials.put("academicPeriodId","6577022e8af7ce488ac69b96");
        examCredentials.put("schoolId",e.getSchoolId());



        given()
                .spec(reqSpec)
                .body(examCredentials)
                .when()
                .post("school-service/api/exams/search")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "getExam")
    public void editExamNegative(){

        examName = "Morphing Spell "+fakerCreate.address().buildingNumber();

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setContent("");
        emailMessage.setSubject("");

        GradeLevel gradeLevel =new GradeLevel();
        gradeLevel.setId("654a91fcc3432c0979fe03af");

        Exam e =new Exam();
        e.setId("6576658888f7954881269489");
        e.setName(examName);
        e.setTranslateName(new ArrayList<>());
        e.setSchool("6576fd8f8af7ce488ac69b89");
        e.setGradeLevel(gradeLevel);
        e.setAcademicPeriod("6577022e8af7ce488ac69b96");
        e.setActive(true);
        e.setDescription("");
        e.setNote("");
        e.setAgreementText("");
        e.setSendSMS(false);
        e.setSms("");
        e.setSendEmailEnabled(false);
        e.setEmailMessage(emailMessage);
        e.setRegistrationStartDate("3566-01-21T00:00:00.000Z");
        e.setRegistrationEndDate("5656-01-26T00:00:00.000Z");
        e.setPaid(false);
        e.setPrice(0);
        e.setBankAccount(null);
        e.setSendEmailToRegistrar(false);
        e.setRegistrarEmails(new ArrayList<>());
        e.setShowDescFirst(false);
        e.setShowNoteFirst(false);
        e.setNoteEnabled(false);
        e.setDescEnabled(false);
        e.setAgreementEnabled(false);
        e.setDocuments(new ArrayList<>());
        e.getSchoolId();



        given()
                .spec(reqSpec)
                .body(e)
                .when()
                .put("school-service/api/exams")
                .then()
                .log().body()
                .statusCode(400);

    }

    @Test(dependsOnMethods = "editExamNegative")
    public void editExamPositive(){

        examName = "Morphing Spell "+fakerCreate.address().buildingNumber();

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setContent("");
        emailMessage.setSubject("");

        GradeLevel gradeLevel =new GradeLevel();
        gradeLevel.setId("654a91fcc3432c0979fe03af");

        Exam e =new Exam();
        e.setId(examId);
        e.setName(examName);
        e.setTranslateName(new ArrayList<>());
        e.setSchool("6576fd8f8af7ce488ac69b89");
        e.setGradeLevel(gradeLevel);
        e.setAcademicPeriod("6577022e8af7ce488ac69b96");
        e.setActive(true);
        e.setDescription("");
        e.setNote("");
        e.setAgreementText("");
        e.setSendSMS(false);
        e.setSms("");
        e.setSendEmailEnabled(false);
        e.setEmailMessage(emailMessage);
        e.setRegistrationStartDate("3566-01-21T00:00:00.000Z");
        e.setRegistrationEndDate("5656-01-26T00:00:00.000Z");
        e.setPaid(false);
        e.setPrice(0);
        e.setBankAccount(null);
        e.setSendEmailToRegistrar(false);
        e.setRegistrarEmails(new ArrayList<>());
        e.setShowDescFirst(false);
        e.setShowNoteFirst(false);
        e.setNoteEnabled(false);
        e.setDescEnabled(false);
        e.setAgreementEnabled(false);
        e.setDocuments(new ArrayList<>());
        e.getSchoolId();

        given()
                .spec(reqSpec)
                .body(e)
                .when()
                .put("school-service/api/exams")
                .then()
                .statusCode(200);


    }

    @Test
    public void deleteExamNegative(){

        String invalidId="11515615";


        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/exams/"+invalidId)
                .then()
                .log().body()
                .statusCode(400)
                .body("message",equalTo("Please fill with valid ID to delete existing Exam"));
    }

    @Test(dependsOnMethods = "editExamPositive")
    public void deleteExamPositive(){

        given()
                .spec(reqSpec)
                .when()
                .delete("school-service/api/exams/"+examId)
                .then()
                .log().body()
                .statusCode(204);
    }



}
