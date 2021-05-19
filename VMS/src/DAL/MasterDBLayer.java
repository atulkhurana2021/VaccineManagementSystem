package DAL;

import Entities.Session;
import Entities.User;
import Entities.Vaccine;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MasterDBLayer {

    Map<String, Session> sessionMap = new HashMap<>();
    Map<String, String> userSessionMap = new HashMap<>(); //user sessions
    Map<String, User> userMap = new HashMap<>();
    Map<String, String> userIdentificationMap = new HashMap<>(); //user's personalIdentificationNo to userID mapping
    Map<String, String> userPasswordMap = new HashMap<>();
    Map<String, Vaccine> vaccineMap = new HashMap<>();
    ConcurrentLinkedQueue<String> availQueue = new ConcurrentLinkedQueue<>();
    Map<String, String> userVaccineMap = new HashMap<>();

    public void addSession(Session session) {
        this.sessionMap.put(session.getSessionId(), session);
        this.userSessionMap.put(session.getUserId(), session.getSessionId());
    }

    public Session getSession(String sessionId) {
        return this.sessionMap.get(sessionId);
    }

    public void updateSessionUsed(String sessionId) {
        Session session = this.sessionMap.get(sessionId);
        session.setSessionLastUsed(new Date());
        // this.sessionMap.put(sessionId, session);
    }

    public String getUserSession(String userId) {
        return this.userSessionMap.get(userId);
    }

    public void inValidateSession(String sessionId) {
        Session session = this.sessionMap.get(sessionId);
        if (session != null) {
            session.setActive(false);
            sessionMap.put(sessionId, session);
            userSessionMap.remove(session.getUserId());
        }
    }

    public void createUser(User user) {
        this.userMap.put(user.getUserId(), user);
        this.userIdentificationMap.put(user.getPersonalIdentificationNo(), user.getUserId());
    }

    public User getUserByID(String userId) {
        return this.userMap.get(userId);
    }

    public String getUserIDByIdentificationInfo(String personalIdentificationNo) {
        return this.userIdentificationMap.get(personalIdentificationNo);
    }

    public void addUserPassword(String userID, String password) {
        this.userPasswordMap.put(userID, password);
    }

    public String getUserPassword(String userID) {
        return this.userPasswordMap.get(userID);
    }

    public void addVaccine(Vaccine vaccine) {

        this.vaccineMap.put(vaccine.getVaccineId(), vaccine);
        this.availQueue.offer(vaccine.getVaccineId());
    }

    public boolean isVaccineAvailable() {
        return !this.availQueue.isEmpty();
    }

    public Vaccine getVaccineAllocatedToUser(String userID) {
        Vaccine vaccine = null;
        String vaccineID = this.userVaccineMap.get(userID);
        if (vaccineID != null) {
            vaccine = this.vaccineMap.get(vaccineID);
        }
        return vaccine;
    }

    public String bookVaccine(String userID) {
        String vaccineID = this.availQueue.poll();
        if (vaccineID != null) {
            Vaccine vaccine = this.vaccineMap.get(vaccineID);
            vaccine.setAssignedTo(userID);
            this.userVaccineMap.put(userID, vaccineID);
        }
        return vaccineID;
    }

}
