package tables;

import main.AttributeType;
import main.Pair;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Pair<AttributeType, String>> getAttributes() {
        List<Pair<AttributeType, String>> userAttributes = new ArrayList<>();
        userAttributes.add(new Pair<>(AttributeType.INT, "userid"));
        userAttributes.add(new Pair<>(AttributeType.STRING, "username"));
        userAttributes.add(new Pair<>(AttributeType.STRING, "password"));
        userAttributes.add(new Pair<>(AttributeType.STRING, "name"));
        userAttributes.add(new Pair<>(AttributeType.STRING, "phonenumber"));
        return userAttributes;
    }

    @Override
    public List<String> primaryKey() {
        List<String> primarykey = new ArrayList<>();
        primarykey.add("userid");
        return primarykey;
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
