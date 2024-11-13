public class User {
    private String username;
    private String password;
    private String Ackpassword;
    private String id;
    private String phonenumber;

    public User() {
    }

    public User(String username, String password, String ackpassword, String id, String phonenumber) {
        this.username = username;
        this.password = password;
        Ackpassword = ackpassword;
        this.id = id;
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAckpassword() {
        return Ackpassword;
    }

    public void setAckpassword(String ackpassword) {
        Ackpassword = ackpassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
