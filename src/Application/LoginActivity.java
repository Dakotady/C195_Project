package Application;

import java.time.LocalDateTime;


public class LoginActivity {

    int attempt;
    LocalDateTime localDateTime;

    /**
     * This creates the reference object for loginActivity.
     * @param attempt
     * @param localDateTime
     */
    public LoginActivity(int attempt, LocalDateTime localDateTime) {

        this.attempt = attempt;
        this.localDateTime = localDateTime;

    }

    /**
     * This gets the attempt.
     * @return
     */
    public int getAttempt() {
        return attempt;
    }

    /**
     * This sets the attempt.
     * @param attempt
     */
    public void setAttempt(int attempt) {
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

}