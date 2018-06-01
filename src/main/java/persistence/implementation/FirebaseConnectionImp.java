package persistence.implementation;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import interfaces.IFirebaseConnection;

//making a connection to the needed firebase using Ifirebaseconnection interface.
public class FirebaseConnectionImp implements IFirebaseConnection {

    @Override
    public void initFirebase(String link, String path) {
        try {
            FirebaseApp firebaseApp = null;
            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setDatabaseUrl(link)
                    .setServiceAccount(new FileInputStream(new File(path)))
                    .build();
            //Check whether the default app is initialized or not 
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
