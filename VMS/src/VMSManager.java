import DAL.MasterDBLayer;

import Service.UserService;
import Service.VaccineService;

import java.util.Date;

public class VMSManager {
    private MasterDBLayer masterDBLayer;
    private UserService userService;
    private VaccineService vaccineService;

    public VMSManager(MasterDBLayer masterDBLayer) {
        this.masterDBLayer = masterDBLayer;
        this.vaccineService = new VaccineService(masterDBLayer);
        this.userService = new UserService(masterDBLayer);
    }

    public String login(String userId, String password) throws Exception {
        return this.userService.login(userId, password);

    }

    public void logout(String sessionID) throws Exception {
        this.userService.logout(sessionID);
    }

    public String register(String IDType, String personalIdentificationNo, Long phone, String name, Integer age,
            String password)
            throws Exception {
        return this.userService.register(IDType, personalIdentificationNo, phone, name, age, password);
    }

    public void addVaccine(String vaccineName, String mfgDetails, Date expiryTime) throws Exception {
        this.vaccineService.addVaccine(vaccineName, mfgDetails, expiryTime);
    }

    public boolean isVaccineAvailable(String sessionId) throws Exception {
        return this.vaccineService.isVaccineAvailable(sessionId);
    }

    public String bookVaccine(String userId, String sessionId) throws Exception {
        return this.vaccineService.bookVaccine(userId, sessionId);
    }

}
