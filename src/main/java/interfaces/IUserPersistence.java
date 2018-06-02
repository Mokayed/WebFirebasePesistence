package interfaces;

import object.User;
import java.util.List;

/**
 *
 * @author Lasse Andersen + Murched Kayed
 */
public interface IUserPersistence {

    /**
     *
     * @param user The IUser interface is a interface which contains the
     * required method we want to save and retrieve from Firebase
     * @return Return a boolean with true if the User is saved in the Firebase
     * Database.
     */
    public boolean addUser(IUser user);//add data to the firebase

    /**
     *
     * @param username The username is the key of the user we want to retrieve
     * from Firebase
     * @return Returns the whole User object with the chosen username
     */
    public IUser getUser(String username);// get data from firebase with id

    /**
     *
     * @param username The username is the key of the user we want to delete
     * from Firebase
     * @return Return
     */
    public IUser deleteUser(String username);

    /**
     *
     * @return The list with the User object, will return all the users in a
     * list from Firebase
     */
    public List<IUser> getAllUsers();

}
