package interfaces;

/**
 *
 * @author Lasse Andersen + Murched kayed
 */
public interface IUser {

    /**
     *
     * @return Returns a String with the address
     */
    public String getAddress();

    /**
     *
     * @return Returns a double with the latitude
     */
    public double getLatitude();

    /**
     *
     * @return Returns a double with the longitude
     */
    public double getLongitude();

    /**
     *
     * @return Returns a String with the password
     */
    public String getPassword();

    /**
     *
     * @return Returns a String with the role
     */
    public String getRole();

    /**
     *
     * @return Returns a String with the username
     */
    public String getUsername();

    /**
     *
     * @return Returns a String with the title
     */
    public String getTitle();
}
