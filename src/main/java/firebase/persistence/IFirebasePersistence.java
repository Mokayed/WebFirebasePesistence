/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firebase.persistence;

import entity.User;
import java.util.List;

/**
 *
 * @author Mk
 */


/**
 *
 * @author MoK
 */
public interface IFirebasePersistence {

    public void addUser(String username, User admin);//add data to the firebase

    public User getUser(String username);// get data from firebase with id
    
    public String deleteUser(String username);
    
    public User editUser(User admin); 
   
    public List<User> getUserd();
    
    

}
