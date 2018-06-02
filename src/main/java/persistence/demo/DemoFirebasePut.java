package persistence.demo;

import object.User;
import java.io.IOException;
import persistence.implementation.FirebaseConnectionImp;
import persistence.implementation.FirebasePersistenceImp;
import interfaces.IFirebaseConnection;
import interfaces.IUser;
import interfaces.IUserPersistence;

public class DemoFirebasePut {

    public static void main(String[] args) throws IOException {
        String link = "https://finaleapp-dcad7.firebaseio.com";
        String path = "C:\\Users\\Lasse Andersen\\Desktop\\Cph Business\\4.Semester (Valgfag)\\Advanced Programming\\WebFirebasePesistence\\\\finaleapp-dcad7-firebase-adminsdk-ultqu-62bc411e68.json";

        IFirebaseConnection IFC = new FirebaseConnectionImp();
        IFC.initFirebase(link, path);

        //Husk at username skal være det samme som hm3 hvis det er det man vælger, ellers går det galt i tilbud i android
        //User user = new User("ged", 0.333, 33.3, "mo123", "admin", "hm7", "cool mo");
        IUser user2 = new User("ged", 0.333, 33.3, "mo123", "admin", "hm7", "cool mo");
        IUserPersistence firebasestorage = new FirebasePersistenceImp();
        boolean succeed = firebasestorage.addUser(user2);
        System.out.println(succeed);

    }

}
