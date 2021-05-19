import DAL.MasterDBLayer;

import java.util.Date;

public class Driver {

    public static void main(String[] args) throws Exception {
        // write your code here
        MasterDBLayer masterDBLayer = new MasterDBLayer();

        VMSManager vmsManager = new VMSManager(masterDBLayer);
        for (int i = 1; i <= 1; i++) {
            vmsManager.addVaccine("COVIDSHEILD", "PHARMA", new Date());
        }

        // register user
        String userId = vmsManager.register("ADHAR", "1234", 37489374l, "atul khurana", 28, "pass");
        String userId2 = vmsManager.register("ADHAR", "12342", 374893742l, "atul khurana", 28, "pass");

        //String userId = vmsManager.register("ADHAR", "1234", 37489374l, "atul khurana", 28, "pass"); //will give error A user with this personalIdentificationNo already present
        //login
        String sessionId = vmsManager.login(userId, "pass");
        String sessionId2 = vmsManager.login(userId2, "pass");
        //String sessionId = vmsManager.login(userId, "pass1"); // will give invalid password error
        //String sessionId = vmsManager.login("userId", "pass1"); // will give User does not exist
        // sessionId = vmsManager.login(userId, "pass"); //There is already an active session going on with this account


        //logout
        //vmsManager.logout(sessionId+ "1");//will giv error  Invalid session
        vmsManager.logout(sessionId);

        //re-login
        String sessionId1 = vmsManager.login(userId, "pass");


        System.out.println(userId);
        System.out.println(sessionId);
        System.out.println(sessionId1);
        System.out.println(userId2);
        System.out.println(sessionId2);


        // Vaccine Availability
        //System.out.println(vmsManager.isVaccineAvailable(sessionId)); // Error Session no more active
        System.out.println(vmsManager.isVaccineAvailable(sessionId1));


        //Booking
       // System.out.println(vmsManager.bookVaccine(userId,sessionId)); // Session no more active
          System.out.println(vmsManager.bookVaccine(userId,sessionId1)); //Vaccine Allocated VC1
          //System.out.println(vmsManager.bookVaccine(userId, sessionId1)); // Error Vaccine already allocated to user
          System.out.println(vmsManager.bookVaccine(userId2, sessionId2)); // Vaccine not available, please try after some time














    }
}
