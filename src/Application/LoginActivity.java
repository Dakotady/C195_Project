package Application;

import java.time.LocalDateTime;


public class LoginActivity {

    String attempt;
    LocalDateTime localDateTime;
    int userID;

    /**
     * This creates the reference object for loginActivity.
     * @param attempt
     * @param localDateTime
     * @param userID
     */
    public LoginActivity(String attempt, LocalDateTime localDateTime, int userID) {

        this.attempt = attempt;
        this.localDateTime = localDateTime;
        this.userID = userID;

    }

    /**
     * This gets the login attempt.
     * @return
     */
    public String getAttempt() {
        return attempt;
    }

    /**
     * This sets the login attempt.
     * @param attempt
     */
    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    /**
     * This gets the localDateTime.
     * @return
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * This sets the localDateTime.
     * @param localDateTime
     */
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * this sets the userID.
     * @return
     */
    public int getUserID() { return  userID; }

    /**
     * This gets the userID.
     * @param userID
     */
    public void setUserID(int userID) { this.userID = userID; }

}