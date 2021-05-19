package Utils;

import DAL.MasterDBLayer;
import Entities.Session;

import java.util.Date;

public class GenericHelper {

    private MasterDBLayer masterDBLayer;

    public GenericHelper(MasterDBLayer masterDBLayer) {
        this.masterDBLayer = masterDBLayer;
    }

    public Session checkIfValidSession(String sessionId) {
        Session session = null;

        //check if already logged in from some other device and that session is still active
        if (sessionId != null) {
            session = masterDBLayer.getSession(sessionId);
            if (session != null) {
                Date lastUsedDate = session.getSessionLastUsed();
                if (!session.isActive()) {
                    session = null;
                } else if ((((new Date().getTime() - lastUsedDate.getTime()) / 1000) > Constants.SESSION_EXPIRY_TIME)) {
                    masterDBLayer.inValidateSession(sessionId);
                    session = null;
                }
            }
        }
        return session;
    }
}
