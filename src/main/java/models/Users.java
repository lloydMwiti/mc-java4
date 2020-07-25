package models;

public class Users {
    private int id;
    private String name,email,badge;

    public Users(String name,String email,String badge){
        this.name=name;
        this.email=email;
        this.badge=badge;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBadge() {
        return badge;
    }
}
