package entity;

/**
 *
 * @author MoK
 */
public class User {

    private String address;
    private Double latitude;
    private Double longitude;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "AdminInformation{" + "address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + ", password=" + password + ", role=" + role + ", username=" + username + ", title=" + title + '}';
    }

}
