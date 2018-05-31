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
import entity.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;
import firebase.persistence.IFirebasePersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import firebase.persistence.IFirebaseConnection;

/**
 *
 * @author MoK
 */
public class FirebasePersistence implements IFirebasePersistence {

    CountDownLatch countDownLatch = new CountDownLatch(1);//thread call
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public FirebasePersistence() {

    }

// (implementation of IFirebasePersistnce interface)puting data in the firebase using the put frim the Ifirebasepersistence
    //+ the inner class CompletionListenerImp
    @Override
    public boolean addUser(User user) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/");
        DatabaseReference childReference = databaseReference.child("users").child(user.getUsername());
        User testUser = getUser(user.getUsername());
        if (testUser == null) {

            //if (childReference != null) {
            CompletionListenerImpl Complistner = new CompletionListenerImpl();
            childReference.setValue(user, Complistner);
            try {
                //wait for firebase to saves record.
                countDownLatch.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return true; // Return true if we add a new user.
        }
        return false; // Return false if not.
    }

//(implementation of IFirebasePersistnce interface) geting data from firebase using the get from the ifirebase interface 
//+ the inner class valuelistenermip
    @Override
    public User getUser(String username) {

        DatabaseReference childReference = database.getReference("users").child(username);

        ValueEListnerImpl listener = new ValueEListnerImpl();
        childReference.addValueEventListener(listener);

        try {
            countDownLatch.await();
            //z countDownLatch.await(1, TimeUnit.SECONDS);

        } catch (InterruptedException ex) {
            Logger.getLogger(FirebasePersistence.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        User admin = listener.getAdmin();

        return admin;

    }

    @Override
    public String deleteUser(String userName) {
        /* 
        
    public User deleteUser(String name) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User p = em.find(User.class, name);
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

        */
       // User testUser = getUser(user.getUsername());

            DatabaseReference childReference = database.getReference("users").child(userName);
            childReference.removeValue();
            try {
                countDownLatch.await();

            } catch (InterruptedException ex) {
                Logger.getLogger(FirebasePersistence.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            countDownLatch.countDown();
        return userName + "is deleted";



    }


 

    private class ValueEListnerImpl implements ValueEventListener {

        private User adminInfo;

        public ValueEListnerImpl() {
        }

        @Override
        public void onDataChange(DataSnapshot ds) {
            adminInfo = ds.getValue(User.class);
            /* if (adminInfo == null) {
                System.out.println("Ingen bruger findes");
            } else {
                

            } *///System.out.println(adminInfo.toString());

            countDownLatch.countDown();

        }

        @Override
        public void onCancelled(DatabaseError de) {
            System.out.println("The read failed: " + de.getCode());
        }

        private User getAdmin() {
            return adminInfo;
        }

    }

    private class CompletionListenerImpl implements CompletionListener {

        public CompletionListenerImpl() {
        }

        @Override
        public void onComplete(DatabaseError de, DatabaseReference dr) {
            System.out.println("Record saved!");
            // decrement countDownLatch value and application will be continues its execution.
            countDownLatch.countDown();
        }
    }

}
