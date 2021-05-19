package Service;

import DAL.MasterDBLayer;
import Entities.Session;
import Entities.Vaccine;
import Utils.Constants;
import Utils.GenericHelper;

import java.util.Date;

public class VaccineService {

    private MasterDBLayer masterDBLayer;
    private GenericHelper genericHelper;

    public VaccineService(MasterDBLayer masterDBLayer) {
        this.masterDBLayer = masterDBLayer;
        this.genericHelper = new GenericHelper(masterDBLayer);
    }

    public void addVaccine(String vaccineName, String mfgDetails, Date expiryTime) throws Exception {
        if (vaccineName == null || mfgDetails == null || expiryTime == null) {
            throw new Exception("Mandatory params null");
        }
        masterDBLayer.addVaccine(new Vaccine(vaccineName, mfgDetails, expiryTime));
    }

    public boolean isVaccineAvailable(String sessionId) throws Exception {
        Session session = genericHelper.checkIfValidSession(sessionId);
        if (session == null) {
            throw new Exception("Session no more active");
        }
        this.masterDBLayer.updateSessionUsed(sessionId);
        return this.masterDBLayer.isVaccineAvailable();
    }

    public String bookVaccine(String userId, String sessionId) throws Exception {
        Session session = genericHelper.checkIfValidSession(sessionId);
        if (session == null) {
            throw new Exception("Session no more active");
        }
        this.masterDBLayer.updateSessionUsed(sessionId);
        if (!(this.masterDBLayer.getVaccineAllocatedToUser(userId) == null)) {
            throw new Exception("Vaccine already allocated to user");
        }
        String vaccine = this.masterDBLayer.bookVaccine(userId);
        if (vaccine == null) {
            return Constants.VACCINE_NOT_AVAILABLE;
        } else {
            return Constants.VACCINE_ALLOCATED + vaccine;
        }
    }
}
