package models;

import java.net.Socket;

public class User implements HasId {

    private final String pseudo;
    private String password;

    public User(String pseudo, String password) {
        this.pseudo = pseudo;
        this.password = password;
    }

    // Getters and Setter
    public String getPseudo() { return pseudo; }

    public String getName(){
        return pseudo;
    }

    public String getPassword() { return password;}
    public void setPassword(String password) { this.password = password;}

}
