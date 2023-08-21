package Application;

import java.time.LocalDateTime;

public class LoginActivity {

    int attempt;
    LocalDateTime localDateTime;

    public LoginActivity(int attempt, LocalDateTime localDateTime) {

        this.attempt = attempt;
        this.localDateTime = localDateTime;

    }


    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

}