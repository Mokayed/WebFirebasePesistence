/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.demo;

import firebase.persistence.IFirebasePersistence;
import pesistence.implementation.FirebaseConectionImp;
import pesistence.implementation.FirebasePersistence;
import firebase.persistence.IFirebaseConnection;

/**
 *
 * @author MoK
 */
public class DemoFirebaseDelete {
    public static void main(String[] args) {
             //calling for the firebase connection and the json file that we get from the firebase
        String link = "https://finaleapp-dcad7.firebaseio.com";
String path = "C:\\Users\\Mk\\Documents\\NetBeansProjects\\Firebase\\finaleapp-dcad7-firebase-adminsdk-ultqu-62bc411e68.json";
       IFirebaseConnection IFC = new FirebaseConectionImp();
      IFC.initFirebase(link,path);
        
      //calling the method from firebasepersistence using the ifriebasepersistence and calling the child by the childname.
        IFirebasePersistence firebase = new FirebasePersistence();
        firebase.deleteUser("hm3");
    }
    
}
