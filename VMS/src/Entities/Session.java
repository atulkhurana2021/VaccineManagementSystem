package Entities;

import Utils.Constants;

import java.util.Date;

public class Session {

    private String sessionId;
    private String userId;
    private Date sessionStart;
    private Date sessionLastUsed;
    private boolean isActive;
    private volatile static int n = 0;

    private synchronized String nextNum() {
        n++;
        return n + "";
    }

    public Session(String userId) {
        this.userId = userId;
        this.sessionId = Constants.SESSION_SEQUENCE + nextNum();
        this.sessionStart = new Date();
        this.sessionLastUsed = this.sessionStart;
        this.isActive = true;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(Date sessionStart) {
        this.sessionStart = sessionStart;
    }

    public Date getSessionLastUsed() {
        return sessionLastUsed;
    }

    public void setSessionLastUsed(Date sessionLastUsed) {
        this.sessionLastUsed = sessionLastUsed;
    }

    public static int getN() {
        return n;
    }

    public static void setN(int n) {
        Session.n = n;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
