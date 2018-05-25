/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.demo;

/**
 *
 * @author Mk
 */
import entity.User;
import java.io.IOException;
import java.util.Date;
import firebase.persistence.IFirebasePersistence;
import pesistence.implementation.FirebaseConectionImp;
import pesistence.implementation.FirebasePersistence;
import firebase.persistence.IFirebaseConnection;

public class DemoFirebasePut {

    public static void main(String[] args) throws IOException {
        String link = "https://finaleapp-dcad7.firebaseio.com";
        String path = "C:\\Users\\Lasse Andersen\\Documents\\NetBeansProjects\\Firebase\\\\finaleapp-dcad7-firebase-adminsdk-ultqu-62bc411e68.json";

        IFirebaseConnection IFC = new FirebaseConectionImp();
        IFC.initFirebase(link, path);

        //Husk at username skal være det samme som hm3 hvis det er det man vælger, ellers går det galt i tilbud i android
        User user = new User("ged", 0, 33, "mo123", "admin", "hm7", "cool mo");
        IFirebasePersistence firebasestorage = new FirebasePersistence();
        boolean succeed = firebasestorage.addUser(user);
        System.out.println(succeed);

    }

}
