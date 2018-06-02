package interfaces;

import javax.ws.rs.core.Response;

/**
 *
 * @author Lasse Andersen + Murched Kayed
 */
public interface IUserResource {

    /**
     *
     * @param username finding the user by his username
     * @return returns a response with the user object
     */
    public Response getUser(String username);

    /**
     *
     * @param entity it takes an object
     * @return it returns an rest response with the added user
     */
    public Response addUser(String entity);

    /**
     *
     * @param username delete the user by his username
     * @return it returns a rest response with the deleted user
     */
    public Response deleteUser(String username);

    /**
     *
     * @return it return a response with all the users
     */
    public Response getAllUsers();

}
