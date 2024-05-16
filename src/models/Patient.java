/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author rahma
 */
public class Patient {
    public int CodePatient ;
   public String NomPatient ;
   public int TelPatient ;

    public Patient(int CodePatient, String NomPatient, int TelPatient) {
        this.CodePatient = CodePatient;
        this.NomPatient = NomPatient;
        this.TelPatient = TelPatient;
    }

    public Patient(String NomPatient, int TelPatient) {
        this.NomPatient = NomPatient;
        this.TelPatient = TelPatient;
    }
    

    public Patient() {
    }

    public int getCodePatient() {
        return CodePatient;
    }

    public String getNomPatient() {
        return NomPatient;
    }

    public int getTelPatient() {
        return TelPatient;
    }

    public void setCodePatient(int CodePatient) {
        this.CodePatient = CodePatient;
    }

    public void setNomPatient(String NomPatient) {
        this.NomPatient = NomPatient;
    }

    public void setTelPatient(int TelPatient) {
        this.TelPatient = TelPatient;
    }
    
    
            
}
