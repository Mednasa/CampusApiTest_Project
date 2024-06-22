package campus.entranceExam.model;

import java.util.ArrayList;

public class Exam {

    private String id;
    private String name;
    private String school;
    private String academicPeriod;
    private Boolean active;
    private String description;
    private String note;
    private String agreementText;
    private Boolean sendSMS;
    private String sms;
    private Boolean sendEmailEnabled;
    private String registrationStartDate;
    private String registrationEndDate;
    private Boolean paid;
    private int price;
    private String bankAccount;
    private Boolean sendEmailToRegistrar;
    private Boolean showDescFirst;
    private Boolean showNoteFirst;
    private Boolean noteEnabled;
    private Boolean descEnabled;
    private Boolean agreementEnabled;
    private GradeLevel gradeLevel;
    private EmailMessage emailMessage;
    private ArrayList<Object>translateName;
    private ArrayList<String>registrarEmails;
    private ArrayList<Object>documents;
    private final String schoolId ="6576fd8f8af7ce488ac69b89";


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAcademicPeriod() {
        return academicPeriod;
    }

    public void setAcademicPeriod(String academicPeriod) {
        this.academicPeriod = academicPeriod;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAgreementText() {
        return agreementText;
    }

    public void setAgreementText(String agreementText) {
        this.agreementText = agreementText;
    }

    public Boolean getSendSMS() {
        return sendSMS;
    }

    public void setSendSMS(Boolean sendSMS) {
        this.sendSMS = sendSMS;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Boolean getSendEmailEnabled() {
        return sendEmailEnabled;
    }

    public void setSendEmailEnabled(Boolean sendEmailEnabled) {
        this.sendEmailEnabled = sendEmailEnabled;
    }

    public String getRegistrationStartDate() {
        return registrationStartDate;
    }

    public void setRegistrationStartDate(String registrationStartDate) {
        this.registrationStartDate = registrationStartDate;
    }

    public String getRegistrationEndDate() {
        return registrationEndDate;
    }

    public void setRegistrationEndDate(String registrationEndDate) {
        this.registrationEndDate = registrationEndDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Boolean getSendEmailToRegistrar() {
        return sendEmailToRegistrar;
    }

    public void setSendEmailToRegistrar(Boolean sendEmailToRegistrar) {
        this.sendEmailToRegistrar = sendEmailToRegistrar;
    }

    public Boolean getShowDescFirst() {
        return showDescFirst;
    }

    public void setShowDescFirst(Boolean showDescFirst) {
        this.showDescFirst = showDescFirst;
    }

    public Boolean getShowNoteFirst() {
        return showNoteFirst;
    }

    public void setShowNoteFirst(Boolean showNoteFirst) {
        this.showNoteFirst = showNoteFirst;
    }

    public Boolean getNoteEnabled() {
        return noteEnabled;
    }

    public void setNoteEnabled(Boolean noteEnabled) {
        this.noteEnabled = noteEnabled;
    }

    public Boolean getDescEnabled() {
        return descEnabled;
    }

    public void setDescEnabled(Boolean descEnabled) {
        this.descEnabled = descEnabled;
    }

    public Boolean getAgreementEnabled() {
        return agreementEnabled;
    }

    public void setAgreementEnabled(Boolean agreementEnabled) {
        this.agreementEnabled = agreementEnabled;
    }



    public ArrayList<Object> getTranslateName() {
        return translateName;
    }

    public void setTranslateName(ArrayList<Object> translateName) {
        this.translateName = translateName;
    }

    public ArrayList<String> getRegistrarEmails() {
        return registrarEmails;
    }

    public void setRegistrarEmails(ArrayList<String> registrarEmails) {
        this.registrarEmails = registrarEmails;
    }

    public ArrayList<Object> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Object> documents) {
        this.documents = documents;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public EmailMessage getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(EmailMessage emailMessage) {
        this.emailMessage = emailMessage;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
