package persistence.implementation;

import object.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.IUserPersistence;

public class FirebasePersistenceImp implements IUserPersistence {

    private CountDownLatch countDownLatch = new CountDownLatch(1);//thread call
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference childReference;
    private DatabaseReference databaseReference;
    private ValueEListnerImpl listener;

    public FirebasePersistenceImp() {

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

        childReference = database.getReference("users").child(username);

        listener = new ValueEListnerImpl();
        childReference.addValueEventListener(listener);

        try {
            countDownLatch.await();
            //z countDownLatch.await(1, TimeUnit.SECONDS);

        } catch (InterruptedException ex) {
            Logger.getLogger(FirebasePersistenceImp.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        User user = listener.getUser();
        //System.out.println(admin.toString());
        return user;

    }

    @Override
    public User deleteUser(String userName) {

        childReference = database.getReference("users").child(userName);
        User user = getUser(userName);
        childReference.removeValue();
        listener = new ValueEListnerImpl();
        try {
            countDownLatch.await();

        } catch (InterruptedException ex) {
            Logger.getLogger(FirebasePersistenceImp.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        countDownLatch.countDown();

        return user;

    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("/");
        childReference = databaseReference.child("users");

        childReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot child : ds.getChildren()) {
                    allUsers.add(child.getValue(User.class));
                    /* allUsers.add(new User(
                            child.child("address").getValue().toString(),
                            Double.parseDouble(child.child("latitude").getValue().toString()),
                            Double.parseDouble(child.child("longitude").getValue().toString()),
                            child.child("password").getValue().toString(),
                            child.child("role").getValue().toString(),
                            child.child("title").getValue().toString(),
                            child.child("username").getValue().toString()
                    ));*/
                }
                countDownLatch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                de.getMessage();
            }
        });
        try {
            countDownLatch.await();
        } catch (Exception e) {

        }
        return allUsers;
    }

    private class ValueEListnerImpl implements ValueEventListener {

        private User user;

        public ValueEListnerImpl() {
        }

        @Override
        public void onDataChange(DataSnapshot ds) {
            user = ds.getValue(User.class);
            /* if (adminInfo == null) {
                System.out.println("Ingen bruger findes");
            } else {
                

            } */
            countDownLatch.countDown();

        }

        @Override
        public void onCancelled(DatabaseError de) {
            System.out.println("The read failed: " + de.getCode());
        }

        private User getUser() {
            return user;
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
