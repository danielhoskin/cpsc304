package tables;

public class Users implements Table {
    private int userid;
    private String username;
    private String password;
    private String name;
    private String phonenumber;

    public Users(int userid, String username, String password, String name, String phonenumber) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phonenumber = phonenumber;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public int getUserid() {

        return userid;
    }
}
