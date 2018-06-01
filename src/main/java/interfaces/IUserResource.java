package interfaces;

import javax.ws.rs.core.Response;

/**
 *
 * @author Lasse Andersen + Murched Kayed
 */
public interface IUserResource {

    /**
     *
     * @param userName
     * @return
     */
    public Response getUser(String userName);

    /**
     *
     * @param entity
     * @return
     */
    public Response addUser(String entity);

    /**
     *
     * @param userName
     * @return
     */
    public Response deleteUser(String userName);

    /**
     *
     * @return
     */
    public Response getAllUsers();

}
