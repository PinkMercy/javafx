package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    private int Ph_id;
    private String Ph_nom;
    private String password;
    private String email;
    private String numtel;

    public User(int Ph_id, String Ph_nom, String password, String email, String numtel) {
        this.Ph_id = Ph_id;
        this.Ph_nom = Ph_nom;
        this.password = password;
        this.email = email;
        this.numtel = numtel;
    }

    public User() {
    }

    public int getPh_id() {
        return Ph_id;
    }

    public String getPh_nom() {
        return Ph_nom;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setPh_id(int Ph_id) {
        this.Ph_id = Ph_id;
    }

    public void setPh_nom(String Ph_nom) {
        this.Ph_nom = Ph_nom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "User{" + "Ph_id=" + Ph_id + ", Ph_nom=" + Ph_nom + ", password=" + password + ", email=" + email + ", numtel=" + numtel + '}';
    }

     
     public String hashMotDePasse(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();

            for(byte b : digest){
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        }catch(NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
     
}
