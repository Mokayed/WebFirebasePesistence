package persistence.implementation;

import object.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import interfaces.IUser;
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
    public boolean addUser(IUser user) {

        databaseReference = FirebaseDatabase.getInstance().getReference("/");
        childReference = databaseReference.child("users").child(user.getUsername());
        IUser testgetUser = getUser(user.getUsername());
        if (testgetUser == null) {

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
    public IUser getUser(String username) {

        childReference = database.getReference("users").child(username);

        listener = new ValueEListnerImpl();
        childReference.addValueEventListener(listener);

        try {
            countDownLatch.await();
            // countDownLatch.await(1, TimeUnit.SECONDS);

        } catch (InterruptedException ex) {
            Logger.getLogger(FirebasePersistenceImp.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        IUser user = listener.getUser();
        //System.out.println(user.toString());
        return user;

    }

    @Override
    public IUser deleteUser(String userName) {

        childReference = database.getReference("users").child(userName);
        IUser user = getUser(userName);
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
    public List<IUser> getAllUsers() {

        databaseReference = FirebaseDatabase.getInstance().getReference("/");
        childReference = databaseReference.child("users");
        listener = new ValueEListnerImpl();
        childReference.addValueEventListener(listener);
        try {
            countDownLatch.await();
        } catch (Exception e) {

        }
        List<IUser> allUsers = listener.getAllUsers();
        return allUsers;
    }

    private class ValueEListnerImpl implements ValueEventListener {

        private IUser user;
        private final List<IUser> allUsersList = new ArrayList<>();

        public ValueEListnerImpl() {
        }

        @Override
        public void onDataChange(DataSnapshot ds) {
            user = ds.getValue(User.class);
            countDownLatch.countDown();
            for (DataSnapshot child : ds.getChildren()) {
                allUsersList.add(child.getValue(User.class));

            }
            countDownLatch.countDown();
        }

        @Override
        public void onCancelled(DatabaseError de) {
            System.out.println("The read failed: " + de.getCode());
        }

        private IUser getUser() {
            return user;
        }

        private List<IUser> getAllUsers() {
 
            return allUsersList;
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
