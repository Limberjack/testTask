package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class User {
    private String email;
    private String fullName;
    private String birthday;
    private String additionalMessage;

    public User(){;}
    public User(String email, String fullName, String birthday, String additionalMessage) {
        this.email = email;
        this.fullName = fullName;
        this.birthday = birthday;
        this.additionalMessage = additionalMessage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    public void setAdditionalMessage(String additionalMessage) {
        this.additionalMessage = additionalMessage;
    }
}