import javax.jws.soap.SOAPBinding;

public class User {

    private String username;
    private String password;
    private Privilege privilege;

    public User(Privilege privilege, String username, String password) {
        this.privilege = privilege;
        this.username = username;
        this.password = password;
    }

    public User() {
        this.privilege = Privilege.USER;
        this.username = null;
        this.password = null;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
