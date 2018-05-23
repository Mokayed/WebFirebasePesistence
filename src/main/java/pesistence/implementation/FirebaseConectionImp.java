/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pesistence.implementation;

/**
 *
 * @author Mk
 */
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import firebase.persistence.IFirebaseConection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author MoK
 */
//making a connection to the needed firebase using Ifirebaseconnection interface.
public class FirebaseConectionImp implements IFirebaseConection {

    @Override
    public void initFirebase(String link, String path) {
        try {
            FirebaseApp firebaseApp = null;
            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setDatabaseUrl(link)
                    .setServiceAccount(new FileInputStream(new File(path)))
                    .build();
//heck whether the default app is initialized or not 
            List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
            if (firebaseApps != null && !firebaseApps.isEmpty()) {
                for (FirebaseApp app : firebaseApps) {
                    if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                        firebaseApp = app;
                    }
                }
            } else {
                firebaseApp = FirebaseApp.initializeApp(firebaseOptions);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
