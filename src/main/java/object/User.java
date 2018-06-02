package object;

import interfaces.IUser;

/**
 *
 * @author MoK
 */
public class User implements IUser {

    private String address;
    private double latitude;
    private double longitude;
    private String password;
    private String role;
    private String username;
    private String title;

    public User() {
    }

    public User(String address, Double latitude, Double longitude, String password, String role, String username, String title) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.password = password;
        this.role = role;
        this.username = username;
        this.title = title;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "AdminInformation{" + "address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + ", password=" + password + ", role=" + role + ", username=" + username + ", title=" + title + '}';
    }

}
