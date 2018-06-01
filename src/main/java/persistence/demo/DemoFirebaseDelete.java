package persistence.demo;

import persistence.implementation.FirebaseConnectionImp;
import persistence.implementation.FirebasePersistenceImp;
import interfaces.IFirebaseConnection;
import interfaces.IUserPersistence;

public class DemoFirebaseDelete {

    public static void main(String[] args) {
        //calling for the firebase connection and the json file that we get from the firebase
        String link = "https://finaleapp-dcad7.firebaseio.com";
        String path = "C:\\Users\\Lasse Andersen\\Desktop\\Cph Business\\4.Semester (Valgfag)\\Advanced Programming\\WebFirebasePesistence\\\\finaleapp-dcad7-firebase-adminsdk-ultqu-62bc411e68.json";
        IFirebaseConnection IFC = new FirebaseConnectionImp();
        IFC.initFirebase(link, path);

        //calling the method from firebasepersistence using the ifriebasepersistence and calling the child by the childname.
        IUserPersistence firebase = new FirebasePersistenceImp();
        firebase.deleteUser("hm9");
    }

}
