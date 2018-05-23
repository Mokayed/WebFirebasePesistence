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
import firebase.persistence.IFirebaseConection;
import firebase.persistence.IFirebasePersistence;
import java.io.IOException;
import pesistence.implementation.FirebaseConectionImp;
import pesistence.implementation.FirebasePersistence;

/**
 *
 * @author MoK
 */
public class DemoFirebaseGet {

    public static void main(String[] args) throws IOException {
        
        //calling for the firebase connection and the json file that we get from the firebase
        String link = "https://finaleapp-dcad7.firebaseio.com";
String path = "C:\\\\Users\\\\MoK\\\\Documents\\\\NetBeansProjects\\\\Firebase\\\\finaleapp-dcad7-firebase-adminsdk-ultqu-62bc411e68.json";
       IFirebaseConection IFC = new FirebaseConectionImp();
      IFC.initFirebase(link,path);
        
      //calling the method from firebasepersistence using the ifriebasepersistence and calling the child by the childname.
        IFirebasePersistence firebase = new FirebasePersistence();
        firebase.getUser("hm2");
        

    }
}
