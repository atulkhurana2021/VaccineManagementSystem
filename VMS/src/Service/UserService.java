package Service;

import DAL.MasterDBLayer;
import Entities.Session;
import Entities.User;
import Utils.GenericHelper;

public class UserService {

    private MasterDBLayer masterDBLayer;
    private GenericHelper genericHelper;

    public UserService(MasterDBLayer masterDBLayer) {
        this.masterDBLayer = masterDBLayer;
        this.genericHelper = new GenericHelper(masterDBLayer);
    }

    public String login(String userId, String password) throws Exception {

        if (password == null) {
            password = "";

        }
        if (userId == null) {
            userId = "";

        }
        if (masterDBLayer.getUserByID(userId) == null) {
            throw new Exception("User does not exist");
        }

        if (masterDBLayer.getUserPassword(userId) == null || !masterDBLayer.getUserPassword(userId)
                .equals(password)) {         //check password

            throw new Exception("Invalid password");
        }
        String existingSessionID = masterDBLayer.getUserSession(userId);

        //check if already logged in from some other device and that session is still active
        Session session = genericHelper.checkIfValidSession(existingSessionID);
        if (session != null) {
            throw new Exception("There is already an active session going on with this account");
        } else {
            session = new Session(userId);
            masterDBLayer.addSession(session);
            return session.getSessionId();
        }
    }

    public void logout(String sessionID) throws Exception {
        if (masterDBLayer.getSession(sessionID) == null) {
            throw new Exception("Invalid session");
        }
        masterDBLayer.inValidateSession(sessionID);
    }

    public String register(String IDType, String personalIdentificationNo, Long phone, String name, Integer age,
            String password)
            throws Exception {
        if (name == null || IDType == null || personalIdentificationNo == null || phone == null || name == null
                || age == null) {
            throw new Exception("Mandatory attributes missing");
        }
        if (masterDBLayer.getUserIDByIdentificationInfo(personalIdentificationNo) != null) {
            throw new Exception("A user with this personalIdentificationNo already present ");
        }
        User user = new User(IDType, personalIdentificationNo, phone, name, age);
        masterDBLayer.createUser(user);
        masterDBLayer.addUserPassword(user.getUserId(), password);
        return user.getUserId();
    }
}
