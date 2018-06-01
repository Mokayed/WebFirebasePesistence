package interfaces;

/**
 *
 * @author Lasse Andersen + Murched Kayed
 */
public interface IFirebaseConnection {

    /**
     * initFirebase initializes the connection to the server.
     * @param link
     * Link is a string with the link to the server.
     * @param path 
     * Path is string with the location with the relevant
     * information to use the server, normally a json object. You need to
     * implement this Interface, so you can pass your Firebase link and your
     * Json file that you get from the Firebase.
     */
    public void initFirebase(String link, String path);
}
