/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


public class Medicament {

  /*  public enum Type {
    SPECIAL,
    NORMAL;
    }*/


    private int CodeMed;
    private String NomMed;
    private double PrixMed;
    private int quantite;
    private String TypeMed;

    public Medicament(int CodeMed, String NomMed, double PrixMed, int quantite, String TypeMed) throws IllegalArgumentException {
        /*if (quantite < 0) {
            throw new IllegalArgumentException("quantite must be positive");
        }*/
        this.CodeMed = CodeMed;
        this.NomMed = NomMed;
        this.PrixMed = PrixMed;
        this.quantite = quantite;
        this.TypeMed = TypeMed;
    }

    public Medicament(String NomMed, double PrixMed, int quantite, String TypeMed) {
        this.NomMed = NomMed;
        this.PrixMed = PrixMed;
        this.quantite = quantite;
        this.TypeMed = TypeMed;
    }

    public int getCodeMed(){
        return this.CodeMed;
    }
    public String getNomMed(){
        return this.NomMed;
    }
    public double getPrixMed(){
        return this.PrixMed;
    }
    public int getquantite() {
        return this.quantite;
    }
    public String getTypeMed() {
        return this.TypeMed;
    }
    public void setCodeMed(int CodeMed) {
        this.CodeMed = CodeMed;
    }
    public void setNomMed(String NomMed) {
        this.NomMed = NomMed;
    }
    public void setPrixMed(double PrixMed) {
        this.PrixMed = PrixMed;
    }
    public void setquantite(int quantite) {
        this.quantite = quantite;
    }
    public void setTypeMed(String TypeMed) {
        this.TypeMed = TypeMed;
    }

}